var ModelVerification = function (opt){
    layui.use(['jquery'], function(){
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
        html += '<input type="text" name="' + name + '" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">'
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
    initOneValible: function (valible) {
        var html = "";
        switch (valible.type) {
            case "select":
                html = this.initSelect(valible.submitName, valible.optionData);
                break;
            case "input":
                html = this.initInput(valible.submitName);
                break;
            case "date":
                html = this.initDate(valible.submitName);
                break;
            case "time":
                html = this.initTime(valible.submitName);
                break;
            default:
                break;
        }
        return html;
    },
    initDiv: function (data) {
        var size = 4;
        var length = data.length;
        var count = Math.ceil(length / size);
        var html = '';
        for (var i = 0; i < count; i++) {
            html += '<div class="layui-form-item">';
            for (var j = 0; j < size && (i + 1) * (j + 1)-1 < length; j++) {
                var index = (i + 1) * (j + 1) - 1;
                html += this.initLabel(data[index].valibaleCn);
                html += this.initOneValible(data[index]);
            }
            html += '</div>';
        }
        return html;
    },
    initSence: function (data) {
        var senceName = data.senceName;
        var valiableData = data.data;
        var html = '<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;font-size: 16px">\n';
        html += '<legend style="font-size: 16px">'+senceName+'</legend>\n<div style="padding-top: 10px">';
        html += this.initDiv(valiableData);
        html += '</div></fieldset>';

        return html;
    },
    initModel:function(data){
        var senceData = data.variableMap;
        var html = '';
        for(var i=0;i<senceData.length;i++){
            html += this.initSence(senceData[i]);
        }
        return html;
    }
}
