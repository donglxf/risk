package com.ht.risk.api.constant.activiti;

public class ActivitiConstants {

    public final static String DROOL_RULE_SERVICE_NAME = "${droolsRuleEngineService}";

    public final static String DROOL_RULE_SERVICE_TYPE = "delegateExpression";

    public final static String PROC_VERSION_PREFIX = "V.";

    public final static String PROC_TASK_ID_CONSTANTS = "proc_task_id";

    public final static String DROOLS_VARIABLE_NAME = "DATA";

    /**
     * 模型调用类型参数名称，模型启动时传入，规则调用时需要从流程变量中获取，0-业务调用 1-验证中心调用
     */
    public final static String EXCUTE_TYPE_VARIABLE_NAME = "ExcuteType";
    /**
     * 实际业务调用
     */
    public final static String EXCUTE_TYPE_SERVICE = "0";
    /**
     *  验证
     */
    public final static String EXCUTE_TYPE_VERFICATION = "1";

}
