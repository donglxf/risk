package com.ht.risk.activiti.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.common.result.Result;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ActivitiService {

    /**
     * 获取模型信息
     * @param modelId
     * @return
     */
    public Model getModelInfo(String modelId);


    /**
     * 新增流程模型
     *
     * @param paramter
     * @return
     */
    public String addModel(ModelParamter paramter)throws Exception;


    /**
     * 流程部署，发布测试版
     * @param modelId
     * @return
     * @throws Exception
     */
    public RpcDeployResult deploy(String modelId) throws Exception;


    /**
     * 流程启动
     * @param paramter
     * @return
     */
    public String startProcess(RpcStartParamter paramter);

    public ObjectNode getEditorJson(String modelId) throws Exception;

    /**
     * 保存流程定义信息
     * @param modelId
     * @param values
     * @throws Exception
     */
    public void saveModel(String modelId, MultiValueMap<String, String> values)throws Exception;

    /**
     * 查询流程中变量值
     * @param processId
     * @param variableName
     * @return
     */
    public List<HistoricVariableInstance> getProcessVarByDeployIdAndName(String processId, String variableName);

    /**
     * 根据变量名模糊查询变量值
     * @param processId
     * @param variableName
     * @return
     */
    public List<HistoricVariableInstance> getHisProcessVarByDeployIdAndNameLike(String processId,String variableName);
}
