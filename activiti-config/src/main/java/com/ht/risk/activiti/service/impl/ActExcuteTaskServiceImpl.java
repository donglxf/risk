package com.ht.risk.activiti.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.mapper.ActProcReleaseMapper;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private ActProcReleaseMapper actProcReleaseMapper;

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
        rpcTask.setBatchId(String.valueOf(task.getBatchId()));
        rpcTask.setId(String.valueOf(task.getId()));
        rpcTask.setInParamter(task.getInParamter());
        rpcTask.setOutParamter(task.getOutParamter());
        rpcTask.setProcInstId(task.getProcInstId());
        rpcTask.setProcReleaseId(String.valueOf(task.getProcReleaseId()));
        rpcTask.setRemark(task.getRemark());
        rpcTask.setSpendTime(task.getSpendTime());
        rpcTask.setStatus(task.getStatus());
        return rpcTask;
    }

    @Override
    public Map<String, Object> getModelGraph(Map<String, Object> map) {
        List<Map<String,Object>> listMap=actExcuteTaskMapper.getModelAvgTime(map);
        List<ActProcRelease> ruleMap=new ArrayList<ActProcRelease>();
        for (Map<String,Object> ma:listMap){
            Map<String,Object> paramMap=new HashMap<String,Object>();
            paramMap.put("procReleaseId",ma.get("PROC_RELEASE_ID"));
            ActProcRelease list=actProcReleaseMapper.selectById(String.valueOf(ma.get("PROC_RELEASE_ID")));  //    getModelExecInfo(paramMap);
            ruleMap.add(list);
        }
        Map<String, Object> resultMap=new HashMap<String,Object>();
        resultMap.put("ruleMap",ruleMap);
        resultMap.put("listMap",listMap);
        return resultMap;
    }
}
