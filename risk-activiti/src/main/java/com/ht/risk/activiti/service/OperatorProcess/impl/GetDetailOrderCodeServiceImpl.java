package com.ht.risk.activiti.service.OperatorProcess.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.OperatorProcess.GetDetailOrderCodeService;
import com.ht.risk.activiti.service.OperatorProcess.ReqDynamicCodeService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.sp.SpDetailOrderCodeDtoOut;
import com.ht.risk.api.model.eip.sp.SpValidCodeDtoIn;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("getDetailOrderCode")
@Log4j2
public class GetDetailOrderCodeServiceImpl implements GetDetailOrderCodeService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("GetDetailOrderCodeServiceImpl execute method excute start...");
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
//        msg.append(dataValid(dataMap));
        callInterface(dataMap,execution);
    }

    public void callInterface(Map dataMap,DelegateExecution execution){
        try {
            SpValidCodeDtoIn in =new SpValidCodeDtoIn();
            in.setUserId(String.valueOf(dataMap.get("userId")));
            in.setCurrentTime(String.valueOf(dataMap.get("timestamp")));
            in.setMobilePhone(String.valueOf(dataMap.get("mobilePhone")));
            Result<SpDetailOrderCodeDtoOut> result=eipServiceInterface.getDetailOrderCode(in);
            if(null != result && "0000".equals(result.getReturnCode())){

            }else{
                execution.setVariable("flag","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取详单验证码接口异常+++++",e.getMessage());
        }
    }


    public StringBuffer dataValid(Map dataMap) {
        StringBuffer msg = new StringBuffer("");

        return msg;
    }
}
