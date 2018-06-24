package com.ht.risk.api.model.eip.bairong;

import lombok.Data;

import java.io.Serializable;

@Data
class Nbank implements Serializable {

    private static final long serialVersionUID = -7243071998024964576L;
    /**
     * 在本机构(本机构为非银)申请次数
     */
    private String selfnum;
    /**
     * 在非银机构申请次数
     */
    private String allnum;
    /**
     * 在非银机构-p2p机构申请次数
     */
    private String p2pAllnum;
    /**
     * 在非银机构-小贷机构申请次数
     */
    private String mcAllnum;
    /**
     * 在非银机构-现金类分期机构申请次数
     */
    private String caAllnum;
    /**
     * 在非银机构-消费类分期机构申请次数
     */
    private String cfAllnum;
    /**
     * 在非银机构-代偿类分期机构申请次数
     */
    private String comAllnum;
    /**
     * 在非银机构-其他申请次数
     */
    private String othAllnum;
    /**
     * 在非银机构-持牌网络小贷机构申请次数
     */
    private String nsloanAllnum;
    /**
     * 在非银机构-持牌汽车金融机构申请次数
     */
    private String autofinAllnum;
    /**
     * 在非银机构-持牌小贷机构申请次数
     */
    private String sloanAllnum;
    /**
     * 在非银机构-持牌消费金融机构申请次数
     */
    private String consAllnum;
    /**
     * 在非银机构-持牌融资租赁机构申请次数
     */
    private String finleaAllnum;
    /**
     * 在非银机构-其他申请次数
     */
    private String elseAllnum;
    /**
     * 在非银机构申请机构数
     */
    private String orgnum;
    /**
     * 在非银机构-p2p申请机构数
     */
    private String p2pOrgnum;
    /**
     * 在非银机构-小贷申请机构数
     */
    private String mcOrgnum;
    /**
     * 在非银机构-现金类分期申请机构数
     */
    private String caOrgnum;
    /**
     * 在非银机构-消费类分期申请机构数
     */
    private String cfOrgnum;
    /**
     * 在非银机构-代偿类分期申请机构数
     */
    private String comOrgnum;
    /**
     * 在非银机构-其他申请机构数
     */
    private String othOrgnum;
    /**
     * 在非银机构-持牌网络小贷机构申请机构数
     */
    private String nsloanOrgnum;
    /**
     * 在非银机构-持牌汽车金融机构申请机构数
     */
    private String autofinOrgnum;
    /**
     * 在非银机构-持牌小贷机构申请机构数
     */
    private String sloanOrgnum;
    /**
     * 在非银机构-持牌消费金融机构申请机构数
     */
    private String consOrgnum;
    /**
     * 在非银机构-持牌融资租赁机构申请机构数
     */
    private String finleaOrgnum;
    /**
     * 在非银机构周末申请次数
     */
    private String weekAllnum;
    /**
     * 在非银机构周末申请机构数
     */
    private String weekOrgnum;
    /**
     * 在非银机构夜间申请次数
     */
    private String nightAllnum;
    /**
     * 在非银机构夜间申请机构数
     */
    private String nightOrgnum;

}
