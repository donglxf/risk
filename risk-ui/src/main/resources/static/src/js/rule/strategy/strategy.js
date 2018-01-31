///////////////////////////////////////////////////////////////////////
var preBindUrl = "/rule/service/variableBind/";
var preUrl = "/rule/service/strategy/";
var p_sceneId = -1,versionIds=0;
layui.use(['table', 'form','laydate'], function () {
    /**
     * 设置表单值
     *
     * @param el
     * @param data
     */
    function setFromValues(el, data) {
        for (var p in data) {
            el.find(":input[name='" + p + "']").val(data[p]);
        }
    }
    var laydate = layui.laydate;
    var layer = layui.layer;
   var table = layui.table;
    var entityTable = layui.table;
    var itemTable = layui.table;
    var app = layui.app, $ = layui.jquery, form = layui.form;
    // var $=layui.jquery;
    // 第一个实例
    entityTable.render({
        elem: '#demo',
        height: 550,
        cellMinWidth: 80,
        url: preUrl + 'page/' // 数据接口
        ,
        page: true // 开启分页
        ,
        id: 'demos',
        cols: [[ // 表头
            {
                field: 'title',
                event: 'setItem',
                title: '名称'
            }, {
                field: 'senceType',
                event: 'setItem',
                title: '类型',
                templet: '#businessType',
            }, {
                field: 'businessName',
                event: 'setItem',
                title: '业务线',
                // templet: '#businessLine',
                fixed: 'right'
            }, {
                field: 'version',
                event: 'setItem',
                title: '版本号'
            }, {
                field: 'isBindVar',
                event: 'setItem',
                templet: '#isBindVar',
                title: '变量绑定'
            }, {
                field: 'testStatus',
                event: 'setItem',
                title: '测试状态',
                templet: '#testStatus',
                sort: true
            }, {
                field: 'sceneId',
                title: '操作',
                fixed: 'right',
                align: 'center',
                toolbar: '#item_bar'
            }]]
    });
    // 重载
    // 这里以搜索为例
    active = {
        reload: function () {
            // var demoReload = $('#demoReload');

            // 执行重载
            table.reload('demos', {
                page: {
                    curr: 1
                    // 重新从第 1 页开始
                },
                where: {
                    key: $('#modelName').val(),
                    businessLine:$("#isBusinessLine").val(),
                    businessType:$("#isBusinessType").val()
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    // 监听工具条
    entityTable.on('tool(entityTable)', function (obj) {
        var data = obj.data;
        console.log(obj);
        if (obj.event === 'manual_verification') { // 手动验证
            manuTest(data.sceneId, data.versionId,data.sceneIdentify,data.version);
        } else if (obj.event === 'auto_verification') { // 自动验证
            if(data.isBindVar!=1){
                layer.msg('请先绑定变量！');
                return ;
            }
            autoTest(data.sceneId, data.versionId,data.sceneIdentify,data.version);
        } else if (obj.event === 'variable_bind') { // 变量绑定
            edit(data.sceneId, data.versionId);
        }
    });


    function manuTest(sceneId, versionId,sceneIdentify,version){
        versionIds=versionId;
        var url=preBindUrl+ "getAll?versionId=" + versionId ;
        var layIndex = layer.open({
            type: 2,
            shade: false,
            title: "",
            content: "/rule/ui/strategy/manualTest",
            zIndex: layer.zIndex, //重点1
            success: function (layero, index) {
                var body = layer.getChildFrame('body',index);//建立父子联系
                // var iframeWin = window[layero.find('iframe')[0]['name']];
                var inputList = body.find("input[type='hidden']");
                for(var j = 0; j< inputList.length; j++){
                    var inputName=inputList[j].name;
                    if(inputName=='senceVersionId'){
                        $(inputList[j]).val(versionId);
                    }else if(inputName =='senceId'){
                        $(inputList[j]).val(sceneId);
                    }else if(inputName =='sceneIdentify'){
                        $(inputList[j]).val(sceneIdentify);
                    }else if(inputName =='version'){
                        $(inputList[j]).val(version);
                    }
                }
                layer.setTop(layero); //重点2
            }
        })
        layer.full(layIndex);
        /*$.ajax({
            type: "get",
            url: url,
            dataType: "json",
            success: function (data) {
                console.log(">>>>>>>>"+data);
                var modelVerification = new ModelVerification();
                var contents = modelVerification.initModel(data);
                var layIndex = layer.open({
                    type: 2,
                    shade: false,
                    title: "",
                    content: "/rule/ui/strategy/manualTest",
                    zIndex: layer.zIndex, //重点1
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body',index);//建立父子联系
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        var inputList = body.find("input[type='hidden']");
                        for(var j = 0; j< inputList.length; j++){
                            var inputName=inputList[j].name;
                            if(inputName=='senceVersionId'){
                                $(inputList[j]).val(versionId);
                            }else if(inputName =='senceId'){
                                $(inputList[j]).val(sceneId);
                            }else if(inputName =='sceneIdentify'){
                                $(inputList[j]).val(sceneIdentify);
                            }
                        }
                        layer.setTop(layero); //重点2
                        var form = layer.getChildFrame('#manu_model_valiable_form', index);
                        form.html(contents);
                    }
                });
                layer.full(layIndex);
            }
        });*/
    }

    <!-- 自动测试 -->
    function autoTest(sceneId, versionId,sceneIdentify,version) {
        var url=preBindUrl+ "getAll?versionId=" + versionId ;
        $.ajax({
            type: "get",
            url: url,
            dataType: "json",
            success: function (data) {
                console.log(">>>>>>>>"+data);
                // var modelVerification = new ModelVerification();
                // var contents = modelVerification.initModel(data);
                var layIndex = layer.open({
                    type: 2,
                    shade: false,
                    title: "",
                    content: "/rule/ui/strategy/autoTest",
                    zIndex: layer.zIndex, //重点1
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body',index);//建立父子联系
                        var iframeWin = window[layero.find('iframe')[0]['name']];
                        var inputList = body.find("input[type='hidden']");
                        for(var j = 0; j< inputList.length; j++){
                            var inputName=inputList[j].name;
                            if(inputName=='senceVersionId'){
                                $(inputList[j]).val(versionId);
                            }else if(inputName =='senceId'){
                                $(inputList[j]).val(sceneId);
                            }else if(inputName =='sceneIdentify'){
                                $(inputList[j]).val(sceneIdentify);
                            }else if(inputName =='version') {
                                $(inputList[j]).val(version);
                            }
                        }
                        layer.setTop(layero); //重点2
                        form.render();
                    }
                });
                layer.full(layIndex);
            }
        });
    }

    function edit(sceneId,versionId) {
        console.log(sceneId+","+versionId);
        $.get("/rule/service/variableBind/getVariableBind?versionId=" + versionId, function(data) {
            var result = data.data;
            $.get('/rule/ui/ruleBind/index/edit', null, function(form) {
                layer.open({
                    type : 1,
                    title : '修改',
                    maxmin : true,
                    shadeClose : false, // 点击遮罩关闭层
                    area : [ '730px', '460px' ],
                    content : form,
                    btn : [ '保存', '取消' ],
                    btnAlign : 'c',
                    zIndex: layer.zIndex, //重点1
                    success : function(da, index) {
                        console.log(">>"+da+">>index:=="+index);


                        setVariableBindFiled(da, result);
                        $("#senceVersionid").val(versionId);
                        $("#sceneId").val(sceneId);


                    },
                    yes : function(index) {
                        // layedit.sync(editIndex);
                        // 触发表单的提交事件
                        $('form.layui-form')
                            .find('button[lay-filter=formDemo]').click();
                        layer.close(index);
                    },
                });
            });
        }, 'json')
    }
    function setVariableBindFiled(el,da) {
        var modelVerification = new ModelVerification();
        var size = da.length;
        for(var i=0;i<size;i++){
            $("#bindColumnTable").append("<tr>\n" +
                "\t\t\t\t\t\t\t<input type=\"hidden\" name=\""+da[i].variableCode+"_bind\" id=\"bindId"+i+"\" value='"+da[i].id+"'>\n" +
                "\t\t\t\t\t\t\t<td><label class=\"layui-form-label mylabel\">变量名:</label></td>\n" +
                "\t\t\t\t\t\t\t<td><div class=\"layui-input-block mycss\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" id=\""+da[i].variableCode+"\" name=\""+da[i].variableCode+"\" required\n" +
                "\t\t\t\t\t\t\t\t\t   lay-verify=\"required\" placeholder=\"请输入变量名\" autocomplete=\"off\"\n" +
                "\t\t\t\t\t\t\t\t\t   class=\"layui-input\" value=\""+da[i].variableName+"\" readonly>\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t<td><label class=\"layui-form-label mylabel\">表名:</label></td>\n" +
                "\t\t\t\t\t\t\t<td><div class=\"layui-input-block mycss\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" id=\""+da[i].variableCode+"_tableName\" name=\""+da[i].variableCode+"_tableName\" required\n" +
                "\t\t\t\t\t\t\t\t\t   lay-verify=\"required\" placeholder=\"请输入业务表名\" autocomplete=\"off\"\n" +
                "\t\t\t\t\t\t\t\t\t   class=\"layui-input\" value=\"TEMP_DATA_CONTAINS\" >\n" +
                "\t\t\t\t\t\t\t</div></td>\n" +
                "\t\t\t\t\t\t\t<td><label class=\"layui-form-label mylabel\">列名:</label></td>\n" +
                "\t\t\t\t\t\t\t<td><div class=\"layui-input-block mycss\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" id=\""+da[i].variableCode+"_column\" name=\""+da[i].variableCode+"_column\" required\n" +
                "\t\t\t\t\t\t\t\t\t   lay-verify=\"required\" placeholder=\"请输入列名\" autocomplete=\"off\"\n" +
                "\t\t\t\t\t\t\t\t\t   class=\"layui-input\" value='"+da[i].bindColumn+"'>\n" +
                "\t\t\t\t\t\t\t</div></td>\n" +
                "\t\t\t\t\t\t</tr>");
        }
    }
});


