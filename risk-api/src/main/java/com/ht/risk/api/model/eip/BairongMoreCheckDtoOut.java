package com.ht.risk.api.model.eip;

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

@Data
class Multipleflag implements Serializable {

    private static final long serialVersionUID = -6888525107559597912L;

    private String applyLoanStr;
}

@Data
class ApplyLoanStr implements Serializable {

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

@Data
class Content implements Serializable {

    private static final long serialVersionUID = -1769361476440855788L;

    private Id id;
    private Id cell;
}

@Data
class Detail implements Serializable {

    private static final long serialVersionUID = 1599086711518389048L;

    /**
     * 申请的间隔天数
     */
    private String inteday;
    /**
     * 连续申请的次数
     */
    private String consnum;
    /**
     * 连续申请的持续天数
     */
    private String csinteday;

}

@Data
class Cdata implements Serializable {

    private static final long serialVersionUID = -15736017638431827L;

    private Detail bank;
    private Detail nbank;
}

@Data
class FlContent implements Serializable {

    private static final long serialVersionUID = 4968692348693511114L;

    private Cdata id;
    private Cdata cell;
}
