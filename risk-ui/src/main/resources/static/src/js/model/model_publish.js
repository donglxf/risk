layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'jquery','laytpl','myutil'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laytpl = layui.laytpl;
    //第一个实例
    table.render({
        elem: '#model_publish_list'
        , height: 'auto'
        , url:pathConfig.ruleServicePath+ 'actProcRelease/list' //数据接口
        , page: true //开启分页
        , cols: [[ //表头\
            {field: 'id', title: '序号', width: "15%", align: "center", sort: true}
            , {field: 'modelName', title: '模型名称', width: "20%", align: "center"}
            , {field: 'modelCategory', title: '业务线', width: "10%", align: "center"}
            , {field: 'versionTypeAlia', title: '版本类型', width: "10%", align: "center"}
            , {field: 'modelVersion', title: '版本号', width: "10%", align: "center", sort: true}
            , {field: 'isApprovedAlia', title: '审核状态', width: "10%", align: "center"}
            , {field: 'isEffectAlia', title: '启用状态', width: "10%", align: "center"}
            , {fixed: '', width: 150, align: 'center', toolbar: '#barDemo', width: "15%", align: "center"}
        ]]
    });

    table.on('tool(model_publish_list)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var modelId = data.id;
        if (layEvent === 'publish') { //发布
            changeEffect(obj, modelId, 1);
        } else if (layEvent === 'disable') {// 停用
            changeEffect(obj, modelId, 0);
        } else if (layEvent === 'republish') { //重新启用
            changeEffect(obj, modelId, 2);
        }
    });

    var active = {
        reload: function () {
            var modelName = $('#modelName').val();
            var isEffect = $('#isEffect').val();
            var modelCategory = $('#modelCategory').val();
            table.reload('model_publish_list', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    "modelName": modelName,
                    "isEffect": isEffect,
                    "modelCategory": modelCategory
                }
            });
        }
    };

    //        触发查询
    $("#find").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function changeEffect(obj, modelId, flag) {
        var tips = '';
        if (flag == '0') {
            tips = "确认停用模型?"
        } else if (flag == "1") {
            tips = "确认发布模型?"
        } else {
            tips = "确认启用模型?"
        }
        layer.confirm(tips, function (index) {
            $.ajax({
                cache: true,
                type: "PUT",
                url: pathConfig.ruleServicePath+'actProcRelease/status?id=' + modelId + '&flag=' + flag,
                timeout: 6000, //超时时间设置，单位毫秒
                async: false,
                error: function (request) {
                    layer.msg("启用失败！");
                },
                success: function (data) {
                    if (data.code == "1") {
                        layer.msg("操作失败")
                    }
                    window.location.href = pathConfig.ruleUiPath+'model/publish/list';
                }
            });
        });
    }

    /**
     * 渲染业务下拉框
     */
    $(function () {
        $.ajax({
            cache: true,
            type: "GET",
            url: pathConfig.ruleServicePath+'business/getAll',
            timeout: 6000, //超时时间设置，单位毫秒
            async: false,
            success: function (data) {
                var getTpl = demo.innerHTML
                    ,view = document.getElementById('modelCategory');
                laytpl(getTpl).render(data, function(html){
                    view.innerHTML = html;
                });
            }
        });
    })
});











