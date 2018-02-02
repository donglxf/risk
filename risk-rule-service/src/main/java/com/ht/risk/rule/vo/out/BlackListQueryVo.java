package com.ht.risk.rule.vo.out;

import lombok.Data;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/2/2 10:50
 */
@Data
public class BlackListQueryVo {

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 证件号
     */
    private String identityCard;
    /**
     * 功能码
     */
    private String functionCode;
    /**
     * 查询识别
     * queryType=1 只查库；
     * queryType=2先查库，如果没结果再调用第三方接口；
     * queryType=0直接调用第三方接口
     */
    private String queryType;

}
