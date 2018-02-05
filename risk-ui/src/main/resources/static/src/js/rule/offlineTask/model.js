///////////////////////////////////////////////////////////////////////
var debug_var;
var preUrl = "/rule/service/modelTask/";//模型url
var preModelTaskUrl = "/rule/service/modelTask/";
var layer, modelTable, table, laydate ,modelTableActive;
var conId, conKey, conType;
var modelId, taskId;
layui.config({
	base : '/rule/ui/src/js/' // 假设这是你存放拓展模块的根目录
}).extend({ // 设定模块别名
	myutil : 'common' // 如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use([ 'table', 'form', 'myutil', 'laydate' ], function() {
	layer = layui.layer;
	table = layui.table;
	modelTable = layui.table;
	form = layui.form;
	laydate = layui.laydate;
	var app = layui.app, $ = layui.jquery, form = layui.form;
	var common = layui.myutil;

	// 渲染时间控件
	laydate.render({
		elem : '#set_date',
		format : 'yyyy-MM-dd HH:mm:ss',
		value : new Date(),
		type : 'datetime'
	});

	// 模型列表
	modelTable.render({
		elem : '#modelTable',
		height : 550,
		cellMinWidth : 80,
		url : preUrl + 'page/' // 数据接口
		,
		page : true // 开启分页
		,
		id : 'modelTable',
		cols : [ [ // 表头
		{
			field : 'modelProcdefId',
			event : 'setTasK',
			title : '模型标识'
		}, {
			field : 'modelName',
			event : 'setTasK',
			title : '模型名称'
		}, {
			field : 'isValidate',
			event : 'setTasK',
			title : '自动测试',
			templet : '#model_auto_test',
			unresize : true,
			fixed : 'right'
		}, {
			field : 'taskStatus',
			event : 'setTasK',
			title : '定时任务',
			templet : '#task_status',
			unresize : true,
			fixed : 'right'
		}, {
			field : 'conId',
			title : '操作',
			fixed : 'right',
			align : 'center',
			toolbar : '#task_bar'
		} ] ]
	});
	
    //重载
    //这里以搜索为例
	modelTableActive = {
        reload: function(){
            //var demoReload = $('#demoReload');
        	alert($('#model_name').val());
            //执行重载
            table.reload('modelTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                	modelName: $('#model_name').val()
                }
            });
        }
    };

	// 监听工具条
	modelTable.on('tool(modelTable)', function(obj) {
		var data = obj.data;
		if (obj.event === 'setTasK') {
			modelId = data.id;
			$.post(preModelTaskUrl + "/getModelTaskByModel", {
				modelProcdefId : modelId
			},
					function(data) {
						//设置表单初始值
						console.log(data.data);
						if (undefined != data.data.cornText) {
							taskId = data.data.id;
							$("#iframe_corn_config").contents().find("#cron")
									.val(data.data.cornText);
							$("#iframe_corn_config").contents().find("#btnFan")
									.click();
							//							$("#cron",document.frames("iframe_corn_config").document).val("123");
						} else {
							$("#iframe_corn_config").contents().find("#cron")
									.val("");
							$("#iframe_corn_config").contents().find("#btnFan")
									.click();
							taskId = -1;
						}
						//					form.render();
						//					  layer.open({
						//						  	type: 2, 
						//						  	area: ['100%', '100%'],
						//						  	offset: ['5px', '5px'],
						//						  	content: ['http://cron.qqe2.com/', 'no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
						//							}); 
					}, 'json');
			//动态渲染form
			$("#div_task_config").show();
		}
		
		if (obj.event === 'start'){
			var data = obj.data;
			layer.confirm('启动任务,修改任务状态，添加到任务调度器', function(index) {
				$.get(preModelTaskUrl + "startModelTask/" + data.id, function(data) {
				}, 'json');
				layer.close(index);
			});
		}
		
		if (obj.event === 'stop'){
			var data = obj.data;
			layer.confirm('停止任务,修改任务状态，从任务调度器删除', function(index) {
				$.get(preModelTaskUrl + "stopModelTask/" + data.id, function(data) {
				}, 'json');
				layer.close(index);
			});
		}
	});

	$("#offline_task_item_btn_save").on('click', function() {
		var cornText = $("#iframe_corn_config").contents().find("#cron").val();//jquery 方法1  ;
		//添加規則
		$.post(preModelTaskUrl + "/edit", {
			modelProcdefId : modelId,
			cornText : cornText,
			taskId : taskId
		}, function(data) {
			if (data.data == '0') {
				layer.msg('操作成功', {
					icon : 1
				});
			} else {
				layer.msg('操作失败', {
					icon : 2
				});
			}
		}, 'json');
	});

	$("#offline_task_btn_add_del").on('click', function() {
		var set_date = $("#set_date").val();
		var frequencyType = $("#frequencyType").val();
		var enableStatus = $("#enableStatus").val();
		//刪除任务
		layer.confirm('真的删除行么', function(index) {
			$.get(preModelTaskUrl + "delete/" + taskId, function(data) {
			}, 'json');
			layer.close(index);
		});
	});

	function datetimeFormat_1(longTypeDate) {
		var datetimeType = "";
		var date = new Date();
		date.setTime(longTypeDate);
		datetimeType += date.getFullYear(); //年 
		datetimeType += "-" + getMonth(date); //月  
		datetimeType += "-" + getDay(date); //日 
		datetimeType += "  " + getHours(date); //时 
		datetimeType += ":" + getMinutes(date); //分
		datetimeType += ":" + getSeconds(date); //分
		return datetimeType;
	}
	//返回 01-12 的月份值  
	function getMonth(date) {
		var month = "";
		month = date.getMonth() + 1; //getMonth()得到的月份是0-11 
		if (month < 10) {
			month = "0" + month;
		}
		return month;
	}
	//返回01-30的日期 
	function getDay(date) {
		var day = "";
		day = date.getDate();
		if (day < 10) {
			day = "0" + day;
		}
		return day;
	}
	//返回小时
	function getHours(date) {
		var hours = "";
		hours = date.getHours();
		if (hours < 10) {
			hours = "0" + hours;
		}
		return hours;
	}
	//返回分
	function getMinutes(date) {
		var minute = "";
		minute = date.getMinutes();
		if (minute < 10) {
			minute = "0" + minute;
		}
		return minute;
	}
	//返回秒
	function getSeconds(date) {
		var second = "";
		second = date.getSeconds();
		if (second < 10) {
			second = "0" + second;
		}
		return second;
	}
});