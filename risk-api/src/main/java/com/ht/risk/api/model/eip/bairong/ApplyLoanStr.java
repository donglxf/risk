package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

@Data
public class ApplyLoanStr {
    private static final long serialVersionUID = 5158718886859118510L;
    /**
     * 近7天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content d7;
    /**
     * 近15天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content d15;
    /**
     * 过去第1个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content m1;
    /**
     * 过去第3个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content m3;
    /**
     * 过去第6个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content m6;
    /**
     * 过去第12个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private Content m12;
    /**
     * 距最早在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private FlContent fst;
    /**
     * 距最近在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况
     */
    private FlContent lst;
}
