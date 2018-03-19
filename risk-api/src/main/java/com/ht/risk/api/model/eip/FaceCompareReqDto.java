package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel
public class FaceCompareReqDto {
    @ApiModelProperty(value = "身份证号。用以查询公安部后台预留照片")
    private String idNumber;
    @ApiModelProperty(value = "与身份证号相对应的姓名。身份证号及姓名相匹配才能查询公安后台照片")
    private String name;
    @ApiModelProperty(value = "需上传的图片文件。上传本地图片可选取此参数")
    private MultipartFile selfieFile;


    @ApiModelProperty(value = "图片网络地址。采用抓取网络图片方式可选取此参数")
    private String selfieUrl;
    @ApiModelProperty(value = "图片的id。在云端上传过的图片可选取此参数")
    private String selfieImageId;
    @ApiModelProperty(value = "开启图片自动旋转功能。开通：true，不开通：false。默认不开通")
    private Boolean selfieAutoRotate;

}
