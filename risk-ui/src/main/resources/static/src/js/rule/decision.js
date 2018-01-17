
/**
 * Name:utils.js
 * Author:Van
 * E-mail:zheng_jinfan@126.com
 * Website:http://kit.zhengjinfan.cn/
 * LICENSE:MIT
 */
layui.define(['layer','form','laytpl'], function (exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laytpl = layui.laytpl,
        _modName = 'sceneUtil';
    /*//条件体
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
    var entityIds = [];*/

    var sceneUtil = {
            flag :true,
            v: '1.0.0',
            sceneId:-1,//场景id
            entityImport:[],
            actionImport:[],
            sub:{
                gradeForm:{},
            },
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
            subForm: {},
            //动作默认
            actionTypes: [{value: "3", text: "得分"}],

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
                var index = $(tr).find("td.index").attr("data-index");
                tr.after(tr.clone());
                //设置行的disp
                $(tr).next().find("td.index").show();
                index = 1+index;
                $(tr).next().find("td.index").attr("data-index",index);
                //设置index的值
               // $("#table tbody tr:last").find("td.index").show();
                sceneUtil.gradeInit();
                //获取当前行行数
                //$(t).append( $(t).parent().parent().parent().clone(true))
            }
            ,
             /**
             * 删除某一条件
             */
             deleteCon:function (t) {
                 $(t).parent().remove();
            
             },
            /**
             * 删除当前行
             * @param t
             */
            deleteRow: function (t) {
                var tr = $(t).parent().parent().parent();
                var rowspan = $(tr).find("td.index").attr("rowspan");
                if(rowspan > 1){
                    $(tr).next().find("td.index").show();
                }
                $(t).parent().parent().parent().remove();
                sceneUtil.rowspan();
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
         * 命名项
         * @param t
         */
        reGroupName:function(t){
            var name = $(t).next().text() +"."+$(t).next().next().text();
           var groupNameO =  $(t).parent().parent().parent().prev().find(".groupName");
           $(groupNameO).text(name);
            $(groupNameO).data("value",name);

        },
            /**
             * 合并
             */
            hb: function () {
                $("#table").rowspan(0); //以第一列合并可用，但是会影响后面的新增，或删除操作
            },
        rowspan:function(){
                var oldIndex = -1;
                var rowspan = 1;
            //合并第一列 的
            $("#table tbody tr td.index").each(function (i) {
                var index = $(this).data("index");
                if(oldIndex == index){
                    rowspan = rowspan + 1;
                    if(rowspan > 1){
                        $(this).hide();
                    }
                }else{
                    //初始化
                    oldIndex = index;
                    rowspan = 1;
                }
                $('#table tbody tr td.index').eq(i-rowspan+1).attr("rowspan",rowspan);
            })

        },
        /**
         * 添加行内行
         * @param t
         */
        addTypeTr:function (t) {
            // console.log($('#trTpl').find(".ctr").html());
            var tr = $(t).parent().parent();

            var rowspan = $(tr).find("td.index").attr("rowspan");
            var copTr = tr;
            for(var i = 1; i < rowspan;i++){
                copTr = $(copTr).next();
            }
            copTr.after(copTr.clone());
            //合并
            sceneUtil.rowspan();
            sceneUtil.gradeInit();
        },
        addCon:function (t) {
            var h =   $(t).parent().find("ul li:last").html();
            var li = '<li>'+h+'</li>';
            $(t).parent().find("ul").append(li);
            sceneUtil.gradeInit();
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
         * 导入页面打开
         */
        openImport:function (type) {
            var url = "/rule/ui/src/html/decision/import_entity.html";
            if(type == 1){

            }else{
                url = "/rule/ui/src/html/decision/import_action.html";
            }
            //打开导入面板
            $.get(url,function(html){
                layer.open({
                    type: 1,
                    skin: 'layui-layer-rim', //加上边框
                    maxmin: false,
                    title: false,
                    shadeClose: true, // 点击遮罩关闭层
                    area: ['450px', '400px'],
                    content: html,
                    success: function (layero, index) {
                        if(type == 1){
                            sceneUtil.entitySelectInit();
                            //初始化 实体 已导入的数据
                            sceneUtil.initEntitys();
                        }else{
                            sceneUtil.actionSelectInit();
                            //初始化 实体
                            sceneUtil.initActions();
                        }
                    }
                });

           });
        },
        /***
         * 品牌下拉初始化
         * @param value
         * @param list
         */
        entitySelectInit:function(){

            if(sceneUtil.entityImport == [] || sceneUtil.entityImport.length == 0){

                $.get("/rule/service/entityInfo/getEntitysAll",function(data){
                    var result = {list : data.data
                    }
                    var getTpl = tableTps.innerHTML
                        ,view = document.getElementById('entitSelectId');
                    laytpl(getTpl).render(result, function(html){
                        view.innerHTML = html;
                    });
                    sceneUtil.entityImport = data.data;
                    form.render('select');
                },'json');
            }else{
                var result = {list : sceneUtil.entityImport
                }
                var getTpl = tableTps.innerHTML
                    ,view = document.getElementById('entitSelectId');
                laytpl(getTpl).render(result, function(html){
                    view.innerHTML = html;
                });
                form.render('select');
            }
    },
        /***
         * 动作下拉初始化
         * @param value
         * @param list
         */
        actionSelectInit:function(){

            if(sceneUtil.actionImport == [] || sceneUtil.actionImport.length == 0){

                $.get("/rule/service/actionInfo/getAll",function(data){
                    var result = {list : data.data
                    }
                    var getTpl = actionTableTps.innerHTML
                        ,view = document.getElementById('actionSelectId');
                    laytpl(getTpl).render(result, function(html){
                        view.innerHTML = html;
                    });
                    sceneUtil.actionImport = data.data;
                    form.render('select');
                },'json');
            }else{
                var result = {list : sceneUtil.entityImport
                }
                var getTpl = actionTableTps.innerHTML
                    ,view = document.getElementById('actionSelectId');
                laytpl(getTpl).render(result, function(html){
                    view.innerHTML = html;
                });
                form.render('select');
            }
        },
        /**
         * 动态导入一条数据
         */
        addEntitys:function () {
            var entityVal = $("#entitSelectId").val();
            var  entity = {};
            var flag = true;
            sceneUtil.data.entitysBank.forEach(function(element){
                if(entityVal == element.value){
                    flag = false;
                    return;
                }
            });
            if(!flag){
                layer.msg("不可重复添加");
                return false;
            }

            for(var i = 0; i < sceneUtil.entityImport.length;i++){
                if(entityVal == sceneUtil.entityImport[i].value){
                    entity = sceneUtil.entityImport[i];
                    console.log(entity);
                    break;
                }
            }
            sceneUtil.data.entitysBank.push(entity);
            var h = '<li>\n' +
                '                <div class="layui-form-item">\n' +
                '                    <label class="layui-form-label">对象名</label>\n' +
                '                    <div class="layui-input-inline">\n' +
                '                        <input type="text" name="" data-value="'+entity.value+'" value="'+entity.text+'" readonly="readonly" autocomplete="off"\n' +
                '                               class="layui-input">\n' +
                '                    </div>\n' +
                '                    <div class="layui-form-mid layui-word-aux">\n' +
                '                        <a class="layui-icon edel" onclick="sceneUtil.deleteEntity(this,'+entity.value+')" title="删除" href="javascript:void(0)" style="font-size: 18px">&#xe640;</a>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li>';
            $("#entityUiDiv").append(h);
            form.render('select');
            //实体对象绑定
            sceneUtil.bandSelectValInit_entity($(".entityC"),sceneUtil.data.entitysBank,"选择对象");
        },
        /**
         * 添加动作
         */
        addActions:function () {
            var actionVal = $("#actionSelectId").val();
            var  action = {};
            var flag = true;
            //判重复
            sceneUtil.data.actionBank.forEach(function(element){
                if(actionVal == element.value){
                    flag = false;
                    return;
                }
            });
            if(!flag){
                layer.msg("不可重复添加");
                return false;
            }
            //获取当前的动作体
            for(var i = 0; i < sceneUtil.actionImport.length;i++){
                if(actionVal == sceneUtil.actionImport[i].value){
                    action = sceneUtil.actionImport[i];
                    break;
                }
            }
            //动作库添加
            sceneUtil.data.actionBank.push(action);
            var h = '<li>\n' +
                '                <div class="layui-form-item">\n' +
                '                    <label class="layui-form-label">动作名</label>\n' +
                '                    <div class="layui-input-inline">\n' +
                '                        <input type="text" name="" data-value="'+action.value+'" value="'+action.text+'" readonly="readonly" autocomplete="off"\n' +
                '                               class="layui-input">\n' +
                '                    </div>\n' +
                '                    <div class="layui-form-mid layui-word-aux">\n' +
                '                        <a class="layui-icon edel" onclick="sceneUtil.deleteAction(this,'+action.value+')" title="删除" href="javascript:void(0)" style="font-size: 18px">&#xe640;</a>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li>';
            $("#actionUiDiv").append(h);
            form.render('select');
        },
        /**
         * 添加实体对象
         */
        initEntitys:function () {
            $("#entityUiDiv").html("");
            sceneUtil.data.entitysBank.forEach(function(entity){
                    var h = '<li>\n' +
                        '                <div class="layui-form-item">\n' +
                        '                    <label class="layui-form-label">对象名</label>\n' +
                        '                    <div class="layui-input-inline">\n' +
                        '                        <input type="text" name="" data-value="'+entity.value+'" value="'+entity.text+'" readonly="readonly" autocomplete="off"\n' +
                        '                               class="layui-input">\n' +
                        '                    </div>\n' +
                        '                    <div class="layui-form-mid layui-word-aux">\n' +
                        '                        <a class="layui-icon edel" onclick="sceneUtil.deleteEntity(this,'+entity.value+')" title="删除" href="javascript:void(0)" style="font-size: 18px">&#xe640;</a>\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </li>';
                    $("#entityUiDiv").append(h);
            });
        },
        /**
         *初始化动作库
         */
        initActions:function () {
            $("#actionUiDiv").html("");
            sceneUtil.data.actionBank.forEach(function(action){
                    var h = '<li>\n' +
                        '                <div class="layui-form-item">\n' +
                        '                    <label class="layui-form-label">动作名</label>\n' +
                        '                    <div class="layui-input-inline">\n' +
                        '                        <input type="text" name="" data-value="'+action.value+'" value="'+action.text+'" readonly="readonly" autocomplete="off"\n' +
                        '                               class="layui-input">\n' +
                        '                    </div>\n' +
                        '                    <div class="layui-form-mid layui-word-aux">\n' +
                        '                        <a class="layui-icon edel" onclick="sceneUtil.deleteAction(this,'+action.value+')" title="删除" href="javascript:void(0)" style="font-size: 18px">&#xe640;</a>\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </li>';
                    $("#actionUiDiv").append(h);
            });
        },
        /**
         * 删除某实体类元素
         * @param t
         * @param v
             */
        deleteEntity:function (t,v) {
            var result = [];
            sceneUtil.data.entitysBank.forEach(function(element){
                if(v != element.value){
                    result.push(element);
                }
            });
            sceneUtil.data.entitysBank = result;
            $(t).parent().parent().parent().remove();
        },
        /**
         * 删除某动作类元素
         * @param t
         * @param v
         */
        deleteAction:function (t,v) {
            var result = [];
            sceneUtil.data.actionBank.forEach(function(element){
                if(v != element.value){
                    result.push(element);
                }
            });
            sceneUtil.data.actionBank = result;
            $(t).parent().parent().parent().remove();
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
         * 一级下拉
         * @param obj 对象
         * @param data 数据
         * @param text 标题
         */
        bandSelectValInit_entity: function (obj,data,text) {
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
                validate: function(value){
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                    $(this).attr("data-value", value);
                    $(this).parent().find(".itemC").text("请选择");
                    $(this).parent().find(".itemC").attr("data-value", "");
                    sceneUtil.bandSelectValInit_item(this,sceneUtil.data.entitysBank,value);
                    //触发变量的选择
                }
            });
        },
        /**
         * 重置变量集合
         * @param entityId
         * @param t
         */
        bandSelectValInit_item: function(obj,entitys,entityId) {
            var items = [];
            for(var i=0;i<entitys.length;i++){
                var enid = entitys[i].value;
                if(enid == entityId){
                    items = entitys[i].sons;
                    break;
                }
            }

            $(obj).parent().find(".itemC").editable('destroy');
            //变量
            $(obj).parent().find(".itemC").editable({
                type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
                source: items,
                title: "变量名",           //编辑框的标题
                disabled: false,           //是否禁用编辑
                emptytext: "选择变量",       //空值的默认文本
                mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
                onblur:"submit",
                validate: function (value) { //字段验证
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                    $(this).attr("data-value",value);
                    //重置
                    $(obj).parent().find(".val").text("输入  ");
                    $(obj).parent().find(".val").data("value","");
                    //设置使用的变量
                    //sceneUtil.addTtemsBank(value);
                    //是否设置常量集合
                    sceneUtil.bandSelectValInit_constants(this,items,value);
                }
            });

        },
        /**
         * 设置使用过的变量值
         * @param value
             */
        addTtemsBank:function (value) {
            var flag = true;
          for (var i = 0; i < sceneUtil.data.itemsBank.length;i++){
              if(value == sceneUtil.data.itemsBank[i]){
                  flag = false;
                  break;
              }
          }
          if(flag){
              sceneUtil.data.itemsBank.push(value);
          }
        },
        /**
         * 重置常量集合
         * @param entityId
         * @param t
         */
        bandSelectValInit_constants: function(obj,items,itemId)  {
            var constants = [];
            var hasCons = false;
            //找到等于的值的地方
            for(var i=0;i<items.length;i++){
                var iid = items[i].value;
                if(iid == itemId){
                    if(items[i].sons != undefined && items[i].sons.length > 0 && items[i].sons != null)
                    constants = items[i].sons;
                    hasCons = true;
                    break;
                }
            }
            $(obj).parent().find(".val").editable('destroy');

            if(constants.length > 0){
                //选择常量
                $(obj).parent().find(".val").parent().find(".val").editable({
                    type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
                    source: constants,
                    title: "选择常量",           //编辑框的标题
                    disabled: false,           //是否禁用编辑
                    emptytext: "选择常量",       //空值的默认文本
                    mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
                    onblur:"submit",
                    validate: function (value) { //字段验证
                        if (!$.trim(value)) {
                            return '不能为空';
                        }
                        $(this).attr("data-value",value);
                    }
                });
            }else{
                sceneUtil.bandOneValInit( $(obj).parent().find(".val"));
            }

        },
        /**
         * 一级下拉
         * @param obj 对象
         * @param data 数据
         * @param text 标题
         */
        bandSelectValInit_condition: function (obj,data,text) {
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
                validate: function(value){
                    if (!$.trim(value)) {
                        return '不能为空';
                    }
                    if (value == '===') {
                        $(this).parent().find(".val").text("");
                        $(this).parent().find(".val").attr("data-value", "true");
                    } else {
                        //$(this).parent().find(".val").text("值");
                        //$(this).parent().find(".val").attr("data-value", "");
                    }
                    $(this).attr("data-value", value);
                }
            });
        },
        /**
         * 一级下拉
         * @param obj 对象
         * @param data 数据
         * @param text 标题
         */
        bandSelectValInit_action: function (obj,data,text) {
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
                validate: function(value){
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
            });
        },

            /**
             * 初始化方法
             */
            gradeInit: function() {
                // headItem();
                $("#table tbody tr td.index").hover(function () {
                    $(this).find(".addTypeTr").show();
                },function () {
                    $(this).find(".addTypeTr").hide();
                });
                $("#table tbody tr td ul li").hover(function () {
                    var groupNameO =  $(this).parent().parent().prev().find(".groupName");
                    var display = $(groupNameO).parent().parent().css('display');
                    if(display != 'none'){
                        $(this).find(".reGroupName").show();
                    }
                    if($(this).index() > 0){
                        //判断是否是第一条
                        $(this).find(".deleteCon").show();
                    }

                },function () {
                    var groupNameO =  $(this).parent().parent().prev().find(".groupName");
                    var display = $(groupNameO).parent().parent().css('display');
                    if(display != 'none'){
                        $(this).find(".reGroupName").hide();
                    }
                    $(this).find(".deleteCon").hide();
                });

                //合并
                sceneUtil.rowspan();
                //绑定条件输入值得输入方式
                sceneUtil.bandOneValInit( $('.val'));
                sceneUtil.bandOneValInit( $('.groupName'));
                //绑定动作值得输入框
                sceneUtil.bandOneValInit( $('.actionVal'));
                //条件绑定
                sceneUtil.bandSelectValInit_condition($(".con"),sceneUtil.data.condition,"选择条件");
                //实体对象绑定
                sceneUtil.bandSelectValInit_entity($(".entityC"),sceneUtil.data.entitysBank,"选择对象");
                //绑定所有的变量
                $(".entityC").each(function(){
                    var entitys = sceneUtil.data.entitysBank;
                    var entityId = $(this).data("value");
                    var iid = $(this).next().data("value");
                    sceneUtil.bandSelectValInit_item(this,entitys,entityId);
                    //绑定所有常量
                    var items = [];
                    for(var i=0;i<entitys.length;i++){
                        var enid = entitys[i].value;
                        if(enid == entityId){
                            items = entitys[i].sons;
                            break;
                        }
                    }
                    sceneUtil.bandSelectValInit_constants(this,items,iid);
                });
            },
        /**
         * 初始化方法
         */
        sceneInit: function() {
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
            sceneUtil.bandSelectValInit($(".entityC"),sceneUtil.entitys,"选择对象",sceneUtil.callBack.entity());
            //条件绑定
            sceneUtil.bandSelectValInit($(".con"),sceneUtil.data.condition,"选择条件",sceneUtil.callBack.contion());
            //动作绑定
            sceneUtil.bandSelectValInit($(".actionType"),sceneUtil.conditionInfos,"选择动作",sceneUtil.callBack.action());
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
                            //console.log(da);
                            if (da.code == 0) {
                                sceneUtil.data.entitysBank =  da.data;
                            } else {
                                layer.msg(da.msg);
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
                                layer.msg(da.msg);
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
                                        layer.msg(da.msg);
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
            getGradeRuleData: function () {
                var subForms = [];
                //统一权值设置
                var weightIndexList = [];
                var groupNameList = [];
                sceneUtil.data.itemsBank = [];
                $("#table>tbody>tr").each(function () {
                    subForm = {
                        //权值
                        group:{},
                        //条件集合
                        conditionInfos: [],
                        //动作集合
                        actionInfos: []
                    }
                    var conditionInfos = [];
                    var actionInfos = [];

                    var groupTd = $(this).find("td.index");
                    //拼分组
                    var groupIndex = $(groupTd).data("index");
                    var groupName = $(groupTd).find("span a.groupName").text();
                    var weight = ($(groupTd).find(".qz").val()== undefined || $(groupTd).find(".qz").val() == '')?0:$(groupTd).find(".qz").val();
                    if(weightIndexList.length <= groupIndex){
                        weightIndexList.push(weight);
                        groupNameList.push(groupName);
                    }else{
                        weight = weightIndexList[groupIndex];
                        groupName = groupName;
                    }
                    subForm.group = {index:groupIndex,name:groupName,weight:weight};
                    //拼条件
                    var conTd = $(this).find("td").eq(1);
                    $(conTd).find("ul li").each(function(i,e){
                        //拼条件 的变量 ，运算符 ，值
                        var entityv = $(e).find("a.entityC").attr("data-value");
                        var entityText = $(e).find("a.entityC").text();
                        var itemv = $(e).find("a.itemC").attr("data-value");
                        var itemText = $(e).find("a.itemC").text();

                        var ysf = $(e).find("a.con").attr("data-value");
                        var ysfText = $(e).find("a.con").text();
                        var val = $(e).find("a.val").attr("data-value");
                        var valText = $(e).find("a.val").text();
                        if (val == '' || ysf == '' || itemv == '') {
                            layer.msg('必选项不能为空');
                            sceneUtil.flag = false;
                            return ;
                        }
                        var conditionInfo = {
                            conditionExpression: '$' + itemv + '$' + '' + ysf + '' + val,
                            conditionDesc: '$' + entityText + ':'+ itemText+ '$' + ysfText + '' + valText,
                            val: valText
                        }
                        conditionInfos.push(conditionInfo);
                        //使用了那些变量，重新赋值了
                        sceneUtil.addTtemsBank(itemv);
                    });
                    //拼动作，分数了
                    var actionTd = $(this).find("td").eq(2);
                    var actionVal = $(actionTd).find("a.actionVal").attr("data-value");
                    var actionType = $(actionTd).find("a.actionVal").attr("actionParamId");
                    if(actionVal == '' ){
                        sceneUtil.flag = false;
                        return;
                    }
                    //动作值
                    var actionValInfo = {
                        //动作id参数Id
                        actionParamId: 3,
                        //值
                        paramValue: actionVal + ''
                    };
                    //权值
                    var actionValQz = {
                        //动作id参数Id
                        actionParamId: 6,
                        //值
                        paramValue: weight+''
                    };
                    actionInfos.push(actionValInfo);
                    actionInfos.push(actionValQz);
                    subForm.conditionInfos = conditionInfos;
                    subForm.actionInfos = actionInfos;
                    subForms.push(subForm);
                });
                sceneUtil.sub.gradeForm = subForms;
            },
        getSceneRuleData: function () {
            var len = itemVals.length;
            var subForms = [];
            var headLen = $("#table>thead>tr>th").length;
            $("#table>tbody>tr").each(function () {
                subForm = {
                    //权值
                    group:{},
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
                            sceneUtil.flag = false;
                            return ;
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
            subGrad: function () {

                sceneUtil.getGradeRuleData();
                if(!sceneUtil.flag){
                    layer.msg("请检查有必填项没填");
                    return;
                }
                if (sceneUtil.sceneId == '') {
                    layer.msg("必须选中一个场景哦");
                    return;
                }
                var iindex =  layer.msg('提交中..', {icon: 16,time:5000});
                //实体类对象拼接
                var entityIds = [];
                for(var i=0;i < sceneUtil.data.entitysBank.length;i++){
                    entityIds.push(sceneUtil.data.entitysBank[i].value);
                }
                var form = {
                    //场景id
                    sceneId: sceneUtil.sceneId,
                    //变量集合
                    itemVals: sceneUtil.data.itemsBank,
                    //实体类集合
                    entityIds: entityIds,
                    //条件 和结果集
                    vos: sceneUtil.sub.gradeForm
                }
                //转json
                var str = JSON.stringify(form);
                console.log(str);
                $.ajax({
                    type: "POST",
                    url: "/rule/service/rule/saveGrade",
                    data: str,
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function (message) {
                        layer.close(iindex);
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