package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class WDEnterpriseDetailRespDtoOut {
    /**
     * 企业照面信息
     */
    @ApiModelProperty(value = "")
    private WDBasic basic;
    /**
     * 企业股东及出资信息
     */
    @ApiModelProperty(value = "")
    private List<WDShareholders> shareholders;
    /**
     * 企业主要管理人员信
     * 息
     */
    @ApiModelProperty(value = "")
    private List<WDPersons> persons;
    /**
     * 企业法定代表人其他
     * 公司任职信息
     */
    @ApiModelProperty(value = "")
    private List<WDFrpositions> frpositions;
    /**
     * 企业对外投资信息
     */
    @ApiModelProperty(value = "")
    private List<WDEntinvs> entinvs;
    /**
     * 股权出质历史信息
     */
    @ApiModelProperty(value = "")
    private List<WDSharesimpawns> sharesimpawns;
    /**
     * 股权冻结历史信息
     */
    @ApiModelProperty(value = "")
    private List<WDSharesfrosts> sharesfrosts;
    /**
     * 企业历史变更信息
     */
    @ApiModelProperty(value = "")
    private List<WDAlters> alters;
    /**
     * 行政处罚历史信息
     */
    @ApiModelProperty(value = "")
    private List<WDCaseinfos> caseinfos;
    /**
     * 企业年检信息
     */
    @ApiModelProperty(value = "")
    private List<WDDealins> dealins;
    /**
     * 企业分支机构信息
     */
    @ApiModelProperty(value = "")
    private List<WDFiliations> filiations;
    /**
     * 最终控股股东信息
     */
    @ApiModelProperty(value = "")
    private List<WDFinalshareholders> finalshareholders;
    /**
     * 清算信息
     */
    @ApiModelProperty(value = "")
    private List<WDLiquidations> liquidations;
    /**
     * 动产抵押信息
     */
    @ApiModelProperty(value = "")
    private List<WDMordetails> mordetails;
    /**
     * 动产抵押物信息
     */
    @ApiModelProperty(value = "")
    private List<WDMorguainfos> morguainfos;

}

@Data
@ApiModel
class WDMorguainfos {
    /**
     * 抵押 ID
     */
    @ApiModelProperty(value = "")
    private String morreg_ID;
    /**
     * 抵押物名称
     */
    @ApiModelProperty(value = "")
    private String guaname;
    /**
     * 数量
     */
    @ApiModelProperty(value = "")
    private String quan;
    /**
     * 价值(万元)
     */
    @ApiModelProperty(value = "")
    private String value;

}

@Data
@ApiModel
class WDShareholders {
    /**
     * 认缴出资币种
     */
    @ApiModelProperty(value = "")
    private String regcapcur;
    /**
     * 认缴出资额(万元)
     */
    @ApiModelProperty(value = "")
    private String subconam;
    /**
     * 出资方式
     */
    @ApiModelProperty(value = "")
    private String conform;
    /**
     * 出资比例
     */
    @ApiModelProperty(value = "")
    private String fundedratio;
    /**
     * 股东名称
     */
    @ApiModelProperty(value = "")
    private String shaname;
    /**
     * 出资日期
     */
    @ApiModelProperty(value = "")
    private String condate;
    /**
     * 国别
     */
    @ApiModelProperty(value = "")
    private String country;
    /**
     * 股东总数量
     */
    @ApiModelProperty(value = "")
    private String invamount;
    /**
     * 股东出资总和(万元)
     */
    @ApiModelProperty(value = "")
    private String sumconam;
    /**
     * 股东出资比例总和
     */
    @ApiModelProperty(value = "")
    private String invsumfundedratio;
    /**
     * 股东类型
     */
    @ApiModelProperty(value = "")
    private String invtype;

}

@Data
@ApiModel
class WDPersons {
    /**
     * 职务
     */
    @ApiModelProperty(value = "")
    private String position;
    /**
     * 人员姓名
     */
    @ApiModelProperty(value = "")
    private String pername;
    /**
     * 人员总数量
     */
    @ApiModelProperty(value = "")
    private String personamount;

}

