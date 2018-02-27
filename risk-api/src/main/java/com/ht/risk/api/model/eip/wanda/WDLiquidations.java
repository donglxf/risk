package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDLiquidations {
    /**
     * 清算完结情况
     */
    @ApiModelProperty(value = "")
    private String ligst;
    /**
     * 清算组成员
     */
    @ApiModelProperty(value = "")
    private String liqmen;
    /**
     * 债权承接人
     */
    @ApiModelProperty(value = "")
    private String claimtranee;
    /**
     * 清算责任人
     */
    @ApiModelProperty(value = "")
    private String ligentity;
    /**
     * 清算负责人
     */
    @ApiModelProperty(value = "")
    private String ligprincipal;
    /**
     * 债务承接人
     */
    @ApiModelProperty(value = "")
    private String debttranee;
    /**
     * 清算完结日期
     */
    @ApiModelProperty(value = "")
    private String ligenddate;

}
