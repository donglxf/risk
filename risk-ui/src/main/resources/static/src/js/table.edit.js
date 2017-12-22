$(document).ready(function () {
    $("#btn1").bind("click", delOneRow),
        $("#btn2").bind("click", delOneCol),
        $("#btn3").bind("click", delNotAllRow),
        $("#btn4").bind("click", delNotAllCol),
        $("#btn5").bind("click", hiddenRow),
        $("#btn6").bind("click", hiddenCol),
        $("#btn7").bind("click", insertRowLast),
        $("#btn8").bind("click", insertRowindex),
        $("#btn9").bind("click", getCellValue),
        $("#btn10").bind("click", getColValue),
        $("#btn11").bind("click", getRowlValue),
        $("#btn12").bind("click", myColSpan),
        $("#btn13").bind("click", myColSplit),
        $("#btn14").bind("click", myRowSpan),
        $("#btn15").bind("click", myRowSplit),
        $("#ckbAll").bind("click", checkAll),
        $("#table4 tr:gt(0) td:last-child input:checkbox").bind("click",checkOne);


    //鼠标移动到行变色,单独建立css类hover
    //tr:gt(0):表示获取大于 tr index 为0 的所有tr，即不包括表头
    $("#table1 tr:gt(0)").hover(
        function () { $(this).addClass("hover") },
        function () { $(this).removeClass("hover") }),

        //奇偶行不同颜色
        $("#table2 tbody tr:odd").css("background-color", "#bbf"),
        $("#table2 tbody tr:even").css("background-color", "#ffc"),
        //当然也可以单独建立css 类 odd 和 even
        //$("#table2 tbody tr:odd").addClass("odd"),
        //$("#table2 tbody tr:even").addClass("even"),

        //点击#table3 的单元格返回 单元格索引
        $("#table3 td").click(function () {
            var tdSeq = $(this).parent().find("td").index($(this));
            var trSeq = $(this).parent().parent().find("tr").index($(this).parent());
            alert("第" + (trSeq) + "行，第" + (tdSeq+1) + "列");
        })
});

//删除指定行（第二行）
function delOneRow() {
    $("#table3 tr:gt(0):eq(1)").remove();
}

//删除指定列第二列
function delOneCol() {
    //eq:获取子元素索引从 0 开始
    $("#table3 tr th:eq(1)").remove();
    //nth-child:获取子元素从 1 开始
    $("#table3 tr td:nth-child(2)").remove();
}

//删除第二行外的所有行
function delNotAllRow() {
    $("#table3 tr:gt(0):not(:eq(1))").remove();
}

//删除第二列外的所有列
function delNotAllCol() {
    $("#table3 tr th:not(:eq(1))").remove();
    $("#table3 tr td:not(:nth-child(2))").remove();
}

//隐藏指定行 第二行
function hiddenRow() {
    $("#table3 tr:gt(0):eq(1)").hide();
    //$("#table3 tr:gt(0):eq(1)").css("display", "none")
    //显示
    //$("#table3 tr:gt(0):eq(1)").css("display", "");
}

//隐藏指定列 第二列
function hiddenCol() {
    $("#table3 tr th:eq(1)").hide();
    $("#table3 tr td:nth-child(2)").hide();
    //$("#table3 tr th:eq(1)").css("display", "none");
    //$("#table3 tr td:nth-child(2)").css("display", "none");
    //显示
    //$("#table3 tr th:eq(1)").css("display", "");
    //$("#table3 tr td:nth-child(2)").css("display", "");
}

//在最后插入新行
function insertRowLast() {
    var newRow = "<tr style=\"background:red;\"><td>新行第一列</td><td>新行第二列</td><td>新行第三列</td><td>新行第四列</td><td>新行第五列</td></tr>";
    $("#table3 tr:last").after(newRow);
}

//在第二行后插入行
function insertRowindex() {
    var newRow = "<tr style=\"background:red;\"><td>新行第一列</td><td>新行第二列</td><td>新行第三列</td><td>新行第四列</td><td>新行第五列</td></tr>";
    $("#table3 tr:gt(0):eq(1)").after(newRow);
}

//获得单元格内值 比如 2*3
function getCellValue() {
    var v = $("#table3 tr:gt(0):eq(1) td:eq(2)").text();
    alert(v);
}

//获得一列的所有值 比如第二列
function getColValue() {
    var v = "";
    $("#table3 tr td:nth-child(2)").each(function () {
        v += $(this).text()+" ";
    });
    alert(v);
}

//获得一行所有值 比如第二行
function getRowlValue() {
    var v = "";
    $("#table3 tr:gt(0):eq(1) td").each(function () {
        v += $(this).text() + " ";
    });
    alert(v);
}

//横向合并单元格 比如合并 第二行 第二个和第三个单元格
function myColSpan() {
    $("#table3 tr:gt(0):eq(1) td:eq(1)").attr("colspan", 2);
    $("#table3 tr:gt(0):eq(1) td:eq(2)").remove();
}

//拆分单元格将刚才的单元格还原
function myColSplit() {

    //注意不能使用
    //$("#table3 tr:gt(0):eq(1) td:eq(1)").removeAttr("colspan");
    $("#table3 tr:gt(0):eq(1) td:eq(1)").attr("colspan", 1);
    $("#table3 tr:gt(0):eq(1) td:eq(1)").after("<td>第二行第三列</td>")

}

//纵向合并单元格 比如和并第二行第二个单元格和第三行第二个单元格
function myRowSpan() {
    $("#table3 tr:gt(0):eq(1) td:eq(1)").attr("rowspan", 2);
    $("#table3 tr:gt(0):eq(2) td:eq(1)").remove();
}

//纵向拆分单元格 将刚刚合并的单元格还原
function myRowSplit() {
    $("#table3 tr:gt(0):eq(1) td:eq(1)").attr("rowspan", 1);
    //在下面行第一个单元格后插入单元格
    $("#table3 tr:gt(0):eq(2) td:eq(0)").after("<td>第三行第二列</td>");
}

function checkAll() {
    if ($("#ckbAll").attr("checked")) {
        $("#table4 tr:gt(0) td:last-child input:checkbox").attr("checked", true);
    }
    else {
        $("#table4 tr:gt(0) td:last-child input:checkbox").attr("checked", false);
    }
}

function checkOne() {
    var i = $("#table4 tr:gt(0) td:last-child input:checkbox").length;
    var c = 0;
    $("#table4 tr:gt(0) td:last-child input:checkbox").each(function () {
        if ($(this).attr("checked")) {
            c += 1;
        }
        else {
            return false;
        }
    });
    if (i == c) {
        $("#ckbAll").attr("checked", true);

    }
    else {
        $("#ckbAll").attr("checked", false);
    }
}