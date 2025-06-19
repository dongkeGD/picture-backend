package com.demo.picturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:Thesy <a href="https://github.com/dongkeGD">dongkeGD</a>
 * @Create:2025/6/19 - 14:20
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -4384254940033092351L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;
}