@Data
@ApiModel
class WDFrpositions {
    /**
     * 企业(机构)名称
     */
    @ApiModelProperty(value = "")
    private String entname;
    /**
     * 注册号
     */
    @ApiModelProperty(value = "")
    private String regno;
    /**
     * 注册资本(万元)
     */
    @ApiModelProperty(value = "")
    private String regcap;
    /**
     * 注册资本币种
     */
    @ApiModelProperty(value = "")
    private String regcapcur;
    /**
     * 开业日期
     */
    @ApiModelProperty(value = "")
    private String esdate;
    /**
     * 企业(机构)类型
     */
    @ApiModelProperty(value = "")
    private String enttype;
    /**
     * 企业状态
     */
    @ApiModelProperty(value = "")
    private String entstatus;
    /**
     * 登记机关
     */
    @ApiModelProperty(value = "")
    private String regorg;
    /**
     * 注销日期
     */
    @ApiModelProperty(value = "")
    private String candate;
    /**
     * 吊销日期
     */
    @ApiModelProperty(value = "")
    private String revdate;
    /**
     * 注册地址行政区编号
     */
    @ApiModelProperty(value = "")
    private String regorgcode;
    /**
     * 法定代表人姓名
     */
    @ApiModelProperty(value = "")
    private String name;
    /**
     * 企业总数量
     */
    @ApiModelProperty(value = "")
    private String ppvamount;
    /**
     * 职务
     */
    @ApiModelProperty(value = "")
    private String position;
    /**
     * 是否法定代表人
     */
    @ApiModelProperty(value = "")
    private String lerepsign;

}

@Data
@ApiModel
class WDEntinvs {
    /**
     * 企业(机构)名称
     */
    @ApiModelProperty(value = "")
    private String entname;
    /**
     * 注册号
     */
    @ApiModelProperty(value = "")
    private String regno;
    /**
     * 注册资本(万元)
     */
    @ApiModelProperty(value = "")
    private String regcap;
    /**
     * 注册资本币种
     */
    @ApiModelProperty(value = "")
    private String regcapcur;
    /**
     * 开业日期
     */
    @ApiModelProperty(value = "")
    private String esdate;
    /**
     * 企业(机构)类型
     */
    @ApiModelProperty(value = "")
    private String enttype;
    /**
     * 企业状态
     */
    @ApiModelProperty(value = "")
    private String entstatus;
    /**
     * 登记机关
     */
    @ApiModelProperty(value = "")
    private String regorg;
    /**
     * 注销日期
     */
    @ApiModelProperty(value = "")
    private String candate;
    /**
     * 吊销日期
     */
    @ApiModelProperty(value = "")
    private String revdate;
    /**
     * 注册地址行政区编号
     */
    @ApiModelProperty(value = "")
    private String regorgcode;
    /**
     * 认缴出资额(万元)
     */
    @ApiModelProperty(value = "")
    private String subconam;
    /**
     * 认缴出资币种
     */
    @ApiModelProperty(value = "")
    private String congrocur;
    /**
     * 出资方式
     */
    @ApiModelProperty(value = "")
    private String conform;
    /**
     * 出资比例
     */
    @ApiModelProperty(value = "")
    private String fundedratio;
    /**
     * 法定代表人姓名
     */
    @ApiModelProperty(value = "")
    private String name;
    /**
     * 企业总数量
     */
    @ApiModelProperty(value = "")
    private String binvvamount;

}

@Data
@ApiModel
class WDSharesimpawns {
    /**
     * 质权人姓名
     */
    @ApiModelProperty(value = "")
    private String imporg;
    /**
     * 质权人类别
     */
    @ApiModelProperty(value = "")
    private String imporgtype;
    /**
     * 出质金额(万元)
     */
    @ApiModelProperty(value = "")
    private String impam;
    /**
     * 出质备案日期
     */
    @ApiModelProperty(value = "")
    private String imponrecdate;
    /**
     * 出质审批部门
     */
    @ApiModelProperty(value = "")
    private String impexaeep;
    /**
     * 出质批准日期
     */
    @ApiModelProperty(value = "")
    private String impsandate;
    /**
     * 出质截至日期
     */
    @ApiModelProperty(value = "")
    private String impto;

}


