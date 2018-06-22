package com.ht.risk.rule.controller;

import com.ht.risk.api.comment.SeflBlackStatusEnum;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.rule.rpc.EipIntefaceRpc;
import com.ht.risk.rule.service.TempDataContainsService;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.vo.OldLaiInVo;
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
    public Result<NetLoanOut> NetLoan(String realName, String identityCard, String mobilePhone) {
        try {
            NetLoanIn dto = new NetLoanIn();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            dto.setMobilePhone(mobilePhone);
            Result<NetLoanOut> result = eipIntefaceRpc.noCacheNetLoan(dto);
            NetLoanOut rdto = result.getData();
            if (null == rdto) {
//                rdto=new NetLoanOut();
//                rdto.setAlsoLateFee("asdfafd");
//                rdto.setAlsoUrgentFee("dfdf");
//                rdto.setBirthAddress("dddddddddddddddddd");
                return Result.error(1, "无信息！！");
            }
            return Result.success(rdto);
        } catch (Exception e) {
            log.error("网贷黑名单异常！！！", e);
        }
        return null;
    }

    @GetMapping("/self")
    @ApiOperation(value = "自有黑名单")
    public Result<SelfDtoOut> self(String identityCard, String mobilePhone) {
        try {
            OldLaiInVo dto = new OldLaiInVo();
            dto.setMobilePhone(mobilePhone);
            dto.setIdentityCard(identityCard);
            Result<SelfDtoOut> result = eipIntefaceRpc.noCacheSelf(dto);
            SelfDtoOut rdto = result.getData();
            if (null == rdto) {
                return Result.error(1, "无信息！！");
            }
            if (SeflBlackStatusEnum.noBlack.getValue().equals(rdto.getIsBlacklistUser())) {
                rdto.setIsBlacklistUser(SeflBlackStatusEnum.noBlack.getName());
            } else if (SeflBlackStatusEnum.yesBlack.getValue().equals(rdto.getIsBlacklistUser())) {
                rdto.setIsBlacklistUser(SeflBlackStatusEnum.yesBlack.getName());
            }
            return Result.success(rdto);
        } catch (Exception e) {
            log.error("自有黑名单异常！！！", e);
        }
        return null;
    }

    @GetMapping("/oldLai")
    @ApiOperation(value = "老赖黑名单")
    public Result<OldLaiOut> oldLai(String realName, String identityCard) {
        try {
            OldLaiIn dto = new OldLaiIn();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            Result<OldLaiOut> result = eipIntefaceRpc.noCacheOldLai(dto);
            OldLaiOut rdto = result.getData();
            if (null == rdto) {
                return Result.error(1, "无信息！！");
            }
            return Result.success(rdto);
        } catch (Exception e) {
            log.error("老赖黑名单异常！！！", e);
        }
        return null;
    }

    @GetMapping("/webank")
    @ApiOperation(value = "汇法网微众")
    public Result<List<Allmsglist>> webank(String realName, String identityCard) {
        try {
            LawxpWebankDtoIn dto = new LawxpWebankDtoIn();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            Result<LawxpWebankDtoOut> result = eipIntefaceRpc.noCacheWebank(dto);
            LawxpWebankDtoOut rdto = result.getData();
            List<Allmsglist> resultList = rdto.getAllmsglist();
            if (null == resultList) {
                return Result.error(1, "无信息！！");
            }
            return Result.success(resultList);
        } catch (Exception e) {
            log.error("汇法网微众异常！！！", e);
        }
        return null;
    }


}
