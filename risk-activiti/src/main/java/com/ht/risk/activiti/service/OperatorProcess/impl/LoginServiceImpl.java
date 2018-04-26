package com.ht.risk.activiti.service.OperatorProcess.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.OperatorProcess.LoginService;
import com.ht.risk.activiti.service.OperatorProcess.ValidCodeService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.sp.SpLoginDtoIn;
import com.ht.risk.api.model.eip.sp.SpLoginDtoOut;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("loginService")
@Log4j2
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("HomeLoanRuleDataMachinImpl execute method excute start...");
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
        // 数据验证
        msg.append(dataValid(dataMap));
        callInterface(dataMap, execution);
    }

    public void callInterface(Map dataMap, DelegateExecution execution) {
        try {
            SpLoginDtoIn in = new SpLoginDtoIn();
            in.setCurrentTime((String)dataMap.get("timestamp"));
            in.setMobilePhone(String.valueOf(dataMap.get("mobilePhone")));
            in.setPassword(String.valueOf(dataMap.get("passWord")));
            in.setUserId(String.valueOf(dataMap.get("userId")));
            Result<SpLoginDtoOut> result = eipServiceInterface.login(in);
            if(null != result && "0000".equals(result.getReturnCode())){
                SpLoginDtoOut out=result.getData();
                if("getImg".equals(out.getImgUrl())){
                    execution.setVariable("flag","2");
                }
                if(out.isSms()){
                    execution.setVariable("flag","0");
                }
            }else{
                execution.setVariable("flag","1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("接口异常+++++", e.getMessage());
        }
    }

    public StringBuffer dataValid(Map dataMap) {
        StringBuffer msg = new StringBuffer("");
        if(null == dataMap.get("passWord")){
            msg.append("密码不能为空");
        }
        return msg;
    }
}
