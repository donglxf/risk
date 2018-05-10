package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.eip.*;
import com.ht.risk.api.model.eip.sp.*;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("risk-eip")
public interface EipServiceInterface {

	@RequestMapping("/zhengxin/wanda")
	public com.ht.ussp.core.Result getZhengxinWanda(WDEnterpriseDetailReqDto paramter);

	/**
	 * 天行数科
	 * @param paramter
	 * @return
	 */
	@RequestMapping("/news/negativeSearch")
	public com.ht.ussp.core.Result<NegativeSearchDtoOut> getNegativeSearch(NegativeSearchDtoIn paramter);

	/**
	 * 老赖黑名单
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/black/oldLai")
	public com.ht.ussp.core.Result<OldLaiOut> oldLai(OldLaiIn input) throws Exception;

	/**
	 * 网贷黑名单
	 * @param input
	 * @return
	 */
	@PostMapping("/black/netLoan")
	public com.ht.ussp.core.Result<NetLoanOut> netLoan(NetLoanIn input);

	/**
	 * 自有黑名单
	 * @param input
	 * @return
	 */
	@PostMapping("/black/self")
	public com.ht.ussp.core.Result<SelfDtoOut> self(OldLaiIn input);

	/**
	 * 考拉黑名单
	 * @param input
	 * @return
	 */
	@PostMapping("/black/klRiskBlack")
	public com.ht.ussp.core.Result<KlRiskBlackListRespDto> kl(KlRiskBlackListReqDto input);


	/**
	 * 汇法个人信息统计
	 * @param input
	 * @return
	 */
	@PostMapping("/lawxp/personClassify")
	public com.ht.ussp.core.Result<LawxpPersonClassifyDtoOut> personClassify(LawxpPersonClassifyDtoIn input);

	/**
	 * 身份证和真实姓名实名认证，商汤
	 * @param input
	 * @return
	 */
	@PostMapping("/st/idVerify")
	public com.ht.ussp.core.Result<IdVerifyRespDto> idVerify(IdVerifyReqDto input);

	/**
	 * 手机号验证，考拉
	 * @param input
	 * @return
	 */
	@PostMapping("/black/mobileValid")
	public com.ht.ussp.core.Result<MobileValidDtoOut> mobileValid(MobileValidDtoIn input);

	/**
	 * 白骑士黑名单
	 * @param input
	 * @return
	 */
	@PostMapping("/black/baiqishi")
	public com.ht.ussp.core.Result<BaiqishiDtoOut> baiqishi(NetLoanIn input);

	/**
	 * 前海黑名单
	 * @param input
	 * @return
	 */
	@PostMapping("/black/frontSea")
	public com.ht.ussp.core.Result<FrontSeaDtoOut> frontSea(FrontSeaDtoIn input);

	/**
	 * 微众法院
	 * @param input
	 * @return
	 */
	@PostMapping("/lawxp/webank")
	public com.ht.ussp.core.Result<LawxpWebankDtoOut> webank(LawxpWebankDtoIn input);

	/**
	 * 百融多次申请核查V2
	 * @param input
	 * @return
	 */
	@PostMapping("/bairong/moreCheck")
	public com.ht.ussp.core.Result<BairongMoreCheckDtoOut> moreCheck(BairongMoreCheckDtoIn input) throws Exception;

	/**
	 * 魔蝎淘宝信息
	 * @param input
	 * @return
	 */
	@PostMapping("/moxie/taobaoGetAll")
	public com.ht.ussp.core.Result<TaobaoInfoDtoOut> taobaoGetAll(TaobaoInfoDtoIn input);


	/**
	 *运营商 上传用户信息
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/userBaseInfo")
	public com.ht.ussp.core.Result<UserBaseInfoDtoOut> userBaseInfo(UserBaseInfoDtoIn input);

	/**
	 *运营商 校验验证码
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/validCode")
	public com.ht.ussp.core.Result<SpValidCodeDtoOut> validCode(SpValidCodeDtoIn input);

	/**
	 *运营商 登录
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/login")
	public com.ht.ussp.core.Result<SpLoginDtoOut> login(SpLoginDtoIn input);

	/**
	 *运营商 请求短信动态验证码
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/reqDynamicCode")
	public com.ht.ussp.core.Result<SpDetailOrderCodeDtoOut> reqDynamicCode(SpDynamicCodeDtoIn input);

	/**
	 *运营商 获取详单验证码
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/getDetailOrderCode")
	public com.ht.ussp.core.Result<SpDetailOrderCodeDtoOut> getDetailOrderCode(SpValidCodeDtoIn input);

	/**
	 *运营商 发送短信动态码
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/sendDynamicCode")
	public com.ht.ussp.core.Result<SpDetailOrderCodeDtoOut> sendDynamicCode(SendDynamicCodeDtoIn input);

	/**
	 *运营商 通话记录查询
	 * @param input
	 * @return
	 */
	@PostMapping("/sp/queryPhoneRecord")
	public com.ht.ussp.core.Result<QueryPhoneRecordDtoOut> queryPhoneRecord(QueryPhoneRecordDtoIn input);

	/**
	 * 电话邦催收分
	 * @param input
	 * @return
	 */
	@PostMapping("/dianhua/collectionMin")
	public com.ht.ussp.core.Result<DianhuaCollectionMinDtoOut> collectionMin(DianhuaCollectionMinDtoIn input);



}
