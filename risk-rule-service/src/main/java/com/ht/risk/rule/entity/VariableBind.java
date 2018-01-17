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
 * @since 2018-01-17
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
	@TableField("SENCE_VERSION_ID")
	@ApiModelProperty(required= true,value = "決策版本流水")
	private Long senceVersionId;
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
     * 变量类型，与RULE_ENTITY_ITEM_INFO.DATA_TYPE 一致
     */
	@TableField("DATA_TYPE")
	@ApiModelProperty(required= true,value = "变量类型，与RULE_ENTITY_ITEM_INFO.DATA_TYPE 一致")
	private String dataType;
    /**
     * 常量ID，与RULE_ENTITY_ITEM_INFO.CONSTANT_ID 一致
     */
	@TableField("CONSTANT_ID")
	@ApiModelProperty(required= true,value = "常量ID，与RULE_ENTITY_ITEM_INFO.CONSTANT_ID 一致")
	private Long constantId;
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
     * 用户输入值，只保存最后一次的
     */
	@TableField("TMP_VALUE")
	@ApiModelProperty(required= true,value = "用户输入值，只保存最后一次的")
	private String tmpValue;
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

	public Long getSenceVersionId() {
		return senceVersionId;
	}

	public void setSenceVersionId(Long senceVersionId) {
		this.senceVersionId = senceVersionId;
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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getConstantId() {
		return constantId;
	}

	public void setConstantId(Long constantId) {
		this.constantId = constantId;
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

	public String getTmpValue() {
		return tmpValue;
	}

	public void setTmpValue(String tmpValue) {
		this.tmpValue = tmpValue;
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
			", senceVersionId=" + senceVersionId +
			", variableCode=" + variableCode +
			", variableName=" + variableName +
			", dataType=" + dataType +
			", constantId=" + constantId +
			", bindTable=" + bindTable +
			", bindColumn=" + bindColumn +
			", isEffect=" + isEffect +
			", tmpValue=" + tmpValue +
			", createUser=" + createUser +
			", createTime=" + createTime +
			"}";
	}
}
