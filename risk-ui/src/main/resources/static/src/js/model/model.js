layui.use('table', function(){
    var table = layui.table;
    //第一个实例
    table.render({
        elem: '#model_list'
        ,height: 500
        ,url: '/rule/service/model/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头\
            {checkbox: true, width:"5%"}
            ,{field: 'name', title: '模型名称', width:"20%"}
            ,{field: 'category', title: '模型类别', width:"15%", sort: true}
            ,{field: 'createTime', title: '创建时间', width:"15%"}
            ,{field: 'description', title: '描述', width: "30%"}
            ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo', width: "15%"}
        ]]
    });
    table.on('tool(model)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(layEvent === 'detail'){ //查看
            console.log(data.id);
            var modelId = data.id;
            showdetail(modelId);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something
            //同步更新缓存对应的值
            obj.update({
                username: '123'
                ,title: 'xxx'
            });
        }
    });

});

function showdetail(modelId){
    var layIndex = layer.open({
        type: 2,
        shade: false,
        title:false,
        content: '/rule/ui/modelDetail?modelId='+modelId+"&date="+new Date(),
        zIndex: layer.zIndex, //重点1
        success: function(layero){
            layer.setTop(layero); //重点2
        }
    });
    layer.full(layIndex);
}

function addModel(){
    var layIndex = layer.open({
        type: 2,
        shade: false,
        title:"",
        area: ['800px','400px'],
        content: '/rule/ui/model/addView',
        zIndex: layer.zIndex, //重点1
        success: function(layero){
            layer.setTop(layero); //重点2
        }
    });
}







