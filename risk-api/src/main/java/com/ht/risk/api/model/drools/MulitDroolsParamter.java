package com.ht.risk.api.model.drools;

import java.util.List;
import java.util.Map;

public class MulitDroolsParamter extends  DroolsParamter{

	private List<Map<String,Object>> mulitDate;

	public List<Map<String, Object>> getMulitDate() {
		return mulitDate;
	}

	public void setMulitDate(List<Map<String, Object>> mulitDate) {
		this.mulitDate = mulitDate;
	}
}
