layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'jquery', 'laydate', 'form','myutil'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;
    var form = layui.form;
    console.log(form);
    //第一个实例
    //第一个实例
    table.render({
        elem: '#model_list'
        ,height: 'auto'
        ,url:pathConfig.activitiServicePath+ 'list' //数据接口
        ,id: 'testReload'
        ,page: true //开启分页
        ,cols: [[ //表头\
            {field: 'modelName', title: '模型名称', width:"35%"}
            ,{field: 'createDate', title: '创建时间', width:"35%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#taskDemo', width: "30%"}
        ]]
    });
    var active = {
        reload: function(){
            var modelName = $('#modelName');
            console.log(modelName.val());
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    "modelName": modelName.val()
                }
            });
        },
        taskReload: function(){
            var modelVersion = $('#modelVersion');
            table.reload('taskTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    "modelVersion": modelVersion.val()
                }
            });
        }
    };
    $('#task_search').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $('#model_search').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    table.on('tool(model)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelCode = data.modelCode;
        if (layEvent === 'detail_info') { //模型验证结果查询
            console.log(data);
            table.render({
                elem: '#task_list'
                , height: 'auto'
                , id:"taskTable"
                , url: pathConfig.activitiConfigPath+'verficationTaskPage?modelCode='+modelCode //数据接口
                , page: true //开启分页
                , where: {}
                , cols: [[ //表头\
                      {field: 'id', title: '流水号', align: "center", width: "15%"}
                    , {field: 'modelVersion', title: '模型版本', align: "center", width: "10%"}
                    , {field: 'modelStatus', title: '执行状态', align: "center", width: "10%",templet:'#statusTpl'}
                    , {field: 'procInstId', title: '模型实例ID', align: "center", width: "15%"}
                    , {field: 'createUser', title: '创建用户', align: "center", width: "15%"}
                    , {field: 'createTime', title: '创建时间', align: "center", width: "25%"}
                    , {fixed: 'right', title: "操作", width: 150, align: 'center', toolbar: '#taskBar', width: "10%"}
                ]]
            });
        }
    });
    table.on('tool(task)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var taskId = data.id;
        $("#task_hidden_input").val(taskId);
        if (layEvent === 'detail_info') { //模型验证结果查询
            var layIndex =layer.open({
                type: 2,
                shade: false,
                area: ['1000px', '600px'],
                title: "模型验证结果",
                //请求的弹出层路径
                content: "/rule/ui/model/verfication/result/detail",
                zIndex: layer.zIndex, //重点1
                success: function (layero, index) {
                    layer.setTop(layero); //重点2
                }
            });
        }
    });
});
