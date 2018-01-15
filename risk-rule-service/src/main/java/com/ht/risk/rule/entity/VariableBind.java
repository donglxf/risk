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
 * 
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@ApiModel
@TableName("RISK_VARIABLE_BIND")
public class VariableBind extends Model<VariableBind> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键,流水号
     */
    @TableId("ID")
	@ApiModelProperty(required= true,value = "主键,流水号")
	private Long id;
    /**
     * 決策版本流水
     */
	@TableField("SENCE_VERSIONID")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private String senceVersionid;
    /**
     * 变量编码
     */
	@TableField("VARIABLE_CODE")
	@ApiModelProperty(required= true,value = "变量编码")
	private String variableCode;
    /**
     * 变量名称
     */
	@TableField("VARIABLE_NAME")
	@ApiModelProperty(required= true,value = "变量名称")
	private String variableName;
    /**
     * 绑定数据表
     */
	@TableField("BIND_TABLE")
	@ApiModelProperty(required= true,value = "绑定数据表")
	private String bindTable;
    /**
     * 绑定数据表字段
     */
	@TableField("BIND_COLUMN")
	@ApiModelProperty(required= true,value = "绑定数据表字段")
	private String bindColumn;
    /**
     * 是否生效：0-有效，1-无效
     */
	@TableField("IS_EFFECT")
	@ApiModelProperty(required= true,value = "是否生效：0-有效，1-无效")
	private String isEffect;
    /**
     * 创建用户
     */
	@TableField("CREATE_USER")
	@ApiModelProperty(required= true,value = "创建用户")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenceVersionid() {
		return senceVersionid;
	}

	public void setSenceVersionid(String senceVersionid) {
		this.senceVersionid = senceVersionid;
	}

	public String getVariableCode() {
		return variableCode;
	}

	public void setVariableCode(String variableCode) {
		this.variableCode = variableCode;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getBindTable() {
		return bindTable;
	}

	public void setBindTable(String bindTable) {
		this.bindTable = bindTable;
	}

	public String getBindColumn() {
		return bindColumn;
	}

	public void setBindColumn(String bindColumn) {
		this.bindColumn = bindColumn;
	}

	public String getIsEffect() {
		return isEffect;
	}

	public void setIsEffect(String isEffect) {
		this.isEffect = isEffect;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	
	@Override
	public String toString() {
		return "VariableBind{" +
			"id=" + id +
			", senceVersionid=" + senceVersionid +
			", variableCode=" + variableCode +
			", variableName=" + variableName +
			", bindTable=" + bindTable +
			", bindColumn=" + bindColumn +
			", isEffect=" + isEffect +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
