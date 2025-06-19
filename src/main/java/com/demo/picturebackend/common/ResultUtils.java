package com.demo.picturebackend.common;

import com.demo.picturebackend.exception.ErrorCode;

/**
 * ClassName:ResultUtils
 * Package:com.demo.picturebackend.common
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 17:33
 * @Version: v1.0
 */
public class ResultUtils {

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, "ok", data);
    }

    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse<?> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    public static BaseResponse<?> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}
