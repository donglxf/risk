package com.ht.risk.rule.quartz.task;

import com.ht.risk.rule.entity.ModelValidateBean;
import com.ht.risk.rule.quartz.job.ModelValidateJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ModelValidateTask extends RecursiveTask<List<ModelValidateBean>> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelValidateJob.class);
    private static final long serialVersionUID = 1L;
    private static final int limit = 50;
    private List<ModelValidateBean> models;//工单队列
    private int start;
    private int end;


    public ModelValidateTask(List<ModelValidateBean> models, int first, int last) {
        super();
        this.models = models;
        this.start = first;
        this.end = last;
    }
    @Override
    protected List<ModelValidateBean> compute() {
        List<ModelValidateBean> resList = new ArrayList<ModelValidateBean>();
        if (end - start < limit) {
            for (int i = start; i < end; i++) {
                ModelValidateBean order = models.get(i);
                //业务处理
                try {
                    ModelValidateBean res = null;
                    //ModelValidateBean res = LoanOrderService.me.loanCheck(order);
                    resList.add(res);
                } catch (Exception e) {
                    LOGGER.info("模型验证异常", e);
                }
            }
            return resList;
        } else {
            // 分解任务 时间复杂度：O（nlogn）
            int middle = (start + end) / 2;
            ModelValidateTask t1 = new ModelValidateTask(models, start, middle + 1);
            ModelValidateTask t2 = new ModelValidateTask(models, middle + 1, end);
            // 执行任务
            invokeAll(t1,t2);
            // 获取任务执行结果
            resList.addAll(t1.join());
            resList.addAll(t2.join());
        }
        return resList;
    }
}
