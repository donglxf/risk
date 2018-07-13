package com.ht.risk.activiti.service.insys.impl;

import com.ht.risk.activiti.service.insys.InPersonMachin;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.common.comenum.*;
import com.ht.risk.common.util.ObjectUtils;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("inPersonMachin")
@Log4j2
public class InPersonMachinServiceImpl implements InPersonMachin {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("InPersonMachinServiceImpl start==========>>>>>>>>>>");
        StringBuffer msg = new StringBuffer("");
        ;
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

        forbit(dataMap, execution); // 禁入数据加工
        inpersonedu(dataMap, execution); // 个人授信数据加工
        inpersonother(dataMap, execution); // 个人授信其他信息  数据加工
    }

    private void inpersonother(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> forbit = (Map<String, Object>) dataMap.get("inpersonother");
        if (ObjectUtils.isEmpty(forbit)) {
            return;
        }
        String inporganizationtype = (String) forbit.get("inpersoncdt_inporganizationtype"); // 组织形式
        if (OrganizationalFormEnum.partnership.getName().equals(inporganizationtype)) {
            inporganizationtype = OrganizationalFormEnum.partnership.getCode();
        } else if (OrganizationalFormEnum.soleproprietorship.getName().equals(inporganizationtype)) {
            inporganizationtype = OrganizationalFormEnum.soleproprietorship.getCode();
        }
        forbit.put("inpersoncdt_inporganizationtype", inporganizationtype);

        String unittype = (String) forbit.get("inpersoncdt_unittype"); // 单位性质
        if (UnitTypeEnum.corporateEnterprise.getName().equals(unittype)) {
            unittype = UnitTypeEnum.corporateEnterprise.getCode();
        } else if (UnitTypeEnum.freework.getName().equals(unittype)) {
            unittype = UnitTypeEnum.freework.getCode();
        } else if (UnitTypeEnum.governmentEnterprise.getName().equals(unittype)) {
            unittype = UnitTypeEnum.governmentEnterprise.getCode();
        } else if (UnitTypeEnum.individual.getName().equals(unittype)) {
            unittype = UnitTypeEnum.individual.getCode();
        } else if (UnitTypeEnum.institutions.getName().equals(unittype)) {
            unittype = UnitTypeEnum.institutions.getCode();
        } else if (UnitTypeEnum.organization.getName().equals(unittype)) {
            unittype = UnitTypeEnum.organization.getCode();
        } else if (UnitTypeEnum.szenterprise.getName().equals(unittype)) {
            unittype = UnitTypeEnum.szenterprise.getCode();
        } else if (UnitTypeEnum.unitother.getName().equals(unittype)) {
            unittype = UnitTypeEnum.unitother.getCode();
        }
        forbit.put("inpersoncdt_unittype", unittype);

        String unitwork = (String) forbit.get("inpersoncdt_unitwork"); // 单位职务
        if (UntiPositionEnum.deploymentManager.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.deploymentManager.getCode();
        } else if (UntiPositionEnum.deploymentzg.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.deploymentzg.getCode();
        } else if (UntiPositionEnum.legalperson.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.legalperson.getCode();
        } else if (UntiPositionEnum.positionother.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.positionother.getCode();
        } else if (UntiPositionEnum.stockholders.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.stockholders.getCode();
        } else if (UntiPositionEnum.worker.getName().equals(unitwork)) {
            unitwork = UntiPositionEnum.worker.getCode();
        }
        forbit.put("inpersoncdt_unitwork", unitwork);


        listDate.add(forbit);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "inpersonother", listDate);
    }

    private void forbit(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> forbit = (Map<String, Object>) dataMap.get("forbit");
        if (ObjectUtils.isEmpty(forbit)) {
            return;
        }
        listDate.add(forbit);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "inpersonzr", listDate);
    }

    private void inpersonedu(Map dataMap, DelegateExecution execution) {
        List<Map<String, Object>> listDate = new ArrayList<>();
        Map<String, Object> forbit = (Map<String, Object>) dataMap.get("inpersonedu");
        if (ObjectUtils.isEmpty(forbit)) {
            return;
        }
        String reValue = null;
        String residence = (String) forbit.get("inpersoncdt_residence"); // 住宅类型
        if (HouseTYpeEnum.apartment.getName().equals(residence)) {
            reValue = HouseTYpeEnum.apartment.getCode();
        } else if (HouseTYpeEnum.commodityhouse.getName().equals(residence)) {
            reValue = HouseTYpeEnum.commodityhouse.getCode();
        } else if (HouseTYpeEnum.officeBuilding.getName().equals(residence)) {
            reValue = HouseTYpeEnum.officeBuilding.getCode();
        } else if (HouseTYpeEnum.villa.getName().equals(residence)) {
            reValue = HouseTYpeEnum.villa.getCode();
        } else if (HouseTYpeEnum.other.getName().equals(residence)) {
            reValue = HouseTYpeEnum.other.getCode();
        }
        forbit.put("inpersoncdt_residence", reValue);

        String sex = (String) forbit.get("inpersoncdt_inpsex");// 性别
        if (SexEnum.human.getName().equals(sex)) {
            forbit.put("inpersoncdt_inpsex", SexEnum.human.getCode());
        } else if (SexEnum.women.getName().equals(sex)) {
            forbit.put("inpersoncdt_inpsex", SexEnum.women.getCode());
        }

        String merry = (String) forbit.get("inpersoncdt_inpmarry"); // 婚姻
        if (MerryEnum.married.getName().equals(merry) || MerryEnum.remarry.getName().equals(merry)) {
            forbit.put("hongteappedu_htmerry", MerryEnum.married.getCode());
        }


        String inpeducation = (String) forbit.get("inpersoncdt_inpeducation"); //学历
        if (EducationGradeEnum.university.getName().equals(inpeducation)) {
            reValue = EducationGradeEnum.university.getCode();
        } else if (EducationGradeEnum.graduate.getName().equals(inpeducation)) {
            reValue = EducationGradeEnum.graduate.getCode();
        } else if (EducationGradeEnum.junior.getName().equals(inpeducation)) {
            reValue = EducationGradeEnum.junior.getCode();
        } else if (EducationGradeEnum.underdz.getName().equals(inpeducation)) {
            reValue = EducationGradeEnum.underdz.getCode();
        } else if (EducationGradeEnum.doctor.getName().equals(inpeducation)) {
            reValue = EducationGradeEnum.doctor.getCode();
        }
        forbit.put("inpersoncdt_inpeducation", reValue);


        listDate.add(forbit);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "inpersonedu", listDate);
    }
}
