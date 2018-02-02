package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ht.risk.activiti.service.ActivitiService;
import com.ht.risk.activiti.vo.ModelPage;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@RestController
public class ActivitiController implements ModelDataJsonConstants {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiController.class);

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private HistoryService historyService;
    @Resource
    private ActivitiService activitiService;


    /**
     * 获取模型信息
     *
     * @param paramter
     * @return
     */
    @RequestMapping(value = "/getModelInfo")
    public Result<ModelParamter> getModelInfo(@RequestBody ModelParamter paramter) {
        LOGGER.info("获取模型信息,参数paramter:" + JSON.toJSONString(paramter));
        Result<ModelParamter> data = null;
        try {
            if (paramter == null || StringUtils.isEmpty(paramter.getModelId())) {
                LOGGER.error("获取模型失败：参数异常，模型ID为空！");
                data = Result.error(1, "参数异常，模型ID为空！");
                return data;
            }
            Model model = activitiService.getModelInfo(paramter.getModelId());
            if (model == null) {
                LOGGER.error("获取模型失败：没有对应的模型！");
                data = Result.error(1, "没有对应的模型！");
                return data;
            }
            paramter.setModelId(model.getId());
            paramter.setName(model.getName());
            paramter.setCategory(model.getCategory());
            data = Result.success(paramter);
        } catch (Exception e) {
            LOGGER.error("获取模型失败：", e);
            data = Result.error(1, "获取模型失败");
        }
        LOGGER.info("获取模型信息！");
        return data;
    }


    /**
     * 新增流程模型
     *
     * @param paramter
     * @return
     */
    @GetMapping(value = "/addModeler")
    @ResponseBody
    public Result<ModelParamter> addModel(ModelParamter paramter) {
        LOGGER.info("addModel paramter:" + JSON.toJSONString(paramter));
        Result<ModelParamter> data = null;
        try {
            String modelId = activitiService.addModel(paramter);
            paramter.setModelId(modelId);
            data = Result.success(paramter);
        } catch (Exception e) {
            LOGGER.error("addModel error：", e);
            data = Result.error(1, "创建模型失败");
        }
        LOGGER.info("addModel end !");
        return data;
    }

    /**
     * 删除流程模型
     *
     * @param paramter
     */
    @RequestMapping(value = "/deleteModel")
    public Result<ModelParamter> deleteModel(ModelParamter paramter) {
        LOGGER.info("delete model paramter:" + JSON.toJSONString(paramter));
        Result<ModelParamter> data = null;
        try {
            if (paramter == null || StringUtils.isEmpty(paramter.getModelId())) {
                LOGGER.error("delete model error.");
                data = Result.error(1, "参数异常.");
                return data;
            }
            repositoryService.deleteModel(paramter.getModelId());
            data = Result.success(paramter);
        } catch (Exception e) {
            LOGGER.error("delete model error,errorMsg:", e);
            data = Result.error(1, "删除模型失败");
        }
        LOGGER.info("delete model end");
        return data;
    }

    /**
     * 根据Model部署
     *
     * @param paramter
     */
    @RequestMapping(value = "/deploy")
    public Result<RpcDeployResult> deploy(@RequestBody ModelParamter paramter) {
        LOGGER.info("deploy model,paramter:" + JSON.toJSONString(paramter));
        Result<RpcDeployResult> data = null;
        try {
            if (paramter == null || StringUtils.isEmpty(paramter.getModelId())) {
                LOGGER.error("deploy model error.");
                data = Result.error(1, "deploy model error.");
                return data;
            }
            RpcDeployResult result = activitiService.deploy(paramter.getModelId());
            data = Result.success(result);
        } catch (Exception e) {
            LOGGER.error("deploy model error,error message：", e);
            data = Result.error(1, "部署流程失败!");
        }
        return data;
    }

    @RequestMapping("/start")
    public Result<String> startProcess(@RequestBody RpcStartParamter paramter) {
        LOGGER.info("start model,paramter:" + JSON.toJSONString(paramter));
        Result<String> data = null;
        try {
            if (paramter == null || StringUtils.isEmpty(paramter.getProcDefId())) {
                LOGGER.info("start model error,paramter error.");
                data = Result.error(1, "参数异常！");
                return data;
            }
            String processInstanceId = activitiService.startProcess(paramter);
            data = Result.success(processInstanceId);
        } catch (Exception e) {
            data = Result.error(1, "模型启动异常！");
            LOGGER.error("deploy model error,error message：", e);
        }
        LOGGER.info("start model sucess.");
        return data;
    }


    //@RequestMapping(value = "/editModel/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    @RequestMapping("/editModel")
    public ObjectNode getEditorJson(ModelParamter paramter) {
        LOGGER.info("getEditorJson invoke start ,paramter:" + JSON.toJSONString(paramter));
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(paramter.getModelId());
        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = objectMapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
                        new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
                modelNode.put("model", editorJsonNode);

            } catch (Exception e) {
                LOGGER.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }

    @RequestMapping(value = "/model/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@RequestParam String modelId, @RequestBody MultiValueMap<String, String> values) {
        LOGGER.info("saveModel invoke ,paramter[modelId:" + modelId + ";values:" + JSON.toJSONString(values));
        List<String> list = values.get("json_xml");
        String json = list.get(0);
        Map<String, Object> map = new GsonJsonParser().parseMap(json);
        List<Map> childShapes = (List<Map>) map.get("childShapes");
        for (Map map1 : childShapes) {
            Map properties = (Map) map1.get("properties");
            Map stencil = (Map) map1.get("stencil");
            //根据节点id,判断是否为自定义节点
            if ("custom".equals(stencil.get("id"))) {
                stencil.put("id", "ServiceTask");
                properties.put("overrideid", "");
                properties.put("name", "");
                properties.put("documentation", "");
                properties.put("asynchronousdefinition", "false");
                properties.put("exclusivedefinition", "false");
                properties.put("executionlisteners", "");
                properties.put("multiinstance_type", "None");
                properties.put("multiinstance_cardinality", "");
                properties.put("multiinstance_collection", "");
                properties.put("multiinstance_variable ", "");
                properties.put("multiinstance_condition", "");
                properties.put("isforcompensation ", "false");
                properties.put("servicetaskclass", "");
                properties.put("servicetaskexpression", "");
                properties.put("servicetaskdelegateexpression", "${mortgageRiskService}");
                properties.put("servicetaskfields", "");
                properties.put("servicetaskresultvariable", "");
                properties.put("delegateExpression", "");
            }
        }
        //重新设置回去
        map.put("childShapes", childShapes);
        //把map转换未json,重新赋值给values
        String json1 = JSON.toJSONString(map);
        values.set("json_xml", json1);
        try {
            activitiService.saveModel(modelId, values);
        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
        LOGGER.info("模型定义保存成功！");
    }

    @RequestMapping(value = "/editor/stencilset")
    public String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }

    @RequestMapping(value = "/list")
    public PageResult<List<Model>> list(ModelPage modelPage) {
        PageResult<List<Model>> data = null;
        try {
            if (modelPage == null) {
                data = PageResult.error(1, "参数异常，分页信息为空！");
                return data;
            }
            String modelName = StringUtils.isEmpty(modelPage.getModelName()) ? "" : modelPage.getModelName();
            modelName = StringUtils.isEmpty(modelName) ? "" : modelName;
            modelName = "%" + modelName + "%";
            List<Model> list = repositoryService.createModelQuery().modelNameLike(modelName).listPage(modelPage.getPage(), modelPage.getLimit());
            Long count = repositoryService.createModelQuery().modelNameLike(modelName).count();
            data = PageResult.success(list, count);
        } catch (Exception e) {
            data = PageResult.error(1, "查询模型列表失败");
            LOGGER.error("查询模型列表失败!", e);
        }
        return data;
    }

    @RequestMapping(value = "/getProcInstVarObj")
    public Object getProcInstVarObj(@RequestBody ModelParamter paramter) {
        LOGGER.info("getProcInstVarObj start,paramter:" + JSON.toJSONString(paramter));
        List<HistoricVariableInstance> instances = activitiService.getProcessVarByDeployIdAndName(paramter.getProcessId(), paramter.getVariableName());
        LOGGER.info("getProcInstVarObj end,paramter:" + JSON.toJSONString(instances));
        if (instances.size() > 0 && instances.get(0).getValue() != null) {
            return instances.get(0).getValue();
        }
        return null;
    }

}