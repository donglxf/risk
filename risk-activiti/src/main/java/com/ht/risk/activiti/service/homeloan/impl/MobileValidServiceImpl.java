package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.homeloan.AuroraLBSService;
import com.ht.risk.activiti.service.homeloan.MobileValidService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mobileValid")
@Log4j2
public class MobileValidServiceImpl implements MobileValidService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            delegateExecution.setVariable("flag","0");
        }catch (Exception e){
            e.printStackTrace();
            log.error("MobileValidServiceImpl 执行异常",e.getMessage());
        }
    }
}
