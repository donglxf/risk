package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserBaseInfoDtoOut {
    @ApiModelProperty(value = "当result =1，表示新增用户成功;\n" +
            "当result =2，表示更新用户成功；\n" +
            "当result =3, 表示已有缓存数据，可直接调用查询接口获取数据\n")
    private int status;
}
