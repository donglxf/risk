package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.QueryPhoneRecordDtoIn;
import com.ht.risk.api.model.eip.QueryPhoneRecordDtoOut;
import com.ht.risk.api.model.eip.sp.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "eip-out",path = "/eip/tc"+"/sp")
@FeignClient(value = "eip",path = "/eip/tc"+"/sp",url="http://192.168.12.25" + 
		":30406")
public interface SpRpc {

    /**
     * 描述：校验验证码
     */
    @PostMapping(value = "/validCode",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SpValidCodeDtoOut> validCode(@RequestBody SpValidCodeDtoIn input);

    /**
     * 描述：登录
     */
    @PostMapping(value = "/login",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SpLoginDtoOut> login(@RequestBody SpLoginDtoIn input);

    /**
     * 描述：获取详单验证码
     */
    @PostMapping(value = "/getDetailOrderCode",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SpDetailOrderCodeDtoOut> getDetailOrderCode(@RequestBody SpValidCodeDtoIn input);

    /**
     * 描述：获取详单验证码
     */
    @PostMapping(value = "/reqDynamicCode",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SpDetailOrderCodeDtoOut> reqDynamicCode(@RequestBody SpDynamicCodeDtoIn input);

    /**
     * 描述：发送短信动态码
     */
    @PostMapping(value = "/sendDynamicCode",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SpDetailOrderCodeDtoOut> sendDynamicCode(@RequestBody SendDynamicCodeDtoIn input);

    /**
     * 描述：用户基本信息入库
     */
    @PostMapping(value = "/userBaseInfo",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<UserBaseInfoDtoOut> userBaseInfo(@RequestBody UserBaseInfoDtoIn input);
    
    /**
     * 描述：查询通话记录
     */
    @PostMapping(value = "/queryPhoneRecord",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryPhoneRecordDtoOut> queryPhoneRecord(@RequestBody QueryPhoneRecordDtoIn input);
}
