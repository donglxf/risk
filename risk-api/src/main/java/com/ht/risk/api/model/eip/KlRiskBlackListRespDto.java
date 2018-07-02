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
    private List<BlacklistDetail> blacklistDetailList;
}
