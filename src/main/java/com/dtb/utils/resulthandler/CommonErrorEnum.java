package com.dtb.utils.resulthandler;

/**
 * @Author：lmx
 * @Description：错误代码及其错误原因
 * @Date：Created on 19:59 2019/2/28.
 * @ModifyBy：
 */
public enum CommonErrorEnum {

    SYSTEM_ERROR("-001","系统异常"),
    BAD_REQUEST("-002","错误的请求参数"),
    NOT_FOUND("-003","找不到请求路径！"),
    CONNECTION_ERROR("-004","网络连接请求失败！"),
    METHOD_NOT_ALLOWED("-005","不合法的请求方式"),
    DATABASE_ERROR("-004","数据库异常"),
    BOUND_STATEMENT_NOT_FOUNT("-006","找不到方法！"),
    REPEAT_REGISTER("001","重复注册"),
    NO_USER_EXIST("002","用户不存在"),
    INVALID_PASSWORD("003","密码错误"),
    NO_PERMISSION("004","非法请求！"),
    SUCCESS_OPTION("005","操作成功！"),
    NOT_MATCH("-007","用户名和密码不匹配"),
    FAIL_GETDATA("-008","获取信息失败"),
    BAD_REQUEST_TYPE("-009","错误的请求类型"),
    INVALID_MOBILE("010","无效的手机号码"),
    INVALID_EMAIL("011","无效的邮箱"),
    INVALID_GENDER("012","无效的性别"),
    REPEAT_MOBILE("014","已存在此手机号"),
    REPEAT_EMAIL("015","已存在此邮箱地址"),
    NO_RECORD("016","没有查到相关记录"),
    LOGIN_SUCCESS("017","登录成功"),
    LOGOUT_SUCCESS("018","已退出登录"),
    SENDEMAIL_SUCCESS("019","邮件已发送，请注意查收"),
    EDITPWD_SUCCESS("020","修改密码成功"),
    NO_FILESELECT("021","未选择文件"),
    FILEUPLOAD_SUCCESS("022","上传成功"),
    NOLOGIN("023","未登陆"),
    ILLEGAL_ARGUMENT("024","参数不合法"),
    ERROR_VERIFYCODE("025","验证码不正确"),
    NO_VERIFYCODE("026","缺少验证码"),
    REFRESH_VERIFYCODE("027","需要刷新验证码"),
    NO_VERIFY_EMAIL("028","未验证邮箱"),
    BAN_LOGIN("029","当前用户已被禁止登录"),
    WAIT_VERIFY_EMAIL("030","注册成功，请验证邮箱以激活账户"),
    NOT_EXIST_EMAIL("031","当前邮箱地址可用"),
    FAILED_CREATEUSER("032","创建用户失败"),
    VERIFYED_EMAIL("033","当前邮箱已验证，请勿重复验证"),
    ERROR_EMAILCODE("034","邮箱验证码错误"),
    FAILED_VERIFY("035","验证失败"),
    SUCCESS_VERIFY_EMAIL("036","邮箱验证成功"),
    FAILED_SENDEMAIL("037","发送邮件失败"),
    SUCCESS_REQUEST("038","请求成功"),
    FAILED_QUESTION("039","操作失败"),
    WAIT_VERIFY("040", "提交成功，等待审核"),
    FAILED_AUTH("041", "非法请求，请登录后操作"),
    FIRST_DOWNLOAD("042", "首次下载");

    private String code;

    private String msg;

    CommonErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
