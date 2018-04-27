package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.homeloan.HomeLoanRuleDataMachin;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.TaoBaoTradeDetailsDto;
import com.ht.risk.api.model.eip.TaobaoInfoDtoIn;
import com.ht.risk.api.model.eip.TaobaoInfoDtoOut;
import com.ht.risk.api.model.eip.sp.*;
import com.ht.risk.common.util.DateUtils;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.common.util.StringUtils;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.bpmn.data.SimpleDataInputAssociation;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Log4j2
@Service("homeLoanMachin")
public class HomeLoanRuleDataMachinImpl implements HomeLoanRuleDataMachin {

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

        //  准入规则数据加工
        zhurMachin(dataMap, execution);
    }

    public StringBuffer dataValid(Map dataMap) {
        StringBuffer msg = new StringBuffer("");
        String taskId = String.valueOf(dataMap.get("taskId")); // 需用户传入,魔蝎淘宝接口调用
        String borrower = String.valueOf(dataMap.get("borrower")); // 借款人姓名,传入
        String borrowerMobile = String.valueOf(dataMap.get("borrowerMobile")); // 借款人申请借款手机号码 ，传入
        if (StringUtils.isEmpty(taskId)) {
            msg.append("任务ID不能为空");
        }
        if (StringUtils.isEmpty(borrower)) {
            msg.append("借款人姓名不能为空");
        }
        if (StringUtils.isEmpty(borrowerMobile)) {
            msg.append("借款人申请借款手机号码不能为空");
        }
        return msg;
    }

    /**
     * 准入规则数据加工
     */
    public void zhurMachin(Map dataMap, DelegateExecution execution) throws ParseException {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> list = new ArrayList();
        // 家装贷准入信息
        Object homeLoanObj = dataMap.get("homeLoanInfo");
        List<Map<String, Object>> homeLoan = homeLoanObj != null ? (List<Map<String, Object>>) homeLoanObj : new ArrayList<Map<String, Object>>();
        Map<String, Object> homeLoanMap = null;
        for (int i = 0; i < homeLoan.size(); i++) {
            homeLoanMap = homeLoan.get(i);
            TaobaoInfoDtoIn dto = new TaobaoInfoDtoIn();
            dto.setTaskId(dataMap.get("taskId").toString());
            Result<TaobaoInfoDtoOut> taobao = eipServiceInterface.taobaoGetAll(dto);
            if (ObjectUtils.isNotEmpty(taobao) && "0000".equals(taobao.getReturnCode())) {
                TaobaoInfoDtoOut out = taobao.getData();
                String firstTime = out.getUserinfo().getFirstOrdertime();
                String realName = out.getUserinfo().getRealName();
                if (!realName.equals(String.valueOf(dataMap.get("borrower"))) || false) {
                    //TODO  修改false条件：  userinfo.authentication为ture
                    homeLoanMap.put("HomeLoanAdmittance_Tbaccount", "否");
                }
                int method = DateUtils.minusMonths(sim.parse(firstTime), new Date());
                homeLoanMap.put("HomeLoanAdmittance_tbTransaction", method);
                // 淘宝有本人收货信息的判断
                List<TaoBaoTradeDetailsDto> detail = out.getTradedetails().getTradedetails();
                boolean bool = false;
                for (int j = 0; i < detail.size(); j++) {
                    TaoBaoTradeDetailsDto det = detail.get(j);
                    if ("TRADE_FINISHED".equals(det.getTradeStatus())) {
                        bool = true;
                        break;
                    }
                }
                if (!bool || true) {
                    //TODO 修改true条件 收货人手机号码recentdeliveraddress.deliver_mobilephone与借款人申请借款的手机号码一致
                    homeLoanMap.put("HomeLoanAdmittance_tbtransactionrecord", "否");
                }
            }
            list.add(homeLoanMap);

        }
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "homeLoan", list);
    }


    public void Machin(){

    }

}
