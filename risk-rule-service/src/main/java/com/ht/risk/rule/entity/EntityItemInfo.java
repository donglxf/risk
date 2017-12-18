package com.ht.risk.rule.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 实体属性信息
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@ApiModel
@TableName("rule_entity_item_info")
public class EntityItemInfo extends Model<EntityItemInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("item_id")
	@ApiModelProperty(required= true,value = "主键")
	private Long itemId;
    /**
     * 实体id
     */
	@TableField("entity_id")
	@ApiModelProperty(required= true,value = "实体id")
	private Long entityId;
    /**
     * 字段名称
     */
	@TableField("item_name")
	@ApiModelProperty(required= true,value = "字段名称")
	private String itemName;
    /**
     * 字段标识
     */
	@TableField("item_identify")
	@ApiModelProperty(required= true,value = "字段标识")
	private String itemIdentify;
    /**
     * 属性描述
     */
	@TableField("item_desc")
	@ApiModelProperty(required= true,value = "属性描述")
	private String itemDesc;
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
     * 是否有效
     */
	@TableField("is_effect")
	@ApiModelProperty(required= true,value = "是否有效")
	private Integer isEffect;
    /**
     * 备注
     */
	@ApiModelProperty(required= true,value = "备注")
	private String remark;


	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemIdentify() {
		return itemIdentify;
	}

	public void setItemIdentify(String itemIdentify) {
		this.itemIdentify = itemIdentify;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
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

	public Integer getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(Integer isEffect) {
		this.isEffect = isEffect;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.itemId;
	}

	@Override
	public String toString() {
		return "EntityItemInfo{" +
			"itemId=" + itemId +
			", entityId=" + entityId +
			", itemName=" + itemName +
			", itemIdentify=" + itemIdentify +
			", itemDesc=" + itemDesc +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			", isEffect=" + isEffect +
			", remark=" + remark +
			"}";
	}
}
