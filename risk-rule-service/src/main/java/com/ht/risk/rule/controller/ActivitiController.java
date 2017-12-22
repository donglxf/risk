package com.ht.risk.rule.controller;

import javax.annotation.Resource;

import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ModelParamter;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ObjectMapper objectMapper;

	private static Logger logger = LoggerFactory.getLogger(ActivitiController.class);

	@GetMapping(value = "demo")
	private String demo(){
		System.out.println("12312313");
		return "123";
	}

	/**
	 * 新增流程模型
	 * @param paramter
	 * @return
	 */
	@GetMapping(value = "addModeler")
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
			data = Result.error(1,"创建模型失败");
		}
		return data;
	}

	/**
	 * 删除流程模型
	 * @param paramter
	 */
	@RequestMapping(value = "/deleteModel")
	public void deleteModel(ModelParamter paramter) {
		repositoryService.deleteModel(paramter.getModelId());
	}

	/**
	 *  根据Model部署
	 * @param paramter
	 */
	@RequestMapping(value = "/modelDeploy")
	@ResponseBody
	public void deploy(ModelParamter paramter) {
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
		} catch (Exception e) {
			logger.error("部署流程失败!：", e);
		}
	}

}
