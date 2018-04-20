/**
 * add by tanrq 2018/1/21
 */
layui.define(['config'],function (exports) {
    // var basePath = "http://localhost:9000/",
    var basePath = "http://172.16.200.110:30111/",
        rule = "uc";
    try {
        basePath = gatewayUrl ? gatewayUrl : basePath;
    } catch (e) {
        console.log(e);
    }
    exports('ht_config', {
        app: "FK"
        , basePath: basePath + rule + "/"
        , loadMenuUrl: basePath + rule + "/auth/loadMenu"
        , loadBtnAndTabUrl: basePath + rule + "/auth/loadBtnAndTab"
        , loginUrl: basePath + "uaa/auth/login"
        , loginIndex:basePath+"rule/ui/login.html"
        , refreshTokenUrl: basePath + "uaa/auth/token"
        , changePwdUrl : changePwdUrl

        , activitiConfigPath:"/config/" // 流程引擎配置模块根路径
        , activitiServicePath:"/activiti/" // 流程引擎配置模块根路径
        , ruleServicePath:"/rule/service/" // 流程引擎配置模块根路径
        , droolsSerivePath:"/drools/" // 流程引擎配置模块根路径
        ,dev:true
    });
});