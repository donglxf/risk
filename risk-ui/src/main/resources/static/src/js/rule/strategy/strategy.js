///////////////////////////////////////////////////////////////////////
var preBindUrl = "/rule/service/variableBind/";
var preUrl = "/rule/service/strategy/";
var p_sceneId = -1;
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
            manuTest(data.sceneId, data.versionId,data.sceneIdentify);
        } else if (obj.event === 'auto_verification') { // 自动验证
            autoTest(data.sceneId, data.versionId,data.sceneIdentify);
        } else if (obj.event === 'varbind') { // 变量绑定
            edit(data.sceneId, data.versionId);
        }
    });


    function manuTest(sceneId, versionId,sceneIdentify){
        var url=preBindUrl+ "getAll?versionId=" + versionId ;
        $.ajax({
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
        });
    }

    <!-- 自动测试 -->
    function autoTest(sceneId, versionId,sceneIdentify) {
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

});


