var modelIds="";
layui.use(['table', 'jquery', 'laydate', 'form'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var form = layui.form;


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

    var modeid = "";
    table.on('tool(model)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        modelIds = modelId;
        if (layEvent === 'manual_verification') { //手动验证
            var layIndex =layer.open({
                type: 2,
                shade: false,
                title: "",
                //请求的弹出层路径
                content: "/rule/ui/model/valiable",
                zIndex: layer.zIndex, //重点1
                success: function (layero, index) {
                    layer.setTop(layero); //重点2
                }
            });
            layer.full(layIndex);
        } else if (layEvent === 'auto_verification') {
            var layIndex =layer.open({
                type: 2,
                shade: false,
                title: "",
                //请求的弹出层路径
                content: "/rule/ui/model/valiable/auto",
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
            url: '/rule/service/actProcRelease/scene/variable/init',
            data: data.field,// 你的formid
            async: false,
            success: function (data) {
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
        url: '/rule/service/actProcRelease/scene/variable/init/auto',
        data: data.field,// 你的formid
        async: false,
        success: function (data) {
            layer.msg(data.msg);
        }
    });
    return false;
    });
    form.on('submit(verfication)', function (data) {
        $.ajax({
            cache: true,
            type: "POST",
            url: '/rule/service/verification/createSingleVerficationTask',
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
})
;

// var data = {
//     "modelName": "房贷模型",
//     "modelVersion": "v1.01",
//     "variableMap": [
//         {
//             "senceName": "房贷评分卡",
//             "data": [
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "sex",
//                     "valibaleCn": "性别",
//                     "submitName": "sex",
//                     "type": "select",
//                     "desc": "性别",
//                     "optionData": [
//                         {
//                             "value": "01",
//                             "name": "男"
//                         }, {
//                             "value": "02",
//                             "name": "女"
//                         },
//                     ]
//                 }
//             ]
//         },
//         {
//             "senceName": "车贷评分卡",
//             "data": [
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "input",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "name",
//                     "valibaleCn": "姓名",
//                     "submitName": "name",
//                     "type": "date",
//                     "desc": "姓名"
//                 },
//                 {
//                     "valibaleEn": "sex",
//                     "valibaleCn": "性别",
//                     "submitName": "sex",
//                     "type": "select",
//                     "desc": "性别",
//                     "optionData": [
//                         {
//                             "value": "01",
//                             "name": "男"
//                         }, {
//                             "value": "02",
//                             "name": "女"
//                         },
//                     ]
//                 }
//             ]
//         }
//     ]
// };