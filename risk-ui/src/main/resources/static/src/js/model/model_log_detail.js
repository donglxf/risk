layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'jquery', 'element', 'laytpl','myutil'], function () {
    var $ = layui.jquery;
    var element = layui.element;
    var laytpl = layui.laytpl;
    var procInstId = $("#procInstId_hidden_input", parent.document).val();
    var type = $("#type_hidden_input", parent.document).val();
    var taskId = $("#taskId_hidden_input", parent.document).val();
    layer.load();
    $.ajax({
        cache : true,
        type : "GET",
        url : pathConfig.ruleServicePath+'verification/queryModelLogResult',
        data:{
            "procInstId":procInstId,
            "type":type,
            "taskId":taskId
        },
        timeout : 6000, //超时时间设置，单位毫秒
        async : false,
        error : function(request) {
            layer.msg("查询失败！");
        },
        success : function(data) {
            if(data.code == 0){
                var flag = $("#verfication_layui_btn_div_show", parent.document).val();
                var hitRules = data.data.hitRules;
                var ruleHtml = "模型执行结果："+data.data.msg+"\n";
                ruleHtml += "\n";
                ruleHtml += "模型执行信息如下："+"\n";
                ruleHtml += data.data.modelMsg+"\n";
                ruleHtml += "\n";
                if(hitRules != null && hitRules.length >0){
                    ruleHtml += "命中如下规则："+"\n";
                    for(var i=0;i<hitRules.length;i++){
                        ruleHtml += "["+(i+1)+"]: "+hitRules[i].ruleDesc+"(命中次数："+hitRules[i].count+")"+"\n";
                    }
                }
                $("#modelResult").html(ruleHtml);
                layer.closeAll('loading');
            }
            if(data.code == 1){
                layer.msg("查询失败！");
            }
            layer.closeAll('loading');
        }
    });

    $("#pass").click( function() {
        layer.load();
        var validateFlag = $("#validateFlag").val();
        if(validateFlag == '1'){
            layer.msg("模型验证未成功，不能更新模型验证状态！");
            layer.closeAll('loading');
            return ;
        }
        var procReleaseId = $("#procReleaseId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.activitiConfigPath+'actProcRelease/verficationPass',
            async: false,
            data: {
                id:procReleaseId,
                isValidate:1,
                taskId:taskId
            },
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });

        //verficationProcRelease(procReleaseId,1);
    });
    $("#unpass").click( function() {
        layer.load();
        if(validateFlag == '1'){
            layer.msg("模型验证未成功，不能更新模型验证状态！");
            layer.closeAll('loading');
            return ;
        }
        var procReleaseId = $("#procReleaseId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.activitiConfigPath+'actProcRelease/verficationUnPass',
            async: false,
            data: {
                id:procReleaseId,
                isValidate:2,
                taskId:taskId
            },
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });
    });
    // 发起审批
    $("#approve").click( function() {
        layer.load();
        var validateFlag = $("#validateFlag").val();
        if(validateFlag == '1'){
            layer.msg("模型验证未成功，不能发起审批！");
            layer.closeAll('loading');
            return ;
        }
        var procReleaseId = $("#procReleaseId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.activitiConfigPath+'actProcRelease/approval',
            async: false,
            data: {
                id:procReleaseId,
                isApprove:3,
                taskId:taskId
            },
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });
    });

    function approveProcRelease(procReleaseId,isApprove){
        console.log(procReleaseId);
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.activitiConfigPath+'actProcRelease/update',
            async: false,
            data: {
                id:procReleaseId,
                isApprove:isApprove
            },
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });
    }


    function verficationProcRelease(procReleaseId,checkType){
        console.log(procReleaseId);
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.activitiConfigPath+'actProcRelease/update',
            async: false,
            data: {
                id:procReleaseId,
                isValidate:checkType
            },
            timeout: 60000,
            error : function(request) {
                layer.closeAll('loading');
                layer.msg("网络异常!");
            },
            success: function (data) {
                layer.closeAll('loading');
                layer.msg(data.msg);
            }
        });
    }


});
