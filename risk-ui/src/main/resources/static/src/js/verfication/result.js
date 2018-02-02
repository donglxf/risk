/*var data = [
    {
        "hitRule": {},
        "paramterData": {
            "variableVos": [{
                "valibaleCn": "年龄",
                "submitName": "age",
                "type": "Double",
                "value": "20"
            }, {
                "valibaleCn": "学历",
                "submitName": "education",
                "type": "Double",
                "value": "本科"
            }, {
                "valibaleCn": "工资",
                "submitName": "salary",
                "type": "Double",
                "value": "100"
            }, {
                "valibaleCn": "芝麻分",
                "submitName": "zhimaScore",
                "type": "Double",
                "value": "750"
            }]
        },
        "senceInfo": {
            "name": "黑灰名单策略"
        }
    },{
        "hitRule": {},
        "paramterData": {
            "variableVos": [{
                "valibaleCn": "年龄",
                "submitName": "age",
                "type": "Double",
                "value": "20"
            }, {
                "valibaleCn": "学历",
                "submitName": "education",
                "type": "Double",
                "value": "本科"
            }, {
                "valibaleCn": "工资",
                "submitName": "salary",
                "type": "Double",
                "value": "100"
            }, {
                "valibaleCn": "芝麻分",
                "submitName": "zhimaScore",
                "type": "Double",
                "value": "750"
            }]
        },
        "senceInfo": {
            "name": "房贷评分模型"
        }
    },{
        "hitRule": {},
        "paramterData": {
            "variableVos": [{
                "valibaleCn": "年龄",
                "submitName": "age",
                "type": "Double",
                "value": "20"
            }, {
                "valibaleCn": "学历",
                "submitName": "education",
                "type": "Double",
                "value": "本科"
            }, {
                "valibaleCn": "工资",
                "submitName": "salary",
                "type": "Double",
                "value": "100"
            }, {
                "valibaleCn": "芝麻分",
                "submitName": "zhimaScore",
                "type": "Double",
                "value": "750"
            }]
        },
        "senceInfo": {
            "name": "房贷评分模型"
        }
    }];*/
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
                    content.html(html);
                    element.render('test');
                });
            }
            if(data.code == 1){
                layer.msg("查询失败！");
            }
        }
    });
});
