package com.ht.risk.eip.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 老赖黑名单
 *
 * @author:喻尊龙
 * @date: 2018/2/2
 */
@Data
public class LlBlackListRespDto implements Serializable {
    private static final long serialVersionUID = 3182953994474257293L;

    /**
     * 记录ID
     */
    private String globalId;
    /**
     * 真实姓名
     */
    private String partyName;
    /**
     * 身份证号
     */
    private String partyIdentityCard;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 执行法院
     */
    private String courtName;
    /**
     * 省份
     */
    private String areaName;
    /**
     * 执行依据文号
     */
    private String gistId;
    /**
     * 立案时间
     */
    private String regDate;
    /**
     * 案号
     */
    private String caseCode;
    /**
     * 做出执行依据单位
     */
    private String gistUnit;
    /**
     * 生效法律文书确定的义务
     */
    private String duty;
    /**
     * 被执行人的履行情况
     */
    private String performance;
    /**
     * 失信被执行人行为具体情形
     */
    private String disruptTypeName;
    /**
     * 发布时间
     */
    private String publishDate;

}
