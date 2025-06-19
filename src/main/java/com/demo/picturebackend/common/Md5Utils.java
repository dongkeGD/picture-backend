package com.demo.picturebackend.common;

import org.springframework.util.DigestUtils;

/**
 * @Author:Thesy <a href="https://github.com/dongkeGD">dongkeGD</a>
 * @Create:2025/6/19 - 13:06
 */
public class Md5Utils {

    public static String getEncryptPassword(String password) {
        // 盐值，混淆密码
        final String SALT = "SALT";
        return DigestUtils.md5DigestAsHex((SALT + password).getBytes());
    }
}
