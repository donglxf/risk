package com.ht.risk.rule.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.ht.risk.rule.entity.enums.DataTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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
@Data
public class EntityItemInfo extends Model<EntityItemInfo> {

    private static final long serialVersionUID = 1L;
	@TableField(exist = false)
    private EntityInfo entityInfo;
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



	@TableField("item_identify")
	@ApiModelProperty(required= true,value = "字段标识")
	private String itemIdentify;
    /**
     * 属性描述
     */
	@TableField("item_desc")
	@ApiModelProperty(required= true,value = "属性描述")
	private String itemDesc;

	public DataTypeEnum getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeEnum dataType) {
		this.dataType = dataType;
	}

	@TableField("data_type")
	private DataTypeEnum dataType;

	@TableField("constant_id")
	@ApiModelProperty(required= true,value = "常量id")
	private Long constantId;



	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	/**
     * 创建人
     */
	@TableField("cre_user_id")
	@ApiModelProperty(required= true,value = "创建人")
	private String creUserId;
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
	public EntityInfo getEntityInfo() {
		return entityInfo;
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
