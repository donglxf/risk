package com.ht.risk.activiti.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.common.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
public interface RiskValidateBatchService extends BaseService<RiskValidateBatch> {

    public Page<RiskValidateBatch> queryBatchForPage(VerficationModelVo verficationModelVo);

}
