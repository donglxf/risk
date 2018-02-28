package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDFinalshareholders {
    /**
     * 最终控股股东名称
     */
    @ApiModelProperty(value = "")
    private String finalentname;
    /**
     * 国别
     */
    @ApiModelProperty(value = "")
    private String finalcountry;
    /**
     * 股东类型
     */
    @ApiModelProperty(value = "")
    private String finalinvtype;
    /**
     * 认缴出资额(万元)
     */
    @ApiModelProperty(value = "")
    private String finalsubconam;
    /**
     * 认缴出资额币种
     */
    @ApiModelProperty(value = "")
    private String finalcurrency;
    /**
     * 实缴出资额(万元)
     */
    @ApiModelProperty(value = "")
    private String finalacconam;
    /**
     * 出资额
     */
    @ApiModelProperty(value = "")
    private String finalconam;
    /**
     * 出资方式
     */
    @ApiModelProperty(value = "")
    private String finalconform;
    /**
     * 出资日期
     */
    @ApiModelProperty(value = "")
    private String finalcondate;
    /**
     * 出资比例
     */
    @ApiModelProperty(value = "")
    private String finalratio;
    /**
     * 出资链
     */
    @ApiModelProperty(value = "")
    private String capitalchain;
    /**
     * 出资链
     */
    @ApiModelProperty(value = "")
    private String capitalchainex;
    /**
     * 审核状态
     */
    @ApiModelProperty(value = "")
    private String finalstatus;

}
