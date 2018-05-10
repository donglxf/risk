package com.ht.risk.activiti.service.htapp.impl;

import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.htapp.HtAppDataMachin;
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

@Service("htAppDataMachin")
@Log4j2
public class HtAppDataMachinImpl implements HtAppDataMachin {

    @Autowired
    RuleServiceInterface ruleServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("HtAppDataMachinImpl execute method excute start...");
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

        forbit(dataMap, execution);
        dataMachin(dataMap, execution);

    }

    private void forbit(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> foribt = (Map<String, Object>) dataMap.get("borrowerInfo"); // 准入
        listDate.add(foribt);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "hongteapp", listDate);
    }

    // 数据处理
    private void dataMachin(Map dataMap, DelegateExecution execution) {


        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> map = (Map<String, Object>) dataMap.get("htappcredit"); // 评分卡
        String sex = (String) map.get("hongteappedu_htsex");
        Double driverflow = (Double) map.get("hongteappedu_driverflow");
        String education = (String) map.get("hongteappedu_hteducation");
        String housetype = (String) map.get("hongteappedu_hthousetype");
        String merry = (String) map.get("hongteappedu_htmerry");
        if ("女".equals(sex)) {
            map.put("hongteappedu_htsex", "women");
        } else {
            map.put("hongteappedu_htsex", "humen");
        }
        if (driverflow < 1) {
            map.put("hongteappedu_driverflow", 1);
        }
        if ("大专".equals(education)) {
            education = "junior";
        } else if ("本科".equals(education)) {
            education = "university";
        } else if ("硕士".equals(education)) {
            education = "graduate";
        } else if ("博士及以上".equals(education)) {
            education = "doctor";
        }
        map.put("hongteappedu_hteducation", education);


        if ("商品房".equals(housetype)) {
            housetype = "commodityhouse";
        } else if ("别墅".equals(housetype)) {
            housetype = "Villa";
        } else if ("写字楼".equals(housetype)) {
            housetype = "officeBuilding";
        } else if ("公寓".equals(housetype)) {
            housetype = "apartment";
        } else if ("商铺".equals(housetype)) {
            housetype = "shop";
        } else if ("其他".equals(housetype)) {
            housetype = "other";
        }
        map.put("hongteappedu_hthousetype", housetype);

        if ("已婚".equals(merry) || "再婚".equals(merry)) {
            map.put("hongteappedu_htmerry", "married");
        }

        Result<String> str = ruleServiceInterface.getCityTypeByCityName(String.valueOf(map.get("hongteappedu_hthouseCityType")));
        String type = str.getData();
        if ("1".equals(type)) {
            type = "cityTypeA";
        } else if ("2".equals(type)) {
            type = "cityTypeB";
        } else if ("3".equals(type)) {
            type = "cityTypeC";
        }
        map.put("hongteappedu_hthouseCityType", type);

        listDate.add(map);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "htappscore", listDate);
    }
}
