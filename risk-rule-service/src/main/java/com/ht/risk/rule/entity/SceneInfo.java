package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@TableName("rule_scene_info")
public class SceneInfo extends Model<SceneInfo> {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId("scene_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long sceneId;
    /**
     * 标识
     */
	@TableField("scene_identify")
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
    /**
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否有效")
	private Integer isEffect;
    /**
     * 创建人
     */
	@TableField("cre_user_id")
	@ApiModelProperty(required= true,value = "创建人")
	private Long creUserId;
    /**
     * 创建时间
     */
	@TableField("cre_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date creTime;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;


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
