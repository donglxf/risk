package com.ht.risk.activiti.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.ActProcReleaseVo;
import com.ht.risk.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hzm
 * @since 2018-01-17
 */
@RestController
@RequestMapping("/actProcRelease")
@Api(tags = "actProcReleaseController", description = "模型查询", hidden = true)
public class ActProcReleaseController {
    private static Logger logger = LoggerFactory.getLogger(ActProcReleaseController.class);
    @Autowired
    private ActProcReleaseService actProcReleaseService;

    @GetMapping("list")
    @ApiOperation(value = "查询模型验证信息表")
    public Result<List<ActProcReleaseVo>> list(Page<ActProcRelease> pagination, Integer page, Integer limit, ActProcRelease actProcRelease) {
        logger.info("查询模型验证信息表");
        pagination.setSize(limit);
        pagination.setCurrent(page);
        EntityWrapper<ActProcRelease> ew = new EntityWrapper<>();
        if ("" != actProcRelease.getModelName() && actProcRelease.getModelName() != null) {
            //模糊查询
            ew.like("MODEL_NAME", "%" + actProcRelease.getModelName() + "%");
        }
        if ("" != actProcRelease.getIsValidate() && actProcRelease.getIsValidate() != null) {
            ew.eq("IS_VALIDATE", actProcRelease.getIsValidate());
        }
        if ("" != actProcRelease.getModelCategory() && actProcRelease.getModelCategory() != null) {
            ew.eq("MODEL_CATEGORY", actProcRelease.getModelCategory());
        }
        if ("" != actProcRelease.getIsEffect() && actProcRelease.getIsEffect() != null) {
            ew.eq("IS_EFFECT", actProcRelease.getIsEffect());
        }
        Page<ActProcRelease> pages = actProcReleaseService.selectPage(pagination, ew);
        List<ActProcRelease> list = pages.getRecords();
        List<ActProcReleaseVo> queryList = new ArrayList<ActProcReleaseVo>();
        for(Iterator<ActProcRelease> iterator = list.iterator();iterator.hasNext();){
            queryList.add(new ActProcReleaseVo(iterator.next()));
        }
        Result<List<ActProcReleaseVo>> result = Result.build(0, "查询成功", queryList, pages.getTotal());
        return result;
    }
    @RequestMapping(value = "status")
    @ApiOperation(value = "发布.启用或停用模型")
    public Result publishModelById(ActProcRelease actProcRelease) {
        Result<Object> result = new Result<>();
        boolean flag = actProcReleaseService.updateById(actProcRelease);
        if(flag){
            result = Result.success("更新成功！");
        }else{
            result = Result.error(1,"更新失败！");
        }
        return result;
    }

}
