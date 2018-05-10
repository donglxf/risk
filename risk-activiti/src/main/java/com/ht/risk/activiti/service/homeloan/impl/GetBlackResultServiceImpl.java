package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.homeloan.GetBlackResultService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
import com.ht.risk.common.util.ObjectUtils;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ht.risk.api.constant.activiti.ActivitiConstants.PROC_HOME_LOAN_RESULT_CODE;

/**
 * 获取黑名单执行结果
 * 根据执行结果判断是否需要封装后续接口调用所需数据
 */
@Service("getBlackResultService")
@Log4j2
public class GetBlackResultServiceImpl implements GetBlackResultService {

    private Expression senceCodeExp;

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("GetBlackResultServiceImpl execute method excute start...");
        boolean bool = false;
        String senceCode = String.valueOf(senceCodeExp.getValue(execution));
        Map<String, Object> result = (Map<String, Object>) execution.getVariable(PROC_HOME_LOAN_RESULT_CODE + "homeLoan");
        String selfHit = (String) result.get("HitSelf");
        String hitNetLoan = (String) result.get("HitNetLoan");
        String hitOldLai = (String) result.get("HitOldLai");
        String hitWebank = (String) result.get("HitWebank");
        String hitCollectionMin = (String) result.get("HitCollectionMin");
        if ("Y".equals(selfHit) || "Y".equals(hitNetLoan) || "Y".equals(hitOldLai) || "Y".equals(hitCollectionMin) || "Y".equals(hitWebank)) {
            // 命中黑名单
            execution.setVariable("flag", "1");
            bool = true;
        }
        if (!bool) {
            List<RuleExcuteDetail> frontSeaResult = (List<RuleExcuteDetail>) execution.getVariable(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR + senceCode);
            if (ObjectUtils.isNotEmpty(frontSeaResult)) {
                execution.setVariable("flag", "1");
            } else {
                execution.setVariable("flag", "0");
                dataMachIn(execution);
            }
        }
    }

    /**
     * 为后续接口调用封装数据
     *
     * @param execution
     */
    public void dataMachIn(DelegateExecution execution) {
        // TODO 中青信用接口调用并封装规则数据

    }
}
