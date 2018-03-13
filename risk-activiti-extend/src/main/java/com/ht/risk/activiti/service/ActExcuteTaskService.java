package com.ht.risk.activiti.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.vo.ActExcuteTaskVo;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
public interface ActExcuteTaskService extends BaseService<ActExcuteTask> {

    public List<RpcActExcuteTaskInfo> queryProcInstIdBybatchId(Long batchId);

    public RpcActExcuteTaskInfo convertRpcActExcuteTask(ActExcuteTask task);

    Map<String,Object> getModelGraph(Map<String,Object> map);

    public Page<ActExcuteTaskVo> verficationTaskPage(Page<ActExcuteTaskVo> page, VerficationModelVo verficationModelVo);
}
