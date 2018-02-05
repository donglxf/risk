package com.ht.risk.eip.rpc;

import com.ht.risk.eip.dto.NetLoanIn;
import com.ht.risk.eip.dto.NetLoanOut;
import com.ht.risk.eip.dto.OldLaiIn;
import com.ht.risk.eip.dto.OldLaiOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *天秤相关外联对接接口
 */
@FeignClient(value = "eip-out",path = "/eip/tc",url = "http://192.168.14.230:30406")
public interface TcRpc {
    /**
     * 描述：网贷黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/netLoan", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<NetLoanOut> netLoan(@RequestBody NetLoanIn input);

    /**
     * 描述：老赖黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/black/oldLai", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<OldLaiOut> oldLai(@RequestBody OldLaiIn input);



}
