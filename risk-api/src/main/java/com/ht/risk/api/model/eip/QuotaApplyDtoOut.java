package com.ht.risk.api.model.eip;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 额度申请信息
 * @author liuzhengquan
 */
@Data
@ApiModel
public class QuotaApplyDtoOut {
	
	@ApiModelProperty(value = "客户编码")
    private String clientCode;

	@ApiModelProperty(value = "户名")
    private String name;

	@ApiModelProperty(value = "账号")
    private String number;

	@ApiModelProperty(value = "产品编号")
    private String productCode;

	@ApiModelProperty(value = "授信额度")
    private Long creditQuota;

	@ApiModelProperty(value = "额度")
    private Long quota;

	@ApiModelProperty(value = "可用额度")
    private Long usableQuota;

	@ApiModelProperty(value = "额度状态")
    private String status;

	@ApiModelProperty(value = "生效时间")
    private Date effectTime;

	@ApiModelProperty(value = "失效时间")
    private Date expireTime;
}