@Data
@ApiModel
class WDBasic {
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String entname;
    /**
     * 法定代表人/ 负责人/
     * 执行事务合伙人
     */
    @ApiModelProperty(value = "法定代表人/ 负责人/执行事务合伙人")
    private String frname;
    /**
     * 注册号
     */
    @ApiModelProperty(value = "")
    private String regno;
    /**
     * 注册资本(万元)
     */
    @ApiModelProperty(value = "")
    private String regcap;
    /**
     * 注册资本币种
     */
    @ApiModelProperty(value = "")
    private String regcapcur;
    /**
     * 开业日期
     */
    @ApiModelProperty(value = "")
    private String esdate;
    /**
     * 经营期限自
     */
    @ApiModelProperty(value = "")
    private String opfrom;
    /**
     * 经营(业务)范围
     */
    @ApiModelProperty(value = "")
    private String opscope;
    /**
     * 经营期限至
     */
    @ApiModelProperty(value = "")
    private String opto;
    /**
     * 企业(机构)类型
     */
    @ApiModelProperty(value = "")
    private String enttype;
    /**
     * 经营状态
     */
    @ApiModelProperty(value = "")
    private String entstatus;
    /**
     * 住址
     */
    @ApiModelProperty(value = "")
    private String dom;
    /**
     * 许可经营项目
     */
    @ApiModelProperty(value = "")
    private String abuitem;
    /**
     * 一般经营项目
     */
    @ApiModelProperty(value = "")
    private String cbuitem;
    /**
     * 经营( 业务) 范围及方
     * 式
     */
    @ApiModelProperty(value = "")
    private String opscoandform;
    /**
     * 登记机关
     */
    @ApiModelProperty(value = "")
    private String regorg;
    /**
     * 最后年检年度
     */
    @ApiModelProperty(value = "")
    private String ancheyear;
    /**
     * 最后年检日期
     */
    @ApiModelProperty(value = "")
    private String anchedate;
    /**
     * 注销日期
     */
    private String candate;
    /**
     * 吊销日期
     */
    @ApiModelProperty(value = "")
    private String revdate;
    /**
     * 实收资本(万元)
     */
    @ApiModelProperty(value = "")
    private String reccap;
    /**
     * 员工人数
     */
    @ApiModelProperty(value = "")
    private String empnum;
    /**
     * 企业英文名称
     */
    @ApiModelProperty(value = "")
    private String entnameeng;
    /**
     * 原注册号
     */
    @ApiModelProperty(value = "")
    private String oriregno;
    /**
     * 组织机构代码
     */
    @ApiModelProperty(value = "")
    private String orgcodes;
    /**
     * 统一社会信用代码
     */
    @ApiModelProperty(value = "")
    private String creditcode;
    /**
     * 变更日期
     */
    @ApiModelProperty(value = "")
    private String changedate;
    /**
     * 所在省份
     */
    @ApiModelProperty(value = "")
    private String regorgprovince;
    /**
     * 注册地址行政区编号
     */
    @ApiModelProperty(value = "")
    private String regorgcode;
    /**
     * 经营场所
     */
    @ApiModelProperty(value = "")
    private String oploc;
    /**
     * 省节点编号
     */
    @ApiModelProperty(value = "")
    private String s_EXT_NODENUM;
    /**
     * 住所所在行政区划
     */
    @ApiModelProperty(value = "")
    private String domdistrict;
    /**
     * 经营业务范围
     */
    @ApiModelProperty(value = "")
    private String zsopscope;
    /**
     * 行业门类名称
     */
    @ApiModelProperty(value = "")
    private String industryphy;
    /**
     * 国民经济行业代码
     */
    @ApiModelProperty(value = "")
    private String industrycocode;
    /**
     * 国民经济行业名称
     */
    @ApiModelProperty(value = "")
    private String industryconame;
    /**
     * 行业门类代码
     */
    @ApiModelProperty(value = "")
    private String industryphycode;
}


