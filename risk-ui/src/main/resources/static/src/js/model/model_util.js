var ModelVerification = function (opt) {
    layui.use(['jquery', 'form', 'laytpl'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        this.options = $.extend({}, this.defaults, opt)
    });
}
ModelVerification.prototype = {

    /**
     * 初始化Lable
     * @param name
     * @returns {string}
     */
    initLabel: function (name) {
        return '<label class="layui-form-label">' + name + '</label>';
    },

    initSelect: function (name, optionData, tmpValue) {
        var html = '<div class="layui-input-inline">';
        html += '<select name="' + name + '" lay-filter="">';
        for (var i = 0; i < optionData.length; i++) {
            var data = optionData[i];
            //需要根据值对比,选中默认值
            if (data.value == tmpValue) {
                html += '<option  selected="selected" value="' + data.value + '">' + data.name + '</option>';
            } else {
                html += '<option value="' + data.value + '">' + data.name + '</option>';
            }
        }
        html += '</select>';
        html += '</div>';
        return html;
    },

    initSelectBoolean: function (name, tmpValue) {
        var html = '<div class="layui-input-inline">';
        html += '<select name="' + name + '" lay-filter="">';
        if (1 == tmpValue) {
            html += '<option  selected="selected" value="1">' + '是' + '</option>';
            html += '<option  value="0">' + '否' + '</option>';
        } else {
            html += '<option  selected="selected" value="0">' + '否' + '</option>';
            html += '<option  value="1">' + '是' + '</option>';
        }
        html += '</select>';
        html += '</div>';
        return html;
    },


    /**
     * 获取变量列表
     * @param variableCode
     */
    getVariableList: function (variableCode) {

        var $ = layui.jquery;

        $.ajax({
            type: "get",
            url: "/rule/service/actProcRelease/scene/variable/constant",
            async: false,//同步请求
            data: {
                variableCode: variableCode
            },
            dataType: "json",
            success: function (data) {
                //因为select需要等到页面生成后才能被渲染出来,所有这里不能直接获取select元素进行渲染,需要组装一段js,然后用在这段生成的js中实现select的交互

                // var getTpl = constantTP.innerHTML
                //     , view = document.getElementById('variableCode');
                // laytpl(getTpl).render(result, function (html) {
                //     view.innerHTML = html;
                // });


                constant = data.data;
                console.log(constant);
                console.log(constant.length);
                console.log(constant[0]);
            }
        });
    },

    /**
     * 初始化普通输入框
     * @param name
     * @returns {string}
     */
    initInput: function (name, tmpValue, dataType) {
        var html = '<div class="layui-input-inline">'

        if (tmpValue != null && tmpValue != "") {
            if (dataType == 'Double') {
                html += '<input value= "' + tmpValue + '"' + 'type="text" name="' + name + '" lay-verify="required" placeholder="数字型" onkeyup="this.value=this.value.replace(/\\D/g,\'\')"  onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="5" autocomplete="off" class="layui-input">';
            } else if (dataType == 'String') {
                html += '<input value= "' + tmpValue + '"' + 'type="text" name="' + name + '" lay-verify="required" placeholder="字符型" autocomplete="off" class="layui-input" maxlength="100">';
            } else if (dataType == 'Integer') {
                html += '<input value= "' + tmpValue + '"' + 'type="text" name="' + name + '" lay-verify="required" placeholder="整形"   onkeyup="this.value=this.value.replace(/\\D/g,\'\')"  onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="5" autocomplete="off" class="layui-input">';
            }
        } else {
            if (dataType == 'Double') {
                html += '<input type="text" name="' + name + '" lay-verify="required" placeholder="数字型" onkeyup="this.value=this.value.replace(/\\D/g,\'\')"  onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="5" autocomplete="off" class="layui-input">';
            } else if (dataType == 'String') {
                html += '<input type="text" name="' + name + '" lay-verify="required" placeholder="字符型" autocomplete="off" class="layui-input" maxlength="100">';
            } else if (dataType == 'Integer') {
                html += '<input type="text" name="' + name + '" lay-verify="required" placeholder="整形"   onkeyup="this.value=this.value.replace(/\\D/g,\'\')"  onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="5" autocomplete="off" class="layui-input">';
            }
        }
        html += '</div>';
        return html;
    },


    /**
     *
     渲染单个变量
     */
    initOneValible: function (valible) {
        console.log(valible);
        var tmpValue = valible.tmpValue;
        var senceCode =valible.senceCode;
        var variableCode = valible.variableCode;
        var senceId = valible.senceVersionId;
        var dataType = valible.dataType;
        var optionData = valible.optionData;
        var name = senceId+"#"+senceCode + '#' + variableCode;
        //需要把对应的variableCode包含的枚举变量集合从session中取出,作为selection中的option值
        var html = "";
        switch (dataType) {
            case "CONSTANT":
                html = this.initSelect(name, optionData, tmpValue);
                //加入异步查询,根据dataType查询这个select有哪些选项
                break;
            case "date":
                html = this.initDate(name, tmpValue);
                break;
            case "time":
                html = this.initTime(name, tmpValue);
                break;
            case "Double":
                html = this.initInput(name, tmpValue, dataType);
                break;
            case "String":
                html = this.initInput(name, tmpValue, dataType);
                break;
            case "Integer":
                html = this.initInput(name, tmpValue, dataType);
                break;
            case "Boolean":
                html = this.initSelectBoolean(name, tmpValue);
                break;
            default:
                break;
        }
        return html;
    },

    /**
     * 初始化日期时间输入框
     * @param name
     * @returns {string}
     */
    initDate: function (name, tmpValue) {
        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="' + name + '" id="date" lay-verify="date" placeholder="yyyy-MM " autocomplete="off" class="layui-input">';
        html += '</div>';
        return html;
    },
    /**
     * 初始化日期输入框
     * @param name
     * @returns {string}
     */
    initTime: function (name, tmpValue) {
        console.log(index)
        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="'
            + name +
            '" id="time' + index
            + '" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input time">';
        html += '</div>';
        index++;
        return html;
    },

    /**
     * 渲染每个变量的div
     * @param data
     * @returns {string}
     */
    initDiv: function (data) {
        console.log(data);
        var size = 3;
        var length = data.length;
        var count = Math.ceil(length / size); //向上取整
        var html = '';
        for (var i = 0; i < count; i++) {
            html += '<div class="layui-form-item">';
            for (var j = i ; j < size && (i + 1) * (j + 1) - 1 < length; j++) {
                var index = (i + 1) * (j + 1) - 1;
                html += this.initLabel(data[index].variableName);
                html += this.initOneValible(data[index]);
            }
            html += '</div>';
        }
        return html;
    },
    /**
     * 渲染单个策列表
     * @param senceData
     * @returns {string}
     */
    initSence: function (senceData) {
        var senceName = senceData.sceneName;
        var valiableData = senceData.data;
        var html = '<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;font-size: 16px">\n';
        html += '<legend style="font-size: 16px">' + senceName + '</legend>\n<div style="padding-top: 10px">';
        html += this.initDiv(valiableData);
        html += '</div></fieldset>';

        return html;
    },
    /**
     * 模型渲染入口
     * @param data
     * @returns {string}
     */
    initModel: function (result) {
        var data = result.data;
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "/plugins/jquery-1.9.1.min.js";
        document.getElementsByTagName('head')[0].appendChild(script);
        console.log("initModel");
        console.log(data);
        var senceData = data.variableMap;
        var html = '';
      /*  html += ' <div class="layui-form-item" >\n' +
            '<button type="reset" class="layui-btn layui-btn-primary">重 置</button>' +
            '<button class="layui-btn"  lay-submit="" lay-filter="save" id="save">保 存</button>\n' +
            '<button type="button" class="layui-btn layui-btn-normal"  lay-filter="test" id="test" >开始测试</button>' +
            '</div>';*/


        for (var i = 0; i < senceData.length; i++) {
            html += this.initSence(senceData[i]);
        }
        return html;
    }
}


