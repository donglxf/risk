layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table','jquery','laydate','myutil'], function(){
    var table = layui.table;
    var $ = layui.jquery;
    table.render({
        elem: '#model_list'
        ,height: 'auto'
        ,url: pathConfig.ruleServicePath+'model/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头\
             {field: 'name', title: '模型名称', width:"20%"}
            ,{field: 'category', title: '模型分类', width:"20%"}
            ,{field: 'version', title: '模型版本', width:"20%"}
            ,{field: 'status', title: '验证状态', width:"10%"}
            ,{field: 'createTime', title: '创建时间', width:"10%"}
            ,{field: 'createTime', title: '更新时间', width:"10%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo', width: "10%"}
        ]]
    });
    table.on('tool(model)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        if(layEvent === 'verification'){ //验证
            var data = {
                "modelName": "房贷模型",
                "modelVersion": "v1.01",
                "variableMap": [
                    {
                        "senceName": "房贷评分卡",
                        "data": [
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "sex",
                                "valibaleCn": "性别",
                                "submitName": "sex",
                                "type": "select",
                                "desc": "性别",
                                "optionData": [
                                    {
                                        "value": "01",
                                        "name": "男"
                                    }, {
                                        "value": "02",
                                        "name": "女"
                                    },
                                ]
                            }
                        ]
                    },
                    {
                        "senceName": "车贷评分卡",
                        "data": [
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "date",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "sex",
                                "valibaleCn": "性别",
                                "submitName": "sex",
                                "type": "select",
                                "desc": "性别",
                                "optionData": [
                                    {
                                        "value": "01",
                                        "name": "男"
                                    }, {
                                        "value": "02",
                                        "name": "女"
                                    },
                                ]
                            }
                        ]
                    }
                ]
            };
            var modelName = data.modelName;
            var modelVerification = new ModelVerification();
            var contents = modelVerification.initModel(data);
            var layIndex = layer.open({
                type: 2,
                shade: false,
                title:modelName,
                content: pathConfig.ruleUiPath+"model/valiable",
                area: ['1000px', '600px'],
                zIndex: layer.zIndex, //重点1
                success: function(layero,index){
                    layer.setTop(layero); //重点2
                    var form = layer.getChildFrame('#model_valiable_form', index);
                    form.html(contents);
                }
            });
           // layer.full(layIndex);
        }
    });
});
