package com.demo.picturebackend.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;
import lombok.val;

/**
 * ClassName:UserRoleEnum
 * Package:com.demo.picturebackend.model.enums
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/19 - 12:32
 * @Version: v1.0
 */
@Getter
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举对象
     * @param value 枚举值的value
     * @return 枚举对象
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.value.equals(value)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
