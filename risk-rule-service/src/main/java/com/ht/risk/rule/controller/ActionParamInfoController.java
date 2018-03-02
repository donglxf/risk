package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.rule.service.ActionParamInfoService;
import com.ht.risk.rule.util.anno.OperationDelete;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
public class ActionParamInfoController extends BaseController {
	
	@Autowired
	private ActionParamInfoService actionParamInfoService;
	
	@GetMapping("getAll")
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
		actionParamInfo.setCreUserId(new Long(this.getUserId()));
		actionParamInfoService.insertOrUpdate(actionParamInfo);
		return Result.success(0);
	}
	
	@GetMapping("getInfoById")
	@ApiOperation(value = "通过id查询详细信息")
	public Result<ActionParamInfo> getDateById( Long id) {
		ActionParamInfo entityInfo = actionParamInfoService.selectById(id);
		return Result.success(entityInfo);
	}
	
	@GetMapping("delete")
	@ApiOperation(value = "通过id删除信息")
	@OperationDelete(tableColumn = {"rule_action_param_value_info&action_param_id"},idVal = "#id")
	public Result<Integer> delete( Long id) {
		actionParamInfoService.deleteById(id);
		return Result.success(0);
	}
	
}

