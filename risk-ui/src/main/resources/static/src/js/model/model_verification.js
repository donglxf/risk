layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'jquery', 'laydate', 'form','laytpl','myutil'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var form = layui.form;
    var laytpl = layui.laytpl;
    //第一个实例
    table.render({
        elem: '#model_list'
        , height: 'auto'
        , url: pathConfig.activitiConfigPath+'actProcRelease/list?isValidate=0' //数据接口
        , page: true //开启分页
        , where: {}
        , cols: [[ //表头\
              {field: 'id', title: '流水号', align: "center", width: "10%",sort: true}
            , {field: 'modelName', title: '模型名称', align: "center", width: "10%"}
            , {field: 'modelCode', title: '模型编码', align: "center", width: "10%"}
            , {field: 'modelVersion', title: '测试版本号', align: "center", width: "10%",sort: true}
            , {field: 'isBing', title: '变量是否绑定', align: "center", width: "10%",templet: '#bindTpl'}
            , {field: 'isValidate', title: '验证状态', align: "center", width: "10%", templet: '#verficationTpl'}
            , {field: 'createUser', title: '创建人', align: "center", width: "10%"}
            , {field: 'createTime', title: '创建时间', align: "center", width: "10%"}
            , {fixed: 'right', title: "操作", align: 'center', toolbar: '#barDemo', width: "20%"}
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

    var modeid = "";
    table.on('tool(model)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        modelIds = modelId;
        if (layEvent === 'manual_verification') { //手动验证
            var layIndex = layer.open({
                type: 2,
                shade: false,
                title: "模型手动验证",
                //请求的弹出层路径
                content: pathConfig.ruleUiPath+"model/valiable",
                zIndex: layer.zIndex, //重点1
                success: function (layero, index) {
                    layer.setTop(layero); //重点2
                }
            });
            layer.full(layIndex);
        } else if (layEvent === 'auto_verification') {
            var layIndex = layer.open({
                type: 2,
                shade: false,
                title: "模型自动验证",
                //请求的弹出层路径
                content: pathConfig.ruleUiPath+"model/valiable/auto",
                zIndex: layer.zIndex, //重点1
                success: function (layero, index) {
                    layer.setTop(layero); //重点2
                }
            });
            layer.full(layIndex);
        }
    });
    form.on('submit(save)', function (data) {
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.ruleServicePath+'actProcRelease/scene/variable/init',
            data: data.field,// 你的formid
            async: false,
            success: function (data) {
                layer.msg(data.msg);
            }
        });
        return false;
    });
    form.on('submit(verfication)', function (data) {
        layer.load();
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.ruleServicePath+'verification/createSingleVerficationTask',
            data: data.field,// 你的formid
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
        return false;
    });
    form.on('submit(save_auto)', function (data) {
        console.log('保存自动校验变量');
        $.ajax({
            cache: true,
            type: "POST",
            url: pathConfig.ruleServicePath+'actProcRelease/scene/variable/init/auto',
            data: data.field,// 你的formid
            async: false,
            success: function (data) {
                layer.msg(data.msg);
            }
        });
        return false;
    });



//时间选择器
    laydate.render({
        elem: '.date'
        , type: 'datetime'
    });

    $('.time').each(function () {
        var id = $(this).attr("id");
        laydate.render({
            elem: '#' + id
            , type: 'datetime'
        });

    });

    /**
     * 渲染业务下拉框
     */
    $(function () {
        $.ajax({
            cache: true,
            type: "GET",
            url: '/rule/service/business/getAll',
            timeout: 6000, //超时时间设置，单位毫秒
            async: false,
            success: function (data) {
                var demo= $("#demo");
                var getTpl = demo.html();
                var view = document.getElementById('modelCategory');
                laytpl(getTpl).render(data, function(html){
                    view.innerHTML = html;
                });
            }
        });
    })
});