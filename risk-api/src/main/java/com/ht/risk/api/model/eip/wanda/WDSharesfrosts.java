package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDSharesfrosts {
    /**
     * 冻结文号
     */
    @ApiModelProperty(value = "")
    private String frodocno;
    /**
     * 冻结机关
     */
    @ApiModelProperty(value = "")
    private String froauth;
    /**
     * 冻结起始日期
     */
    @ApiModelProperty(value = "")
    private String frofrom;
    /**
     * 冻结截至日期
     */
    @ApiModelProperty(value = "")
    private String froto;
    /**
     * 冻结金额(万元)
     */
    @ApiModelProperty(value = "")
    private String froam;
    /**
     * 解冻机关
     */
    @ApiModelProperty(value = "")
    private String thawauth;
    /**
     * 解冻文号
     */
    @ApiModelProperty(value = "")
    private String thawdocno;
    /**
     * 解冻日期
     */
    @ApiModelProperty(value = "")
    private String thawdate;
    /**
     * 解冻说明
     */
    @ApiModelProperty(value = "")
    private String thawcomment;
}
