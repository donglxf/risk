package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDCaseinfos {
    /**
     * 处罚机关
     */
    @ApiModelProperty(value = "")
    private String penauth;
    /**
     * 案由
     */
    @ApiModelProperty(value = "")
    private String casereason;
    /**
     * 处罚决定书签发日期
     */
    @ApiModelProperty(value = "")
    private String pendecissdate;
    /**
     * 处罚依据
     */
    @ApiModelProperty(value = "")
    private String penbasis;
    /**
     * 处罚结果
     */
    @ApiModelProperty(value = "")
    private String penresult;
    /**
     * 处罚金额(万元)
     */
    @ApiModelProperty(value = "")
    private String penam;
    /**
     * 处罚执行情况
     */
    @ApiModelProperty(value = "")
    private String penexest;
    /**
     * 案件类型
     */
    @ApiModelProperty(value = "")
    private String casetype;
    /**
     * 主要违法事实
     */
    @ApiModelProperty(value = "")
    private String illegfact;
    /**
     * 处罚种类
     */
    @ApiModelProperty(value = "")
    private String pentype;
    /**
     * 案发时间
     */
    @ApiModelProperty(value = "")
    private String casetime;
    /**
     * 处罚决定文书
     */
    @ApiModelProperty(value = "")
    private String pendecno;
    /**
     * 案值
     */
    @ApiModelProperty(value = "")
    private String caseval;
    /**
     * 案件结果
     */
    @ApiModelProperty(value = "")
    private String caseresult;
    /**
     * 执行类别
     */
    @ApiModelProperty(value = "")
    private String exesort;

}
