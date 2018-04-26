package com.ht.risk.activiti.service.OperatorProcess.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.OperatorProcess.UploadBaseInfo;
import com.ht.risk.activiti.service.OperatorProcess.ValidCodeService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.sp.SpValidCodeDtoIn;
import com.ht.risk.api.model.eip.sp.SpValidCodeDtoOut;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("validCodeService")
@Log4j2
public class ValidCodeServiceImpl implements ValidCodeService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("ValidCodeServiceImpl execute method excute start...");
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
        String timestamp=(String)execution.getVariable("timestamp");
        try {
            SpValidCodeDtoIn input=new SpValidCodeDtoIn();
            input.setMobilePhone(String.valueOf(dataMap.get("mobilePhone")));
            input.setCurrentTime(timestamp);
            input.setUserId(String.valueOf(dataMap.get("userId")));
            Result<SpValidCodeDtoOut> validCode=eipServiceInterface.validCode(input);
            if(null != validCode && "0000".equals(validCode.getReturnCode())){
                SpValidCodeDtoOut out=validCode.getData();
                if("1".equals(out.getStatus())){
                    execution.setVariable("flag","0");
                }else{
                    execution.setVariable("flag","2");
                }
            }else{
                execution.setVariable("flag","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("校验验证码接口调用异常+++++",e.getMessage());
        }
    }

    public StringBuffer dataValid(Map dataMap) {
        StringBuffer msg = new StringBuffer("");

        return msg;
    }
}
