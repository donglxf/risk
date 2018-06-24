package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Id implements Serializable {

    private static final long serialVersionUID = -2979622741979837675L;

    /**
     * 申请信用卡（类信用卡）的机构数
     */
    private String pdlOrgnum;
    /**
     * 申请信用卡（类信用卡）的次数
     */
    private String pdlAllnum;
    /**
     * 申请线上小额现金贷的机构数
     */
    private String caonOrgnum;
    /**
     * 申请线上小额现金贷的次数
     */
    private String caonAllnum;
    /**
     * 申请线上现金分期的机构数
     */
    private String relOrgnum;
    /**
     * 申请线上现金分期的次数
     */
    private String relAllnum;
    /**
     * 申请线下现金分期的机构数
     */
    private String caoffOrgnum;
    /**
     * 申请线下现金分期的次数
     */
    private String caoffAllnum;
    /**
     * 申请线上消费分期的机构数
     */
    private String cooffOrgnum;
    /**
     * 申请线上消费分期的次数
     */
    private String cooffAllnum;
    /**
     * 申请线下消费分期的机构数
     */
    private String afOrgnum;
    /**
     * 申请线下消费分期的次数
     */
    private String afAllnum;
    /**
     * 申请汽车金融的机构数
     */
    private String coonOrgnum;
    /**
     * 申请汽车金融的次数
     */
    private String coonAllnum;
    /**
     * 申请其他的机构数
     */
    private String othOrgnum;
    /**
     * 申请其他的次数
     */
    private String othAllnum;
    /**
     * 银行机构
     */
    private Bank bank;
    /**
     * 非银行机构
     */
    private Nbank nbank;

}
