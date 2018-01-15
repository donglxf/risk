(function ($) {
    $.fn.extend({
        //表格合并单元格，colIdx要合并的列序号，从0开始
        "rowspan": function (colIdx) {
            return this.each(function () {
                var that;
                $('tr', this).each(function (row) {
                    $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {
                        if (that != null && $(this).html() == $(that).html()) {
                            rowspan = $(that).attr("rowSpan");
                            if (rowspan == undefined) {
                                $(that).attr("rowSpan", 1);
                                rowspan = $(that).attr("rowSpan");
                            }
                            rowspan = Number(rowspan) + 1;
                            $(that).attr("rowSpan", rowspan);
                            $(this).hide();
                        } else {
                            that = this;
                        }
                    });
                });
            });
        }
    });

})(jQuery);

/**
 * Name:utils.js
 * Author:Van
 * E-mail:zheng_jinfan@126.com
 * Website:http://kit.zhengjinfan.cn/
 * LICENSE:MIT
 */
layui.define(['layer'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        _modName = 'sceneUtil';
    //条件体
    var conditionInfo = {
        //条件拼接
        conditionExpression: '',
        //条件+值 中文描述
        conditionDesc: '',
        val: ''
    };
    //动作体
    var actionValInfo = {
        //动作id参数Id
        actionParamId: '3',
        //值
        paramValue: ''

    };
    var subForm = {
        //条件集合
        conditionInfos: [],
        //动作集合
        actionInfos: [],
    }
    var itemVals = [];
    var itemTexts = [];
    var entityIds = [];

    var sceneUtil = {
            v: '1.0.0',
            sceneId:-1,//场景id
            data:{
                //常量库
                dicBank:[],
                //动作库
                actionBank:[],
                //条件常量
                condition: [
                    {value: "==", text: "等于"},
                    {value: "!=", text: "不等于"},
                    {value: "<", text: "小于"},
                    {value: "<=", text: "小于或等于"},
                    {value: ">", text: "大于"},
                    {value: ">=", text: "大于或等于"},
                    {value: "in", text: "包含"},
                    {value: "not in", text: "不包含"},
                    {value: "like%", text: "开始以"},
                    {value: "%like", text: "结束以"},
                    {value: "===", text: "忽略"},
                ],
                //数据对象库
                entitysBank:[],
                //变量库
                itemsBank:[],
            },
            //实体类集合
            entitys: [],
            //提交格式设置
            subForm: subForm,
            //动作默认
            actionTypes: [{value: "3", text: "得分"},],

            /**
             * 根据一个html内容读取出body标签里的文本
             */
            getBodyContent: function (content) {
                var REG_BODY = /<body[^>]*>([\s\S]*)<\/body>/,
                    result = REG_BODY.exec(content);
                if (result && result.length === 2)
                    return result[1];
                return content;
            },

            /**
             * 当前行下添加一行
             * @param t
             */
            addRow: function (t) {
                // console.log($('#trTpl').find(".ctr").html());
                var tr = $(t).parent().parent().parent();
                tr.after(tr.clone());
                init();
                //获取当前行行数
                //$(t).append( $(t).parent().parent().parent().clone(true))
            }
            ,
            /**
             * 删除当前行
             * @param t
             */
            deleteRow: function (t) {
                $(t).parent().parent().parent().remove();
            }
            ,
            /**
             * 添加条件列
             */
            addCol: function () {

                $th = $("#tpl").find("th:eq(0)").clone();
                $td = $("#tpl").find("td:eq(0)").clone();
                $col = $("#tpl").find("col:eq(0)").clone();
                var index = $(".contion").length;
                index = index - 2;
                $("#table>colgroup>col:eq(" + index + ")").after($col);
                $("#table>thead>tr>th:eq(" + index + ")").after($th);

                //需要添加多少列
                $("#table>tbody>tr").each(function (i, e) {
                    var td = $("#tpl").find("td:eq(0)").clone();
                    $(e).find("td:eq(" + index + ")").after(td);
                    // $($(e).find("td:eq("+index+")")[0]).after(td);
                });
                init();
            }
            ,
            /**
             * 添加动作列
             */
            addActionCol: function () {

                $th = $("#tpl").find("th:eq(1)").clone();
                $td = $("#tpl").find("td:eq(1)").clone();
                $col = $("#tpl").find("col:eq(1)").clone();
                var index = $("#table thead tr th").length;
                index = index - 2;
                //  index = index - 2;
                $("#table>colgroup>col:eq(" + index + ")").after($col);
                $("#table>thead>tr>th:eq(" + index + ")").after($th);

                //需要添加多少列
                $("#table>tbody>tr").each(function (i, e) {
                    var td = $("#tpl").find("td:eq(1)").clone();
                    $(e).find("td:eq(" + index + ")").after(td);
                    // $($(e).find("td:eq("+index+")")[0]).after(td);
                });
                init();
            },

            /**
             * 添加条件列
             */
            deleteCol: function (t) {
                var td = $(t).parent();
                var index = td.index();
                $("#table>colgroup>col:eq(" + index + ")").remove();

                $("#table>thead>tr>th:eq(" + index + ")").remove();
                $("#table>tbody>tr").each(function (i, e) {
                    var td = $("#tpl").find("td").clone();
                    $(e).find("td:eq(" + index + ")").remove();
                });
            },
            /**
             * 合并
             */
            hb: function () {
                $("#table").rowspan(0); //以第一列合并可用，但是会影响后面的新增，或删除操作
            },
            /*
             *值的编辑初始化 .val -> $('.val')
             */
            bandOneValInit: function (obj) {
                obj.editable('destroy');
                obj.editable({
                    type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
                    title: "值",              //编辑框的标题
                    disabled: false,             //是否禁用编辑
                    emptytext: "空文本",          //空值的默认文本
                    mode: "popup",              //编辑框的模式：支持popup和inline两种模式，默认是popup
                    onblur: "submit",
                    validate: function (value) { //字段验证
                        if (!$.trim(value)) {
                            return '不能为空';
                        }
                        $(this).attr("data-value", value);
                    }
                });
            },
        /**
         * 回调方法
         */
        callBack:{
          contion:function(value){
              if (!$.trim(value)) {
                  return '不能为空';
              }
              if (value == '===') {
                  $(this).parent().find(".val").text("");
                  $(this).parent().find(".val").attr("data-value", "true");
              } else {
                  $(this).parent().find(".val").text("值");
                  $(this).parent().find(".val").attr("data-value", "");
              }
              $(this).attr("data-value", value);
          },
            entity:function(value){
                if (!$.trim(value)) {
                    return '不能为空';
                }
                $(this).attr("data-value", value);
                $(this).parent().find(".itemC").text("请选择");
                $(this).parent().find(".itemC").attr("data-value", "");
                setItemSelect(value, this);
                //触发变量的选择
            }   ,
            action:function(value){
                if (!$.trim(value)) {
                    return '不能为空';
                }
                var actionId = value;
                //动作参数值
                for (var i = 0; i < actions.length; i++) {
                    if (actions[i].value == actionId) {
                        $(this).attr("data-value", actions[i].paramInfoList[0].value);
                        $(this).parent().find(".actionVal").text(actions[i].paramInfoList[0].text);
                        $(this).parent().find(".actionVal").attr("data-value", " ");
                        break;
                    }
                }
            }
        },
        /**
         * 一级下拉
         * @param obj 对象
         * @param data 数据
         * @param text 标题
         */
        bandSelectValInit: function (obj,data,text,callback) {
            //摧毁
            obj.editable('destroy');
            obj.editable({
                type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
                // value:'2',
                source: data,
                title: text,           //编辑框的标题
                disabled: false,           //是否禁用编辑
                // emptytext: "选择对象",       //空值的默认文本
                mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
                onblur: "submit",
                validate: callback,
            });
        },
            /**
             * 初始化方法
             */
            gradeInit: function() {
                // headItem();
                $("#table thead tr th.contion ").hover(function () {
                    $(this).find(".del").show();
                }, function () {
                    $(this).find(".del").hide();
                });
                //绑定条件输入值得输入方式
                sceneUtil.bandOneValInit( $('.val'));
                //绑定动作值得输入框
                sceneUtil.bandOneValInit( $('.actionVal'));
                //实体对象绑定
                sceneUtil.bandSelectValInit($(".entityC"),sceneUtil.entitys,"选择对象",sceneUtil.callBack.entity(value));
                //条件绑定
                sceneUtil.bandSelectValInit($(".con"),sceneUtil.conditionInfos,"选择条件",sceneUtil.callBack.contion(value));
                //动作绑定
                sceneUtil.bandSelectValInit($(".actionType"),sceneUtil.conditionInfos,"选择动作",sceneUtil.callBack.action(value));
            },
        /**
         * 数据初始化
         */
        dataInit: {
                //数据变量库
                entityBank:function () {
                    $.ajax({
                        cache: true,
                        type: "get",
                        url: '/rule/service/entityInfo/getEntitysByScene',
                        data: {sceneId: sceneUtil.sceneId},// 你的formid
                        async: false,
                        success: function (da) {
                            if (da.code == 0) {
                                sceneUtil.data.entitysBank =  da.data;
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    });
                },
                //动作库导入
                actionBank:function () {
                    $.ajax({
                        cache: true,
                        type: "get",
                        url: '/rule/service/actionInfo/getByScene',
                        data: {sceneId: sceneUtil.sceneId},// 你的formid
                        async: false,
                        success: function (da) {
                            if (da.code == 0) {
                                sceneUtil.data.actionBank =  da.data;
                            } else {
                                layer.msg(data.msg);
                            }
                        }
                    });
                },
                //常量库导入
                dicBank:function () {
                        //if (sceneUtil.data.dicBank == [] || sceneUtil.data.dicBank.length < 1) {
                            $.ajax({
                                cache: true,
                                type: "get",
                                url: '/rule/service/variable/getByIds',
                                data: {ids: '1,2,3,4,5,6,7'},
                                async: false,
                                error: function (request) {
                                    alert("Connection error");
                                },
                                success: function (da) {
                                    if (da.code == 0) {
                                        actions = da.data;
                                    } else {
                                        layer.msg(data.msg);
                                    }
                                }
                            });
                       // }
                }

            },
            /**
             * 获取变量列表
             */
            headItem: function () {
                itemVals = [];
                itemTexts = [];
                $("#table>thead>tr>th a.itemC").each(function () {
                    itemVals.push($(this).data('value'));
                    itemTexts.push($(this).text());
                });
                $("#table>thead>tr>th a.entityC").each(function () {
                    entityIds.push($(this).attr('data-value'));
                });


                return itemVals;
            },
            getRuleList: function () {
                var len = itemVals.length;
                var subForms = [];
                var headLen = $("#table>thead>tr>th").length;
                $("#table>tbody>tr").each(function () {
                    subForm = {
                        //条件集合
                        conditionInfos: [],
                        //动作集合
                        actionInfos: []
                    }
                    var conditionInfos = [];
                    var actionInfos = [];
                    $(this).find("td").each(function (i, e) {
                        //获取条件体
                        if (i < len) {
                            //拼条件 的变量 ，运算符 ，值
                            var itemv = itemVals[i];
                            var itemText = itemTexts[i];

                            var ysf = $(e).find("a.con").attr("data-value");
                            var ysfText = $(e).find("a.con").text();
                            var val = $(e).find("a.val").attr("data-value");
                            if (val == '' || ysf == '' || itemv == '') {
                                layer.msg('必选项不能为空');
                                return null;
                            }
                            var conditionInfo = {
                                conditionExpression: '$' + itemv + '$' + '' + ysf + '' + val,
                                conditionDesc: '$' + itemText + '$' + '' + ysfText + '' + val,
                                val: val
                            }
                            conditionInfos.push(conditionInfo);
                        }
                        //结果
                        else if (i < headLen - 1) {
                            var actionV = $(e).find("a.actionVal").attr("data-value");
                            var actionType = $(e).find("a.actionType").attr("data-value");
                            if (actionV == '') {
                                layer.msg('必选项不能为空');
                                return null;
                            }
                            var actionValInfo = {
                                //动作id参数Id
                                actionParamId: actionType,
                                //值
                                paramValue: actionV
                            };
                            actionInfos.push(actionValInfo);
                        }

                    });
                    subForm.conditionInfos = conditionInfos;
                    subForm.actionInfos = actionInfos;
                    subForms.push(subForm);
                });

                return subForms;
            },
            /**
             * 提交数据
             */
            sub: function () {

                var itemVal = headItem();
                console.info('条件值：' + itemVal);
                //获取
                var subForms = getRuleList();
                console.info(subForms);
                if (sceneId == '') {
                    layer.msg("必须选中一个场景哦");
                    return;
                }
                var form = {
                    //场景id
                    sceneId: sceneId,
                    //变量集合
                    itemVals: itemVals,
                    //实体类集合
                    entityIds: entityIds,
                    //条件 和结果集
                    vos: subForms
                }
                //转json
                var str = JSON.stringify(form);
                console.log(str);
                $.ajax({
                    type: "POST",
                    url: "/rule/service/rule/save",
                    data: str,
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function (message) {
                        console.log(message);
                        if (message.code == '') {
                            layer.msg("恭喜保存成功");
                        }
                    },
                    error: function (message) {
                        $("#request-process-patent").html("提交数据失败！");
                    }
                });

            }
        }
    ;


    exports('sceneUtil', sceneUtil);
});