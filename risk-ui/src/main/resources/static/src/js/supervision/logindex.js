///////////////////////////////////////////////////////////////////////
var preUrl = "/drools/droolsLog/";
var preDetailUrl = "/drools/droolsProcessLog/";
var p_logId = 0;
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名

});
layui.use(['table', 'form','laydate','util','myutil','element'], function () {
    var layer = layui.layer;
   var table = layui.table;
    var entityTable = layui.table,modelTable=layui.table;
    var app = layui.app, $ = layui.jquery, form = layui.form;
    // 第一个实例
    entityTable.render({
        elem: '#demo'
        ,height: 550
        ,cellMinWidth: 80
        ,url: preUrl + 'page' //数据接口
        ,page: true //开启分页
        ,id:'demos'
        ,cols: [[ // 表头
            {
                field: 'id',
                event: 'setItem',
                title: 'id'
            },{
                field: 'createTime',
                event: 'setItem',
                title: '触发时间',
                templet: '#createTime'
            }, {
                field: 'procinstId',
                event: 'setItem',
                title: '模型实例id'
            }, {
                field: 'modelName',
                event: 'setItem',
                title: '模型名',
                fixed: 'right'
            }, {
                field: 'senceVersionid',
                event: 'setItem',
                title: '决策版本号'
            }, {
                field: 'inParamter',
                event: 'setItem',
                title: '入参'
            }, {
                field: 'outParamter',
                event: 'setItem',
                title: '计算结果'
            }, {
                field: 'type',
                title: '调用方',
                align: 'center',
                templet: '#checkboxTpl'
            }, {
                field: 'type',
                title: '操作',
                align: 'center',
                templet: '#operation'
            }]]
    });

    modelTable.render({
        elem: '#modelTab'
        ,height: 550
        ,cellMinWidth: 80
        ,url: '/config/model/page' //数据接口
        ,page: true //开启分页
        ,id:'modelTab'
        ,cols: [[ // 表头
            {
                field: 'id',
                event: 'setItem',
                title: 'id'
            },{
                field: 'batchId',
                event: 'setItem',
                title: '批次号',
                templet: '#createTime'
            }, {
                field: 'procInstId',
                event: 'setItem',
                title: '模型实例id'
            }, {
                field: 'procReleaseId',
                event: 'setItem',
                title: '版本id',
                fixed: 'right'
            }, {
                field: 'status',
                event: 'setItem',
                title: '状态'
            }, {
                field: 'inParamter',
                event: 'setItem',
                title: '入参'
            }, {
                field: 'outParamter',
                event: 'setItem',
                title: '出参'
            }, {
                field: 'type',
                event: 'setItem',
                title: '类型'
            }, {
                field: 'spendTime',
                event: 'setItem',
                title: '花费时间'
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                templet: '#createTime'
            }, {
                field: 'type',
                title: '操作',
                align: 'center',
                templet: '#modelOperation'
            }]]
    });

    // 重载
    // 这里以搜索为例
    active = {
        reload: function () {
            // var demoReload = $('#demoReload');

            // 执行重载
            table.reload('demos', {
                page: {
                    curr: 1
                    // 重新从第 1 页开始
                },
                where: {
                    date: $('#startDate').val(),
                    logId:$('#logId').val()
                }
            });
        }
    };

    // 重载
    // 这里以搜索为例
    modelActive = {
        reload: function () {
            // var demoReload = $('#demoReload');

            // 执行重载
            table.reload('demos', {
                page: {
                    curr: 1
                    // 重新从第 1 页开始
                },
                where: {
                    date: $('#startDate').val(),
                    logId:$('#logId').val()
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    entityTable.on('tool(entityTable)',function(obj){
        var data = obj.data;
        if (obj.event === 'view'){
            edit(data.id);
        }
    });

    function edit(logId) {
        p_logId=logId;

        // $.get('/rule/ui/supervision/log/view',function(form) {
        //     layer.open({
        //         type : 1,
        //         title : '查看详情',
        //         maxmin : true,
        //         shadeClose : false, // 点击遮罩关闭层
        //         area : [ '730px', '460px' ],
        //         content : form,
        //         btn : [ '关闭'],
        //         btnAlign : 'c',
        //         zIndex: layer.zIndex, //重点1
        //         success : function(da, index) {
        //
        //             layer.setTop(da); //重点2
        //         }
        //     });
        // });

            layer.open({
                type : 2,
                title : '查看详情',
                maxmin : true,
                shadeClose : false, // 点击遮罩关闭层
                area : [ '730px', '460px' ],
                content : '/rule/ui/supervision/log/view',
                btn : [ '关闭'],
                btnAlign : 'c',
                zIndex: layer.zIndex, //重点1
                success : function(da, index) {

                    layer.setTop(da); //重点2
                }

        });
    }

});


