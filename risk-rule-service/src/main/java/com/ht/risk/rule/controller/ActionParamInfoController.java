package com.ht.risk.rule.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.rule.service.ActionParamInfoService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 动作参数信息表 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/actionParamInfo")
public class ActionParamInfoController {
	
	@Autowired
	private ActionParamInfoService actionParamInfoService;
	
	@GetMapping("getAll/")
	@ApiOperation(value = "查询所有的对象")
	public Result<List<ActionParamInfo>> getAlls(String actionId) {
		Wrapper<ActionParamInfo> wrapper = new EntityWrapper<>();
		wrapper.eq("action_id", actionId);
		List<ActionParamInfo> list = actionParamInfoService.selectList(wrapper);
		// Page<EntityInfo> page = new Page<>();
		// page = entityInfoService.selectPage(page);
		
		return Result.success(list);
	}
	
	@PostMapping("edit")
	@ApiOperation(value = "动作新增or修改")
	@Transactional()
	public Result<Integer> edit(ActionParamInfo actionParamInfo) {
		actionParamInfo.setCreTime(new Date());
		actionParamInfo.setIsEffect(1);
		actionParamInfo.setCreUserId(new Long(1));
		actionParamInfoService.insertOrUpdate(actionParamInfo);
		return Result.success(0);
	}
	
	@GetMapping("getInfoById/{id}")
	@ApiOperation(value = "通过id查询详细信息")
	public Result<ActionParamInfo> getDateById(@PathVariable(name = "id") Long id) {
		System.out.println(">>>>>>>"+id);
		ActionParamInfo entityInfo = actionParamInfoService.selectById(id);
		return Result.success(entityInfo);
	}
	
	@GetMapping("delete/{id}")
	@ApiOperation(value = "通过id删除信息")
	public Result<Integer> delete(@PathVariable(name = "id") Long id) {
		actionParamInfoService.deleteById(id);
		return Result.success(0);
	}
	
}

