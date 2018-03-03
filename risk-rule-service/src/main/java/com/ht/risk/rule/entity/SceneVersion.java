package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-03
 */
@ApiModel
@TableName("rule_scene_version")
public class SceneVersion extends Model<SceneVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 版本记录id
     */
    @TableId(value = "version_id", type = IdType.AUTO)
    @ApiModelProperty(required = true, value = "版本记录id")
    private Long versionId;
    /**
     * 版本号
     */
    @ApiModelProperty(required = true, value = "版本号 ")
    private String version;

    /**
     * 正式版本号
     */
    @TableField("official_version")
    @ApiModelProperty(required = true, value = "正式版本号")
    private String officialVersion;

    /**
     * 类型：1决策或评分卡2模型
     */
    @ApiModelProperty(required = true, value = "类型：0测试版 1正式版")
    private Integer type;

    /**
     * 标题
     */
    @ApiModelProperty(required = true, value = "标题")
    private String title;
    /**
     * 详细描述
     */
    @ApiModelProperty(required = true, value = "详细描述")
    private String comment;
    /**
     * 业务id
     */
    @TableField("scene_id")
    @ApiModelProperty(required = true, value = "场景id")
    private Long sceneId;

    /**
     * 业务id
     */
    @TableField("scene_identify")
    @ApiModelProperty(required = true, value = "场景code ")
    private String sceneIdentify;


    /**
     * 创建时间
     */
    @TableField("cre_time")
    @ApiModelProperty(required = true, value = "创建时间")
    private Date creTime;
    /**
     * 创建用户
     */
    @TableField("cre_user_id")
    @ApiModelProperty(required = true, value = "创建用户")
    private String creUserId;
    /**
     * 规则html
     */
    @TableField("rule_div")
    @ApiModelProperty(required = true, value = "规则html")
    private String ruleDiv;
    /**
     * rule文件内容
     */
    @TableField("rule_drl")
    @ApiModelProperty(required = true, value = "rule文件内容")
    private String ruleDrl;
    @TableField("status")
    @ApiModelProperty(required = true, value = "1启用 0 禁用")
    private Integer status;

    /**
     * 测试是否通过，1-通过，0-未通过
     */
    @TableField("test_status")
    @ApiModelProperty(required = true, value = "测试是否通过，1-通过，0-未通过")
    private Integer testStatus;

    /**
     * 业务类型，1-评分卡，2-决策表
     */
    @TableField("business_type")
    @ApiModelProperty(required = true, value = "业务类型，1-评分卡，2-决策表")
    private String businessType;
    /**
     * 业务线，1-房速贷，2-现金贷
     */
    @TableField("business_line")
    @ApiModelProperty(required = true, value = "业务线，1-房速贷，2-现金贷")
    private String businessLine;
    /**
     * 是否绑定变量，1-绑定，0-未绑定
     */
    @TableField("is_bind_var")
    @ApiModelProperty(required = true, value = "是否绑定变量，1-绑定，0-未绑定")
    private String isBindVar;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public String getCreUserId() {
        return creUserId;
    }

    public void setCreUserId(String creUserId) {
        this.creUserId = creUserId;
    }

    public String getRuleDiv() {
        return ruleDiv;
    }

    public void setRuleDiv(String ruleDiv) {
        this.ruleDiv = ruleDiv;
    }

    public String getRuleDrl() {
        return ruleDrl;
    }

    public void setRuleDrl(String ruleDrl) {
        this.ruleDrl = ruleDrl;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public String getSceneIdentify() {
        return sceneIdentify;
    }

    public void setSceneIdentify(String sceneIdentify) {
        this.sceneIdentify = sceneIdentify;
    }

    @Override
    protected Serializable pkVal() {
        return this.versionId;
    }

    public String getOfficialVersion() {
        return officialVersion;
    }

    public void setOfficialVersion(String officialVersion) {
        this.officialVersion = officialVersion;
    }

    public Integer getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(Integer testStatus) {
        this.testStatus = testStatus;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getIsBindVar() {
        return isBindVar;
    }

    public void setIsBindVar(String isBindVar) {
        this.isBindVar = isBindVar;
    }

    @Override
    public String toString() {
        return "SceneVersion{" +
                "versionId=" + versionId +
                ", version=" + version +
                ", officialVersion=" + officialVersion +
                ", type=" + type +
                ", title=" + title +
                ", comment=" + comment +
                ", sceneId=" + sceneId +
                ", creTime=" + creTime +
                ", creUserId=" + creUserId +
                ", ruleDiv=" + ruleDiv +
                ", ruleDrl=" + ruleDrl +
                "}";
    }
}
