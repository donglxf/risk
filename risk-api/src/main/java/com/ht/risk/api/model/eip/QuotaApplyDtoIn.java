package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 额度申请信息
 * @author liuzhengquan
 */
@Data
@ApiModel
public class QuotaApplyDtoIn extends commEntryIn {
    @ApiModelProperty(value = "客户编码")
    private String clientCode;
    @ApiModelProperty(value = "产品编号")
    private String productCode;
    @ApiModelProperty(value = "额度")
    private String quota;
    @ApiModelProperty(value = "生效时间")
    private String effectTime;
    @ApiModelProperty(value = "失效时间")
    private String expireTime;
}
