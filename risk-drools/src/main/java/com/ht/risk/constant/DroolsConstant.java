package com.ht.risk.constant;

public class DroolsConstant {
	
	public final static String PACKAGE="package";
	public final static String PACKAGE_NAME="com.drools.rules";
	
	public final static String IMPORT="import";
	public final static String RULE_EXECUTIONR_RESULT="com.ht.risk.model.fact.RuleExecutionResult";
	public final static String RULE_EXECUTION_OBJECT="com.ht.risk.model.fact.RuleExecutionObject";
	public final static String DROOLS_ACTION_SERVICE="com.ht.risk.service.DroolsActionService";
	public final static String GLOBAL="global";
	public final static String GLOBAL_VARIABLE="RuleExecutionResult";
	public final static String GLOBAL_VARIABLE_NAME="_result";
	public final static String String="java.lang.String";
	public final static String LIST="java.util.List";
	public final static String MAP="java.util.Map";
	
	
	public final static String CONDITION_FACT="$fact";
	public final static String CONDITION_FACT_VALUE="RuleExecutionObject()";
	public final static String CONDITION_ACTION="$action";
	public final static String CONDITION_ACTION_VALUE="DroolsActionService()";
	public final static String CONDITION_MAP="$map";
	
	public final static String EXECUTE_METHOD="execute";
	public final static String SAVE_LOG="saveLog";
	
	public final static String DRL_PATH="src/main/resources/com/drools/rules/";
	
}
