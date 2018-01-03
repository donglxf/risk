package com.ht.risk.rule.controller;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.service.ActionInfoService;
import com.ht.risk.rule.vo.ActionInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


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
public class ActionInfoController {
    @Autowired
    private ActionInfoService actionInfoService;

    @GetMapping("/getByIds")
    @ApiOperation(value = "通过选中的id查询动作库")
    public Result<List<ActionInfoVo>> getByIds(String ids){
      //  List<ActionInfo> sss = actionInfoService.selectList(null);
        List<ActionInfoVo> list = actionInfoService.findByIds(ids);
        return Result.success(list);

    }
    
    
    @GetMapping("page")
	@ApiOperation(value = "分页查询")
	public PageResult<List<ActionInfo>> page(String key, Integer page, Integer limit) {
		Wrapper<ActionInfo> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(key)) {
			 wrapper.like("action_type",ActionEnum.findByName(key) == null ? key : ActionEnum.findByName(key).getCode() );
			 wrapper.or().like("action_name", key);
			 wrapper.or().like("action_desc", key);
			 wrapper.or().like("action_class", key);
		}
		Page<ActionInfo> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = actionInfoService.selectPage(pages, wrapper);
		return PageResult.success(pages.getRecords(), pages.getTotal());
	}
    
    enum ActionEnum{
    	
    	realize("1","实现"),self("2","自身");
    	
    	private String code;
    	private String name;
    	
    	ActionEnum(String code,String name){
    		this.code=code;
    		this.name=name;
    	}
    	
    	public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public static ActionEnum findByName(String key){
    		ActionEnum[] list=ActionEnum.values();
    		for (ActionEnum actionEnum : list) {
				if(key.equals(actionEnum.getName())){
					return actionEnum ;
				}
			}
    		return null;
    	}
    	
    }
    
    @PostMapping("edit")
	@ApiOperation(value = "动作新增or修改")
	@Transactional()
	public Result<Integer> edit(ActionInfo actionInfo) {
		actionInfo.setCreTime(new Date());
		actionInfo.setIsEffect(1);
		actionInfo.setCreUserId(new Long(1));
		actionInfoService.insertOrUpdate(actionInfo);
		return Result.success(0);
	}
    
    @GetMapping("delete/{id}")
	@ApiOperation(value = "通过id删除信息")
	public Result<Integer> delete(@PathVariable(name = "id") Long id) {
    	actionInfoService.deleteById(id);
		return Result.success(0);
	}
    
    @GetMapping("getInfoById/{id}")
	@ApiOperation(value = "通过id查询详细信息")
	public Result<ActionInfo> getDateById(@PathVariable(name = "id") Long id) {
    	ActionInfo entityInfo = actionInfoService.selectById(id);
		return Result.success(entityInfo);
	}
}

