package com.ht.risk.api.model.eip.bairong;

import com.ht.risk.api.model.eip.bairong.ApplyLoanStr;
import com.ht.risk.api.model.eip.bairong.Nbank;
import lombok.Data;

import java.io.Serializable;

/**
 * 百融多次申请核查V2
 *
 * @author:喻尊龙
 * @date: 2018/2/5
 */
@Data
public class BairongMoreCheckDtoOut implements Serializable {
    private static final long serialVersionUID = 7729858758009155717L;

    private String tianChengStatus;
    /**
     * 流水号
     */
    private String swiftNumber;
    /**
     * 获取时间
     */
    private String createTime;
    /**
     * 产品输出标识,请参考产品输出标识
     */
    private Multipleflag flag;
    /**
     * 详细内容
     */
    private ApplyLoanStr applyLoanStr;


}














