package com.ht.risk.api.model.eip.wanda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class WDAlters {
    /**
     *变更前内容
     */
    @ApiModelProperty(value = "")
    private String altbe;
    /**
     *变更日期
     */
    @ApiModelProperty(value = "")
    private String altdate;
    /**
     *变更事项
     */
    @ApiModelProperty(value = "")
    private String altitem;
    /**
     *变更后内容
     */
    @ApiModelProperty(value = "")
    private String altaf;

}
