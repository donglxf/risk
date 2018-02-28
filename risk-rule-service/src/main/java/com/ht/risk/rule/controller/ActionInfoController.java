package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.comenum.ActionEnum;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.service.ActionInfoService;
import com.ht.risk.rule.service.SceneInfoService;
import com.ht.risk.rule.vo.ActionInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


//import java.util.List;


/**
 * <p>
 * 规则动作定义信息 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/actionInfo")
@Api(tags = "ActionInfoController", description = "动作库相关api描述", hidden = true)
public class ActionInfoController extends BaseController{
    @Autowired
    private ActionInfoService actionInfoService;

	@Autowired
	private SceneInfoService sceneInfoService;

    @GetMapping("/getByIds")
    @ApiOperation(value = "通过选中的id查询动作库")
    public Result<List<ActionInfoVo>> getByIds(String ids){
        List<ActionInfoVo> list = actionInfoService.findByIds(ids);
		return Result.success(list);

    }
	@GetMapping("/getByScene")
	@ApiOperation(value = "通过sceneId查询动作库")
	public Result<List<ActionInfoVo>> getByScene(Long sceneId) throws Exception{

		List<ActionInfoVo> list = actionInfoService.findActionVos(sceneId);
		return Result.success(list);

	}

	@GetMapping("/getAll")
	@ApiOperation(value = "通过sceneId查询动作库")
	public Result<List<ActionInfoVo>> getAll(Long businessId) throws Exception{
		List<ActionInfoVo> list = actionInfoService.findActionAllVos(businessId);
		return Result.success(list);

	}

    
    @GetMapping("page")
	@ApiOperation(value = "分页查询")
	public PageResult<List<ActionInfo>> page(String key, Long businessId, Integer page, Integer limit) {
		Wrapper<ActionInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(key)) {
			 wrapper.like("action_type",ActionEnum.findByName(key) == null ? key : ActionEnum.findByName(key).getCode() );
			 wrapper.or().like("action_name", key);
			 wrapper.or().like("action_desc", key);
			 wrapper.or().like("action_class", key);
		}
		if(businessId != null ){
			wrapper.eq("business_id",businessId);
		}
		Page<ActionInfo> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = actionInfoService.selectPage(pages, wrapper);
		return PageResult.success(pages.getRecords(), pages.getTotal());
	}
    
    @PostMapping("edit")
	@ApiOperation(value = "动作新增or修改")
	@Transactional()
	public Result<Integer> edit(ActionInfo actionInfo) {
		actionInfo.setCreTime(new Date());
		actionInfo.setIsEffect(1);
		actionInfo.setCreUserId(Long.parseLong(userInfoHelper.getUserId()));
		actionInfoService.insertOrUpdate(actionInfo);
		return Result.success(0);
	}
    
    @GetMapping("delete")
	@ApiOperation(value = "通过id删除信息")
	public Result<Integer> delete( Long id) {
    	actionInfoService.deleteById(id);
		return Result.success(0);
	}
    
    @GetMapping("getInfoById")
	@ApiOperation(value = "通过id查询详细信息")
	public Result<ActionInfo> getDateById(Long id) {
    	ActionInfo entityInfo = actionInfoService.selectById(id);
		return Result.success(entityInfo);
	}

}

