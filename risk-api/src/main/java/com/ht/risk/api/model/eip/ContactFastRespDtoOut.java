package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContactFastRespDtoOut {
    private static final long serialVersionUID = -5636325073060556522L;
    @ApiModelProperty(value = "分类信息")
    private List<String> catnames;
    @ApiModelProperty(value = "商户名称")
    private String name;
    @ApiModelProperty(value = "电话号码的描述")
    private String teldesc;
    @ApiModelProperty(value = "查询匹配的电话号码")
    private String telnum;
    @ApiModelProperty(value = "号码运营商信息")
    private String telloc;
    @ApiModelProperty(value = "号码标记信息")
    private Object flag;
    @ApiModelProperty(value = "号码金融标签")
    private Integer[] itag_ids;
    @ApiModelProperty(value = "")
    private String status;


}


