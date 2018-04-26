package com.ht.risk.activiti.service.OperatorProcess.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.OperatorProcess.UploadBaseInfo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.sp.UserBaseInfoDtoIn;
import com.ht.risk.api.model.eip.sp.UserBaseInfoDtoOut;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("uploadBaseInfo")
@Log4j2
public class UploadBaseInfoImpl implements UploadBaseInfo {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("UploadBaseInfoImpl execute method excute start...");
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
        if("".equals(msg.toString())){
            execution.setVariable("flag","1");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_ERROR_MSG,msg);
            return ;
        }
        // 接口调用
        callInteface(dataMap,execution);

    }

    public void callInteface(Map dataMap,DelegateExecution execution){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        UserBaseInfoDtoIn in = new UserBaseInfoDtoIn();
        try {
            String timestamp=String.valueOf(sim.parse(sim.format(new Date())).getTime());
            execution.setVariable("timestamp",timestamp);
            in.setCurrentTime(timestamp);
            in.setMobilePhone(String.valueOf(dataMap.get("mobilePhone"))); // 手机号 ，传入
            in.setRealName(String.valueOf(dataMap.get("realName"))); // 真实姓名 ，传入
            in.setUserId(String.valueOf(dataMap.get("userId"))); // userid ，传入
            Result<UserBaseInfoDtoOut> result = eipServiceInterface.userBaseInfo(in);
            if(null != result && "0000".equals(result.getReturnCode())){
                UserBaseInfoDtoOut out=result.getData();
                if("1".equals(out.getStatus())){
                    execution.setVariable("flag","0");
                }else{
                    execution.setVariable("flag","1");
                }
            }else{
                execution.setVariable("flag","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("用户信息入库接口调用异常+++++",e.getMessage());
        }
    }

    public StringBuffer dataValid(Map dataMap) {
        StringBuffer msg = new StringBuffer("");
        if(null == dataMap.get("realName")){
            msg.append("真实姓名不能为空");
        }if(null == dataMap.get("mobilePhone")){
            msg.append("手机号码不能为空");
        }if(null == dataMap.get("userId")){
            msg.append("userId不能为空");
        }
        return msg;
    }
}
