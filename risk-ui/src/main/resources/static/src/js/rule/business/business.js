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

var  business = {
    baseUrl: "/rule/service/business/",
    uiUrl :"edit.html",
    tableId: "business",
    toolbarId: "#toolbar",
    unique: "id",
    order: "asc",
    currentItem: {}
};

//表头
business.cols = function () {
    return [ //表头
        //{field: 'businessId',  event: 'setItem',title: 'ID',sort: true, fixed: 'left'}
        {field: 'businessName',
            align:'center',
            title: '业务线名', sort: true
        }
        ,
        {field: 'businessDesc',
            align:'center',
            title: '描述'}
        ,
        {field: 'creTime',
            align:'center',
            width:180,
            title: '创建时间',sort: true
        }
        ,
        {field: 'businessId',
            title: '操作',
            fixed: 'right',
            align:'center',
            width:200,
            toolbar: business.toolbarId
        }
    ];
};
var layerTopIndex;
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table','form','laytpl','myutil','ht_auth'], function() {
    var laytpl = layui.laytpl;
    businessTable = layui.table;
    var app = layui.app,
        ht_auth=layui.ht_auth,
        form = layui.form;
    $ = layui.jquery;

    /**
     * 公共方法：保存
     * @param url
     * @param result
     */
    function save(url, result) {
        $.get(url, function (form) {
            layerTopIndex =  layer.open({
                type: 1,
                title: '保存信息',
                maxmin: true,
                shadeClose: false, // 点击遮罩关闭层
                area: ['550px', '460px'],
                content: form,
                btnAlign: 'c',
                btn: ['保存', '取消'],
                success: function (layero, index) {
                    setFromValues(layero, result);
                }
                , yes: function (index) {
                    //触发表单的提交事件
                    $('form.layui-form').find('button[lay-filter=formDemo]').click();
                    layer.close(index);
                },
            });
        });
    }


    //第一个实例
    businessTable.render({
        elem: '#'+business.tableId
        , height: 'full'
        // , cellMinWidth: 10
        , url: business.baseUrl + 'page' //数据接口
        // data:[{"businessId":1,"businessName":"测试规则","businessDesc":"测试规则引擎","businessIdentify":"testrule","pkgName":"com.sky.testrule","creUserId":1,"creTime":1500522092000,"isEffect":1,"remark":null}]
        , page: true //开启分页
        , id: business.tableId
        , cols: [business.cols()]
    });
    //重载
    //这里以搜索为例
    active = {
        reload: function () {
            //执行重载
            businessTable.reload(business.tableId, {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    key:$("#key").val()
                }
            });
        }
    };
    businessTable.on('renderComplete('+business.tableId+')', function (obj) {
        ht_auth.render();
    });
    //监听工具条
    businessTable.on('tool('+business.tableId+')', function (obj) {
        var data = obj.data;
        console.log(obj);
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                $.get(business.baseUrl + 'delete/' + data.businessId, function (data) {
                    layer.msg("删除成功！");
                    obj.del();
                    layer.close(index);
                });
            });
        }else if(obj.event === 'edit'){
            edit(data.businessId);
        }
    });
    //新增
    $("#scene_btn_add").on('click', function () {
        save(business.uiUrl, null);
    });
    //修改
    function edit(id) {
        $.get(business.baseUrl + "getInfoById/" + id, function (data) {
            var result = data.data;
            save(business.uiUrl, result);
        }, 'json')
    }
    ht_auth.render();
});