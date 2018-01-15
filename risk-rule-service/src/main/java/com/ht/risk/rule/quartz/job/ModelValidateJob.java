package com.ht.risk.rule.quartz.job;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.rule.entity.ModelRelease;
import com.ht.risk.rule.entity.ModelValidateBean;
import com.ht.risk.rule.entity.ValidateBatch;
import com.ht.risk.rule.quartz.task.ModelValidateTask;
import com.ht.risk.rule.service.ValidateBatchService;
import com.ht.risk.rule.util.RedisLockUtil;
import com.ht.risk.rule.util.ThreadPoolUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

@Component
@Configurable
@EnableScheduling
public class ModelValidateJob implements Job {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelValidateJob.class);

    @Resource
    private ValidateBatchService validateBatchService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("ModelValidateJob start!");
        ValidateBatch validateBatch = new ValidateBatch();
        EntityWrapper<ValidateBatch> entityWrapper = new EntityWrapper<ValidateBatch>();
        entityWrapper.setEntity(validateBatch);
        List<ValidateBatch> jobs = validateBatchService.selectList(entityWrapper);
        String lockKey = null;
        String id = null;
        for(Iterator<ValidateBatch> iterator =jobs.iterator();iterator.hasNext();){
            validateBatch = iterator.next();
            id  = validateBatch.getId();
            lockKey = "risk_model_"+id;
            RedisLockUtil lock = new RedisLockUtil(redisTemplate, lockKey, 10000, 20000);
            try {
                if (lock.lock()) {
                    LOGGER.info("模型验证开始,验证批次号为："+validateBatch.getId()+"；");
                    long start = System.currentTimeMillis();
                    validateBatch.setStatus("1");
                    validateBatchService.updateById(validateBatch);
                    //TODO 执行模型验证
                    List<ModelValidateBean> list = validateBatchService.analysisBatchJobs(validateBatch);
                    if(list == null || list.size() ==0){
                        continue;
                    }
                    ModelValidateTask task = new ModelValidateTask(list, 0, list.size());
                    ForkJoinPool pool = ThreadPoolUtils.getForkJoinPool();
                    Future<List<ModelValidateBean>> result = pool.submit(task);
                    List<ModelValidateBean> resList = result.get();

                    validateBatch.setStatus("2");
                    validateBatch.setCount(validateBatch.getCount()+1);
                    validateBatchService.updateById(validateBatch);
                    LOGGER.info("模型验证结束,验证批次号为："+validateBatch.getId()+"；所用时间："+ (System.currentTimeMillis() - start) / 1000);
                }
            } catch (Exception e) {
                LOGGER.error("模型验证异常！",e);
                validateBatch.setStatus("3");
                validateBatch.setCount(validateBatch.getCount()+1);
                validateBatchService.updateById(validateBatch);
            } finally {
                lock.unlock();
            }
        }
        LOGGER.info("ModelValidateJob end!");
    }
}
