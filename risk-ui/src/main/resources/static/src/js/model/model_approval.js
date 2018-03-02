layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table','jquery','myutil'], function(){
    var table = layui.table;
    var $ = layui.jquery;
    var common = layui.myutil;
    var modelId = null;
    //查询构造
    common.business.init("",$("#business_ser"),"businessId_ser");
    //第一个实例
    table.render({
        elem: '#model_approval_list'
        ,height: 'auto'
        ,url: pathConfig.activitiConfigPath+'actProcRelease/list?isApprove=3' //数据接口
        ,id: 'testReload'
        ,page: true //开启分页
        ,cols: [[ //表头\
            {field: 'modelName', title: '模型名称', width:"20%"}
            ,{field: 'modelVersion', title: '模型版本', width:"20%"}
            ,{field: 'modelCode', title: '模型编码', width:"15%"}
            ,{field: 'createUser', title: '创建人', width:"10%"}
            ,{field: 'createTime', title: '创建时间', width:"15%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#approvalBar', width: "20%"}
        ]]
    });
    table.on('tool(model)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var id = data.id;
        var deploymentId = data.deploymentId;
        var taskId = data.approveTaskId;
        console.log(data);
        if(layEvent === 'pass'){ //通过
            layer.confirm('您确认审核通过吗？', function(index){
                updateRelease(id,1);
                layer.close(index);
            });
        }else if(layEvent === 'noPass'){// 不通过
            layer.confirm('您确认审核不通过吗？', function(index){
                updateRelease(id,2);
                layer.close(index);
            });
        } else if(layEvent == 'detail'){ //查看详情
            $("#task_hidden_input").val(taskId);
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
    var active = {
        reload:function(){
            var modelName = $('#modelNameId');
            var modelType = $("#businessId_ser");
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    "modelName": modelName.val(),
                    "modelCategory":modelType.val()
                }
            });
        }
    };
    function reloadData(){
        var modelName = $('#modelNameId');
        var modelType = $("#businessId_ser");
        table.reload('testReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                "modelName": modelName.val(),
                "modelCategory":modelType.val()
            }
        });
    }
    $('#model_search').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $('#version_search').on('click', function(){
        var type = $(this).data('type');
        console.log(type);
        active[type] ? active[type].call(this) : '';
    });
    // 审批
    function updateRelease(procReleaseId,isEffect){
        $.ajax({
            cache : true,
            type : "POST",
            url : pathConfig.activitiConfigPath+'actProcRelease/update',
            data:{
                id:procReleaseId,
                isApprove:isEffect
            },
            timeout : 6000, //超时时间设置，单位毫秒
            async : false,
            error : function(request) {
                layer.msg("网络异常！");
            },
            success : function(data) {
                layer.msg(data.msg);
                reloadData();
            }
        });
    }

    function searchVerficationDetail(){

    }

});











