/**
 * Name:utils.js
 * Author:Van
 * E-mail:zheng_jinfan@126.com
 * Website:http://kit.zhengjinfan.cn/
 * LICENSE:MIT
 */
layui.define(['layer'], function(exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        _modName = 'htmlUtil';
    var htmlUtil = {
        v: '1.0.3',
        /**
         * 初始化下拉控件
         * @param name
         * @param optionData
         * @returns {string}
         */
        initSelect:function(name,optionData){
            var html = '<div class="layui-input-block">';
            html+='<select name="'+name+'" lay-filter="aihao">';
            for(var i=0;i<optionData.length;i++){
                var data = optionData[i];
                html+='<option value="'+data.value+'">'+data.name+'</option>';
            }
            html+='</select>';
            html+='</div>';
            return html;
        },
        /**
         * 初始化Lable
         * @param name
         * @returns {string}
         */
        initLabel:function(name){
            return '<label class="layui-form-label">'+name+'</label>';
        },
        /**
         * 初始化普通输入框
         * @param name
         * @returns {string}
         */
        initInput:function(name){
            var html = '<div class="layui-input-block">'
            html+='<input type="text" name="'+name+'" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">'
            html+='</div>';
            return html;
        },
        /**
         * 初始化日期时间输入框
         * @param name
         * @returns {string}
         */
        initDate:function(name){
            var html = '<div class="layui-input-inline">';
            html+='<input type="text" name="'+name+'" id="date" lay-verify="date" placeholder="yyyy-MM-dd " autocomplete="off" class="layui-input">';
            html+='</div>';
            return html;
        },
        /**
         * 初始化日期输入框
         * @param name
         * @returns {string}
         */
        initTime:function(name){
            var html = '<div class="layui-input-inline">';
            html+='<input type="text" name="'+name+'" id="date" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">';
            html+='</div>';
            return html;
        },
        initOneValible:function(valible){
            var html = "";
            switch (valible.type){
                case "select":html = this.initSelect(valible.name,valible.optionData);break;
                case "select":html = this.initInput(valible.name);break;
                case "select":html = this.initDate(valible.name);break;
                default:break;
            }
            return html;
        },
        initDiv:function(data){
            var size = 4;
            var length = data.length;
            var tmp =  length / size ;
            var count = tmp == 0 ? tmp :tmp+1;
            var html = '';
            for(var i=0;i<count;i++){
                html+='<div class="layui-form-item">';
                for(var j=0;j<size && (i+1)*(j+1)<length;j++){
                    html+this.initLabel(data.cnName);
                    html+=this.initOneValible(data[(i+1)*(j+1)-1]);
                }
                html += '</div>';
            }
            return html;
        }
    };
    exports('htmlUtil', htmlUtil);
});