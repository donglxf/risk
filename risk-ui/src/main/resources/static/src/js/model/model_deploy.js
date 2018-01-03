layui.use(['table','jquery'], function(){
    var table = layui.table;
    var $ = layui.jquery;
    //第一个实例
    table.render({
        elem: '#model_info_list'
        ,height: 'auto'
        ,url: '/activiti/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头\
            {field: 'name', title: '模型名称', width:"30%"},
            {field: 'description', title: '模型描述', width:"50%"},
            {fixed: 'right', align:'center', toolbar: '#barDemo', width: "20%"}
        ]]
    });

    table.render({
        elem: '#deploy_version_list'
        ,height: 'auto'
        ,url: '/activiti/getDeployVersionList' //数据接口
        ,page: false //开启分页
        ,cols: [[ //表头\
            {field: 'name', title: '模型名称', width:"30%"},
            {field: 'description', title: '模型描述', width:"50%"},
            {fixed: 'right', align:'center', toolbar: '#barDemo', width: "20%"}
        ]]
    });


});






