package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/2/7 14:59
 */
@FeignClient(value = "eip-out",path = "/eip/tc"+"/lawxp",url = "http://192.168.14.230:30406")
public interface LawxpRpc {

    /**
     * 描述：汇法网-个人分类统计查询
     */
    @PostMapping(value = "/personClassify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpPersonClassifyDtoOut> personClassify(@RequestBody LawxpPersonClassifyDtoIn input);

    /**
     * 描述：汇法网-微众(法院信息)
     */
    @PostMapping(value = "/webank", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpWebankDtoOut> webank(@RequestBody LawxpWebankDtoIn input);

    /**
     * 描述：汇法网-全文检索(法院信息)
     */
    @PostMapping(value = "/fullText", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<LawxpFullTextDtoOut> fullText(@RequestBody LawxpFullTextDtoIn input);
}
