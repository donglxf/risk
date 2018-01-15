package com.ht.risk.rule.service;

import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.common.result.Result;

public interface ModelDeployService {

   public Result<RpcDeployResult> modelDeploy(ModelParamter paramter)throws Exception;
}
