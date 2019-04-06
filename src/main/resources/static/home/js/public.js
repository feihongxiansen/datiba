// JavaScript Document
layui.use(['element', 'layer', 'form'], function(){
	var element = layui.element
	,layer = layui.layer
	,form = layui.form
	,carousel = layui.carousel;
});

/*更新成功提示*/
function refresh() {
    window.location.reload();//刷新当前界面
}

/*更新成功提示，关闭弹窗*/
function refreshParent() {
    window.parent.location.reload();//刷新父界面
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);//关闭弹出层
}

/**
 * 获取当前日期时间字符串
 * @returns {string}
 */
function getNowTimeStr() {
    var date = new Date();
    var nowTime = date.getFullYear() + '-'
        + (date.getMonth() + 1) + '-'
        + date.getDate() + ' '
        + date.getHours() + ':'
        + date.getMinutes() + ':'
        + date.getSeconds();
    return nowTime;
}

/**
 * 页面跳转
 */
function goTo(href) {
    window.location.href = href;
}