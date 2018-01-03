
///////////////////////////////////////////////////////////////////////
var preItemUrl = "/rule/service/actionParamInfo/";
var preUrl = "/rule/service/actionInfo/";
var layer,entityTable,itemTable,table,active,itemActive;
var actionId;
layui.use(['table','form'], function(){
    /**
     * 设置表单值
     * @param el
     * @param data
     */
    function setFromValues(el, data) {
        for (var p in data) {
            el.find(":input[name='" + p + "']").val(data[p]);
        }
    }
    layer = layui.layer;
    table = layui.table;
    entityTable = layui.table;
    itemTable = layui.table;
    var app = layui.app,
        $ = layui.jquery
    ,form = layui.form;
    //第一个实例
    entityTable.render({
        elem: '#demo'
        ,height: 550
        ,cellMinWidth: 80
        ,url: preUrl + 'page/' //数据接口
        // data:[{"conId":1,"entityName":"测试规则","entityDesc":"测试规则引擎","entityIdentify":"testrule","pkgName":"com.sky.testrule","creUserId":1,"creTime":1500522092000,"isEffect":1,"remark":null}]
        ,page: true //开启分页
        ,id:'demos'
        ,cols: [[ //表头
            //{field: 'conId',  event: 'setItem',title: 'ID',sort: true, fixed: 'left'}
             {field: 'actionType',  event: 'setItem',title: '动作类型'}
            ,{field: 'actionName', event: 'setItem', title: '动作名称'}
            ,{field: 'actionDesc', event: 'setItem', title: '动作描述'}
            ,{field: 'actionClass', event: 'setItem', title: '动作实现类'}
            ,{field: 'isEffect',  event: 'setItem',title: '状态', sort: true,templet: '#checkboxTpl', unresize: true,fixed: 'right'}
            ,{field: 'actionId', title: '操作', fixed: 'right',align:'center', toolbar: '#bar'}
        ]]
    });
    //重载
    //这里以搜索为例
     active = {
        reload: function(){
            //var demoReload = $('#demoReload');

            //执行重载
            table.reload('demos', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: $('#entityName_ser').val()
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //监听锁定操作
    form.on('checkbox(lockDemo)', function(obj){
        layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);
    });
    //监听工具条
    entityTable.on('tool(entityTable)', function(obj){
        var data = obj.data;
        console.log(obj);
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.actionId + ' 的查看操作');
        } else if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                $.get(preUrl+'delete/'+data.actionId,function (data) {
                    layer.msg("删除成功！");
                    obj.del();
                    layer.close(index);
                });

            });
        } else if(obj.event === 'edit'){
          //  layer.alert('编辑行：<br>'+ JSON.stringify(data))
            edit(data.actionId);
        }else if(obj.event === 'setItem'){
            //选择实体对象的id
            actionId = data.actionId;
            itemActive.reload(data.actionId);
        }
    });


    //第一个实例
    itemTable.render({
        elem: '#itemTable'
        ,height: 550
        ,cellMinWidth: 80
        ,url: '/rule/service/actionParamInfo/getAll/' //数据接口
        // data:[{"conId":1,"entityName":"测试规则","entityDesc":"测试规则引擎","entityIdentify":"testrule","pkgName":"com.sky.testrule","creUserId":1,"creTime":1500522092000,"isEffect":1,"remark":null}]
         ,page: false
        ,id:'itemT'
        ,cols: [[ //表头
            {field: 'actionParamId', title: 'ID',  sort: true, fixed: 'left'}
            ,{field: 'actionId', title: '动作ID'}
            ,{field: 'actionParamName', title: '参数名称'}
            ,{field: 'paramIdentify', title: '标识'}
            ,{field: 'actionParamDesc', title: '参数描述'}
            ,{field: 'actionParamId', title: '操作', fixed: 'right',align:'center', toolbar: '#item_bar'}
        ]]
    });
    //监听工具条
    itemTable.on('tool(itemTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.actionParamId + ' 的查看操作');
        } else if(obj.event === 'del2'){
            layer.confirm('真的删除行么', function(index){
                $.get(preUrl+'delete/'+data.actionParamId,function (data) {
                    layer.msg("删除成功！");
                    obj.del();
                    layer.close(index);
                });

            });
        } else if(obj.event === 'edit2'){
            //  layer.alert('编辑行：<br>'+ JSON.stringify(data))
            editItem(data.actionParamId);
        }
    });
    //重载
    //这里以搜索为例
    itemActive = {
        reload: function(actionId){
            //var demoReload = $('#demoReload');
            //执行重载
            table.reload('itemT', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                	actionId: actionId
                }
            });
        }
    };
    //新增
    $("#action_btn_add").on('click',function () {
        $.get('/rule/ui/rule/ruleAction/edit', null, function (form) {
            layer.open({
                type :1,
                title : '新增',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '600px', '460px' ],
                content :  form,
                btnAlign: 'c',
                btn: ['保存', '取消'],
                success: function (layero, index) {
                   // setFromValues(layero, result);
                }
                ,yes: function (index) {
                    //layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=formDemo]').click();
                    layer.close(index);
                },
            });
        });
    });
    function  edit(id) {
        $.get(preUrl+"getInfoById/"+id,function (data) {
            var result = data.data;
            $.get('/rule/ui/rule/ruleAction/edit', null, function (form) {
                layer.open({
                    type :1,
                    title : '修改',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '600px', '460px' ],
                    content :  form,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    success: function (layero, index) {
                        console.log(layero);
                        setFromValues(layero, result);
                        
                        var dataType = result.actionType;

                        layero.find("option:contains('"+dataType+"')").attr("selected",true);
                        console.log( layero.find("#dataId"));
                        var form = layui.form;
                        form.render();
                    }
                    ,yes: function (index) {
                        //layedit.sync(editIndex);
                        //触发表单的提交事件
                        $('form.layui-form').find('button[lay-filter=formDemo]').click();
                        layer.close(index);
                    },
                });
            });
        },'json')
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //新增
    $("#entity_item_btn_add").on('click',function () {
        if(actionId == undefined || actionId == ''){
            layer.msg("必须选择一个数据对象哦");
            return ;
        }

        $.get('/rule/ui/rule/ruleAction/actionParamEdit', null, function (form) {
            layer.open({
                type :1,
                title : '新增',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '550px', '560px' ],
                content :  form,
                btnAlign: 'c',
                btn: ['保存', '取消'],
                success: function (layero, index) {
                    var result = {"actionId":actionId};
                    setFromValues(layero, result);
                }
                ,yes: function (index) {
                    //layedit.sync(editIndex);
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=formDemo]').click();
                    layer.close(index);
                },
            });
        });
    });
    function  editItem(id) {
        $.get(preItemUrl+"getInfoById/"+id,function (data) {
            var result = data.data;
            $.get('/rule/ui/rule/ruleAction/actionParamEdit', null, function (form) {
                layer.open({
                    type :1,
                    title : '修改',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '550px', '560px' ],
                    content :  form,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    success: function (layero, index) {
                        setFromValues(layero, result);
                        var dataType = result.dataType;

                        layero.find("option:contains('"+dataType+"')").attr("selected",true);
                        console.log( layero.find("#dataId"));
                        var form = layui.form;
                        form.render('select');
                    }
                    ,yes: function (index) {
                        //layedit.sync(editIndex);
                        //触发表单的提交事件
                        $('form.layui-form').find('button[lay-filter=formDemo]').click();
                        layer.close(index);
                    },
                });
            });
        },'json')
    }
});
