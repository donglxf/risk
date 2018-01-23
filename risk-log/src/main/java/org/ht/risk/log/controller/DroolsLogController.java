package org.ht.risk.log.controller;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.service.DroolsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/droolsLog")
public class DroolsLogController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DroolsLogController.class);
	
	@Resource
	private DroolsLogService droolsLogService ;
	
	@RequestMapping("/save")
	@ApiOperation(value = "新增日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody DroolsLog entity){
		String str=null;
        try {
        	droolsLogService.insertOrUpdate(entity);
        	System.out.println(entity.getId()+">>>>>>>>>");
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }

	@RequestMapping("/getHitRuleInfo")
	public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody String procInstId){
		LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ procInstId);
		Result<List<RpcHitRuleInfo>> result = null;
		if(procInstId == null){
			LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
			result =Result.error(1,"参数异常！");
			return  result;
		}
		try {
			List<RpcHitRuleInfo> tasks = droolsLogService.queryHitRuleInfoByProcInstId(procInstId);
			result = Result.success(tasks);
		}catch (Exception e){
			result = Result.error(2,"queryModelProcInstIdByBatchId mothod excute exception,"+e);
			return  result;
		}
		LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
		return result;
	}

	@RequestMapping("/countHitRuleInfo")
	public Result<List<RpcHitRuleInfo>> countHitRuleInfo(@RequestBody List<String> procInstId){
		LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ procInstId);
		Result<List<RpcHitRuleInfo>> result = null;
		if(procInstId == null){
			LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
			result =Result.error(1,"参数异常！");
			return  result;
		}
		try {
			List<RpcHitRuleInfo> tasks = droolsLogService.countHitRuleInfo(procInstId);
			result = Result.success(tasks);
		}catch (Exception e){
			result = Result.error(2,"queryModelProcInstIdByBatchId mothod excute exception,"+e);
			return  result;
		}
		LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
		return result;
	}
}
