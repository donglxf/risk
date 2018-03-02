package com.ht.risk.activiti.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.ActProcReleaseVo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ActExcuteTaskService actExcuteTaskService;

    @GetMapping("list")
    @ApiOperation(value = "查询模型验证信息表")
    public Result<List<ActProcReleaseVo>> list(Page<ActProcRelease> pagination, Integer page, Integer limit, ActProcRelease actProcRelease) {
        logger.info("查询模型验证信息表");
        pagination.setSize(limit);
        pagination.setCurrent(page);
        EntityWrapper<ActProcRelease> ew = new EntityWrapper<>();
        ew.setEntity(actProcRelease);
        ew.orderBy("create_time",false);
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
    @ApiOperation(value = "发布、启用、审批通过、审批不通过、停用模型")
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
    @RequestMapping(value = "/verficationPass")
    @ApiOperation(value = "模型验证通过")
    public Result verficationPass(ActProcRelease actProcRelease){
        Result<Object> result = new Result<>();
        if(actProcRelease == null || actProcRelease.getId() == null || StringUtils.isEmpty(actProcRelease.getTaskId())){
            result = Result.error(1,"参数异常！");
            return result;
        }
        // TODO 1 校验验证状态是否成功
        Long taskId = Long.parseLong(actProcRelease.getTaskId());
        ActExcuteTask task = actExcuteTaskService.selectById(taskId);
        if(task != null && ActivitiConstants.PROC_STATUS_SUCCESS.equals(task.getStatus())){
            ActProcRelease release = actProcReleaseService.selectById(actProcRelease);
            if(!"0".equals(release.getIsApprove())){
                result = Result.error(2,"该版本已提交审批,不能更新验证状态");
                return result;
            }
            boolean flag = actProcReleaseService.updateById(actProcRelease);
            if(flag){
                result = Result.success("更新成功！");
            }else{
                result = Result.error(1,"更新失败！");
            }
        }else{
            result = Result.error(2,"模型验证执行未成功");
        }
        return result;
    }

    @RequestMapping(value = "/verficationUnPass")
    @ApiOperation(value = "模型验证不通过")
    public Result verficationUnPass(ActProcRelease actProcRelease){
        Result<Object> result = new Result<>();
        if(actProcRelease == null || actProcRelease.getId() == null ){
            result = Result.error(1,"参数异常！");
            return result;
        }
        ActProcRelease release = actProcReleaseService.selectById(actProcRelease);
        if(!"0".equals(release.getIsApprove())){
            result = Result.error(2,"该版本已提交审批,不能更新验证状态");
            return result;
        }
        // 已验证通过
        if("1".equals(release.getIsValidate())){
            result = Result.error(2,"该版本已验证通过");
            return result;
        }
        boolean flag = actProcReleaseService.updateById(actProcRelease);
        if(flag){
            result = Result.success("更新成功！");
        }else{
            result = Result.error(1,"更新失败！");
        }
        return result;
    }

    @RequestMapping(value = "/approval")
    @ApiOperation(value = "模型提交审批")
    public Result approval(ActProcRelease actProcRelease){
        Result<Object> result = new Result<>();
        if(actProcRelease == null || actProcRelease.getId() == null || StringUtils.isEmpty(actProcRelease.getTaskId())){
            result = Result.error(1,"参数异常！");
            return result;
        }
        Long taskId = Long.parseLong(actProcRelease.getTaskId());
        ActExcuteTask task = actExcuteTaskService.selectById(taskId);
        if(task == null || !ActivitiConstants.PROC_STATUS_SUCCESS.equals(task.getStatus())){
            result = Result.error(1,"该版本模型执行异常，无法提交审批！");
            return result;
        }
        ActProcRelease release = actProcReleaseService.selectById(actProcRelease);
        // 已验证通过
        if("3".equals(release.getIsValidate()) || !"0".equals(release.getIsApprove())){
            result = Result.error(2,"该版本已提交审批");
            return result;
        }
        if("2".equals(release.getIsValidate())){
            result = Result.error(2,"该版本验证不通过，无法提交审批");
            return result;
        }
        actProcRelease.setApproveTaskId(String.valueOf(taskId));
        boolean flag = actProcReleaseService.updateById(actProcRelease);
        if(flag){
            result = Result.success("更新成功！");
        }else{
            result = Result.error(1,"更新失败！");
        }
        return result;
    }

    @RequestMapping(value = "/update")
    @ApiOperation(value = "模型版本信息更新")
    public Result updateProcReleaseInfo(ActProcRelease actProcRelease){
        Result<Object> result = new Result<>();
        ActProcRelease release = actProcReleaseService.selectById(actProcRelease);
        boolean flag = actProcReleaseService.updateById(actProcRelease);
        if(flag){
            result = Result.success("更新成功！");
        }else{
            result = Result.error(1,"更新失败！");
        }
        return result;
    }
}

