package com.ht.risk.activiti.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.api.comment.EntstatusEnum;
import com.ht.risk.api.comment.WLInterfaceReturnStatusEnum;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailRespDtoOut;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.common.util.ProvinceUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("hourseRuleDataGain")
public class HourseRuleDataMachinImpl implements HourseRuleDataGain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataMachinImpl.class);

    @Autowired
    private EipServiceInterface eipServiceInterface;

    /**
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.error("HourseRuleDataMachinImpl execute method excute start...");
        Map map = (Map) execution.getVariable("variableMap");
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap();
        }
        String age = (String) map.get("borrowerInfo_borrowerAge"); // 借款人年龄
        String method = (String) map.get(""); // 借款人借款期限
        map.put("borrowerInfo_borrowerAgePlusLoanterm", ProvinceUtil.getborrowAge(Integer.parseInt(age), Integer.parseInt(method)));
        // 万达接口调用
        WDEnterpriseDetailReqDto wdReq = new WDEnterpriseDetailReqDto();
        wdReq.setRegicode("广东鸿特信息咨询有限公司");
        wdReq.setKeyType("2");
        Result<WDEnterpriseDetailRespDtoOut> result = eipServiceInterface.getZhengxinWanda(wdReq);
        JSONObject str = JSONObject.parseObject(JSON.toJSONString(result));
        if (WLInterfaceReturnStatusEnum.success.getValue().equals(str.getString("returnCode"))) { // 执行成功
            String entstatus = str.getJSONObject("data").getJSONObject("basic").getString("entstatus");
            if (EntstatusEnum.closeUp.getName().equals(entstatus) || EntstatusEnum.liquidate.getName().equals(entstatus)
                    || EntstatusEnum.outOfBusiness.getName().equals(entstatus)) {
                map.put("borrowerInfo_borrowerCompanyStatus", entstatus);
            }
        }
        //

        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }
}
