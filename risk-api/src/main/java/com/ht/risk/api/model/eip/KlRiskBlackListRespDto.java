package com.ht.risk.api.model.eip;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 考拉风险黑名单
 * @author:喻尊龙
 * @date: 2018/3/7
 */
@Data
public class KlRiskBlackListRespDto implements Serializable {
    private static final long serialVersionUID = 2933579175695741696L;

    /**
     *外部订单流水号
     */
    private String outOrderNo;
    /**
     *考拉交易流水
     */
    private String jnlNo;
    /**
     *时间戳
     */
    private String timeStamp;
    /**
     *黑名单详细列表
     */
    private List<BlacklistDetail> blacklistDetail;


}
@Data
class BlacklistDetail implements Serializable{

    private static final long serialVersionUID = 5908717173007321498L;

    /**
     *借款日期
     */
    private String borrowDate;
    /**
     *借款金额
     */
    private String borrowAmount;
    /**
     *借款期限
     */
    private String borrowPeriod;
    /**
     *逾期日期
     */
    private String overdueDate;
    /**
     *逾期级别
     */
    private String overdueLevel;
    /**
     *逾期金额
     */
    private String overdueAmount;

}
