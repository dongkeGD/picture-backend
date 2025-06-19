package com.demo.picturebackend.common;

import com.demo.picturebackend.exception.ErrorCode;

import java.io.Serializable;

/**
 * 全局响应封装类
 * ClassName:BaseResponse
 * Package:com.demo.picturebackend.common
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 17:24
 * @Version: v1.0
 */
public class BaseResponse<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(int code, T data) {
        this(code, "", data);
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), "", null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
