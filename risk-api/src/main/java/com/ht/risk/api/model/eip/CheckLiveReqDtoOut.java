package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class CheckLiveReqDtoOut {
    @ApiModelProperty(value = "本次请求的id")
    private String requestId;
    @ApiModelProperty(value = "使用file、url方式上传活体数据返回的活体数据的id")
    private String livenessDataId;
    @ApiModelProperty(value = "使用file、url方式上传公安部后台预留返回的公安部后台预留的带水印照片的id")
    private String imageId;
    @ApiModelProperty(value = "防hack得分，取值范围是0~1。值越大表示被hack的可能性越大")
    private float hackScore;
    @ApiModelProperty(value = "置信度，值为 0~1，值越大表示两张照片是同一个人的可能性越大")
    private float verifyScore;

}
