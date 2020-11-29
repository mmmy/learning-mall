package com.example.mall.learning.common.api;

/**
 * 通用返回对象
 */
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param <T> 数据类型
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回失败的结果
     * @return
     */
    public static <T> CommonResult failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    public static <T> CommonResult<T> failed() {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), ResultCode.FORBIDDEN.getMessage(), null);
    }

    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
