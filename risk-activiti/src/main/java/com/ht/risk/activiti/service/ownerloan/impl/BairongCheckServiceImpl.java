package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.BairongCheckService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

/**
 * 百融多次核查
 */
@Service("bairongCheckService")
public class BairongCheckServiceImpl implements BairongCheckService {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
