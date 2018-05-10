package com.ht.risk.activiti.service.htapp.impl;

import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.htapp.HtAppDataMachin;
import com.ht.risk.activiti.service.htapp.ResultProService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.common.result.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("htAppResultPro")
@Log4j2
public class ResultProServiceImpl implements ResultProService {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("ResultProServiceImpl execute method excute start...");
        StringBuffer msg = new StringBuffer("");
        Object dataObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        execution.setVariable(ActivitiConstants.PROC_START_CURRENT_TIME, System.currentTimeMillis());
        Map dataMap = null;
        if (dataObj == null) {
            dataMap = new HashMap();
            msg.append("模型所需数据为空;");
        } else {
            dataMap = (Map) dataObj;
        }

    }
}
