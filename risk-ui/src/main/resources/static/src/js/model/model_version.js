layui.use(['table','jquery'], function(){
    var table = layui.table;
    var $ = layui.jquery;
    //第一个实例
    table.render({
        elem: '#model_list'
        ,height: 'auto'
        ,url: '/activiti/list' //数据接口
        ,id: 'testReload'
        ,page: true //开启分页
        ,cols: [[ //表头\
             {field: 'name', title: '模型名称', width:"30%"}
            ,{field: 'desc', title: '模型描述', width:"15%", sort: true,templet: '#deployTpl'}
            ,{field: 'createTime', title: '创建时间', width:"20%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#modelBar', width: "35%"}
        ]]
    });
    table.render({
        elem: '#version_list'
        ,height: 'auto'
        ,url: '/config/page' //数据接口
        ,id: "versionReload"
        ,page: true //开启分页
        ,cols: [[ //表头\
            {field: 'modelVersion', title: '模型版本', width:"20%"}
            ,{field: 'status', title: '状态', width:"20%"}
            ,{field: 'createUser', title: '发布人', width:"20%"}
            ,{field: 'createTime', title: '发布时间', width:"20%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#versionBar', width: "20%"}
        ]]
    });
    table.on('tool(model)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        var deploymentId = data.deploymentId;
        console.log(data);
        if(layEvent === 'edit'){ //查看
            showdetail(modelId);
        }else if(layEvent === 'deploy'){// 发布测试版本
            deploy(modelId);
        } else if(layEvent === 'del'){ //删除
            deleteModel(obj,modelId,deploymentId);
        } else if(layEvent === 'version'){ //编辑
            queryVersionList(modelId);
        }
    });

    table.on('tool(version)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var modelId = data.id;
        var deploymentId = data.deploymentId;
        console.log(data);
        if(layEvent === 'start'){ //启用
            alert("启用");
        }else if(layEvent === 'stop'){// 停用
            alert("停用");
        } else if(layEvent === 'publish'){ //发布
            alert("发布");
        }
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
});











