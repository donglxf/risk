package com.ht.risk.api.model.eip;

import lombok.Data;

@Data
public class BlacklistDetail {
    private static final long serialVersionUID = 5908717173007321498L;

    /**
     * 借款日期
     */
    private String borrowDate;
    /**
     * 借款金额
     */
    private String borrowAmount;
    /**
     * 借款期限
     */
    private String borrowPeriod;
    /**
     * 逾期日期
     */
    private String overdueDate;
    /**
     * 逾期级别
     */
    private String overdueLevel;
    /**
     * 逾期金额
     */
    private String overdueAmount;
}
