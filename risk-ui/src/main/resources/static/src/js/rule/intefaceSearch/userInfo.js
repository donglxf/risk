///////////////////////////////////////////////////////////////////////
var preUrl = "/rule/service/rpc/";
var layer, entityTable, itemTable, table, active, itemActive, topIndex;
layui.config({
    base: '/rule/ui/src/js/modules/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    myutil: 'common' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table', 'form', 'myutil', 'element'], function () {

    layer = layui.layer;
    table = layui.table;
    var $ = layui.jquery;
    var form = layui.form;
    form.render();
    var baseUrl = "/cls/core/find/";
    var element = layui.element;
    //loadTaobaoData();
    var phoneNo = null;
    var id = null;
    /*var identificationCard = null;*/
    element.on('tab(docDemoTabBrief)', function(elem){
        id = $(elem.elem.context).attr("id");
        switch (id){
            case "taobao": loadTaobaoData();break;
            case "jingdong": loadJingdongData();break;
            case "zhifubao": loadZhifubaoData();break;
            case "xingyongka": loadXingyongkaData();break;
            case "yunyingshang": loadYunyingshangData();break;
            case "tongxunlun": loadTongxunlunData();break;
            case "tonghua": loadTonghuaData();break;
            case "duanxing": loadDuanxingData();break;
        }
    });

    $('#searchBut').on('click', function () {
        if(checkUserInput() == true){
            switch (id){
                case "taobao": loadTaobaoData();break;
                case "jingdong": loadJingdongData();break;
                case "zhifubao": loadZhifubaoData();break;
                case "xingyongka": loadXingyongkaData();break;
                case "yunyingshang": loadYunyingshangData();break;
                case "tongxunlun": loadTongxunlunData();break;
                case "tonghua": loadTonghuaData();break;
                case "duanxing": loadDuanxingData();break;
                default: id ="taobao";loadTaobaoData();break;
            }
        }
        //element.tabChange('docDemoTabBrief', 'taobao');
        //loadTaobaoData();
    });

    /**
     * 淘宝信息
     */
    function loadTaobaoData(){
        if(checkUserInput() == true){
            var query = {
                "query":{
                    "phoneNo":phoneNo
                }
            }
            table.render({
                elem: '#taobao_table'
                ,url: baseUrl+'auth/find-tb-trade-list'
                ,method:"POST"
                ,cols: [[
                    {field:'tradeId',  title: '交易流水'}
                    ,{field:'itemName', title: '商品名称'}
                    ,{field:'original',  title: '原始商品价格'}
                    ,{field:'realTotal', title: '真实交易价格'}
                    ,{field:'quantity', title: '商品数量'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                ,where:query
                ,page: true
            });
            query = {
                "phoneNo":phoneNo
            }
            $.ajax({
                cache: true,
                type: "POST",
                url: baseUrl+'auth/find-tb-detail',
                data: JSON.stringify(query),
                contentType : "application/json",
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    initInputValue("taobao_","taobao_div",da.data);
                    layer.msg('个人淘宝信息查询成功！');
                }
            });
        };
    }
    /**
     * 京东信息
     */
    function loadJingdongData(){
        if(checkUserInput() == true){
            var query = {
                "query":{
                    "phoneNo":phoneNo
                }
            }
            table.render({
                elem: '#jingdong_table'
                ,url: baseUrl+'auth/find-jd-trade-list'
                ,method:"POST"
                ,cols: [[
                    {field:'tradeId',  title: '订单编号'}
                    ,{field:'receiver', title: '此订单收件人'}
                    ,{field:'receiveAddress',  title: '此订单收件地址'}
                    ,{field:'receivePhone', title: '此订单收件人手机'}
                    ,{field:'amount', title: '订单金额'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                ,where:query
                ,page: true
            });
            query = {
                "phoneNo":phoneNo
            }
            $.ajax({
                cache: true,
                type: "POST",
                url: baseUrl+'auth/find-jd-detail',
                data: JSON.stringify(query),
                contentType : "application/json",
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    initInputValue("jingdong_","jingdong_div",da.data);
                    layer.msg('个人京东信息查询成功！');
                }
            });
        };

    }
    /**
     * 支付宝信息
     */
    function loadZhifubaoData(){
        if(checkUserInput() == true){
            var query = {
                "query":{
                    "phoneNo":phoneNo
                }
            }
            table.render({
                elem: '#zhifubao_table'
                ,url: baseUrl+'auth/find-zfb-trade-list'
                ,method:"POST"
                ,cols: [[
                    {field:'tradeNumber',  title: '交易号'}
                    ,{field:'tradeTime', title: '付款时间'}
                    ,{field:'tradeLocation',  title: '交易来源地'}
                    ,{field:'productName', title: '商品名称'}
                    ,{field:'tradeAmount', title: '金额（元）'}
                    ,{field:'incomeorexpense', title: '收/支'}
                    ,{field:'tradeStatus', title: '交易状态'}
                    ,{field:'serviceCharge', title: '服务费（元）'}
                    ,{field:'refund', title: '成功退款（元）'}
                    ,{field:'comments', title: '备注'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                ,where:query
                ,page: true
            });
            query = {
                "phoneNo":phoneNo
            }
            $.ajax({
                cache: true,
                type: "POST",
                url: baseUrl+'auth/find-zfb-detail',
                data: JSON.stringify(query),
                contentType : "application/json",
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (da) {
                    initInputValue("zhifubao_","zhifubao_div",da.data);
                    layer.msg('个人支付宝信息查询成功！');
                }
            });
        };

    }
    /**
     * 信用卡信息
     */
    function loadXingyongkaData(){
        if(checkUserInput() == true){
            /*var data = null;
            table.render({
                elem: '#zhifubao_table'
                ,url:'/demo/table/user/'
                ,cols: [[
                    {field:'tradeNumber',  title: '交易号'}
                    ,{field:'tradeTime', title: '付款时间'}
                    ,{field:'tradeLocation',  title: '交易来源地'}
                    ,{field:'counterparty', title: '交易对方'}
                    ,{field:'productName', title: '商品名称'}
                    ,{field:'tradeAmount', title: '金额（元）'}
                    ,{field:'incomeorexpense', title: '收/支'}
                    ,{field:'serviceCharge', title: '服务费（元）'}
                    ,{field:'refund', title: '成功退款（元）'}
                ]]
                ,page: true
            });
            initInputValue("zhifubao_","zhifubao_div",data);*/
        };

    }
    /**
     * 运营商信息
     */
    function loadYunyingshangData(){
        if(checkUserInput() == true){
            /*var data = null;
            table.render({
                elem: '#zhifubao_table'
                ,url:'/demo/table/user/'
                ,cols: [[
                    {field:'tradeNumber',  title: '交易号'}
                    ,{field:'tradeTime', title: '付款时间'}
                    ,{field:'tradeLocation',  title: '交易来源地'}
                    ,{field:'counterparty', title: '交易对方'}
                    ,{field:'productName', title: '商品名称'}
                    ,{field:'tradeAmount', title: '金额（元）'}
                    ,{field:'incomeorexpense', title: '收/支'}
                    ,{field:'serviceCharge', title: '服务费（元）'}
                    ,{field:'refund', title: '成功退款（元）'}
                ]]
                ,page: true
            });
            initInputValue("zhifubao_","zhifubao_div",data);*/
        };

    }
    /**
     * 通讯录信息
     */
    function loadTongxunlunData() {
        if (checkUserInput() == true) {
            var query = {
                "query": {
                    "phoneNo":phoneNo
                }
            }
            var data = null;
            table.render({
                elem: '#tongxunlun_table'
                , url: baseUrl+'findContactRecords'
                , method:"POST"
                , cols: [[
                    {field: 'contactName', title: '联系人姓名'}
                    , {field: 'contactPhoneNumber', title: '联系人电话'}
                    , {field: 'imei', title: 'IMEI'}
                    , {field: 'createTime', title: '创建时间'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                , page: true
                , where:query
            });
        };
    }
    /**
     * 通话信息
     */
    function loadTonghuaData(){
        if(checkUserInput() == true){
            var query = {
                "query": {
                    "phoneNo":phoneNo
                }
            }
            table.render({
                elem: '#tonghua_table'
                , url: baseUrl+'findPhoneContacts'
                , method:"POST"
                ,cols: [[
                    {field: 'contactName', title: '联系人姓名'}
                    , {field: 'contactPhoneNumber', title: '联系人电话'}
                    , {field: 'imei', title: 'IMEI'}
                    , {field: 'createTime', title: '创建时间'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                , page: true
                , where:query
            });
        };

    }
    /**
     * 短信信息
     */
    function loadDuanxingData(){
        if(checkUserInput() == true){
            var query = {
                "query": {
                    "phoneNo":phoneNo
                }
            }
            table.render({
                elem: '#duanxing_table'
                , url: baseUrl+'findShortMessages'
                , method:"POST"
                ,cols: [[
                    {field: 'contactName', title: '联系人姓名'}
                    , {field: 'contactPhoneNumber', title: '联系人电话'}
                    , {field: 'imei', title: 'IMEI'}
                    , {field: 'content', title: '短信内容'}
                    , {field: 'createTime', title: '创建时间'}
                ]]
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    ,statusCode: "0000" //成功的状态码，默认：0
                    ,dataName: 'data' //数据列表的字段名称，默认：data
                }
                , page: true
                , where:query
            });
        };
    }

    function checkUserInput(){
        /*identificationCard = $("#user_identification_card").val();*/
        phoneNo =$("#user_phone_no").val();
        if(!phoneNo){
            layer.msg('手机号为必填！');
            return false;
        }
        return true;
    }

    function initInputValue(tab,divSelect,obj){
        var divObj = $("#"+divSelect);
        var key = null;
        var value = null;
        $.each(obj, function(i, val) {
            key = i;
            value = val;
            divObj.find("#"+tab+key).val(value);
        });
    }
});