<!--定义一个自动测试类-->
var ModelAutoVerification = function (opt) {
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        this.options = $.extend({}, this.defaults, opt)
    });
}

var modelVerification = new ModelVerification();
var index = 0;

ModelAutoVerification.prototype = {
    /**
     * 初始化Lable
     * @param name
     * @returns {string}
     */
    initLabel: function (name) {
        // return '<div class="layui-input-inline"><label class="layui-form-label">' + name + '</label></div>';
        return '<div class="layui-input-inline" style="margin: 0px" ><input type="text" value=' + name + ' class="layui-input" disabled="disabled" style="width: auto"/></div>';
    },
    /**
     * 初始化普通输入框
     * @param name
     * @returns {string}
     */
    initInput: function (name, bindTable, bindColumn) {
        var html = ''
        html += '<div class="layui-input-inline" style="margin: 0px"><input value= "' + bindTable + '"' + 'type="text" name="' + name + "#table" + '"  style="width: auto" placeholder="未绑定" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px"><input value= "' + bindColumn + '"' + 'type="text" name="' + name + "#column" + '"  style="width: auto" placeholder="未绑定" autocomplete="off" class="layui-input"</div>'
        return html;
    },


    /**
     *
     渲染单个变量
     */
    initOneValible: function (valible) {
        console.log(valible);
        var senceVersionId = valible.senceVersionId;
        var variableCode = valible.variableCode;
        var bindTable = valible.bindTable;
        var bindColumn = valible.bindColumn;
        var name = senceVersionId + '#' + variableCode
        return this.initInput(name, bindTable, bindColumn);
    },


    /**
     * 渲染每个变量的div
     * @param data
     * @returns {string}
     */
    initDiv: function (data){
        console.log(data);
        var size = 3;
        var length = data.length;
        var count = Math.ceil(length / size);
        var html = '';

        for (var i = 0; i < count; i++) {

            for (var j = i; j < size && (i + 1) * (j + 1) - 1 < length; j++) {
                html += '<div class="layui-form-item">';
                var index = (i + 1) * (j + 1) - 1;
                html += this.initLabel(data[index].variableName);//包含一个div
                html += this.initOneValible(data[index]); //包含两个div
                html += '</div>';
            }
        }
        return html;
    },
    /**
     * 渲染单个策列表
     * @param senceData
     * @returns {string}
     */
    initSence: function (senceData) {
        var senceName = senceData.sceneName;
        var valiableData = senceData.data;
        var html = '<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;font-size: 16px">\n';
        html += '<legend style="font-size: 16px">' + senceName + '</legend>\n<div style="padding-top: 10px">';
        html += '<div class="layui-form-item">'
        html += '<div class="layui-input-inline" style="margin: 0px"><input  disabled="disabled"  value="变量名称" type="text"  lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px" ><input  disabled="disabled"  value="数据表" type="text" lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px"><input  disabled="disabled"  value="字段" type="text" lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
        html += '</div>'
        html += this.initDiv(valiableData);
        html += '</div></fieldset>';

        return html;
    },
    /**
     * 模型渲染入口
     * @param data
     * @returns {string}
     */
    initModel: function (data) {
        var senceData = data.variableMap;
        var html = '';
        for (var i = 0; i < senceData.length; i++) {
            html += this.initSence(senceData[i]);
        }
        return html;
    }

}