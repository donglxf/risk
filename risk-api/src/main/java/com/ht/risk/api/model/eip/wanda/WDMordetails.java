package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDMordetails {
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
     * 注册地址行政区编号
     */
    @ApiModelProperty(value = "")
    private String regorgcode;
    /**
     * 抵押人
     */
    @ApiModelProperty(value = "")
    private String mortgagor;
    /**
     * 状态标识
     */
    @ApiModelProperty(value = "")
    private String mortype;
    /**
     * 抵押 ID
     */
    @ApiModelProperty(value = "")
    private String morreg_ID;
    /**
     * 履约起始日期
     */
    @ApiModelProperty(value = "")
    private String pefperform;
    /**
     * 履约截止日期
     */
    @ApiModelProperty(value = "")
    private String pefperto;
    /**
     * 登记日期
     */
    @ApiModelProperty(value = "")
    private String regidate;
    /**
     * 登记证号
     */
    @ApiModelProperty(value = "")
    private String morregcno;
    /**
     * 被担保主债权数额(万
     * 元)
     */
    @ApiModelProperty(value = "")
    private String priclasecam;
    /**
     * 申请抵押原因
     */
    @ApiModelProperty(value = "")
    private String appregrea;
    /**
     * 被担保主债权种类
     */
    @ApiModelProperty(value = "")
    private String priclaseckind;
    /**
     * 抵押权人
     */
    @ApiModelProperty(value = "")
    private String more;

}
