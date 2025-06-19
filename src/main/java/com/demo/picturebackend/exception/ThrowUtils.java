package com.demo.picturebackend.exception;

/**
 * ClassName:ThrowUtils
 * Package:com.demo.picturebackend.exception
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 17:04
 * @Version: v1.0
 */
public class ThrowUtils {

    /**
     * 条件成立则抛出异常
     * @param condition 断言条件
     * @param runtimeException 抛出异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     *
     * @param condition 断言条件
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     *
     * @param condition 断言条件
     * @param errorCode 错误码
     * @param message   错误消息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }

}
