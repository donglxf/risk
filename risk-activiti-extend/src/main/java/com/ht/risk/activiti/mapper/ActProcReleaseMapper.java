package com.ht.risk.activiti.mapper;

import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-16
 */
public interface ActProcReleaseMapper extends SuperMapper<ActProcRelease> {

    public List<ActProcRelease> getModelLastedVersion(Map<String,Object> paramter);

}
