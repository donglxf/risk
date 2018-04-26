package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.service.homeloan.CreditAuditService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service("creditAudit")
public class CreditAuditServiceImpl implements CreditAuditService {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
