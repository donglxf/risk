package com.ht.risk.activiti.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.mapper.RiskValidateBatchMapper;
import com.ht.risk.activiti.service.RiskValidateBatchService;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-19
 */
@Service
public class RiskValidateBatchServiceImpl extends BaseServiceImpl<RiskValidateBatchMapper, RiskValidateBatch> implements RiskValidateBatchService {

    @Autowired
    private RiskValidateBatchMapper riskValidateBatchMapper;

    @Override
    public Page<RiskValidateBatch> queryBatchForPage(VerficationModelVo verficationModelVo) {
        Page<RiskValidateBatch> page = new Page<RiskValidateBatch>();
        page.setSize(verficationModelVo.getLimit());
        page.setCurrent(verficationModelVo.getPage());
        RiskValidateBatch batch = new RiskValidateBatch();
        batch.setModelName(verficationModelVo.getModelName());
        batch.setModelVersion(verficationModelVo.getModelVersion());
        batch.setProcReleaseId(verficationModelVo.getProcReleaseId());
        page.setRecords(riskValidateBatchMapper.queryValidateBatchList(page,batch));
        return page;
    }
}
