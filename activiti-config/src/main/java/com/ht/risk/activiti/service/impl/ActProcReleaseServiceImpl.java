package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.controller.ActivitiProcessController;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.mapper.ActProcReleaseMapper;
import com.ht.risk.activiti.mapper.RiskValidateBatchMapper;
import com.ht.risk.activiti.rpc.ActivitiRpc;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.ActProcReleaseVo;
import com.ht.risk.activiti.vo.ModelStartVo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-16
 */
@Service
public class ActProcReleaseServiceImpl extends BaseServiceImpl<ActProcReleaseMapper, ActProcRelease> implements ActProcReleaseService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActProcReleaseServiceImpl.class);

    @Resource
    private ActProcReleaseMapper actProcReleaseMapper;
    @Resource
    private ActExcuteTaskMapper actExcuteTaskMapper;
    @Resource
    private RiskValidateBatchMapper riskValidateBatchMapper;

    @Resource
    private ActivitiRpc activitiRpc;

    @Override
    public Page<ActProcRelease> queryProcReleaseForPage(Page<ActProcRelease> page, ActProcRelease actProcRelease) {
        EntityWrapper<ActProcRelease> entityWrapper = new EntityWrapper<ActProcRelease>();
        entityWrapper.setEntity(actProcRelease);
        entityWrapper.orderBy("create_time",false);
        return page.setRecords( actProcReleaseMapper.selectPage(page, entityWrapper));
    }


    @Override
    public Result<RpcDeployResult> proceDeploy(ModelParamter paramter,String userId) {
        // 部署流程到引擎
        Result<RpcDeployResult> rpcResult = activitiRpc.modelDeploy(paramter);
        if (rpcResult == null) {
            return Result.error(2, "模型部署异常，流程引擎中心返回值为空");
        }
        if (rpcResult.getCode() != 0) {
            return Result.error(3, "模型部署异常," + rpcResult.getMsg());
        }
        RpcDeployResult rpcDeployResult = rpcResult.getData();
        if (rpcResult.getData() == null || StringUtils.isEmpty(rpcDeployResult.getProcessDefineId())) {
            return Result.error(4, "模型部署异常,无法获取流程定义信息");
        }
        String prcdefId = rpcDeployResult.getProcessDefineId();
        String modelVersion = rpcDeployResult.getVersion();
        // 往模型版本控制表中插入一条记录
        Result<ModelParamter> modelResult = activitiRpc.getModelInfo(paramter);
        ModelParamter modelRpc = modelResult.getData();
        ActProcRelease release = new ActProcRelease();
        release.setModelCategory(modelRpc.getCategory());
        release.setModelCode(modelRpc.getKey());
        release.setModelId(paramter.getModelId());
        release.setModelName(modelRpc.getName());
        release.setModelVersion(modelVersion);
        release.setModelProcdefId(prcdefId);
        release.setVersionType("0");
        release.setCreateTime(new Date(System.currentTimeMillis()));
        release.setCreateUser(userId);
        actProcReleaseMapper.insert(release);
        rpcDeployResult.setReleaseId(String.valueOf(release.getId()));
        return Result.success(rpcDeployResult);
    }

    @Override
    public String startProcess(RpcStartParamter rpcStartParamter,String userId) {
        Long releaseId = getProcReleaseId(rpcStartParamter.getProcDefId(), rpcStartParamter.getVersion());
        if (releaseId == null) {
            return null;
        }
        ActExcuteTask task  = this.saveTask(releaseId,ActivitiConstants.EXCUTE_TYPE_VERFICATION,rpcStartParamter.getBatchId(),JSON.toJSONString(rpcStartParamter),userId);
        Result<String> result = this.start(rpcStartParamter,task.getId());
        LOGGER.info("startProcess complete... result:"+ JSON.toJSONString(result));
        // 更新模型任务流程实例ID
        if (result != null && result.getCode() == 0 && StringUtils.isNotEmpty(result.getData())) {
            task.setProcInstId(result.getData());
            updateTask(task);
            return result.getData();
        }else{
            task.setStatus(ActivitiConstants.PROC_STATUS_EXCEPTION);
            updateTask(task);
            return null;
        }
    }

    @Override
    public String startModel(ModelStartVo modelStartVo,String userId) {
        String modelVersion = modelStartVo.getModelVersion();
        ActProcRelease release = null;
        // 版本信息为空，获取模型最新版本
        if(StringUtils.isEmpty(modelVersion)){
            release = this.getModelLastedVersion(modelStartVo.getModelCode());
        }else{
            release = this.getModelInfo(modelStartVo.getModelCode(),modelStartVo.getModelVersion());
        }
        if(release == null){
            return ActivitiConstants.MODEL_UNEXIST;
        }
        ActExcuteTask task  = this.saveTask(release.getId(),ActivitiConstants.EXCUTE_TYPE_SERVICE,null,JSON.toJSONString(modelStartVo.getData()),userId);
        RpcStartParamter rpcStartParamter = new RpcStartParamter();
        rpcStartParamter.setProcDefId(release.getModelProcdefId());
        rpcStartParamter.setType(ActivitiConstants.EXCUTE_TYPE_SERVICE);
        rpcStartParamter.setData(modelStartVo.getData());
        // 启动模型
        Result<String> result = this.start(rpcStartParamter,task.getId());
        LOGGER.info("startModel complete... result:"+ JSON.toJSONString(result));
        // 更新模型任务流程实例ID
        if (result != null && result.getCode() == 0 && StringUtils.isNotEmpty(result.getData())) {
            // 模型成功启动
            task.setProcInstId(result.getData());
            updateTask(task);
            return String.valueOf(task.getId());
        }else{
            //模型启动异常
            task.setStatus(ActivitiConstants.PROC_STATUS_EXCEPTION);
            updateTask(task);
            return null;
        }
    }

    @Override
    public Long startInputValidateProcess(RpcStartParamter rpcStartParamter,String userId) throws Exception {
        LOGGER.info("startInputValidateProcess start... paramter:"+ JSON.toJSONString(rpcStartParamter));
        // 获取模型版本信息
        Long releaseId = getProcReleaseId(rpcStartParamter.getProcDefId(), rpcStartParamter.getVersion());
        if (releaseId == null) {
            return null;
        }
        RiskValidateBatch batch = saveBatchInfo(releaseId,userId);
        rpcStartParamter.setBatchId(batch.getId());
        ActExcuteTask task  = this.saveTask(releaseId,rpcStartParamter.getType(),rpcStartParamter.getBatchId(),JSON.toJSONString(rpcStartParamter),userId);
        Result<String> result = this.start(rpcStartParamter,task.getId());
        LOGGER.info("startInputValidateProcess complete... result:"+ JSON.toJSONString(result));
        // 更新模型任务流程实例ID
        if (result != null && result.getCode() == 0 && StringUtils.isNotEmpty(result.getData())) {
            task.setProcInstId(result.getData());
        }else{
            task.setStatus("4");
        }
        updateTask(task);
        return batch.getId();
    }

    @Override
    public Long startBatchValidateProcess(RpcStartParamter rpcStartParamter,String userId) throws Exception {
        // 获取模型版本信息
        Long releaseId = getProcReleaseId(rpcStartParamter.getProcDefId(), rpcStartParamter.getVersion());
        if (releaseId == null) {
            return null;
        }
        RiskValidateBatch batch = saveBatchInfo(releaseId,userId);
        for (int i = 0; i < batch.getBatchSize(); i++) {
            rpcStartParamter.setData(rpcStartParamter.getDatas().get(i));
            rpcStartParamter.setBatchId(batch.getId());
            String procInstId = startProcess(rpcStartParamter,userId);
            ActExcuteTask task  = this.saveTask(releaseId,rpcStartParamter.getType(),rpcStartParamter.getBatchId(),JSON.toJSONString(rpcStartParamter),userId);
            Result<String> result = this.start(rpcStartParamter,task.getId());
            LOGGER.info("startBatchValidateProcess complete... result:"+ JSON.toJSONString(result));
            if (result != null && result.getCode() == 0 && StringUtils.isNotEmpty(result.getData())) {
                task.setProcInstId(result.getData());
            }else{
                task.setStatus("4");
            }
            updateTask(task);
        }
        return batch.getId();
    }

    @Override
    public RpcModelReleaseInfo convertRpcActExcuteTask(ActProcRelease release) {
        RpcModelReleaseInfo rpcRelease = new RpcModelReleaseInfo();
        rpcRelease.setId(release.getId());
        rpcRelease.setIsApprove(release.getIsApprove());
        rpcRelease.setIsValidate(release.getIsValidate());
        rpcRelease.setModelCategory(release.getModelCategory());
        rpcRelease.setModelName(release.getModelName());
        rpcRelease.setModelProcdefId(release.getModelProcdefId());
        rpcRelease.setModelVersion(release.getModelVersion());
        rpcRelease.setVersionType(release.getVersionType());
        return rpcRelease;
    }

    private Long getProcReleaseId(String procDefId, String version) {
        Map<String, Object> paramter = new HashMap<String, Object>();
        paramter.put("MODEL_PROCDEF_ID", procDefId);
        paramter.put("MODEL_VERSION", version);
        paramter.put("IS_EFFECT", "0");//有效
        List<ActProcRelease> releases = actProcReleaseMapper.selectByMap(paramter);
        if (releases == null || releases.size() == 0) {
            return null;
        }
        return releases.get(0).getId();
    }

    /**
     * 获取模型最新版本
     * @param modelCode
     * @return
     */
    private ActProcRelease getModelLastedVersion(String modelCode) {
        EntityWrapper<ActProcRelease> wrapper = new EntityWrapper<ActProcRelease>();
        ActProcRelease actProcRelease = new ActProcRelease();
        actProcRelease.setModelCode(modelCode);
        actProcRelease.setVersionType("1");
        wrapper.setEntity(actProcRelease);
        wrapper.orderBy("model_version",false);
        List<ActProcRelease> releases = actProcReleaseMapper.selectList(wrapper);
        if (releases == null || releases.size() == 0) {
            return null;
        }
        return releases.get(0);
    }

    /**
     *获取模型信息
     * @param modelCode
     * @return
     */
    private ActProcRelease getModelInfo(String modelCode,String modeVersion) {
        EntityWrapper<ActProcRelease> wrapper = new EntityWrapper<ActProcRelease>();
        ActProcRelease actProcRelease = new ActProcRelease();
        actProcRelease.setModelCode(modelCode);
        actProcRelease.setModelVersion(modeVersion);
        actProcRelease.setVersionType("1");
        wrapper.setEntity(actProcRelease);
        List<ActProcRelease> releases = actProcReleaseMapper.selectList(wrapper);
        if (releases == null || releases.size() == 0) {
            return null;
        }
        return releases.get(0);
    }

    private ActExcuteTask saveTask(Long releaseId, String type, Long batchId,String inParamter,String userId) {
        ActExcuteTask task = new ActExcuteTask();
        task.setProcReleaseId(releaseId);
        task.setType(StringUtils.isEmpty(type) ? "1" : type);
        task.setCreateTime(new Date(System.currentTimeMillis()));
        task.setBatchId(batchId);
        task.setInParamter(inParamter);
        task.setCreateUser(userId);
        actExcuteTaskMapper.insert(task);
        return task;
    }

    // 启动模型
    private Result<String> start(RpcStartParamter rpcStartParamter,Long taskId) {
        Map<String, Object> data = rpcStartParamter.getData() == null ? new HashMap<String, Object>() : rpcStartParamter.getData();
        rpcStartParamter.setData(data);
        rpcStartParamter.setTaskId(taskId);
        Result<String> result = activitiRpc.startProcess(rpcStartParamter);
        return  result;
    }

    private RiskValidateBatch saveBatchInfo(Long releaseId,String userId){
        RiskValidateBatch batch = new RiskValidateBatch();
        batch.setProcReleaseId(releaseId);
        batch.setBatchSize(1);
        batch.setCount(0);
        batch.setCreateTime(new Date(System.currentTimeMillis()));
        batch.setCreateUser(userId);
        batch.setIsEffect("1");
        batch.setStatus("0");
        riskValidateBatchMapper.insert(batch);
        return batch;
    }
    private void updateTask(ActExcuteTask task) {
        actExcuteTaskMapper.updateById(task);
    }
}
