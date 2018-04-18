package com.ht.risk.eip.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.BlackRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.eip.dto.*;
import com.ht.risk.eip.logs.CommonLogService;
import com.ht.risk.eip.util.BlackAtiveUtil;
import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/black")
@Api(tags = "BlackController", description = "黑名单接口", hidden = true)
public class BlackController {

    protected static final Logger log = LoggerFactory.getLogger(BlackController.class);

    @Autowired
    private BlackRpc tcRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BlackAtiveUtil util;

    @Autowired
    private CommonLogService commonLogService;

    @PostMapping("/netLoan")
    @ApiOperation(value = "网贷黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NetLoanOut> netLoan(@RequestBody NetLoanIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<NetLoanOutResult> cacheResult =  mongoTemplate.find(query,NetLoanOutResult.class);
        Result<NetLoanOut> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty() && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.netLoan(input);
            updateNetLoanResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("netLoan",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheNetLoan")
    @ApiOperation(value = "网贷黑名单,直接访问天秤接口",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NetLoanOut> noCacheNetLoan(@RequestBody NetLoanIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<NetLoanOut> result =  tcRpc.netLoan(input);
        updateNetLoanResult(result,input.getIdentityCard());
        commonLogService.insertLog("netLoan","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheOldLai")
    @ApiOperation(value = "老赖黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OldLaiOut> noCacheOldLai(@RequestBody OldLaiIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<OldLaiOut> result =  tcRpc.oldLai(input);
        updateOldLaiResult(result,input.getIdentityCard());
        commonLogService.insertLog("oldLai","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/oldLai")
    @ApiOperation(value = "老赖黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OldLaiOut> oldLai(@RequestBody OldLaiIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<OldLaiOutResult> cacheResult =  mongoTemplate.find(query,OldLaiOutResult.class);
        Result<OldLaiOut> result = null;
        String type = "1";
        // 存在相关记录
        if(cacheResult.size()>0 && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.oldLai(input);
            updateOldLaiResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("oldLai",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/self")
    @ApiOperation(value = "自有黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SelfDtoOut> self(@RequestBody OldLaiIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<SelfDtoOutResult> cacheResult =  mongoTemplate.find(query,SelfDtoOutResult.class);
        Result<SelfDtoOut> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty() && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.self(input);
            updateSelfResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("self",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheSelf")
    @ApiOperation(value = "自有黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SelfDtoOut> noCacheSelf(@RequestBody OldLaiIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<SelfDtoOut> result =  tcRpc.self(input);
        updateSelfResult(result,input.getIdentityCard());
        commonLogService.insertLog("self","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/frontSea")
    @ApiOperation(value = "前海黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FrontSeaDtoOut> frontSea(@RequestBody FrontSeaDtoIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<FrontSeaDtoOutResult> cacheResult =  mongoTemplate.find(query,FrontSeaDtoOutResult.class);
        Result<FrontSeaDtoOut> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty() && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.frontSea(input);
            updateFrontSeaResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("frontSea",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }


    @PostMapping("/noCacheFrontSea")
    @ApiOperation(value = "前海黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FrontSeaDtoOut> noCacheFrontSea(@RequestBody FrontSeaDtoIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<FrontSeaDtoOut> result =  tcRpc.frontSea(input);
        updateFrontSeaResult(result,input.getIdentityCard());
        commonLogService.insertLog("frontSea","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/baiqishi")
    @ApiOperation(value = "白骑士黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BaiqishiDtoOut> baiqishi(@RequestBody NetLoanIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<BaiqishiDtoOutResult> cacheResult =  mongoTemplate.find(query,BaiqishiDtoOutResult.class);
        Result<BaiqishiDtoOut> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty() && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.baiqishi(input);
            updateBaiqishiResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("baiqishi",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheBaiqishi")
    @ApiOperation(value = "白骑士黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BaiqishiDtoOut> noCacheBaiqishi(@RequestBody NetLoanIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<BaiqishiDtoOut> result =  tcRpc.baiqishi(input);
        updateBaiqishiResult(result,input.getIdentityCard());
        commonLogService.insertLog("oldLai","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/mobileValid")
    @ApiOperation(value = "手机号验证",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MobileValidDtoOut> mobileValid(@RequestBody MobileValidDtoIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在相关记录
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<MobileValidDtoOutResult> cacheResult =  mongoTemplate.find(query,MobileValidDtoOutResult.class);
        Result<MobileValidDtoOut> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty() && util.hasBlackActive(cacheResult.get(0).getCreateTime())){
            result = Result.buildSuccess(cacheResult.get(0).getData());
            type = "0";
        }else{
            result =  tcRpc.mobileValid(input);
            updateMobileValidResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("mobileValid",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheMobileValid")
    @ApiOperation(value = "手机号验证",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MobileValidDtoOut> noCacheMobileValid(@RequestBody MobileValidDtoIn input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<MobileValidDtoOut> result =  tcRpc.mobileValid(input);
        updateMobileValidResult(result,input.getIdentityCard());
        commonLogService.insertLog("mobileValid","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/klRiskBlack")
    @ApiOperation(value = "考拉黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<KlRiskBlackListRespDto> klRiskBlack(@RequestBody KlRiskBlackListReqDto input) throws Exception{
        long startTime = System.currentTimeMillis();
        //查询本地该身份证是否存在黑白名单
        Query query = new Query(Criteria.where("identityCard").is(input.getIdentityCard()));
        List<KlRiskBlackListRespDto> cacheResult =  mongoTemplate.find(query,KlRiskBlackListRespDto.class);
        Result<KlRiskBlackListRespDto> result = null;
        String type = "1";
        // 存在相关记录
        if(!cacheResult.isEmpty()){
            result = Result.buildSuccess(cacheResult.get(0));
            type = "0";
        }else{
            result =  tcRpc.klRiskBlack(input);
            updateKlRiskBlackResult(result,input.getIdentityCard());
        }
        commonLogService.insertLog("klRiskBlack",type,input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    @PostMapping("/noCacheKlRiskBlack")
    @ApiOperation(value = "考拉黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<KlRiskBlackListRespDto> noCacheKlRiskBlack(@RequestBody KlRiskBlackListReqDto input) throws Exception{
        long startTime = System.currentTimeMillis();
        Result<KlRiskBlackListRespDto> result =  tcRpc.klRiskBlack(input);
        updateKlRiskBlackResult(result,input.getIdentityCard());
        commonLogService.insertLog("klRiskBlack","1",input,result,System.currentTimeMillis()-startTime);
        return result;
    }

    /**
     * 老嬾黑名單更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateOldLaiResult( Result<OldLaiOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<OldLaiOutResult> list = mongoTemplate.find(query,OldLaiOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, OldLaiOutResult.class);
            }else{
                OldLaiOutResult outResult = new OldLaiOutResult(result);
                outResult.setIdentityCard(identityCard);
                mongoTemplate.insert(outResult);
            }
        }
    }


    /**
     * 网贷黑名单更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateNetLoanResult(Result<NetLoanOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<NetLoanOutResult> list = mongoTemplate.find(query,NetLoanOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, NetLoanOutResult.class);
            }else{
                NetLoanOutResult outResult = new NetLoanOutResult(result);
                outResult.setIdentityCard(identityCard);
                mongoTemplate.insert(outResult);
            }
        }
    }


    /**
     * 自有黑名单更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateSelfResult(Result<SelfDtoOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<SelfDtoOutResult> list = mongoTemplate.find(query,SelfDtoOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, SelfDtoOutResult.class);
            }else{
                SelfDtoOutResult outResult = new SelfDtoOutResult(result);
                outResult.setIdentityCard(identityCard);
                mongoTemplate.insert(outResult);
            }
        }
    }


    /**
     * 前海黑名单更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateFrontSeaResult(Result<FrontSeaDtoOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<FrontSeaDtoOutResult> list = mongoTemplate.find(query,FrontSeaDtoOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, FrontSeaDtoOutResult.class);
            }else{
                FrontSeaDtoOutResult outResult = new FrontSeaDtoOutResult(result);
                outResult.setIdentityCard(identityCard);
                mongoTemplate.insert(outResult);
            }
        }
    }

    /**
     * 白骑士黑名单更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateBaiqishiResult(Result<BaiqishiDtoOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<BaiqishiDtoOutResult> list = mongoTemplate.find(query,BaiqishiDtoOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, BaiqishiDtoOutResult.class);
            }else{
                BaiqishiDtoOutResult outResult = new BaiqishiDtoOutResult();
                outResult.setCodeDesc(result.getCodeDesc());
                outResult.setCreateTime(new Date(System.currentTimeMillis()));
                outResult.setData(result.getData());
                outResult.setMsg(result.getMsg());
                outResult.setReturnCode(result.getReturnCode());
                mongoTemplate.insert(outResult);
            }
        }
    }


    /**
     * 手机号验证更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateMobileValidResult(Result<MobileValidDtoOut> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<MobileValidDtoOutResult> list = mongoTemplate.find(query,MobileValidDtoOutResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, MobileValidDtoOutResult.class);
            }else{
                MobileValidDtoOutResult outResult = new MobileValidDtoOutResult();
                outResult.setCodeDesc(result.getCodeDesc());
                outResult.setCreateTime(new Date(System.currentTimeMillis()));
                outResult.setData(result.getData());
                outResult.setMsg(result.getMsg());
                outResult.setReturnCode(result.getReturnCode());
                mongoTemplate.insert(outResult);
            }
        }
    }


    /**
     * 考拉黑名单更新到mongoDB，之保存成功的紀錄
     * @param result
     * @param identityCard
     */
    private void updateKlRiskBlackResult(Result<KlRiskBlackListRespDto> result,String identityCard){
        if(ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())) {
            Query query = new Query(Criteria.where("identityCard").is(identityCard));
            List<KlRiskBlackListRespDtoResult> list = mongoTemplate.find(query,KlRiskBlackListRespDtoResult.class);
            if(!list.isEmpty()){
                Update update = new Update();
                update.set("codeDesc", result.getCodeDesc());
                update.set("returnCode", result.getReturnCode());
                update.set("createTime", new Date());
                update.set("msg", result.getMsg());
                update.set("identityCard", identityCard);
                update.set("data", result.getData());
                mongoTemplate.updateMulti(query, update, KlRiskBlackListRespDtoResult.class);
            }else{
                KlRiskBlackListRespDtoResult outResult = new KlRiskBlackListRespDtoResult();
                outResult.setCodeDesc(result.getCodeDesc());
                outResult.setCreateTime(new Date(System.currentTimeMillis()));
                outResult.setData(result.getData());
                outResult.setMsg(result.getMsg());
                outResult.setReturnCode(result.getReturnCode());
                mongoTemplate.insert(outResult);
            }
        }
    }

}
