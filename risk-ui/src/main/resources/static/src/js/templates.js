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
                case "select":html = this.initSelect(valible.submitName,valible.optionData);break;
                case "input":html = this.initInput(valible.submitName);break;
                case "date":html = this.initDate(valible.submitName);break;
                case "time":html = this.initTime(valible.submitName);break;
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
                    html+this.initLabel(data.valibaleCn);
                    html+=this.initOneValible(data[(i+1)*(j+1)-1]);
                }
                html += '</div>';
            }
            return html;
        },
        initModel:function(data){
            console.log(data);
            var senceName = data.senceName;
            var valiableData = data.data;
            var html = '';
            html += '<div class="layui-collapse" lay-filter="test">';
            html += '<div class="layui-colla-item">';
            html += '<h2 class="layui-colla-title">'+senceName+'</h2>';
            html += '<div class="layui-colla-content">';
            html += initDiv(valiableData);
            html += '</div>';
            html += '</div>';
            html += '</div>';
        },
        demo:function(){
            var data = {
                "modelName":"房贷模型",
                "modelVersion":"v1.01",
                "variableMap":[
                    {
                        "senceName": "房贷评分卡",
                        "data": [
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "age",
                                "valibaleCn": "年龄",
                                "submitName": "age",
                                "type": "input",
                                "desc": "年龄"
                            },
                            {
                                "valibaleEn": "sex",
                                "valibaleCn": "性别",
                                "submitName": "sex",
                                "type": "select",
                                "desc": "性别",
                                "optionData":[
                                    {
                                        "value":"01",
                                        "name":"男"
                                    },{
                                        "value":"02",
                                        "name":"女"
                                    },
                                ]
                            }
                        ]
                    },
                    {
                        "senceName": "车贷评分卡",
                        "data": [
                            {
                                "valibaleEn": "name",
                                "valibaleCn": "姓名",
                                "submitName": "name",
                                "type": "input",
                                "desc": "姓名"
                            },
                            {
                                "valibaleEn": "age",
                                "valibaleCn": "年龄",
                                "submitName": "age",
                                "type": "input",
                                "desc": "年龄"
                            },
                            {
                                "valibaleEn": "sex",
                                "valibaleCn": "性别",
                                "submitName": "sex",
                                "type": "select",
                                "desc": "性别",
                                "optionData":[
                                    {
                                        "value":"01",
                                        "name":"男"
                                    },{
                                        "value":"02",
                                        "name":"女"
                                    }
                                ]
                            }
                        ]
                    }
                ]
            };
            console.log('初始化模型信息！');
            this.initModel(data);
        }
    };
    exports('htmlUtil', htmlUtil);
});