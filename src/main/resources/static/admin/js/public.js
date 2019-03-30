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
 * 页面跳转
 */
function goTo(href) {
    window.location.href = href;
}

/*刷新验证码*/
function refreshVerify(id) {
    var myData = new Date();
    var times = myData.getTime();
    $("#" + id).attr("src", "/common/verifyCode/getVerifyCode?times=" + times);
}