package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDDealins {
    /**
     * 年检年度
     */
    @ApiModelProperty(value = "")
    private String ancheyear;
    /**
     * 币种
     */
    @ApiModelProperty(value = "")
    private String currency;
    /**
     * 资产总额
     */
    @ApiModelProperty(value = "")
    private String assgro;
    /**
     * 利润总额
     */
    @ApiModelProperty(value = "")
    private String progro;
    /**
     * 亏损额
     */
    @ApiModelProperty(value = "")
    private String deficit;
    /**
     * 销售(营业)收入
     */
    @ApiModelProperty(value = "")
    private String vendinc;
    /**
     * 长期投资
     */
    @ApiModelProperty(value = "")
    private String lterminv;
    /**
     * 长期负债
     */
    @ApiModelProperty(value = "")
    private String ltermliaam;
    /**
     * 净利润
     */
    @ApiModelProperty(value = "")
    private String netinc;
    /**
     * 运营状况
     */
    @ApiModelProperty(value = "")
    private String busst;
    /**
     * 负债总额
     */
    @ApiModelProperty(value = "")
    private String liagro;
    /**
     * 服务营业收入
     */
    @ApiModelProperty(value = "")
    private String servinc;
    /**
     * 纳税总额
     */
    @ApiModelProperty(value = "")
    private String ratgro;

}
