package com.demo.picturebackend.controller;

import com.demo.picturebackend.common.BaseResponse;
import com.demo.picturebackend.common.ResultUtils;
import com.demo.picturebackend.exception.BusinessException;
import com.demo.picturebackend.exception.ErrorCode;
import com.demo.picturebackend.exception.ThrowUtils;
import com.demo.picturebackend.model.dto.user.UserLoginRequest;
import com.demo.picturebackend.model.dto.user.UserRegisterRequest;
import com.demo.picturebackend.model.entity.User;
import com.demo.picturebackend.model.vo.LoginUserVO;
import com.demo.picturebackend.service.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:Thesy <a href="https://github.com/dongkeGD">dongkeGD</a>
 * @Create:2025/6/19 - 13:13
 */
@RestController
@RequestMapping("/")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest request, HttpServletRequest servletRequest) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        String userAccount = request.getUserAccount();
        String userPassword = request.getUserPassword();
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, servletRequest);
        return ResultUtils.success(loginUserVO);
    }

    @GetMapping("/getLoginUser")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest httpServletRequest) {
        User loginUser = userService.getLoginUser(httpServletRequest);
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

    @GetMapping("/uesrLogout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userService.userLogout(request));
    }
}
