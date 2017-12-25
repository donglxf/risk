package com.ht.risk.rule.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActReModel;
import com.ht.risk.rule.entity.ModelParamter;
import com.ht.risk.rule.service.ActReModelService;
import io.swagger.annotations.ApiOperation;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@RestController
@RequestMapping("/activiti")
public class ActivitiController {

	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ActReModelService actReModelService;

	private static Logger logger = LoggerFactory.getLogger(ActivitiController.class);

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
	@RequestMapping(value = "deleteModel")
	public Result<ModelParamter> deleteModel(ModelParamter paramter) {
		Result<ModelParamter> data = null;
		try{
			repositoryService.deleteModel(paramter.getModelId());
			data = Result.success(paramter);
		}catch (Exception e) {
			logger.error("删除模型失败：", e);
			data = Result.error(1,"删除模型失败");
		}
		return data;
	}

	/**
	 *  根据Model部署
	 * @param paramter
	 */
	@RequestMapping(value = "modelDeploy")
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
			data = Result.error(1,"部署流程失败!");
		}
		return data;
	}
	@GetMapping(value = "/start")
	public Result startHireProcess(String key,String ruleData1) throws Exception {
		Result<ModelParamter> data = null;
		try{
			Map<String, Object> ruleData = new HashMap<String, Object>();
			ruleData.put("name","zhangsan");
			ruleData.put("age","25");
			ruleData.put("salary","2000");
			Map<String, Object> paramterMap = new HashMap<String, Object>();
			paramterMap.put("ruleData",ruleData);
			//JSON.parseObject(ruleData, Map.class);
			runtimeService.startProcessInstanceByKey(key,paramterMap);
			data = Result.success();
		}catch (Exception e) {
			logger.error("启动模型失败：", e);
			data = Result.error(1,"启动模型失败");
		}
		return data;

	}


	@GetMapping("list")
	@ApiOperation(value = "查询模型列表")
	public Result<List<ActReModel>> list(Page<ActReModel> page) {
		Page<ActReModel> pages =  actReModelService.selectPage(page);
		Result<List<ActReModel>> result = Result.build(0,"",pages.getRecords(),pages.getTotal());
		return result;
	}

}
