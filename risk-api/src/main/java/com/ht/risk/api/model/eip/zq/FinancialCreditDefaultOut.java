package com.ht.risk.api.model.eip.zq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class FinancialCreditDefaultOut {
    @ApiParam("逾期最早出现时间")
    private String result_YQ_ZZSJ;
    @ApiParam("逾期最近出现时间")
    private String result_YQ_ZJSJ;
    @ApiParam("逾期累计出现次数")
    private String result_YQ_LJCS;
    @ApiParam("当前逾期金额")
    private String result_YQ_DQJE;
    @ApiParam("当前逾期时长")
    private String result_YQ_DQSC;
    @ApiParam("历史最大逾期金额")
    private String result_YQ_ZDJE;
    @ApiParam("历史最大逾期时长")
    private String result_YQ_ZDSC;
    @ApiParam("欺诈最早出现时间")
    private String result_QZ_ZZSJ;
    @ApiParam("欺诈最近出现时间")
    private String result_QZ_ZJSJ;
    @ApiParam("欺诈累计出现次数")
    private String result_QZ_LJCS;
    @ApiParam("失信最早出现时间")
    private String result_SX_ZZSJ;
    @ApiParam("失信最近出现时间")
    private String result_SX_ZJSJ;
    @ApiParam("失信累计出现次数")
    private String result_SX_LJCS;
}
