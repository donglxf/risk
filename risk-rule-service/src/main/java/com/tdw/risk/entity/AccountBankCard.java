package com.tdw.risk.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 帐号银行卡
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-04
 */
@ApiModel
@TableName("tb_account_bank_card")
public class AccountBankCard extends Model<AccountBankCard> {

    private static final long serialVersionUID = 1L;

    /**
     * 账户银行卡编号guid
     */
    @TableId("account_bank_id")
	@ApiModelProperty(value = "账户银行卡编号guid")
	private String accountBankId;
    /**
     * 账户编号
     */
	@TableField("account_id")
	@ApiModelProperty(required= true,value = "账户编号")
	private String accountId;
    /**
     * 银行账号
     */
	@TableField("bank_card_no")
	@ApiModelProperty(required= true,value = "银行账号")
	private String bankCardNo;
    /**
     * 开户行
     */
	@TableField("open_bank")
	@ApiModelProperty(required= true,value = "开户行")
	private String openBank;
    /**
     * 开户支行
     */
	@TableField("open_sub_bank")
	@ApiModelProperty(required= true,value = "开户支行")
	private String openSubBank;
    /**
     * 银行卡归属地省
     */
	@TableField("bank_province")
	@ApiModelProperty(required= true,value = "银行卡归属地省")
	private String bankProvince;
    /**
     * 银行卡归属地市
     */
	@TableField("bank_city")
	@ApiModelProperty(required= true,value = "银行卡归属地市")
	private String bankCity;
    /**
     * 银行卡预留手机号码
     */
	@TableField("phone_number")
	@ApiModelProperty(required= true,value = "银行卡预留手机号码")
	private String phoneNumber;
    /**
     * 创建用户
     */
	@TableField("create_user")
	@ApiModelProperty(required= true,value = "创建用户")
	private String createUser;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@ApiModelProperty(required= true,value = "创建时间")
	private Date createTime;
    /**
     * 更新用户
     */
	@TableField("update_user")
	@ApiModelProperty(required= true,value = "更新用户")
	private String updateUser;
    /**
     * 更新时间
     */
	@TableField("update_time")
	@ApiModelProperty(required= true,value = "更新时间")
	private Date updateTime;
    /**
     * 状态1:可用,0:不可用
     */
	@TableField("status_flag")
	@ApiModelProperty(required= true,value = "状态1:可用,0:不可用")
	private Integer statusFlag;
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
	private String remark;
    /**
     * 是否主卡,1:主卡,0:副卡
     */
	@TableField("main_flag")
	@ApiModelProperty(required= true,value = "是否主卡,1:主卡,0:副卡")
	private Integer mainFlag;
    /**
     * 是否注册了存管,1.是,0:否
     */
	@TableField("cunguan_flag")
	@ApiModelProperty(required= true,value = "是否注册了存管,1.是,0:否")
	private Integer cunguanFlag;


	public String getAccountBankId() {
		return accountBankId;
	}

	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getOpenSubBank() {
		return openSubBank;
	}

	public void setOpenSubBank(String openSubBank) {
		this.openSubBank = openSubBank;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(Integer statusFlag) {
		this.statusFlag = statusFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMainFlag() {
		return mainFlag;
	}

	public void setMainFlag(Integer mainFlag) {
		this.mainFlag = mainFlag;
	}

	public Integer getCunguanFlag() {
		return cunguanFlag;
	}

	public void setCunguanFlag(Integer cunguanFlag) {
		this.cunguanFlag = cunguanFlag;
	}

	@Override
	protected Serializable pkVal() {
		return this.accountBankId;
	}

	@Override
	public String toString() {
		return "AccountBankCard{" +
			", accountBankId=" + accountBankId +
			", accountId=" + accountId +
			", bankCardNo=" + bankCardNo +
			", openBank=" + openBank +
			", openSubBank=" + openSubBank +
			", bankProvince=" + bankProvince +
			", bankCity=" + bankCity +
			", phoneNumber=" + phoneNumber +
			", createUser=" + createUser +
			", createTime=" + createTime +
			", updateUser=" + updateUser +
			", updateTime=" + updateTime +
			", statusFlag=" + statusFlag +
			", remark=" + remark +
			", mainFlag=" + mainFlag +
			", cunguanFlag=" + cunguanFlag +
			"}";
	}
}
