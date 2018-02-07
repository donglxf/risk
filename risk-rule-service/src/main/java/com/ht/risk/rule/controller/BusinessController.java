package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.Business;
import com.ht.risk.rule.service.BusinessService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangpeng
 * @since 2018-01-22
 */
@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<Business>> page(String key , Integer page , Integer limit){
        Wrapper<Business> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("business_name",key);
            wrapper.or().like("business_desc",key);
        }
        wrapper.orderBy("cre_time",false);
        Page<Business> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        pages = businessService.selectPage(pages,wrapper);
        return PageResult.success(pages.getRecords(),pages.getTotal());
    }

    @GetMapping("getAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Business>>  getAll(String key){
        Wrapper<Business> wrapper = new EntityWrapper<>();
        wrapper.orderBy("cre_time",false);
        List<Business> list = businessService.selectList(wrapper);
        return Result.success(list);
    }


    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit( @Validated Business business){
        business.setCreTime(new Date());
        business.setCreUserId("无");
        businessService.insertOrUpdate(business);
        return Result.success(0);
    }

    @GetMapping("getInfoById/{id}")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<Business> getDateById(@PathVariable(name = "id") Long id){
        Business business = businessService.selectById(id);
        return Result.success(business);
    }

    @GetMapping("delete/{id}")
    @ApiOperation(value = "通过id删除信息")
    public Result<Integer> delete(@PathVariable(name = "id") Long id){
        businessService.deleteById(id);
        return Result.success(0);
    }

}

