package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/2/7 14:59
 */
@PropertySource("classpath:config.properties")
@FeignClient(value = "eip-out",path = "/eip/tc"+"/lawxp")
public interface LawxpRpc {

    /**
     * 描述：汇法网-个人分类统计查询
     */
    @PostMapping(value = "/personClassify",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpPersonClassifyDtoOut> personClassify(@RequestBody LawxpPersonClassifyDtoIn input);

    /**
     * 描述：汇法网-微众(法院信息)
     */
    @PostMapping(value = "/webank",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpWebankDtoOut> webank(@RequestBody LawxpWebankDtoIn input);

    /**
     * 描述：汇法网-全文检索(法院信息)
     */
    @PostMapping(value = "/fullText",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpFullTextDtoOut> fullText(@RequestBody LawxpFullTextDtoIn input);
}
