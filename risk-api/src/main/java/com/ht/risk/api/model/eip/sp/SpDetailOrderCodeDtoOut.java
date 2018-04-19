package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SpDetailOrderCodeDtoOut {
    @ApiModelProperty(value = "判断此步是否正常,当status =1，表示获取到数据。\n" +
            "当status =0，表示用户不存在\n" +
            "当status <0，表示其他错\n")
    private int status;
    @ApiModelProperty(value = "验证码图片地址，（部分省份运营商不返回验证码，不需要展示给用户）")
    private String imgUrl;
    @ApiModelProperty(value = "判断是否还有下一步")
    private boolean result;
    @ApiModelProperty(value = "用户忘记密码链接")
    private String forgetPassUrl;
    @ApiModelProperty(value = "输入的密码类型")
    private String passName;
    @ApiModelProperty(value = "时间戳")
    private String currentTime;
}
