package com.ht.risk.activiti.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
@Service
public class ActExcuteTaskServiceImpl extends BaseServiceImpl<ActExcuteTaskMapper, ActExcuteTask> implements ActExcuteTaskService {

    @Resource
    private ActExcuteTaskMapper actExcuteTaskMapper;

    @Override
    public List<RpcActExcuteTaskInfo> queryProcInstIdBybatchId(Long batchId) {
        EntityWrapper<ActExcuteTask> wrapper = new EntityWrapper<ActExcuteTask>();
        ActExcuteTask task = new ActExcuteTask();
        task.setBatchId(batchId);
        wrapper.setEntity(task);
        List<ActExcuteTask> list = actExcuteTaskMapper.selectList(wrapper);
        List<RpcActExcuteTaskInfo> rpcTasks = new ArrayList<RpcActExcuteTaskInfo>();
        RpcActExcuteTaskInfo rpcTask;
        for(Iterator<ActExcuteTask> iterator = list.iterator();iterator.hasNext();){
            rpcTask = convertRpcActExcuteTask(iterator.next());
            rpcTasks.add(rpcTask);
        }
        return rpcTasks;
    }

    public RpcActExcuteTaskInfo convertRpcActExcuteTask(ActExcuteTask task){
        RpcActExcuteTaskInfo rpcTask = new RpcActExcuteTaskInfo();
        rpcTask.setBatchId(task.getBatchId());
        rpcTask.setId(task.getId());
        rpcTask.setInParamter(task.getInParamter());
        rpcTask.setOutParamter(task.getOutParamter());
        rpcTask.setProcInstId(task.getProcInstId());
        rpcTask.setProcReleaseId(task.getProcReleaseId());
        rpcTask.setRemark(task.getRemark());
        rpcTask.setSpendTime(task.getSpendTime());
        rpcTask.setStatus(task.getStatus());
        return rpcTask;
    }
}
