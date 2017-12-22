(function ($) {
    $.fn.extend({
        //表格合并单元格，colIdx要合并的列序号，从0开始
        "rowspan": function (colIdx) {
            return this.each(function () {
                var that;
                $('tr', this).each(function (row) {
                    $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {
                        if (that != null && $(this).html() == $(that).html()) {
                            rowspan = $(that).attr("rowSpan");
                            if (rowspan == undefined) {
                                $(that).attr("rowSpan", 1);
                                rowspan = $(that).attr("rowSpan");
                            }
                            rowspan = Number(rowspan) + 1;
                            $(that).attr("rowSpan", rowspan);
                            $(this).hide();
                        } else {
                            that = this;
                        }
                    });
                });
            });
        }
    });

})(jQuery);

/**
 * 当前行下添加一行
 * @param t
 */
function  addRow(t) {
   // console.log($('#trTpl').find(".ctr").html());
    var tr = $(t).parent().parent().parent();
    tr.after( tr.clone() );
    init();
    //获取当前行行数
    //$(t).append( $(t).parent().parent().parent().clone(true))
}

/**
 * 删除当前行
 * @param t
 */
function deleteRow(t){
     $(t).parent().parent().parent().remove();
}

/**
 * 添加条件列
 */
function addCol() {

    $th = $("#tpl").find("th:eq(0)").clone();
    $td = $("#tpl").find("td:eq(0)").clone();
    $col = $("#tpl").find("col:eq(0)").clone();
    var index = $(".contion").length;
    index = index - 2;
    $("#table>colgroup>col:eq("+index+")").after($col);
    $("#table>thead>tr>th:eq("+index+")").after($th);

    //需要添加多少列
    $("#table>tbody>tr").each(function(i,e){
        var td = $("#tpl").find("td:eq(0)").clone();
        $(e).find("td:eq("+index+")").after(td);
       // $($(e).find("td:eq("+index+")")[0]).after(td);
    });
    init();
}

/**
 * 添加动作列
 */
function addActionCol() {

    $th = $("#tpl").find("th:eq(1)").clone();
    $td = $("#tpl").find("td:eq(1)").clone();
    $col = $("#tpl").find("col:eq(1)").clone();
    var index = $("#table thead tr th").length;
    index = index - 2;
  //  index = index - 2;
    $("#table>colgroup>col:eq("+index+")").after($col);
    $("#table>thead>tr>th:eq("+index+")").after($th);

    //需要添加多少列
    $("#table>tbody>tr").each(function(i,e){
        var td = $("#tpl").find("td:eq(1)").clone();
        $(e).find("td:eq("+index+")").after(td);
        // $($(e).find("td:eq("+index+")")[0]).after(td);
    });
    init();
}

/**
 * 添加条件列
 */
function deleteCol(t) {
    var td = $(t).parent();
    var index = td.index();
    $("#table>colgroup>col:eq("+index+")").remove();

    $("#table>thead>tr>th:eq("+index+")").remove();
    $("#table>tbody>tr").each(function(i,e){
        var td = $("#tpl").find("td").clone();
        $(e).find("td:eq("+index+")").remove();
    });


}

function hb(){
    $("#table").rowspan(0); //以第一列合并可用，但是会影响后面的新增，或删除操作
}

var condata = [
    { value: "&&==", text: "等于" },
    { value: "&&!=", text: "不等于" },
    {value: "&&<",text:"小于"},
    {value:"&&<=",text:"小于或等于"},
    {value:"&&>",text:"大于"},
    {value:"&&>=",text:"大于或等于"},
    {value:"&&in",text:"包含"},
    {value:"&&not in",text:"不包含"},
    {value:"&&like%",text:"开始以"},
    {value:"&&%like",text:"结束以"},
];
function init(){
    $("#table thead tr th.contion ").hover(function () {
        $(this).find(".del").show();
    },function () {
        $(this).find(".del").hide();
    })
    $('.val').editable({
        type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
        title: "值",              //编辑框的标题
        disabled: false,             //是否禁用编辑
        emptytext: "空文本",          //空值的默认文本
        mode: "popup",              //编辑框的模式：支持popup和inline两种模式，默认是popup
        validate: function (value) { //字段验证
            if (!$.trim(value)) {
                return '不能为空';
            }
        }
    });
    $('.action').editable({
        type: "text",                //编辑框的类型。支持text|textarea|select|date|checklist等
        title: "结果",              //编辑框的标题
        disabled: false,             //是否禁用编辑
        emptytext: "空文本",          //空值的默认文本
        mode: "inline",              //编辑框的模式：支持popup和inline两种模式，默认是popup
        validate: function (value) { //字段验证
            if (!$.trim(value)) {
                return '不能为空';
            }
        }
        ,success: function(response, newValue) {
            alert(newValue);
        }
    });
    //测试部门
    $('.itemC').editable({
        type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
        source: [{ value: 1, text: "开发部" }, { value: 2, text: "销售部" }, {value:3,text:"行政部"}],
        title: "选择变量名",           //编辑框的标题
        disabled: false,           //是否禁用编辑
        emptytext: "选择变量",       //空值的默认文本
        mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
        validate: function (value) { //字段验证

            if (!$.trim(value)) {
                return '不能为空';
            }
        }
    });
    $('.entityC').editable({
        type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
        // value:'2',
        source: [{ value: 1, text: "开发部" }, { value: 2, text: "销售部" }, {value:3,text:"行政部"}],
        title: "选择对象",           //编辑框的标题
        disabled: false,           //是否禁用编辑
        emptytext: "选择对象",       //空值的默认文本
        mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
        validate: function (value) { //字段验证
            if (!$.trim(value)) {
                return '不能为空';
            }
        }
    });

    $('.con').editable({
        type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
        source: condata,
        title: "选择条件",           //编辑框的标题
        disabled: false,           //是否禁用编辑
        emptytext: "选择条件",       //空值的默认文本
        mode: "popup",            //编辑框的模式：支持popup和inline两种模式，默认是popup
        validate: function (value) { //字段验证
            console.log($(this));
            console.log(value);
            $(this).attr("val",value);
            $(this).attr("data-value",value);
            if (!$.trim(value)) {
                return '不能为空';
            }
        }
    });

}
$(function () {
    init();

});