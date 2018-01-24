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
 * 业务线
 * </p>
 *
 * @author zhangpeng
 * @since 2018-01-22
 */
@ApiModel
@TableName("risk_business")
@Data
public class Business extends Model<Business> {

    private static final long serialVersionUID = 1L;

	@TableId(value="business_id", type= IdType.AUTO)
	@ApiModelProperty(required= true,value = "业务线ID")
	private Long businessId;
    /**
     * 业务线名
     */
	@TableField("business_name")
	@ApiModelProperty(required= true,value = "业务线名")
	private String businessName;
    /**
     * 描述
     */
	@TableField("business_desc")
	@ApiModelProperty(required= true,value = "描述")
	private String businessDesc;
    /**
     * 状态：1正常0禁用
     */
	@ApiModelProperty(required= true,value = "状态：1正常0禁用")
	private Integer status;
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date creTime;


	@Override
	protected Serializable pkVal() {
		return this.businessId;
	}

	@Override
	public String toString() {
		return "Business{" +
			"businessId=" + businessId +
			", businessName=" + businessName +
			", businessDesc=" + businessDesc +
			", status=" + status +
			", creUserId=" + creUserId +
			", creTime=" + creTime +
			"}";
	}
}
