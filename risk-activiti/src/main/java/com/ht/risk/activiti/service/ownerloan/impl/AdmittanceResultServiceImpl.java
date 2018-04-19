package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.AdmittanceResultService;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 准入规则结果处理类，判断是否准入
 */
public class AdmittanceResultServiceImpl implements AdmittanceResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmittanceResultServiceImpl.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("AdmittanceResultServiceImpl start ...");
        LOGGER.info("AdmittanceResultServiceImpl end ...");
    }

}
