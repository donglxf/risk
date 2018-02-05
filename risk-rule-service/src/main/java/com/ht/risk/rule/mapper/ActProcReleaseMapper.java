package com.ht.risk.rule.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.ActProcRelease;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dyb
 * @since 2018-01-17
 */
public interface ActProcReleaseMapper extends SuperMapper<ActProcRelease> {
	
    List<ActProcRelease> findModelTaskList(Pagination page,@Param("ew") Wrapper<ActProcRelease> wrapper);

}
