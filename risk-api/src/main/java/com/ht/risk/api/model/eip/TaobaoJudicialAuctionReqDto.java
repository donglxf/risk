package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaobaoJudicialAuctionReqDto extends commEntryIn {

	@ApiModelProperty(value = "与查询号码通话的电话号码")
	private String productType;

	@ApiModelProperty(value = "房产地址")
	private String houseAddress;

	@ApiModelProperty(value = "所有权证号")
	private String housePropertyNo;

	@ApiModelProperty(value = "国有土地证号")
	private String stateLandNo;

	@ApiModelProperty(value = "地号")
	private String landNo;

	@ApiModelProperty(value = "车牌号")
	private String carNo;

	@ApiModelProperty(value = "品牌型号")
	private String typeNo;

	@ApiModelProperty(value = "车架号")
	private String carFrameNo;

	@ApiModelProperty(value = "车辆识别码")
	private String carDetectionNo;

	@ApiModelProperty(value = "发动机号")
	private String engineNo;

}