package com.ht.risk.rule.service.impl;

import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcSenceInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ModelRelease;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.rpc.ActivitiRpc;
import com.ht.risk.rule.service.ModelDeployService;
import com.ht.risk.rule.service.ModelReleaseService;
import com.ht.risk.rule.service.ModelSenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("modelDeployService")
public class ModelDeployServiceImpl implements ModelDeployService {

    @Resource
    private ActivitiRpc activitiRpc;
    @Resource
    private ModelSenceService senceService;
    @Resource
    private ModelReleaseService modelReleaseService;

    public Result<RpcDeployResult> modelDeploy(ModelParamter paramter)throws Exception{
        // 部署流程到引擎
        Result<RpcDeployResult> result = activitiRpc.modelDeploy(paramter);
        if(result == null){
            return Result.error(2,"模型部署异常，流程引擎中心返回值为空");
        }
        if(result.getCode() != 0){
            return Result.error(3,"模型部署异常,"+result.getMsg());
        }
        RpcDeployResult rpcDeployResult = result.getData();
        if(result.getData() == null || StringUtils.isEmpty(rpcDeployResult.getProcessDefineId())){
            return Result.error(4,"模型部署异常,无法获取流程定义信息");
        }
        String prcdefId = rpcDeployResult.getProcessDefineId();
        String modelVersion = rpcDeployResult.getVersion();
        List<RpcSenceInfo> list = rpcDeployResult.getSences();
        // 将模型引用的决策初始化到RISK_MODEL_SENCE
        if(list != null && list.size()>0){
            List<ModelSence> sences = new ArrayList<ModelSence>(list.size());
            ModelSence sence  = null;
            for (RpcSenceInfo rpcSence : list) {
                sence = new ModelSence();
                sence.setModelProcdefId(rpcDeployResult.getProcessDefineId());
                sence.setCreateTime(new Date(System.currentTimeMillis()));
                //TODO 操作用户设置
                sence.setCreateUser("Robot");
                sence.setSenceVersionId(getSenceVersionId(rpcSence.getSenceCode(),rpcSence.getSenceVersion()));
                sences.add(sence);
            }
            senceService.insertBatch(sences);
        }
        // 往模型版本控制表中插入一条记录
        Result<ModelParamter> modelResult = activitiRpc.getModelInfo(paramter);
        ModelParamter modelRpc = modelResult.getData();
        ModelRelease release = new ModelRelease();
        release.setModelCategory(modelRpc.getCategory());
        release.setModelName(modelRpc.getName());
        release.setModelVersion(modelVersion);
        release.setModelProcdefId(prcdefId);
        release.setVersionType("0");
        modelReleaseService.insert(release);
        return result;
    }
    String getSenceVersionId(String senceCode,String senceVersion){
        return "00010001";
    }
}
