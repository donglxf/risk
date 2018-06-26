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
    var form = layui.form;
    form.render();

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
    form.on('submit(formDemo)', function(data){
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
    });

    // 网贷黑名单查询
    form.on('submit(netLoanForm)', function(data){
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
    });

    // 自有黑名单查询
    form.on('submit(selfForm)', function(data){
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
        return false;
    });

    // 老赖黑名单查询
    form.on('submit(oldLaiForm)', function(data){
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
        return false;
    });

    // 汇法网黑名单查询
    form.on('submit(webankForm)', function(data){
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
        return false;
    });

    // 前海征信黑名单查询
    form.on('submit(frontSeaForm)', function(data){
        $.ajax({
            cache: true,
            type: "GET",
            url: preUrl + 'frontSea',
            data: {
                "realName": $("#frontSeaRealName").val()
                , "identityCard": $("#frontSeaIdentityCard").val()
                , "idType": $("#idType").val()
                , "reasonNo": $("#reasonNo").val()
            },
            async: false,
            error: function (request) {
                alert("Connection error");
            },
            success: function (da) {
                if (da.code == 0) {
                    setFromValues(da.data);
                } else {
                    $("#errMsg").val(da.msg);
                    // layer.msg(da.msg);
                }
            }
        });
        return false;
    });

    // 百融查询
    form.on('submit(baiRongform)', function(data){
        $.ajax({
            cache: true,
            type: "GET",
            url: preUrl + 'bairong',
            data: {
                "realName": $("#brRealName").val()
                , "identityCard": $("#brIdentityCard").val()
                , "mobilePhone": $("#brMobilePhone").val()
            },
            async: false,
            error: function (request) {
                alert("Connection error");
            },
            success: function (da) {
                if (da.code == 0) {
                    var bank = renderBarRongTabBank(da.data);
                    var nBank = renderBarRongTabNoBank(da.data);
                    $("#bankTempTr").replaceWith(bank);
                    $("#nBankTempTr").replaceWith(nBank);
                } else {
                    // $("#errMsg").val(da.msg);
                    layer.msg(da.msg);
                }
            }
        });
        return false;
    });

    function renderBarRongTabBank(da) {
        var applyLoanStr = da.applyLoanStr;
        var bank = "<tr>";
        var d7 = applyLoanStr.d7;
        bank += "<td rowspan=\"2\">1</td>";
        bank += "<td rowspan=\"2\">近7天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var d7Id = d7.id; // 身份证查询
        if (d7Id) {
            var d7Bk = d7Id.bank;
            if (d7Bk) {
                bank += (gerationBankStr(d7Bk));
            } else {
                bank += (gerationBankNullStr());
            }
        } else {
            bank += (gerationBankNullStr());
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var d7CellObj = d7.cell;
        if (d7CellObj) {
            var d7Cell = d7CellObj.bank;
            if (d7Cell) {
                bank += gerationBankStr(d7Cell); //手机号查询
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr><tr>";

        var d15 = applyLoanStr.d15;
        bank += "<td rowspan=\"2\">2</td>";
        bank += "<td rowspan=\"2\">近15天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var d15IdObj = d15.id;
        if (d15IdObj) {
            var d15Id = d15IdObj.bank;
            if (d15Id) {
                bank += gerationBankStr(d15Id);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var d15CellObj = d15.cell;
        if (d15CellObj) {
            var d15Cell = d15CellObj.bank;
            if (d15Cell) {
                bank += gerationBankStr(d15Cell);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr><tr>";

        var m1 = applyLoanStr.m1;
        bank += "<td rowspan=\"2\">3</td>";
        bank += "<td rowspan=\"2\">过去第1个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m1IdObj = m1.id;
        if (m1IdObj) {
            var m1Id = m1IdObj.bank;
            if (m1Id) {
                bank += gerationBankStr(m1Id);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var m1CellObj = m1.cell;
        if (m1CellObj) {
            var m1Cell = m1CellObj.bank;
            if (m1Cell) {
                bank += gerationBankStr(m1Cell);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr><tr>";

        var m3 = applyLoanStr.m3;
        bank += "<td rowspan=\"2\">4</td>";
        bank += "<td rowspan=\"2\">过去第3个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m3IdObj = m3.id;
        if (m3IdObj) {
            var m3Id = m3IdObj.bank;
            if (m3Id) {
                bank += gerationBankStr(m3Id);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var m3CellObj = m3.cell;
        if (m3CellObj) {
            var m3Cell = m3CellObj.bank;
            if (m3Cell) {
                bank += gerationBankStr(m3Cell);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr><tr>";

        var m6 = applyLoanStr.m6;
        bank += "<td rowspan=\"2\">5</td>";
        bank += "<td rowspan=\"2\">过去第6个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m6IdObj = m6.id;
        if (m6IdObj) {
            var m6Id = m6IdObj.bank;
            if (m6Id) {
                bank += gerationBankStr(m6Id);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var m6CellObj = m6.cell;
        if (m6CellObj) {
            var m6Cell = m6CellObj.bank;
            if (m6Cell) {
                bank += gerationBankStr(m6Cell);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr><tr>";
        var m12 = applyLoanStr.m12;
        bank += "<td rowspan=\"2\">6</td>";
        bank += "<td rowspan=\"2\">过去第12个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m12IdObj = m12.id;
        if (m12IdObj) {
            var m12Id = m12IdObj.bank;
            if (m12Id) {
                bank += gerationBankStr(m12Id);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>身份证查询</td>";
        bank += "</tr><tr>";
        var m12CellObj = m12.cell;
        if (m12CellObj) {
            var m12Cell = m12CellObj.bank;
            if (m12Cell) {
                bank += gerationBankStr(m12Cell);
            } else {
                bank += gerationBankNullStr(bank);
            }
        } else {
            bank += gerationBankNullStr(bank);
        }
        bank += "<td>手机号查询</td>";
        bank += "</tr>";
        return bank;
    }

    function isNullOrUndefined(obj) {
        if (null == obj || undefined == obj || "undefined" == obj) {
            return "";
        }
        return obj;
    }

    function gerationBankStr(obj) {
        var bank = "";
        bank += "<td>" + isNullOrUndefined(obj.selfnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.allnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.traAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.retAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.orgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.traOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.retOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.weekAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.weekOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nightAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nightOrgnum) + "</td>";

        return bank;
    }

    function gerationBankNullStr() {
        var bank = "";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        return bank;
    }

    function gerationNoBankStr(obj) {
        var bank = "";
        bank += "<td>" + isNullOrUndefined(obj.selfnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.allnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.p2pAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.mcAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.caAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.cfAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.comAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.othAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nsloanAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.autofinAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.sloanAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.consAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.finleaAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.elseAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.orgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.p2pOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.mcOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.caOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.cfOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.comOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.othOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nsloanOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.autofinOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.sloanOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.consOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.finleaOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.weekAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.weekOrgnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nightAllnum) + "</td>";
        bank += "<td>" + isNullOrUndefined(obj.nightOrgnum) + "</td>";
        return bank;
    }

    function gerationNoBankNullStr(obj) {
        var bank = "";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        bank += "<td></td>";
        return bank;
    }

    // 非银行
    function renderBarRongTabNoBank(da) {
        var applyLoanStr = da.applyLoanStr;
        var noBank = "<tr>";
        var d7 = applyLoanStr.d7;
        noBank += "<td rowspan=\"2\">1</td>";
        noBank += "<td rowspan=\"2\">近7天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var d7IdObj = d7.id; // 身份证查询
        if (d7IdObj) {
            var d7Bk = d7Id.nbank;
            if (d1Bk) {
                noBank += gerationNoBankStr(d7Bk);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var d7CellO = d7.cell; //手机号查询
        if (d7CellO) {
            var d7Cell = d7CellO.nbank;
            if (d7Cell) {
                noBank += gerationNoBankStr(d7Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        var d15 = applyLoanStr.d15;
        noBank += "<td rowspan=\"2\">2</td>";
        noBank += "<td rowspan=\"2\">近15天在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var d15IdO = d15.id;
        if (d15IdO) {
            var d15Id = d15IdO.nbank;
            if (d15Id) {
                noBank += gerationNoBankStr(d15Id);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var d15CellO = d15.cell;
        if (d15CellO) {
            var d15Cell = d15CellO.nbank;
            if (d15Cell) {
                noBank += gerationNoBankStr(d15Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        var m1 = applyLoanStr.m1;
        noBank += "<td rowspan=\"2\">3</td>";
        noBank += "<td rowspan=\"2\">过去第1个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m1IdO = m1.id;
        if (m1IdO) {
            var m1Id = m1IdO.nbank;
            if (m1Id) {
                noBank += gerationNoBankStr(m1Id);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var m1CellO = m1.cell;
        if (m1CellO) {
            var m1Cell = m1CellO.nbank;
            if (m1Cell) {
                noBank += gerationNoBankStr(m1Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        var m3 = applyLoanStr.m3;
        noBank += "<td rowspan=\"2\">4</td>";
        noBank += "<td rowspan=\"2\">过去第3个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m3IdO = m3.id;
        if (m3IdO) {
            var m3Id = m3IdO.nbank;
            if (m3Id) {
                noBank += gerationNoBankStr(m3Id);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var m3CellO = m3.cell;
        if (m3CellO) {
            var m3Cell = m3CellO.nbank;
            if (m3Cell) {
                noBank += gerationNoBankStr(m3Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        var m6 = applyLoanStr.m6;
        noBank += "<td rowspan=\"2\">5</td>";
        noBank += "<td rowspan=\"2\">过去第6个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m6IdO = m6.id;
        if (m6IdO) {
            var m6Id = m6IdO.nbank;
            if (m6Id) {
                noBank += gerationNoBankStr(m6Id);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var m6CellO = m6.cell;
        if (m6CellO) {
            var m6Cell = m6CellO.nbank;
            if (m6Cell) {
                noBank += gerationNoBankStr(m6Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        var m12 = applyLoanStr.m12;
        noBank += "<td rowspan=\"2\">6</td>";
        noBank += "<td rowspan=\"2\">过去第12个月在百融的虚拟信贷联盟(银行、非银、非银细分类型)中的多次信贷申请情况</td>";
        var m12IdO = m12.id;
        if (m12IdO) {
            var m12Id = m12IdO.nbank;
            if (m12Id) {
                noBank += gerationNoBankStr(m12Id);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>身份证查询</td>";
        noBank += "</tr><tr>";
        var m12CellO = m12.cell;
        if (m12CellO) {
            var m12Cell = m12CellO.nbank;
            if (m12Cell) {
                noBank += gerationNoBankStr(m12Cell);
            } else {
                noBank += gerationNoBankNullStr();
            }
        } else {
            noBank += gerationNoBankNullStr();
        }
        noBank += "<td>手机号查询</td>";
        noBank += "</tr>";

        return noBank;
    }

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
