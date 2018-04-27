package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.homeloan.GetBlackResultService;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取黑名单执行结果
 * 根据执行结果判断是否需要封装后续接口调用所需数据
 */
@Service("getBlackResultService")
@Log4j2
public class GetBlackResultServiceImpl implements GetBlackResultService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("GetBlackResultServiceImpl execute method excute start...");

    }
}
