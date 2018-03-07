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
        elem: '#model_list'
        ,height: 'auto'
        ,url: pathConfig.activitiServicePath+'list' //数据接口
        ,id: 'testReload'
        ,page: true //开启分页
        ,cols: [[ //表头\
             {field: 'modelName', title: '模型名称', width:"35%"}
            ,{field: 'createDate', title: '创建时间', width:"35%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#modelBar', width: "30%"}
        ]]
    });
    table.render({
        elem: '#version_list'
        ,height: 'auto'
        ,url: pathConfig.activitiConfigPath+'page?isApprove=1' //数据接口
        ,id: "versionReload"
        ,page: true //开启分页
        ,cols: [[ //表头\
             {field: 'modelCode', title: '模型编码', width:"20%"}
            ,{field: 'modelVersion', title: '模型版本', width:"10%",templet:'#versionTpl'}
            ,{field: 'isEffect', title: '状态', width:"10%", templet: '#statusTpl'}
            ,{field: 'createUser', title: '发布人', width:"20%"}
            ,{field: 'createTime', title: '发布时间', width:"20%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#versionBar', width: "20%"}
        ]]
    });
    table.on('tool(model)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.modelId;
        $("#modelId").val(modelId);
        var deploymentId = data.deploymentId;
        console.log(data);
         if(layEvent === 'version'){ //查看
            queryVersionList(modelId);
        }
    });
    table.on('tool(version)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var id = data.id;
        var deploymentId = data.deploymentId;
        console.log(data);
        if(layEvent === 'start'){ //启用
            updateRelease(id,0);
        }else if(layEvent === 'stop'){// 停用
            updateRelease(id,1);
        } else if(layEvent === 'publish'){ //发布
            updateRelease(id,0,1);
        }
    });
    var active = {
        reload: function(){
            var modelName = $('#modelName');
            var modelType = $("#businessId_ser");
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    "modelName": modelName.val(),
                    "modeType":modelType.val()
                }
            });
        },
        versionReload: function(){
            var modelVersion = $('#modelVersion');
            table.reload('versionReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    "modelVersion": modelVersion.val()
                }
            });
        }
    };
    $('#model_search').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    function queryVersionList(modelId){
        table.reload('versionReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            ,where: {
                "modelId": modelId
            }
        });
    }
    $('#version_search').on('click', function(){
        var type = $(this).data('type');
        console.log(type);
        active[type] ? active[type].call(this) : '';
    });

    // 启用
    function updateRelease(procReleaseId,isEffect,versionType){
        $.ajax({
            cache : true,
            type : "POST",
            url : pathConfig.activitiConfigPath+'actProcRelease/status',
            data:{
                id:procReleaseId,
                isEffect:isEffect,
                versionType:versionType
            },
            timeout : 6000, //超时时间设置，单位毫秒
            async : false,
            error : function(request) {
                layer.msg("网络异常！");
            },
            success : function(data) {
                layer.msg(data.msg);
                var modelId = $("#modelId").val();
                queryVersionList(modelId);
            }
        });
    }
});











