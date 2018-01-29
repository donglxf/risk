layui.use(['table', 'jquery'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    //第一个实例
    table.render({
        elem: '#model_publish_list'
        , height: 'auto'
        , url: '/rule/service/actProcRelease/list' //数据接口
        , page: true //开启分页
        , cols: [[ //表头\
            {field: 'id', title: '序号', width: "15%", align: "center", sort: true}
            , {field: 'modelName', title: '模型名称', width: "20%", align: "center"}
            , {field: 'modelCategory', title: '业务线', width: "10%", align: "center"}
            , {field: 'versionTypeAlia', title: '版本类型', width: "10%", align: "center"}
            , {field: 'modelVersion', title: '版本号', width: "10%", align: "center",sort: true}
            , {field: 'isApprovedAlia', title: '审核状态', width: "10%", align: "center"}
            , {field: 'isEffectAlia', title: '启用状态', width: "10%", align: "center"}
            , {fixed: '', width: 150, align: 'center', toolbar: '#barDemo', width: "15%", align: "center"}
        ]]
    });
    table.on('tool(model_publish_list)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        if (layEvent === 'publish') { //发布
            publish(obj, modelId);
        } else if (layEvent === 'disable') {// 停用
            disable(obj,modelId)
        } else if (layEvent === 'republish') { //重新启用
            republish(obj, modelId);
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

    function publish(obj, modelId) {
        layer.confirm('确认启用模型吗', function (index) {
            //向服务端发送删除指令
            $.ajax({
                cache: true,
                type: "POST",
                url: '/rule/service/actProcRelease/publish?id=' + modelId,
                timeout: 6000, //超时时间设置，单位毫秒
                async: false,
                error: function (request) {
                    layer.msg("启用失败！");
                },
                success: function (data) {
                    window.location.href = '/rule/ui/model/publish/list';
                }
            });
        });
    }

    function republish(obj, modelId) {
        layer.confirm('确认启用模型吗', function (index) {
            //向服务端发送删除指令
            $.ajax({
                cache: true,
                type: "POST",
                url: '/rule/service/actProcRelease/republish?id=' + modelId,
                timeout: 6000, //超时时间设置，单位毫秒
                async: false,
                error: function (request) {
                    layer.msg("启用失败！");
                },
                success: function (data) {
                    window.location.href = '/rule/ui/model/publish/list';
                }
            });
        });
    }

    function disable(obj, modelId) {
        layer.confirm('确认停用模型吗', function (index) {
            //向服务端发送删除指令
            $.ajax({
                cache: true,
                type: "POST",
                url: '/rule/service/actProcRelease/disable?id=' + modelId,
                timeout: 6000, //超时时间设置，单位毫秒
                async: false,
                error: function (request) {
                    layer.msg("停用失败！");
                },
                success: function (data) {
                    window.location.href = '/rule/ui/model/publish/list';
                }
            });
        });
    }


});











