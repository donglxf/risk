package com.ht.risk.eip.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 说明：7.1.5	查询银行账号信息
 *
 * @auther 张鹏
 * @create
 */
@Data
@ApiModel
public class QueryUserInformationAuthDtoIn {
    @ApiModelProperty(value = "类型")
    private String deviceType;
    @ApiModelProperty(value = "用户id")
    private String userId;

}
