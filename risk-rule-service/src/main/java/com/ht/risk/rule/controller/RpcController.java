package com.ht.risk.rule.controller;

import com.ht.risk.api.model.eip.*;
import com.ht.risk.rule.rpc.EipIntefaceRpc;
import com.ht.risk.rule.service.TempDataContainsService;
import com.ht.risk.common.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提供模型获取数据接口
 * </p>
 *
 * @author dyb
 * @since 2018-02-26
 */
@RestController
@RequestMapping("/rpc")
@Log4j2
public class RpcController {

    @Autowired
    private TempDataContainsService tempDataContainsService;

    @Autowired
    private EipIntefaceRpc eipIntefaceRpc;

    @PostMapping("get")
    @ApiOperation(value = "房速贷准入流程获取数据接口")
    public List<Map<String, Object>> getVariableBindBySenceVersionId() {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        List<Map<String, Object>> map = tempDataContainsService.getAutoValidaionData("");
        return map;
    }

    @GetMapping("/riskBlack")
    @ApiOperation(value = "考拉黑名单")
    public Result<List<BlacklistDetail>> blackRiskBlack(String realName, String identityCard, String mobilePhone) {
        try {
            KlRiskBlackListReqDto dto = new KlRiskBlackListReqDto();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            dto.setMobilePhone(mobilePhone);
            Result<KlRiskBlackListRespDto> result = eipIntefaceRpc.noCacheKlRiskBlack(dto);
            KlRiskBlackListRespDto rdto = result.getData();
            List<BlacklistDetail> list = rdto.getBlacklistDetail();
            if (null == list) {
                return Result.error(1, "无信息！！");
            }
            return Result.success(list);
        } catch (Exception e) {
            log.error("考拉黑名单异常！！！", e);
        }
        return null;
    }

    @GetMapping("/netLoan")
    @ApiOperation(value = "网贷黑名单")
    public Result<NetLoanOut> NetLoan(String realName1, String identityCard1, String mobilePhone1) {
        try {
            NetLoanIn dto = new NetLoanIn();
            dto.setRealName(realName1);
            dto.setIdentityCard(identityCard1);
            dto.setMobilePhone(mobilePhone1);
            Result<NetLoanOut> result = eipIntefaceRpc.noCacheNetLoan(dto);
            NetLoanOut rdto = result.getData();
            if (null == rdto) {
                return Result.error(1, "无信息！！");
            }
            return Result.success(rdto);
        } catch (Exception e) {
            log.error("网贷黑名单！！！", e);
        }
        return null;
    }


}
