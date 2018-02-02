/**
 * add by tanrq 2018/1/21
 */
layui.define(function (exports) {
    // var basePath = "http://localhost:9000/",
    var basePath = "http://10.110.1.240:30111/",
        rule = "uc";
    exports('ht_config', {
        app: "FK"
        , basePath: basePath + rule + "/"
        , loadMenuUrl: basePath + rule + "/auth/loadMenu"
        , loadBtnAndTabUrl: basePath + rule + "/auth/loadBtnAndTab"
        , loginUrl: basePath + "uaa/auth/login"
        , refreshTokenUrl: basePath + "uaa/auth/token"
    });
});