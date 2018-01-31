///////////////////////////////////////////////////////////////////////
var preBindUrl = "/rule/service/variableBind/";
var preUrl = "/log/droolsLog/";
var p_sceneId = -1,versionIds=0;
layui.use(['table', 'form','laydate','util'], function () {
    var laydate = layui.laydate;
    var layer = layui.layer;
   var table = layui.table;
    var entityTable = layui.table;
    var app = layui.app, $ = layui.jquery, form = layui.form;
    // 第一个实例
    entityTable.render({
        elem: '#demo',
        height: 550,
        cellMinWidth: 80,
        url: preUrl + 'page/' // 数据接口
        ,
        page: true // 开启分页
        ,
        id: 'demos',
        cols: [[ // 表头
            {
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
                    key: $('#startDate').val()
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});


