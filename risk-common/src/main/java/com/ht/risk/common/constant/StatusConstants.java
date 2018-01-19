package com.ht.risk.common.constant;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/1/18 11:54
 */
public interface StatusConstants {
    /**
     * 对应数据库值为0
     */
    static final String NOT_YET_VALIDATE = "待验证";
    /**
     * 对应数据库值为1
     */
    static final String ALREADY_VALIDATE = "通过";
    /**
     * 对应数据库值为2
     */
    static final String NOT_ALLOW_VALIDATE = "未通过";
}
