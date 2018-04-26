package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.service.homeloan.BackValidService;
import com.ht.risk.activiti.service.homeloan.HomeLoanRuleDataMachin;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service("HomeLoanBackValid")
public class BackValidServiceImpl implements BackValidService {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
