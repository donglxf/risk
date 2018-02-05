package com.ht.risk.rule.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActProcRelease;
import com.ht.risk.rule.entity.ModelTask;
import com.ht.risk.rule.quartz.init.ModelTaskInitRunner;
import com.ht.risk.rule.quartz.job.ModelTaskJob;
import com.ht.risk.rule.service.ModelTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 风控模型离线任务表 前端控制器
 * </p>
 * @author liuzq
 * @since 2018-01-29
 */
@RestController
@RequestMapping("/modelTask")
@Api(tags = "ModelTaskController", description = "离线任务接口")
public class ModelTaskController {
	
    @Autowired
    private ModelTaskService modelTaskService;
    
	@GetMapping("page")
	@ApiOperation(value = "分页查询")
	public PageResult<List<ActProcRelease>> page(Integer page, Integer limit,String modelName) {
		Wrapper<ActProcRelease> wrapper = new EntityWrapper<>();
		wrapper.andNew("IS_VALIDATE = 1");
		wrapper.andNew("VERSION_TYPE = 1");
		
		if(StringUtils.isNotBlank(modelName)) {
			wrapper.eq("MODEL_NAME",modelName);
		}
		
		Page<ActProcRelease> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = modelTaskService.queryModelTask(pages, wrapper);
		return PageResult.success(pages.getRecords(), pages.getTotal());
	}
    
    @GetMapping("getModelTaskById/{id}")
    @ApiOperation(value = "通过id查询模块任务详细")
    public Result<ModelTask> getModelTashById(@PathVariable(name = "id") Long id){
    	ModelTask modelTask = modelTaskService.selectById(id);
        return Result.success(modelTask);
    }
    
    @PostMapping("getModelTaskByModel")
    @ApiOperation(value = "通过模型id查询模型任务详细")
    public Result<ModelTask> getModelTaskByModel(String modelProcdefId){
    	Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("model_procdef_id", modelProcdefId);
    	List<ModelTask> listTask = modelTaskService.selectByMap(paramMap);
    	ModelTask retModelTask = new ModelTask();
    	if(listTask.size() >= 1){
    		retModelTask = listTask.get(0);
    	}
        return Result.success(retModelTask);
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增离线任务")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> edit(String modelProcdefId,String cornText,Long taskId){
    	ModelTask modelTask = new ModelTask();
    	if(taskId != -1) {
    		modelTask.setId(taskId);
    	}
        modelTask.setModelProcdefId(modelProcdefId);
        //Date runTimeDate = DateUtil.stringToDate("yyyy-MM-dd HH:mm:ss",runTime);//"2018-09-09 12:12:12"
        modelTask.setCornText(cornText);
        modelTask.setTaskStatus("1");//默认任务停止，通过界面启动
        modelTask.setCreateTime(new Date());
        boolean ret = modelTaskService.insertOrUpdate(modelTask);
        if(ret) {
        	return Result.success(0);
        }else {
        	return Result.success(1);
        }
    }

    @GetMapping("delete/{id}")
    @ApiOperation(value = "通过任务id删除任务信息")
    public Result<Integer> delete(@PathVariable(name = "id") Long id){
    	modelTaskService.deleteById(id);
        return Result.success(0);
    }
    
	@GetMapping("startModelTask/{id}")
	@ApiOperation(value = "启动任务,修改任务状态，添加到任务调度器")
	public Result<Integer> startModelTask(@PathVariable(name = "id") Long id) {
		Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("model_procdef_id", id);
    	List<ModelTask> listTask = modelTaskService.selectByMap(paramMap);
    	ModelTask modelTask = listTask.get(0);
    	modelTask.setTaskStatus("1");
    	modelTask.setUpdateTime(new Date());
    	modelTask.setCreateUser("todo");
        boolean ret = modelTaskService.insertOrUpdate(modelTask);
        if(ret) {//成功，继续添加任务到任务状态
            String jobName = "modelTaskJob"+modelTask.getModelProcdefId();//job前缀抽出到配置文件
            String jobGroupName = "modelTaskJobGroup";//抽出到配置文件
            String triggerName = "modelTasktrigger"+modelTask.getModelProcdefId();//trigger前缀抽出到配置文件
            String triggerGroupName = "modelTasktriggerGroup";//抽出到配置文件
            String cronText = modelTask.getCornText();
            JobDataMap map = new JobDataMap();
            map.put("modelid", modelTask.getModelProcdefId());
        	ModelTaskInitRunner.addJob(jobName, jobGroupName, triggerName, triggerGroupName, ModelTaskJob.class, cronText, map);
        	return Result.success(0);
        }else {
        	return Result.success(1);
        }
	}
	
	@GetMapping("stopModelTask/{id}")
	@ApiOperation(value = "停止任务,修改任务状态，从任务调度器删除")
	public Result<Integer> stopModelTask(@PathVariable(name = "id") Long id) {
		Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("model_procdef_id", id);
    	List<ModelTask> listTask = modelTaskService.selectByMap(paramMap);
    	ModelTask modelTask = listTask.get(0);
    	modelTask.setTaskStatus("2");
    	modelTask.setCreateUser("todo ");
        boolean ret = modelTaskService.insertOrUpdate(modelTask);
        if(ret) {//成功，继续添加任务到任务状态
        	String jobName = "modelTaskJob"+modelTask.getModelProcdefId();
            String jobGroupName = "modelTaskJobGroup";
            String triggerName = "modelTasktrigger"+modelTask.getModelProcdefId();
            String triggerGroupName = "modelTasktriggerGroup";//抽出到配置文件
        	ModelTaskInitRunner.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
        	return Result.success(0);
        }else {
        	return Result.success(1);
        }
	}
    
}

