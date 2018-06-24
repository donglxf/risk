package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Bank implements Serializable {

    private static final long serialVersionUID = -8938328911635526667L;

    /**
     * 在本机构(本机构为银行)的申请次数
     */
    private String selfnum;
    /**
     * 在银行机构申请次数
     */
    private String allnum;
    /**
     * 在银行机构-传统银行申请次数
     */
    private String traAllnum;
    /**
     * 在银行机构-网络零售银行申请次数
     */
    private String retAllnum;
    /**
     * 在银行机构申请机构数
     */
    private String orgnum;
    /**
     * 在银行机构-传统银行申请机构数
     */
    private String traOrgnum;
    /**
     * 在银行机构-网络零售银行申请机构数
     */
    private String retOrgnum;
    /**
     * 在银行机构周末申请次数
     */
    private String weekAllnum;
    /**
     * 在银行机构周末申请机构数
     */
    private String weekOrgnum;
    /**
     * 在银行机构夜间申请次数
     */
    private String nightAllnum;
    /**
     * 在银行机构夜间申请机构数
     */
    private String nightOrgnum;

}
