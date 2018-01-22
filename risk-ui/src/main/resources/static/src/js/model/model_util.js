var ModelVerification = function (opt) {
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        this.options = $.extend({}, this.defaults, opt)
    });
}
ModelVerification.prototype = {
    initSelect: function (name, optionData) {
        var html = '<div class="layui-input-inline">';
        html += '<select name="' + name + '" lay-filter="aihao">';
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
        return '<label class="layui-form-label">' + name + '</label>';
    },
    /**
     * 初始化普通输入框
     * @param name
     * @returns {string}
     */
    initInput: function (name) {
        var html = '<div class="layui-input-inline">'
        html += '<input type="text" name="' + name + '" lay-verify="identity" placeholder="整形" autocomplete="off" class="layui-input">'
        html += '</div>';
        return html;
    },
    /**
     * 初始化日期时间输入框
     * @param name
     * @returns {string}
     */
    initDate: function (name) {
        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="' + name + '" id="date" lay-verify="date" placeholder="yyyy-MM-dd " autocomplete="off" class="layui-input">';
        html += '</div>';
        return html;
    },
    /**
     * 初始化日期输入框
     * @param name
     * @returns {string}
     */
    initTime: function (name) {
        var html = '<div class="layui-input-inline">';
        html += '<input type="text" name="' + name + '" id="date" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">';
        html += '</div>';
        return html;
    },

    /**
     *
     渲染单个变量
     */
    initOneValible: function (valible) {
        var html = "";
        switch (valible.dataType) {
            case "select":
                html = this.initSelect(valible.variableName, valible.optionData);
                break;
            case "input":
                html = this.initInput(valible.variableName);
                break;
            case "date":
                html = this.initDate(valible.variableName);
                break;
            case "time":
                html = this.initTime(valible.variableName);
                break;
            case "Double":
                html = this.initInput(valible.variableName);
                break;
            default:
                break;
        }
        return html;
    },
    /**
     * 渲染每个变量的div
     * @param data
     * @returns {string}
     */
    initDiv: function (data) {
        var size = 4;
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
            '            <button class="layui-btn layui-btn-primary"  lay-submit="" lay-filter="demo2">导入旧值</button>\n' +
            '<button type="reset" class="layui-btn" >保存</button>' +
            '        </div>';


        for (var i = 0; i < senceData.length; i++) {
            html += this.initSence(senceData[i]);
        }


        return html;
    }
}
