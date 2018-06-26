package com.ht.risk.rule.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.api.comment.FrontSeaRskMarkEnum;
import com.ht.risk.api.comment.FrontSeaSourceIdEnum;
import com.ht.risk.api.comment.SeflBlackStatusEnum;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.api.model.eip.bairong.BairongMoreCheckDtoOut;
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
            log.info("考拉黑名单入参===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<KlRiskBlackListRespDto> result = eipIntefaceRpc.noCacheKlRiskBlack(dto);
            log.info("考拉黑名单===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                KlRiskBlackListRespDto rdto = result.getData();
                if (null == rdto) {
                    return Result.error(1, "无信息！！");
                }
                List<BlacklistDetail> list = rdto.getBlacklistDetailList();
                if (null == list) {
                    return Result.error(1, "无信息！！");
                }
                return Result.success(list);
            } else {
                return Result.error(1, result.getMsg());
            }

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
            log.info("网贷黑名单===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<NetLoanOut> result = eipIntefaceRpc.noCacheNetLoan(dto);
            log.info("网贷黑名单结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                NetLoanOut rdto = result.getData();
                if (null == rdto) {
                    return Result.error(1, "无信息！！");
                }
                return Result.success(rdto);
            } else {
                return Result.error(1, result.getMsg());
            }

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
            log.info("自有黑名单===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<SelfDtoOut> result = eipIntefaceRpc.noCacheSelf(dto);
            log.info("自有黑名单返回结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
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
            } else {
                return Result.error(1, result.getMsg());
            }
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
            dto.setQueryType("1");
            log.info("老赖黑名单===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<OldLaiOut> result = eipIntefaceRpc.noCacheOldLai(dto);
            log.info("老赖黑名单结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                OldLaiOut rdto = result.getData();
                if (null == rdto) {
                    return Result.error(1, "无信息！！");
                }
                return Result.success(rdto);
            } else {
                return Result.error(1, result.getMsg());
            }
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
            log.info("汇法网微众入参===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<LawxpWebankDtoOut> result = eipIntefaceRpc.noCacheWebank(dto);
            log.info("汇法网微众结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                LawxpWebankDtoOut rdto = result.getData();
                if (rdto == null) {
                    return Result.error(1, "无信息！！");
                }
                List<Allmsglist> resultList = rdto.getAllmsglist();
                if (null == resultList) {
                    return Result.error(1, "无信息！！");
                }
                return Result.success(resultList);
            } else {
                return Result.error(1, result.getMsg());
            }
        } catch (Exception e) {
            log.error("汇法网微众异常！！！", e);
        }
        return null;
    }

    @GetMapping("/frontSea")
    @ApiOperation(value = "前海征信黑名单")
    public Result<FrontSeaDtoOut> frontSea(String realName, String identityCard, String idType, String reasonNo) {
        try {
            FrontSeaDtoIn dto = new FrontSeaDtoIn();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            dto.setReasonNo(reasonNo);
            dto.setIdType(idType);
            log.info("前海征信黑名单入参===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<FrontSeaDtoOut> result = eipIntefaceRpc.noCacheFrontSea(dto);
            log.info("前海征信黑名单结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                FrontSeaDtoOut rdto = result.getData();
                if (null == rdto) {
                    return Result.error(1, "无信息！！");
                }
                if (FrontSeaSourceIdEnum.A.getValue().equals(rdto.getSourceId())) {
                    rdto.setSourceId(FrontSeaSourceIdEnum.A.getName());
                } else if (FrontSeaSourceIdEnum.B.getValue().equals(rdto.getSourceId())) {
                    rdto.setSourceId(FrontSeaSourceIdEnum.B.getName());
                } else if (FrontSeaSourceIdEnum.C.getValue().equals(rdto.getSourceId())) {
                    rdto.setSourceId(FrontSeaSourceIdEnum.C.getName());
                } else if (FrontSeaSourceIdEnum.D.getValue().equals(rdto.getSourceId())) {
                    rdto.setSourceId(FrontSeaSourceIdEnum.D.getName());
                }
                if (FrontSeaRskMarkEnum.B1.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.B1.getName());
                } else if (FrontSeaRskMarkEnum.B2.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.B2.getName());
                } else if (FrontSeaRskMarkEnum.B3.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.B3.getName());
                } else if (FrontSeaRskMarkEnum.C1.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.C1.getName());
                } else if (FrontSeaRskMarkEnum.C2.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.C2.getName());
                } else if (FrontSeaRskMarkEnum.C3.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.C3.getName());
                } else if (FrontSeaRskMarkEnum.C4.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.C4.getName());
                } else if (FrontSeaRskMarkEnum.N99.getValue().equals(rdto.getRskMark())) {
                    rdto.setRskMark(FrontSeaRskMarkEnum.N99.getName());
                }
                return Result.success(rdto);
            } else {
                return Result.error(1, result.getMsg());
            }
        } catch (Exception e) {
            log.error("前海征信黑名单异常！！！", e);
        }
        return null;
    }

    @GetMapping("/bairong")
    @ApiOperation(value = "百融核查")
    public Result<BairongMoreCheckDtoOut> bairong(String realName, String identityCard, String mobilePhone) {
        try {
            BairongMoreCheckDtoIn dto = new BairongMoreCheckDtoIn();
            dto.setRealName(realName);
            dto.setIdentityCard(identityCard);
            dto.setMobilePhone(mobilePhone);
            log.info("百融核查入参===>" + JSON.toJSONString(dto));
            com.ht.ussp.core.Result<BairongMoreCheckDtoOut> result = eipIntefaceRpc.moreCheck(dto);
            log.info("百融核查结果===>" + JSON.toJSONString(result));
            if ("0000".equals(result.getReturnCode())) {
                BairongMoreCheckDtoOut rdto = result.getData();
                if (null == rdto) {
                    return Result.error(1, "无信息");
                }
                log.info(JSON.toJSONString(rdto));
                return Result.success(rdto);
            } else {
                return Result.error(1, result.getMsg());
            }
        } catch (Exception e) {
            log.error("百融核查异常！！！", e);
        }
        return null;
    }

}
