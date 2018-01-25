var ModelVerification = function (opt) {
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
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

    initSelect: function (name, tmpValue, dataType, variable, variableCode) {

        var html = '<div class="layui-input-inline">';
        html += '<select name="' + name + '0" lay-filter="aihao">';

        html += '<option value="0" >请选择</option>';
        html += '<option >111</option>';

        html += '</select>';
        html += '</div>';
        return html;
        //
        // <div class="">
        //         <label class="">角色</label>
        //         <div class="">
        //         <select th:value="${user.role.roleId}" id="role_sel">
        //         <option value="0">请选择</option>
        //         <option th:each="item,iterStat:${roles}" th:text="${item.roleName}"
        //     th:value="${item.roleId}" th:selected="(${item.roleName} ==
        //     ${user.role.roleId})"></option>
        //
        //     </select>
        //     </div>
        //     </div>
    },

    /**
     * 初始化普通输入框
     * @param name
     * @returns {string}
     */
    initInput: function (name, tmpValue) {
        var html = '<div class="layui-input-inline">'
        if (tmpValue != null && tmpValue != "") {
            html += '<input value= "' + tmpValue + '"' + 'type="text" name="' + name + '" lay-verify="required" placeholder="整形" autocomplete="off" class="layui-input">'
        } else {
            html += '<input type="text" name="' + name + '" lay-verify="required" placeholder="整形" autocomplete="off" class="layui-input">'
        }
        html += '</div>';
        return html;
    },


    /**
     *
     渲染单个变量
     */
    initOneValible: function (valible) {
        var tmpValue = valible.tmpValue;
        var senceVersionId = valible.senceVersionId;
        var variableCode = valible.variableCode;
        var dataType = valible.dataType;
        //需要把对应的variableCode包含的枚举变量集合从session中取出,作为selection中的option值

        var html = "";
        switch (dataType) {
            case "select":
                html = this.initSelect(senceVersionId + '_' + variableCode, tmpValue, dataType, variableCode);
                //加入异步查询,根据dataType查询这个select有哪些选项
                break;
            case "date":
                html = this.initDate(senceVersionId + '_' + variableCode, tmpValue);
                break;
            case "time":
                html = this.initTime(senceVersionId + '_' + variableCode, tmpValue);
                break;
            case "Double":
                html = this.initInput(senceVersionId + '_' + variableCode, tmpValue);
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
    initDate: function (name) {
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
    initTime: function (name) {
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
        var size = 3;
        var length = data.length;
        var count = Math.ceil(length / size);
        var html = '';
        for (var i = 0; i < count; i++) {
            html += '<div class="layui-form-item">';
            for (var j = 0; j < size && (i + 1) * (j + 1) - 1 < length; j++) {
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
    initModel: function (data) {
        var senceData = data.variableMap;
        var html = '';
        html += ' <div class="layui-form-item" >\n' +
            '<button type="reset" class="layui-btn layui-btn-primary">重 置</button>' +
            '<button class="layui-btn"  lay-submit="" lay-filter="save" id="save">保 存</button>\n' +
            '<button type="button" class="layui-btn layui-btn-normal"  lay-filter="test" id="test" >开始测试</button>' +
            '</div>';


        for (var i = 0; i < senceData.length; i++) {
            html += this.initSence(senceData[i]);
        }
        return html;
    }
}


<!--定义一个手动测试类-->
var ModelAutoVerification = function (opt) {
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        this.options = $.extend({}, this.defaults, opt)
    });
}

var modelVerification = new ModelVerification();
var index = 0;

ModelAutoVerification.prototype = {

    initSelect: function (name, optionData) {
        var html = '<div class="layui-input-inline">';
        html += '<select name="' + name + '0" lay-filter="aihao">';
        for (var i = 0; i < optionData.length; i++) {
            var data = optionData[i];
            html += '<option value="' + data.value + '">' + data.name + '</option>';
        }
        html += '</select>';
        html += '</div>';
        return html;
    },
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
        html += '<div class="layui-input-inline" style="margin: 0px"><input value= "' + bindTable + '"' + 'type="text" name="' + name + "_table" + '"  style="width: auto" placeholder="未绑定" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px"><input value= "' + bindColumn + '"' + 'type="text" name="' + name + "_column" + '"  style="width: auto" placeholder="未绑定" autocomplete="off" class="layui-input"</div>'
        return html;
    },


    /**
     *
     渲染单个变量
     */
    initOneValible: function (valible) {
        var senceVersionId = valible.senceVersionId;
        var variableCode = valible.variableCode;
        var bindTable = valible.bindTable;
        var bindColumn = valible.bindColumn;
        return this.initInput(senceVersionId + '_' + variableCode, bindTable, bindColumn);
    },

    /**
     * 初始化日期时间输入框
     * @param name
     * @returns {string}
     */
    initDate: function (name) {

        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="' + name + '" id="date' + index + '" lay-verify="date" placeholder="yyyy-MM-dd " autocomplete="off" class="layui-input">';
        html += '</div>';
        index++;
        return html;
    },
    /**
     * 初始化日期输入框
     * @param name
     * @returns {string}
     */
    initTime: function (name) {
        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="' + name + '" id="dateTime" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">';
        html += '</div>';
        return html;
    },

    /**
     * 渲染每个变量的div
     * @param data
     * @returns {string}
     */
    initDiv: function (data) {
        var size = 3;
        var length = data.length;
        var count = Math.ceil(length / size);
        var html = '';

        for (var i = 0; i < count; i++) {

            for (var j = 0; j < size && (i + 1) * (j + 1) - 1 < length; j++) {
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
        html += '<div class="layui-input-inline" style="margin: 0px"><input  disabled="disabled"  value="变量名称" type="text" name="' + name + '" lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px" ><input  disabled="disabled"  value="数据表" type="text" name="' + name + '" lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
        html += '<div class="layui-input-inline" style="margin: 0px"><input  disabled="disabled"  value="字段" type="text" name="' + name + '" lay-verify="required" style="width: 400px" autocomplete="off" class="layui-input"></div>'
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
        html += ' <div class="layui-form-item" >\n' +
            ' <div class="layui-inline">\n' +
            '      <label class="layui-form-label" >测试数量</label>\n' +
            '      <div class="layui-input-inline" style="max-width: 50px">\n' +
            '        <input style="max-width: 100px" type="text" name="amount" autocomplete="off" class="layui-input" onkeyup="this.value=this.value.replace(/\\D/g,\'\')"  onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" maxlength="5" placeholder="请输入整数"/>\n' +
            '      </div>\n' +
            '    </div>' +
            '<button type="reset" class="layui-btn layui-btn-primary">重 置</button>' +
            '<button class="layui-btn"  lay-submit="" lay-filter="save_auto" id="save_auto">保 存</button>\n' +
            '</div>';


        for (var i = 0; i < senceData.length; i++) {
            html += this.initSence(senceData[i]);
        }
        return html;
    }


}