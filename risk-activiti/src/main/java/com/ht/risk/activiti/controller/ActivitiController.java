package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ht.risk.activiti.model.ModelParamter;
import com.ht.risk.activiti.model.ProcessDefinitionModel;
import com.ht.risk.common.result.Result;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

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
    /*@Resource
	private ActReModelService actReModelService;*/

    private static Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    /**
     * 新增流程模型
     *
     * @param paramter
     * @return
     */
    @GetMapping(value = "/addModeler")
    @ResponseBody
    public Result<ModelParamter> addModel(ModelParamter paramter) {
        Result<ModelParamter> data = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, paramter.getName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, paramter.getDescription());
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(paramter.getName());
            modelData.setKey(paramter.getKey());
            // 存入ACT_RE_MODEL
            repositoryService.saveModel(modelData);
            // 存入ACT_GE_BYTEARRAY
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            paramter.setModelId(modelData.getId());
            data = Result.success(paramter);
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
            data = Result.error(1, "创建模型失败");
        }
        return data;
    }

    /**
     * 删除流程模型
     *
     * @param paramter
     */
    @RequestMapping(value = "/deleteModel")
    public Result<ModelParamter> deleteModel(ModelParamter paramter) {
        Result<ModelParamter> data = null;
        try {
            repositoryService.deleteModel(paramter.getModelId());
            data = Result.success(paramter);
        } catch (Exception e) {
            logger.error("删除模型失败：", e);
            data = Result.error(1, "删除模型失败");
        }
        return data;
    }

    /**
     * 根据Model部署
     *
     * @param paramter
     */
    @RequestMapping(value = "/modelDeploy")
    @ResponseBody
    public Result deploy(ModelParamter paramter) {
        Result<ModelParamter> data = null;
        try {
            Model modelData = repositoryService.getModel(paramter.getModelId());
            ObjectNode modelNode;
            modelNode = (ObjectNode) new ObjectMapper()
                    .readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model, "GBK");
            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                    .addString(processName, new String(bpmnBytes)).deploy();
            modelData.setDeploymentId(deployment.getId());
            repositoryService.saveModel(modelData);
			/*if(model != null) {
				Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
				for(FlowElement e : flowElements) {
					System.out.println("flowelement id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
				}
			}*/
            data = Result.success();
        } catch (Exception e) {
            logger.error("部署流程失败!：", e);
            data = Result.error(1, "部署流程失败!");
        }
        return data;
    }

    @RequestMapping(value = "/start")
    public Result startProcess(@RequestParam Map<String, String> paramter) throws Exception {
        LOGGER.info("###############模型执行开始");
        LOGGER.info("###############模型执行参数：" + JSON.toJSONString(paramter));
        Result<String> data = null;
        try {
            if (paramter != null) {
                String instanceByKey = paramter.get("key");
                if (StringUtils.isNotEmpty(instanceByKey)) {
                    paramter.remove("key");
                    Map<String, Object> paramterMap = new HashMap<String, Object>();
                    paramterMap.put("senceData", paramter);
                    LOGGER.info("###############模型执行参数：" + JSON.toJSONString(paramter));
                    ProcessInstance instance = runtimeService.startProcessInstanceByKey(instanceByKey, paramterMap);
                    data = Result.success(instance.getProcessInstanceId());
                    return data;
                }
            }
            data = Result.error(1, "参数异常");
        } catch (Exception e) {
            logger.error("启动模型失败：", e);
            data = Result.error(1, "启动模型失败");
        }
        LOGGER.info("###############模型执行结束");
        return data;
    }


    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ObjectNode getEditorJson(@PathVariable String modelId) {
        ObjectNode modelNode = null;
        Model model = repositoryService.getModel(modelId);
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

    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
        try {
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, values.getFirst("name"));
            modelJson.put(MODEL_DESCRIPTION, values.getFirst("description"));
            model.setMetaInfo(modelJson.toString());
            model.setName(values.getFirst("name"));
            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), values.getFirst("json_xml").getBytes("utf-8"));
            InputStream svgStream = new ByteArrayInputStream(values.getFirst("svg_xml").getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);
            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
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

    @RequestMapping(value = "/getProcessVarByDeployId")
    public Result getProcessVarByDeployId(String processId) {
        Result<String> data = null;
        //List<HistoricDetail> list = historyService.createHistoricDetailQuery().processInstanceId(processId).list();
        List<HistoricVariableInstance> vars = historyService.createHistoricVariableInstanceQuery().processInstanceId(processId).variableName("result").list();
        String resutStr = null;
        if (vars != null && vars.size() > 0) {
            resutStr = String.valueOf(vars.get(0).getValue());
        }
        data = Result.success(resutStr);
        return data;
    }

    @RequestMapping(value = "/getDeployVersionList")
    public Result<List<ProcessDefinitionModel>> getDeployVersionList(String deployId) {
        LOGGER.info("getDeployVersionList interface start");
        Result<List<ProcessDefinitionModel>> data = null;
        try {
            if (StringUtils.isEmpty(deployId)) {
                data = Result.success();
                LOGGER.info("getDeployVersionList interface end,paramter deployId is null");
                return data;
            }
            ProcessDefinitionEntity entity = null;
            List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).list();
            if(definitions.size()>0){
                String key = definitions.get(0).getKey();
                List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).orderByDeploymentId().desc().list();
                ProcessDefinition definition = null;
                ProcessDefinitionModel definitionModel = null;
                List<ProcessDefinitionModel> definitionModels = new ArrayList<ProcessDefinitionModel>(list.size());
                for (Iterator<ProcessDefinition> iterator = list.iterator();iterator.hasNext();){
                    definition = iterator.next();
                    definitionModel = new ProcessDefinitionModel(definition);
                    definitionModels.add(definitionModel);
                }
                data = Result.success(definitionModels);
            }else{
                data = Result.success();
                LOGGER.info("getDeployVersionList interface no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = Result.error(1, "查询流程定义信息失败");
            LOGGER.error("查询流程定义信息失败!", e);
        }
        LOGGER.info("getDeployVersionList interface end");
        return data;
    }

    @GetMapping("/list")
    public Result list(String modelName) {
        Result<List<Model>> data = null;
        try {
            modelName = StringUtils.isEmpty(modelName)?"":modelName;
            modelName = "%"+modelName+"%";
            List<Model> list = repositoryService.createModelQuery().modelNameLike(modelName).list();
            data = Result.success(list);
        } catch (Exception e) {
            data = Result.error(1, "查询模型列表失败");
            LOGGER.error("查询模型列表失败!", e);
        }
        return data;
    }

}
