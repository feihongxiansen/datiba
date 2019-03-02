package com.dtb.utils.resulthandler;

/**
 * @Author：lmx
 * @Description：自定义异常处理类，以便使用自定义的异常
 * @Date：Created on 20:51 2019/2/28.
 * @ModifyBy：
 */
public class CommonRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected String code;

    protected String msg;

    protected String message;//打印出的日志信息

    public CommonRuntimeException(CommonErrorEnum enums, String message) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.message = message;
    }

    public CommonRuntimeException(CommonErrorEnum enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public CommonRuntimeException() {
        super();
    }

    public CommonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonRuntimeException(String message) {
        super(message);
    }
}
