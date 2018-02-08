layui.use(['table', 'jquery', 'element', 'laytpl'], function () {
    var $ = layui.jquery;
    var element = layui.element;
    var laytpl = layui.laytpl;
    var sence = $("#sence")
    var senceHtml = sence.html();
    var content = $("#main_content");
    console.log(parent);
    var taskId = $("#task_hidden_input", parent.document).val();
    console.log(taskId);
    $.ajax({
        cache : true,
        type : "GET",
        url : '/rule/service/verification/queryTaskVerficationResult',
        data:{
            "taskId":taskId
        },
        timeout : 6000, //超时时间设置，单位毫秒
        async : false,
        error : function(request) {
            layer.msg("查询失败！");
        },
        success : function(data) {
            if(data.code == 0){
                laytpl(senceHtml).render(data.data, function (html) {
                    var contentHtml = content.html()+html;
                    content.html(contentHtml);
                    element.render('test');
                });
            }
            if(data.code == 1){
                layer.msg("查询失败！");
            }
        }
    });

    $("#pass").click( function() {
        layer.load();
        var procReleaseId = $("#procReleaseId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: '/rule/service/verification/createSingleVerficationTask',
            data: {
                procReleaseId:procReleaseId
            },
            async: false,
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
    $("#unpass").click( function() {
        layer.load();
        var procReleaseId = $("#procReleaseId").val();


    });

    function verficationProcRelease(procReleaseId,checkType){
        $.ajax({
            cache: true,
            type: "POST",
            url: '/config/verification/verficationProcRelease',
            async: false,
            data: {
                procReleaseId:procReleaseId,
                type:checkType
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
