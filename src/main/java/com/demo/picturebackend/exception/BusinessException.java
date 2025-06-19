package com.demo.picturebackend.exception;

import javax.servlet.annotation.ServletSecurity;

/**
 * ClassName:BusinessException
 * Package:com.demo.picturebackend.exception
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 16:57
 * @Version: v1.0
 */
public class BusinessException extends RuntimeException{

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
