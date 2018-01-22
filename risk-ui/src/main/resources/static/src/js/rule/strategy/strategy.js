///////////////////////////////////////////////////////////////////////
var preBindUrl = "/rule/service/variableBind/";
var preUrl = "/rule/service/strategy/";
var layer, entityTable, itemTable, table, active, itemActive;
var versionId;
layui.use(['table', 'form'], function () {
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

    layer = layui.layer;
    table = layui.table;
    entityTable = layui.table;
    itemTable = layui.table;
    var app = layui.app, $ = layui.jquery, form = layui.form;
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
            // {field: 'conId', event: 'setItem',title: 'ID',sort: true, fixed:
            // 'left'}
            {
                field: 'title',
                event: 'setItem',
                title: '名称'
            }, {
                field: 'businessType',
                event: 'setItem',
                title: '类型',
                templet: '#businessType',
            }, {
                field: 'businessLine',
                event: 'setItem',
                title: '业务线',
                templet: '#businessLine',
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
                    key: $('#entityName_ser').val()
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
            test(data.sceneId, data.versionId,data.sceneIdentify);
        } else if (obj.event === 'auto_verification') { // 自动验证
            autoTest(data.versionId);
        } else if (obj.event === 'varbind') { // 变量绑定
            edit(data.sceneId, data.versionId);
        }
    });

    <!-- 手动测试 -->
    function test(sceneId, versionId,sceneIdentify) {
        $.get(preBindUrl + "getAll?sceneId=" + versionId, function (data) {
            var result = data.data;
            $.get('/rule/ui/strategy/manualTest', null, function (form) {
                var layIndex=layer.open({
                    type: 1,
                    title: '修改',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    content: form,
                    zIndex: layer.zIndex, //重点1
                    success: function (da, index) {
                        console.log(">>" + da + ">>index:==" + index);
                        $("#senceVersionid").val(versionId);
                        $("#sceneId").val(sceneId);
                        $("#sceneIdentify").val(sceneIdentify);

                        setVariableVal(result); // 渲染表单
                    },
                    yes: function (index) {
                        // layedit.sync(editIndex);
                        // 触发表单的提交事件
                        $('form.layui-form')
                            .find('button[lay-filter=formDemo]').click();
                        layer.close(index);
                    },
                });
                layer.full(layIndex);
            });
        }, 'json')
    }

    <!-- 自动测试 -->
    function autoTest(sceneId, versionId,sceneIdentify) {
        $.get(preBindUrl + "getAll?sceneId=" + versionId, function (data) {
            var result = data.data;
            $.get('/rule/ui/strategy/autoTest', null, function (form) {
                var layIndex=layer.open({
                    type: 1,
                    title: '修改',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    content: form,
                    btn: ['保存', '取消'],
                    zIndex: layer.zIndex, //重点1
                    success: function (da, index) {
                        console.log(">>" + da + ">>index:==" + index);
                        $("#senceVersionid").val(versionId);
                        $("#sceneId").val(sceneId);
                        $("#sceneIdentify").val(sceneIdentify);
                    },
                    yes: function (index) {
                        // 触发表单的提交事件
                        $('form.layui-form')
                            .find('button[lay-filter=formDemo]').click();
                        layer.close(index);
                    },
                });
                layer.full(layIndex);
            });
        }, 'json')
    }
    
    function setVariableVal(da) {
        // var modelVerification = new ModelVerification();
        var size = da.length;
        for(var i=0;i<size;i++){
            $("#bindColumnTable").append("<tr>\n" +
                "\t\t\t\t\t\t\t<input type=\"hidden\" name=\""+da[i].variableCode+"_bind\" id=\"bindId"+i+"\" value='"+da[i].id+"'>\n" +
                "\t\t\t\t\t\t\t<td><label class=\"layui-form-label mylabel\">"+da[i].variableName+":</label></td>\n" +
                "\t\t\t\t\t\t\t<td><div class=\"layui-input-block mycss\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"text\" id=\""+da[i].variableCode+"\" name=\""+da[i].variableCode+"\" required\n" +
                "\t\t\t\t\t\t\t\t\t   lay-verify=\"required\" placeholder=\"请输入"+da[i].variableName+"\" autocomplete=\"off\"\n" +
                "\t\t\t\t\t\t\t\t\t   class=\"layui-input\" >\n" +
                "\t\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t</tr>");
        }
    }

    function showRuleTestResult(logId,versionId){
        $
            .ajax({
                cache : true,
                type : "GET",
                url: preUrl + 'ruleMatchResult/'+logId+'/'+versionId, // 数据接口
                data : {
                    "sceneId" : sceneId
                },
                async : false,
                error : function(request) {
                    alert("Connection error");
                },
                success : function(da) {
                    console.log(da);
                    var size = da.data.length;


                }
            });
    }

    function edit(sceneId, versionId) {
        $.get(preUrl + "getAll?sceneId=" + versionId, function (data) {
            var result = data.data;
            $.get('/rule/ui/ruleBind/index/edit', null, function (form) {
                layer.open({
                    type: 1,
                    title: '修改',
                    maxmin: true,
                    shadeClose: false, // 点击遮罩关闭层
                    area: ['730px', '460px'],
                    content: form,
                    btn: ['保存', '取消'],
                    btnAlign: 'c',
                    zIndex: layer.zIndex, //重点1
                    success: function (da, index) {
                        console.log(">>" + da + ">>index:==" + index);


                        $("#senceVersionid").val(versionId);
                        $("#sceneId").val(sceneId);


                    },
                    yes: function (index) {
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
});

