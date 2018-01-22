layui.use(['table', 'jquery', 'laydate'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
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
                    curr: 1 //重新从第 1 页开始
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
                type: "post",
                url: "/rule/service/actProcRelease/scene/variable",
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
                        content: "/rule/ui/model/valiable",
                        zIndex: layer.zIndex, //重点1
                        success: function (layero, index) {
                            layer.setTop(layero); //重点2
                            var form = layer.getChildFrame('#model_valiable_form', index);
                            form.html(contents);
                            laydate.render({
                                elem: "#date"
                                //elem: "[lay-verify='date']"
                            });
                        }
                    });
                    layer.full(layIndex);
                }
            });


        }
    });
});

/**
 * 返回的数据格式如下
 */
// {
//     modelName: "测试模型",
//         sceneList: [
//     {
//         scenceName: "房贷评分卡",
//         data: [
//             {
//                 name: "hello"
//             },
//             {
//                 age: 18
//             },
//             {
//                 marry: "Y"
//             }
//         ]
//     },
//     {
//         scenceName: "车贷评分卡",
//         data: [
//             {
//                 name: "hzm"
//             },
//             {
//                 age: 18
//             },
//             {
//                 marry: "Y"
//             }
//         ]
//     }
// ]
// }


/**
 * 请求id为  953535633275584514  的时候，后台返回数据如下
 * @type {ModelVerification}
 */
// data = {
//     "variableMap": [{
//         "data": [{
//             "id": "1",
//             "senceVersionId": "1",
//             "variableCode": "age",
//             "variableName": "年龄",
//             "dataType": "Double",
//             "bindTable": "TEMP_DATA_CONTAINS",
//             "bindColumn": "age2",
//             "isEffect": "1",
//             "createTime": 1516195542000
//         }, {
//             "id": "2",
//             "senceVersionId": "1",
//             "variableCode": "hourseAge",
//             "variableName": "楼龄",
//             "dataType": "Double",
//             "bindTable": "TEMP_DATA_CONTAINS",
//             "bindColumn": "hourseAge2",
//             "isEffect": "1",
//             "createTime": 1516195542000
//         }],
//         "sceneName": "测试",
//         "id": "953536595562131457",
//         "modelProcdefId": "hourse_process:27:190004",
//         "senceVersionId": "1",
//         "isEffect": "0",
//         "createTime": 1516175815000,
//         "createUser": "Robot"
//     }, {
//         "data": [],
//         "sceneName": "无名的策列表或评分卡",
//         "id": "953620371659091969",
//         "modelProcdefId": "hourse_process:27:190004",
//         "senceVersionId": "120022",
//         "isEffect": "0",
//         "createTime": 1516195789000,
//         "createUser": "Robot"
//     }, {
//         "data": [],
//         "sceneName": "无名的策列表或评分卡",
//         "id": "953620371659091970",
//         "modelProcdefId": "hourse_process:27:190004",
//         "senceVersionId": "120022",
//         "isEffect": "0",
//         "createTime": 1516195789000,
//         "createUser": "Robot"
//     }], "modelName": "房贷评分模型", "modelVersion": "V.27"
// }


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