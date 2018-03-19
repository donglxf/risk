package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class FaceCompareDtoOut {
    @ApiModelProperty(value = "本次请求的id")
    private String requestId;
    @ApiModelProperty(value = "置信度。值为 0~1，值越大表示两张照片属于同一个人的可能性越大。无法得到公安后台预留水印照时该值为 null")
    private String confidence;
    @ApiModelProperty(value = "公安接口调用结果")
    private Identity identity;
    @ApiModelProperty(value = "请求参数中使用file、url方式会返回图片的id")
    private Selfie selfie;
}

@Data
@ApiModel
class Identity {
    @ApiModelProperty(value = "身份证和姓名经过公安接口验证是否匹配。匹配为 true，不匹配为 false")
    private String validity;
    @ApiModelProperty(value = "公安后台预留照片的 ID。公安后台无该身份信息对应的照片时该值为 null")
    private String photoId;
    @ApiModelProperty(value = "公安接口出错的原因。正常为Gongan status OK 。其他错误类型参考reason字段表")
    private String reason;
}

@Data
@ApiModel
class Selfie {
    @ApiModelProperty(value = "图片Id")
    private String imageId;
}