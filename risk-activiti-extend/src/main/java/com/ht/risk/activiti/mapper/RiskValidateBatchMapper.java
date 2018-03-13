package com.ht.risk.activiti.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
public interface RiskValidateBatchMapper extends SuperMapper<RiskValidateBatch> {

    public List<RiskValidateBatch> queryValidateBatchList(Pagination page, RiskValidateBatch batch);

}
