package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class QueryPhoneRecordDtoOut{

	@ApiModelProperty(value = "邮箱")
    private String email;
	
	@ApiModelProperty(value = "爬取时间")
    private String effective_time;
	
	@ApiModelProperty(value = "身份证号")
    private String idCard;
	
	@ApiModelProperty(value = "地址")
    private String addr;
	
	@ApiModelProperty(value = "真实姓名")
    private String realName;
	
	@ApiModelProperty(value = "当前余额")
    private String phoneRemain;
	
	@ApiModelProperty(value = "入网时间")
    private String registerDate;
	
	@ApiModelProperty(value = "手机号码")
    private String phone;
    
	@ApiModelProperty(value = "所属运营商")
    private String source;
	
	@ApiModelProperty(value = "付费类型")
    private String payType;
	
	@ApiModelProperty(value = "短消息域")
    private List<MessageData> messageData;
	
	@ApiModelProperty(value = "通话详单域")
    private List<TelData> telData;
	
	@ApiModelProperty(value = "月份账单域")
    private List<BillData> billData;
	
	@ApiModelProperty(value = "流量详单域")
    private List<FlowDetail> flowDetail;
	
	@ApiModelProperty(value = "流量账单域")
    private List<FlowBill> flowBill;
}

@Data
class MessageData{
	
	@ApiModelProperty(value = "对方号码")
    private String receiverPhone;
	
	@ApiModelProperty(value = "发送时间")
    private String sentTime;
	
	@ApiModelProperty(value = "通信方式")
    private String tradeWay;
	
	@ApiModelProperty(value = "通信地址")
    private String sentAddr;

}
@Data
class TelData{

	@ApiModelProperty(value = "通信类型")
    private String tradeType;
	
	@ApiModelProperty(value = "通信时长（秒）")
    private String tradeTime;
	
	@ApiModelProperty(value = "通话起始时间")
    private String cTime;
	
	@ApiModelProperty(value = "通信地点")
    private String tradeAddr;
	
	@ApiModelProperty(value = "对方号码")
    private String receiverPhone;
	
	@ApiModelProperty(value = "呼叫类型")
    private String callType;

}
@Data
class BillData {

	@ApiModelProperty(value = "消费金额")
    private String callPay;
	
	@ApiModelProperty(value = "所属月份")
    private String time;

}
@Data
class FlowDetail {

	@ApiModelProperty(value = "网络类型")
    private String netType;
	
	@ApiModelProperty(value = "上网时长（秒）")
    private String tradeTime;
	
	@ApiModelProperty(value = "上网流量（KB）")
    private String flow;
	
	@ApiModelProperty(value = "上网起始时间")
    private String cTime;
	
	@ApiModelProperty(value = "上网地点")
    private String tradeAddr;
	
	@ApiModelProperty(value = "消费金额 （元）")
    private String fee;

}
@Data
class FlowBill {

	@ApiModelProperty(value = "所属月份")
    private String time;
	
	@ApiModelProperty(value = "上网总流量（KB）")
    private String allFlow;
	
	@ApiModelProperty(value = "上网总时长（秒）")
    private String llTime;
	
	@ApiModelProperty(value = "消费金额（元）")
    private String allPay;
}