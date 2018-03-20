package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.File;

@Data
@ApiModel
public class CheckLiveReqDtoIn {
    @ApiModelProperty(value = "自拍照片，上传本地图片进行检测时选取此参数")
    private String selfieFile;
    @ApiModelProperty(value = "自拍照片的网络地址，采用抓取网络图片方式时需选取此参数")
    private String selfieUrl;
    @ApiModelProperty(value = "自拍照片的id，在云端上传过自拍照片可采用选取此参数")
    private String selfieImageId;
    @ApiModelProperty(value = "开启图片自动旋转功能。开通：true，不开通：false。默认false")
    private String selfieAutoRotate;
    @ApiModelProperty(value = "移动端SDK返回的protobuf文件。上传本地文件时选取此参数")
    private File livenessDataFile;
    @ApiModelProperty(value = "移动端SDK返回的protobuf文件的url。采用抓取网络文件方式时需选取此参数")
    private String livenessDataUrl;
    @ApiModelProperty(value = "移动端SDK返回的protobuf文件的id。在云端上传过文件可采用此参数")
    private String livenessDataId;
}
