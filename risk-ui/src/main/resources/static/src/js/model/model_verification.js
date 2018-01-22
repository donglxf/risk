layui.use(['table', 'jquery', 'laydate', 'form'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var form = layui.form;

    console.log(form);
    //第一个实例
    table.render({
        elem: '#model_list'
        , height: 'auto'
        , url: '/rule/service/actProcRelease/list' //数据接口
        , page: true //开启分页
        , where: {}
        , cols: [[ //表头\
            {field: 'id', title: '序号', align: "center", width: "15%"}
            , {field: 'modelName', title: '模型名称', align: "center", width: "15%"}
            , {field: 'modelCategory', title: '业务线', align: "center", width: "20%"}
            , {field: 'modelVersion', title: '版本', align: "center", width: "10%"}
            , {field: 'status', title: '变量绑定', align: "center", width: "10%"}
            , {field: 'isValidateAlia', title: '验证状态', align: "center", width: "10%"}
            , {fixed: 'right', title: "操作", width: 150, align: 'center', toolbar: '#barDemo', width: "20%"}
        ]]
    });

    var active = {
        reload: function () {
            var modelName = $('#modelName').val();
            var isValidate = $('#isValidate').val();
            var modelCategory = $('#modelCategory').val();
            //执行重载
            table.reload('model_list', {
                page: {
                    curr: 1, //重新从第 1 页开始
                }
                , where: {
                    modelName: modelName,
                    isValidate: isValidate,
                    modelCategory: modelCategory
                }
            });
        }
    };

//        触发查询
    $("#find").click(function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    table.on('tool(model)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        console.log(modelId);
        if (layEvent === 'manual_verification') { //手动验证
            //接口未做，需要异步请求模型数据
            $.ajax({
                type: "get",
                url: "/rule/service/actProcRelease/scene/variable/manual",
                data: {
                    actProcRealeseId: modelId
                },
                dataType: "json",
                success: function (data) {
                    var modelVerification = new ModelVerification();
                    var contents = modelVerification.initModel(data);
                    var layIndex = layer.open({
                        type: 2,
                        shade: false,
                        title: "",
                        //请求的弹出层路径
                        content: "/rule/ui/model/valiable",
                        zIndex: layer.zIndex, //重点1
                        success: function (layero, index) {
                            layer.setTop(layero); //重点2
                            var form = layer.getChildFrame('#model_valiable_form', index);
                            form.html(contents);
                            //日期控件
                            laydate.render({
                                elem: "#date"
                                //elem: "[lay-verify='date']"
                            });
                        }
                    });
                    layer.full(layIndex);
                }
            });
        } else if (layEvent = 'auto_verification') {
            console.log('自动测试');
            $.ajax({
                type: "get",
                url: "/rule/service/actProcRelease/scene/variable/auto",
                data: {
                    actProcRealeseId: modelId
                },
                dataType: "json",
                success: function (data) {
                    var modelAutoVerification = new ModelAutoVerification();
                    var contents = modelAutoVerification.initModel(data);
                    console.log('自动测试返回数据');
                    var layIndex = layer.open({
                        type: 2,
                        shade: false,
                        title: "",
                        content: "/rule/ui/model/valiable/auto",
                        zIndex: layer.zIndex, //重点1
                        success: function (layero, index) {
                            layer.setTop(layero); //重点2
                            layer.setTop(layero); //重点2
                            var form = layer.getChildFrame('#model_valiable_form_auto', index);
                            form.html(contents);

                        }
                    });
                    layer.full(layIndex);
                }
            });
        }
    });


    form.on('submit(save)', function (data) {
        $.ajax({
            cache: true,
            type: "POST",
            url: '/rule/service/actProcRelease/scene/variable/init',
            data: data.field,// 你的formid
            async: false,
            success: function (data) {
                layer.msg(data.msg);
            }
        });
        return false;
    });

    $("#test").click(function () {
        layer.msg("需调用其它接口");
    });
});
