
//config的设置是全局的,引入工具包
layui.config({
    base: '/rule/ui/src/js/rule/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    sceneUtil: 'decision' //如果 mymod.js 是在根目录，也可以不用设定别名
});
layui.use(['table','form','laytpl','laydate','sceneUtil'], function() {
    var laytpl = layui.laytpl;
    var sceneUtil = layui.sceneUtil;
    var $ = layui.jquery;
    sceneUtil.sceneId = sceneId;


    sceneUtil.dataInit.actionBank();
    console.log(sceneUtil.data.actionBank);
});

