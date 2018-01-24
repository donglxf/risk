package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 规则引擎使用场景
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_scene_info s left join rule_scene_version v on v.scene_id = s.scene_id")
@Data
public class SceneInfoVersion extends Model<SceneInfoVersion> {

    private static final long serialVersionUID = 1L;
    /**
     * 标识
     */
	@TableField("s.scene_identify")
	@ApiModelProperty(required= true,value = "标识")
	private String sceneIdentify;
    /**
     * 类型(暂不使用)
     */
	@TableField("scene_type")
	@ApiModelProperty(required= true,value = "类型(暂不使用)")
	private Integer sceneType;
    /**
     * 名称
     */
	@TableField("scene_name")
	@ApiModelProperty(required= true,value = "名称")
	private String sceneName;
    /**
     * 描述
     */
	@TableField("scene_desc")
	@ApiModelProperty(required= true,value = "描述")
	private String sceneDesc;


	@TableField("business_id")
	@ApiModelProperty(required= true,value = "业务线id")
	private Long businessId;
    /**
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "策略是否有效")
	private Integer isEffect;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;
	@ApiModelProperty(required= true,value = "版本是否有效")
	private Integer status ;
	/**
	 * 版本记录id
	 */
	@TableId(value="version_id", type= IdType.AUTO)
	@ApiModelProperty(required= true,value = "版本记录id")
	private Long versionId;
	/**
	 * 版本号
	 */
	@TableField("v.`version`")
	@ApiModelProperty(required= true,value = "版本号 ")
	private String version;
	/**
	 * 类型：1决策或评分卡2模型
	 */
	@ApiModelProperty(required= true,value = "类型：0测试版 1正式版")
	private Integer type;



	/**
	 * 正式版本号
	 */
	@TableField("official_version")
	@ApiModelProperty(required = true, value = "正式版本号")
	private String officialVersion;


	@TableField("test_status")
	@ApiModelProperty(required= true,value = "测试是否通过，1-通过，0-未通过")
	private Integer testStatus;
	/**
	 * 标题
	 */
	@ApiModelProperty(required= true,value = "标题")
	private String title;
	/**
	 * 详细描述
	 */
	@ApiModelProperty(required= true,value = "详细描述")
	private String comment;
	/**
	 * 业务id
	 */
	@TableField("v.scene_id")
	@ApiModelProperty(required= true,value = "业务id")
	private String sceneId;
	/**
	 * 创建时间
	 */
	@TableField("v.cre_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date creTime;
	/**
	 * 创建用户
	 */
	@TableField("v.cre_user_id")
	@ApiModelProperty(required= true,value = "创建用户")
	private Long creUserId;
	/**
	 * 规则html
	 */
	@TableField("rule_div")
	@ApiModelProperty(required= true,value = "规则html")
	private String ruleDiv;
	/**
	 * rule文件内容
	 */
	@TableField("rule_drl")
	@ApiModelProperty(required= true,value = "rule文件内容")
	private String ruleDrl;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
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


	public String getSceneIdentify() {
		return sceneIdentify;
	}

	public void setSceneIdentify(String sceneIdentify) {
		this.sceneIdentify = sceneIdentify;
	}

	public Integer getSceneType() {
		return sceneType;
	}

	public void setSceneType(Integer sceneType) {
		this.sceneType = sceneType;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getSceneDesc() {
		return sceneDesc;
	}

	public void setSceneDesc(String sceneDesc) {
		this.sceneDesc = sceneDesc;
	}

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public Long getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(Long creUserId) {
		this.creUserId = creUserId;
	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.sceneId;
	}

	@Override
	public String toString() {
		return "SceneInfo{" +
			"sceneId=" + sceneId +
			", sceneIdentify=" + sceneIdentify +
			", sceneType=" + sceneType +
			", sceneName=" + sceneName +
			", sceneDesc=" + sceneDesc +
			", isEffect=" + isEffect +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			", remark=" + remark +
			"}";
	}
}
