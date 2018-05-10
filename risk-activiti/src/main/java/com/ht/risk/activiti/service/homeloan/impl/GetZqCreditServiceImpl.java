package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.service.homeloan.GetZqCreditService;
import com.ht.risk.activiti.service.homeloan.QxbValidService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

@Service("getZqCredit")
@Log4j2
public class GetZqCreditServiceImpl implements GetZqCreditService {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("flag","0");
    }
}
