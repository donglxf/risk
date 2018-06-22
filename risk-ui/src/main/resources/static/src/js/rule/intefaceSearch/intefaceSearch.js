///////////////////////////////////////////////////////////////////////
var preUrl = "/rule/service/rpc/";
var layer, entityTable, itemTable, table, active, itemActive, topIndex;
var actionId;
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名

});
layui.use(['table', 'form', 'myutil', 'element'], function () {
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
    var app = layui.app,
        $ = layui.jquery
    var common = layui.myutil;

    function resultTab(da) {
        var html = '[';
        for (var i = 0; i < da.length; i++) {
            if (i == da.length - 1) {
                html += "{\"borrowDate\":\"" + da[i].borrowDate + "\",\"borrowAmount\":\"" + da[i].borrowAmount + "\",\"borrowPeriod\":\"" + da[i].borrowPeriod + "\",\"overdueDate\":\"" + da[i].overdueDate + "\",\"overdueLevel\":\"" + da[i].overdueLevel + "\",\"overdueAmount\":\"" + da[i].overdueAmount + "\"}";
            } else {
                html += "{\"borrowDate\":\"" + da[i].borrowDate + "\",\"borrowAmount\":\"" + da[i].borrowAmount + "\",\"borrowPeriod\":\"" + da[i].borrowPeriod + "\",\"overdueDate\":\"" + da[i].overdueDate + "\",\"overdueLevel\":\"" + da[i].overdueLevel + "\",\"overdueAmount\":\"" + da[i].overdueAmount + "\"},";
            }
        }
        html += "]";
        html = JSON.parse(html);
        table.render({
            elem: '#demo'
            , cols: [[ //标题栏
                {field: 'borrowDate', event: 'setItem', title: '借款日期', templet: '#chairConvert'}
                , {field: 'borrowAmount', event: 'setItem', title: '借款金额'}
                , {field: 'borrowPeriod', event: 'setItem', title: '借款期限'}
                , {field: 'overdueDate', event: 'setItem', title: '逾期日期'}
                , {field: 'overdueLevel', event: 'setItem', title: '逾期级别'}
                , {field: 'overdueAmount', event: 'setItem', title: '逾期金额'}
            ]]
            , data: html
            , even: true
        });

    }

    // //这里以搜索为例
    active = {
        reload: function () {

            $.ajax({
                cache: true,
                type: "GET",
                url: preUrl + 'riskBlack',
                data: {
                    "realName": $('#realName').val()
                    , "identityCard": $("#identityCard").val()
                    , "mobilePhone": $("#mobilePhone").val()
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    if (da.code == 0) {
                        resultTab(da.data);
                    } else {
                        layer.msg(da.msg);
                    }
                    $("#stateCode").val(da.code);
                }
            });

        }
    };

});
