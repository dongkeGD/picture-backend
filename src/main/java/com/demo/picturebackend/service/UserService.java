package com.demo.picturebackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.picturebackend.model.dto.user.UserQueryRequest;
import com.demo.picturebackend.model.dto.user.UserRegisterRequest;
import com.demo.picturebackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.picturebackend.model.vo.LoginUserVO;
import com.demo.picturebackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 15815
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-06-19 12:29:56
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param request 用户注册对象
     * @return id
     */
    long userRegister(UserRegisterRequest request);

    /**
     * 用户登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @param httpServletRequest 请求
     * @return 登录视图对象
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest);

    /**
     * 转换成视图对象
     * @param user 源对象
     * @return 目标对象
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取当前用户
     * @param request 请求
     * @return user
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 用户注销
     * @param request 请求对象
     * @return 是 / 否
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取返回给前端的脱敏用户
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取返回给前端的脱敏用户列表
     * @param userList
     * @return
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 用于生成
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
