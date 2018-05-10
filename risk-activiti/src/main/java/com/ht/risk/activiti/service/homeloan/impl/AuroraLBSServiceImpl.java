package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.service.homeloan.AuroraLBSService;
import com.ht.risk.activiti.service.homeloan.BackValidService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("AuroraLBSValid")
public class AuroraLBSServiceImpl implements AuroraLBSService {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<Map<String,Object>> dataMap=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("ownerLoanBorrower_ownerLoanBorrowerAge","10");
        map.put("ownerLoanBorrower_ownerLoanAccountProvince","海外");
        dataMap.add(map);
        Map<String,Object> map1=new HashMap<>();
        map1.put("ownerLoanBorrower_ownerLoanBorrowerAge","20");
        map1.put("ownerLoanBorrower_ownerLoanAccountProvince","香港");
        dataMap.add(map1);
        Map<String,Object> map2=new HashMap<>();
        map2.put("ownerLoanBorrower_ownerLoanBorrowerAge","50");
        map2.put("ownerLoanBorrower_ownerLoanAccountProvince","澳门");
        dataMap.add(map2);
        Map<String,Object> map3=new HashMap<>();
        map3.put("ownerLoanBorrower_ownerLoanBorrowerAge","70");
        map3.put("ownerLoanBorrower_ownerLoanAccountProvince","北京");
        dataMap.add(map3);
        Map<String,Object> map4=new HashMap<>();
        map4.put("ownerLoanBorrower_ownerLoanBorrowerAge","10");
        map4.put("ownerLoanBorrower_ownerLoanAccountProvince","台湾");
        dataMap.add(map4);
        delegateExecution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"ownerLoanAdmittance",dataMap);

        delegateExecution.setVariable("flag","0");
    }
}
