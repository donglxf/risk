package com.ht.risk.common.comenum;

public enum RuleCallTypeEnum {

	business("1", "business", "业务"), model("3", "model", "模型"), rule("2", "rule", "规则");

	private String type;
	private String code;
	private String name;

	RuleCallTypeEnum(String type, String code, String name) {
		this.type = type;
		this.code = code;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
