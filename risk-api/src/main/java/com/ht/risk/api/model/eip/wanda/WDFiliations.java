package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDFiliations {
    /**
     * 一般经营项目
     */
    @ApiModelProperty(value = "")
    private String cbuitem;
    /**
     * 分支机构企业注册号
     */
    @ApiModelProperty(value = "")
    private String brregno;
    /**
     * 分支机构名称
     */
    @ApiModelProperty(value = "")
    private String brname;
    /**
     * 分支机构地址
     */
    @ApiModelProperty(value = "")
    private String braddr;
    /**
     * 分支机构负责人
     */
    @ApiModelProperty(value = "")
    private String brprincipal;

}
