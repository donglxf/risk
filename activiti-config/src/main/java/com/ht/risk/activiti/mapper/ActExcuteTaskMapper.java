package com.ht.risk.activiti.mapper;

import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
public interface ActExcuteTaskMapper extends SuperMapper<ActExcuteTask> {
    /**
     * 计算规则平均耗时
     * @return
     */
    List<Map<String,Object>> getModelAvgTime(Map<String,Object> obj);

    /**
     * 获取规则执行信息
     * @return
     */
    Map<String,Object> getModelExecInfo(Map<String,Object> obj);
}
