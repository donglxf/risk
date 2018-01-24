package com.ht.risk.rule.service.impl;

import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcSenceInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.rpc.ActivitiConfigRpc;
import com.ht.risk.rule.service.ModelDeployService;
import com.ht.risk.rule.service.ModelSenceService;
import com.ht.risk.rule.service.SceneVersionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("modelDeployService")
public class ModelDeployServiceImpl implements ModelDeployService {

    @Resource
    private ActivitiConfigRpc activitiConfigRpc;
    @Resource
    private ModelSenceService senceService;
    @Resource
    private SceneVersionService sceneVersionService;

    public Result<RpcDeployResult> modelDeploy(ModelParamter paramter)throws Exception{
        // 部署流程到引擎
        Result<RpcDeployResult> result = activitiConfigRpc.deploy(paramter);
        if(result == null || result.getCode() != 0 || result.getData() ==null){
            return Result.error(3,"模型部署异常,");
        }
        RpcDeployResult rpcDeployResult = result.getData();
        String prcdefId = rpcDeployResult.getProcessDefineId();
        List<RpcSenceInfo> list = rpcDeployResult.getSences();
        // 将模型引用的决策初始化到RISK_MODEL_SENCE
        if(list != null && list.size()>0){
            List<ModelSence> sences = new ArrayList<ModelSence>();
            ModelSence sence  = null;
            Long sceneVersionId = null;
            for (RpcSenceInfo rpcSence : list) {
                sence = new ModelSence();
                sence.setModelProcdefId(rpcDeployResult.getProcessDefineId());
                sence.setCreateTime(new Date(System.currentTimeMillis()));
                //TODO 操作用户设置
                sence.setCreateUser("Robot");
                sceneVersionId = getSenceVersionId(rpcSence.getSenceCode(),rpcSence.getSenceVersion());
                if(sceneVersionId == null){
                    continue;
                }
                sence.setSenceVersionId(sceneVersionId);
                sences.add(sence);
            }
            if(sences.size() >0 ){
                senceService.insertBatch(sences);
            }
        }
        return result;
    }

    public Long getSenceVersionId(String senceCode,String senceVersion){
        SceneVersion sceneVersion = sceneVersionService.querySceneVersionInfoByCodeAndVersion(senceCode,senceVersion);
        if(sceneVersion != null){
            return sceneVersion.getVersionId();
        }
        return null;
    }
}
