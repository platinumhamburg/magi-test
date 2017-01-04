package com.ocean.wy.magi.auth.server.api;

/**
 * 类ApiResult.java的实现描述：所有的HTTP API输出必须用这个类包装
 * @author ocean.wy
 *
 */
public class ApiResult {

    /**
     * 生成唯一的uuid,用于追踪问题
     */
    private String  requestId;

    /**
     * 是否调用成功,如果出错,则根据errorCode和errorMsg查看错误信息
     */
    private boolean success   = true;
    /**
     * 输出数据
     */
    private Object  data;
    /**
     * 错误码,默认值是0,表示未定义的错误码
     */
    private int     errorCode = 0;
    /**
     * 错误信息
     */
    private String  errorMsg;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
