package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.ModelValidateBean;
import com.ht.risk.rule.entity.ValidateBatch;
import com.ht.risk.rule.mapper.ValidateBatchMapper;
import com.ht.risk.rule.service.ValidateBatchService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.bouncycastle.util.Arrays;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@Service("validateBatchService")
public class ValidateBatchServiceImpl extends BaseServiceImpl<ValidateBatchMapper, ValidateBatch> implements ValidateBatchService {

    @Override
    public List<ModelValidateBean> analysisBatchJobs(ValidateBatch validateBatch){
        int beachSize = validateBatch.getBatchSize();
        List<ModelValidateBean> beans = new ArrayList<ModelValidateBean>(beachSize);
        ModelValidateBean bean = null;
        for (int i = 0; i <beachSize ; i++) {
            bean = new ModelValidateBean();
            beans.add(bean);
        }
        return beans;
    }

}
