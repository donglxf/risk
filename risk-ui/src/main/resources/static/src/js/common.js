/**
 * Name:utils.js
 * Author:Van
 * E-mail:zheng_jinfan@126.com
 * Website:http://kit.zhengjinfan.cn/
 * LICENSE:MIT
 */
layui.define(['layer','laytpl','form'], function(exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        laytpl = layui.laytpl,
        _modName = 'myutil';
    var myUtil = {
        v: '1.0.3',
        baseSerive:'/rule/service',
        //业务相关
        business:{
            select:{
                name:'businessId',
                id:'businessId'
            },
            init_url:'/rule/service/business/getAll',
            init_html: function () {
                return     '      <div class="layui-input-inline">\n' +
                    '                    <select name="'+myUtil.business.select.name+'"  lay-filter="business_select" lay-search="" id="'+myUtil.business.select.id+'" lay-verify="required">\n' +
                    '                        <option value="">选择业务线</option>\n' +
                    '                    </select>\n' +
                    '                </div>'
            },
            init_html2: function (id) {

                return ' <select name="'+myUtil.business.select.name+'" lay-filter="business_select" lay-search="" id="'+id+'" lay-verify="required">\n' +
                    '    <option value="">选择业务线</option>\n' +
                    '    </select>';
            },
            /**
             * 显示下拉框
             * @param businessId 业务id
             * @param obj 放入的地方
             */
            init:function (businessId,obj,selectId) {
                $.get(myUtil.business.init_url,function(data){
                    if(data.code == '0'){
                        if(selectId == '' || selectId == undefined){
                            selectId = myUtil.business.select.id;
                        }
                        var h = myUtil.business.init_html2(selectId);
                        //初始化
                        $(obj).html(h);
                        var result = data.data;
                        for(var i = 0; i<result.length;i++){
                            var ischeck = '';
                            //选中的设置
                            if(result[i].businessId == businessId){
                                ischeck = 'selected="true"';
                            }
                            var option = '<option value="'+result[i].businessId+'" '+ischeck+' >'+result[i].businessName+'</option>';
                            $(obj).find("#"+selectId).append(option);
                        }
                        form.render('select');
                        form.on('select(business_select)', function(data){
                            myUtil.business.selectBack(data);
                        });
                    }
                },'json');
            },
            selectBack:function (data) {
                console.log(data);
            }
        },

    };
    exports('myutil', myUtil);
});