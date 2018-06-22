///////////////////////////////////////////////////////////////////////
var preUrl = "/rule/service/rpc/";
var layer, entityTable, itemTable, table, active, itemActive, topIndex;
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'form', 'myutil', 'element'], function () {


    layer = layui.layer;
    table = layui.table;
    var $ = layui.jquery;

    /**
     * 设置表单值
     * @param el
     * @param data
     */
    function setFromValues(data) {
        for (var p in data) {
            // $("#" + el).find("input[name='" + p + "']").val(data[p]);
            $("#" + p).val(data[p]);
        }
    }

    function resultTab(da) {
        var html = '[';
        for (var i = 0; i < da.length; i++) {
            if (i == da.length - 1) {
                html += "{\"borrowDate\":\"" + da[i].borrowDate + "\",\"borrowAmount\":\"" + da[i].borrowAmount + "\",\"borrowPeriod\":\"" + da[i].borrowPeriod + "\",\"overdueDate\":\"" + da[i].overdueDate + "\",\"overdueLevel\":\"" + da[i].overdueLevel + "\",\"overdueAmount\":\"" + da[i].overdueAmount + "\"}";
            } else {
                html += "{\"borrowDate\":\"" + da[i].borrowDate + "\",\"borrowAmount\":\"" + da[i].borrowAmount + "\",\"borrowPeriod\":\"" + da[i].borrowPeriod + "\",\"overdueDate\":\"" + da[i].overdueDate + "\",\"overdueLevel\":\"" + da[i].overdueLevel + "\",\"overdueAmount\":\"" + da[i].overdueAmount + "\"},";
            }
        }
        var arr = new Array(da.length);
        for (var i = 0; i < da.length; i++) {
            var opt = {
                borrowDate: da[i].borrowDate
                , borrowAmount: da[i].borrowAmount
                , borrowPeriod: da[i].borrowPeriod
                , overdueDate: da[i].overdueDate
                , overdueLevel: da[i].overdueLevel
                , overdueAmount: da[i].overdueAmount
            };
            arr[i] = opt;
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
            , data: arr
            , even: true
        });

    }

    // 考拉黑名单查询
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
                }
            });
        }
    };

    // 网贷黑名单查询
    netLoan = {
        reload: function () {
            $.ajax({
                cache: true,
                type: "GET",
                url: preUrl + 'netLoan',
                data: {
                    "realName": $('#realName1').val()
                    , "identityCard": $("#identityCard1").val()
                    , "mobilePhone": $("#mobilePhone1").val()
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    if (da.code == 0) {
                        setFromValues(da.data);
                    } else {
                        layer.msg(da.msg);
                    }
                }
            });
        }
    };

    // 自有黑名单查询
    self = {
        reload: function () {
            $.ajax({
                cache: true,
                type: "GET",
                url: preUrl + 'self',
                data: {
                    "identityCard": $("#selfidentityCard").val()
                    , "mobilePhone": $("#selfmobilePhone").val()
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    if (da.code == 0) {
                        setFromValues(da.data);
                    } else {
                        layer.msg(da.msg);
                    }
                }
            });
        }
    };

    // 老赖黑名单查询
    oldlai = {
        reload: function () {
            $.ajax({
                cache: true,
                type: "GET",
                url: preUrl + 'oldLai',
                data: {
                    "realName": $("#oldLaiRealName").val()
                    , "identityCard": $("#oldLaiIdentityCard").val()
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    if (da.code == 0) {
                        setFromValues(da.data);
                    } else {
                        layer.msg(da.msg);
                    }
                }
            });
        }
    };

    // 汇法网黑名单查询
    webank = {
        reload: function () {
            $.ajax({
                cache: true,
                type: "GET",
                url: preUrl + 'webank',
                data: {
                    "realName": $("#webankRealName").val()
                    , "identityCard": $("#webankIdentityCard").val()
                },
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    if (da.code == 0) {
                        renderTab(da.data);
                    } else {
                        layer.msg(da.msg);
                    }
                }
            });
        }
    };

    function renderTab(da) {
        var arr = new Array(da.length);
        for (var i = 0; i < da.length; i++) {
            var opt = {
                casenum: da[i].casenum
                , type: da[i].type
                , datetime: da[i].datetime
                , address: da[i].address
                , money: da[i].money
            };
            arr[i] = opt;
        }
        table.render({
            elem: '#webankDemo'
            , cols: [[ //标题栏
                {field: 'casenum', event: 'setItem', title: '案件编号'}
                , {field: 'type', event: 'setItem', title: '案件类型'}
                , {field: 'datetime', event: 'setItem', title: '案件时间'}
                , {field: 'address', event: 'setItem', title: '案件地点'}
                , {field: 'money', event: 'setItem', title: '涉案金额'}
            ]]
            , data: arr
            , even: true
        });
    }

});
