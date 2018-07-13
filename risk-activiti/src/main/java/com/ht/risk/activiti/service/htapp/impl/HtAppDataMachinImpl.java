package com.ht.risk.activiti.service.htapp.impl;

import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.htapp.HtAppDataMachin;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.common.comenum.CityTypeEnum;
import com.ht.risk.common.comenum.HouseTYpeEnum;
import com.ht.risk.common.comenum.MerryEnum;
import com.ht.risk.common.comenum.SexEnum;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.ObjectUtils;
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

        execution.setVariable(ActivitiConstants.PROC_BUSINESS_KEY, dataMap.get("businessId"));
        forbit(dataMap, execution);
        dataMachin(dataMap, execution);
        houseDataMachIn(dataMap, execution);
        carDataMachIn(dataMap, execution);
    }

    private void forbit(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> foribt = (Map<String, Object>) dataMap.get("borrowerInfo"); // 准入
        if (ObjectUtils.isEmpty(foribt)) {
            return;
        }
        listDate.add(foribt);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "hongteapp", listDate);
    }

    /**
     * 房产数据处理
     *
     * @param dataMap
     * @param execution
     */
    public void houseDataMachIn(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> ListMap = (List<Map<String, Object>>) dataMap.get("houseInfo"); // 评分卡
        if (ObjectUtils.isEmpty(ListMap)) {
            return;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ListMap.forEach(map -> {
            String housetype = (String) map.get("hongteappedu_hthousetype");
            if (HouseTYpeEnum.commodityhouse.getName().equals(housetype)) {
                housetype = "commodityhouse";
            } else if (HouseTYpeEnum.villa.getName().equals(housetype)) {
                housetype = "Villa";
            } else if (HouseTYpeEnum.officeBuilding.getName().equals(housetype)) {
                housetype = "officeBuilding";
            } else if (HouseTYpeEnum.apartment.getName().equals(housetype)) {
                housetype = "apartment";
            } else if (HouseTYpeEnum.shop.getName().equals(housetype)) {
                housetype = "shop";
            } else if (HouseTYpeEnum.other.getName().equals(housetype)) {
                housetype = "other";
            }
            map.put("hongteappedu_hthousetype", housetype);

            Result<String> str = ruleServiceInterface.getCityTypeByCityName(String.valueOf(map.get("hongteappedu_hthouseCityType")));
            String type = str.getData();
            if (CityTypeEnum.cityTypeA.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeA.getName();
            } else if (CityTypeEnum.cityTypeB.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeB.getName();
            } else if (CityTypeEnum.cityTypeC.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeC.getName();
            }
            map.put("hongteappedu_hthouseCityType", type);

            list.add(map);

        });
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "htapphouseInfo", list);


    }

    /**
     * 车辆数据处理
     *
     * @param dataMap
     * @param execution
     */
    public void carDataMachIn(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) dataMap.get("carInfo"); // 评分卡
        if (ObjectUtils.isEmpty(listMap)) {
            return;
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        listMap.forEach(map -> {
//            Double driverflow = (Double) map.get("hongteappedu_driverflow"); // 行驶里程
//            if (driverflow < 1) {
//                map.put("hongteappedu_driverflow", 1);
//            }


            Result<String> str = ruleServiceInterface.getCityTypeByCityName(String.valueOf(map.get("hongteappedu_carcity")));
            String type = str.getData();
            if (CityTypeEnum.cityTypeA.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeA.getName();
            } else if (CityTypeEnum.cityTypeB.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeB.getName();
            } else if (CityTypeEnum.cityTypeC.getCode().equals(type)) {
                type = CityTypeEnum.cityTypeC.getName();
            }
            map.put("hongteappedu_carcity", type);
            list.add(map);

        });
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "htappCarInfo", list);

    }

    // 数据处理
    private void dataMachin(Map dataMap, DelegateExecution execution) {


        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> map = (Map<String, Object>) dataMap.get("htappcredit"); // 评分卡
        if (ObjectUtils.isEmpty(map)) {
            return;
        }
        String sex = (String) map.get("hongteappedu_htsex");
        Double workyear = Double.parseDouble(String.valueOf(map.get("hongteappedu_workyear")));
        String education = (String) map.get("hongteappedu_hteducation");

        String merry = (String) map.get("hongteappedu_htmerry");
        if (SexEnum.women.getName().equals(sex)) {
            map.put("hongteappedu_htsex", SexEnum.women.getCode());
        } else {
            map.put("hongteappedu_htsex", SexEnum.human.getCode());
        }

        if (workyear <= 1) {
            map.put("hongteappedu_workyear", 1);
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


        if (MerryEnum.married.getName().equals(merry) || MerryEnum.remarry.getName().equals(merry)) {
            map.put("hongteappedu_htmerry", MerryEnum.married.getCode());
        }


        listDate.add(map);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "htappscore", listDate);
    }
}
