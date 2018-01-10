package com.ht.risk.rule.service.impl;

import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcSenceInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.rpc.ActivitiRpc;
import com.ht.risk.rule.service.ModelSenceService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelDeployServiceImpl {

    @Resource
    private ActivitiRpc activitiRpc;
    @Resource
    private ModelSenceService senceService;

    void modelDeploy(ModelParamter paramter){
        // 部署流程到引擎
        Result<RpcDeployResult> result = activitiRpc.modelDeploy(paramter);
        // 将模型引用的决策初始化到RISK_MODEL_SENCE
        if(result != null){
            RpcDeployResult rpcDeployResult = result.getData();
            if(result.getCode() == 0 && rpcDeployResult != null){
                List<RpcSenceInfo> list = rpcDeployResult.getSences();
                if(list != null && list.size()>0){
                    List<ModelSence> sences = new ArrayList<ModelSence>(list.size());
                    ModelSence sence  = null;
                    for (RpcSenceInfo rpcSence : list) {
                        sence = new ModelSence();
                        sence.setModelProcdefId(rpcDeployResult.getProcessDefineId());
                        sence.setCreateTime(new Date(System.currentTimeMillis()));
                        sence.setCreateUser("Robot");
                        sence.setSenceVersionId(getSenceVersionId(rpcSence.getSenceCode(),rpcSence.getSenceVersion()));
                        sences.add(sence);
                    }
                    senceService.insertBatch(sences);
                }
            }
        }
    }

    String getSenceVersionId(String senceCode,String senceVersion){
        return "00010001";
    }
}
