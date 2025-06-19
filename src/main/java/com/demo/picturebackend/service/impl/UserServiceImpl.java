package com.demo.picturebackend.service.impl;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.picturebackend.common.Md5Utils;
import com.demo.picturebackend.exception.BusinessException;
import com.demo.picturebackend.exception.ErrorCode;
import com.demo.picturebackend.exception.ThrowUtils;
import com.demo.picturebackend.model.dto.user.UserRegisterRequest;
import com.demo.picturebackend.model.entity.User;
import com.demo.picturebackend.model.enums.UserRoleEnum;
import com.demo.picturebackend.model.vo.LoginUserVO;
import com.demo.picturebackend.service.UserService;
import com.demo.picturebackend.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.demo.picturebackend.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author 15815
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-06-19 12:29:56
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Override
    public long userRegister(UserRegisterRequest request) {
         String userAccount = request.getUserAccount();
         String userPassword = request.getUserPassword();
         String checkPassword = request.getCheckPassword();

        // 1. 校验
        ThrowUtils.throwIf(
                StrUtil.hasBlank(userAccount, userPassword, checkPassword),
                ErrorCode.PARAMS_ERROR,
                "参数为空");
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "user account is less than four");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"pwd is less than 8");
        }
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "checkpwd is not pwd");
        }
        // 2. 检查是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.baseMapper.selectCount(queryWrapper);
        ThrowUtils.throwIf(count > 0, ErrorCode.OPERATION_ERROR, "account is not only");
        // 3. 加密
        String encryptPassword = Md5Utils.getEncryptPassword(userPassword);
        // 4. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("用户暂未来命名");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean save = this.save(user);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "save is fail");
        }
        return user.getId();
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "account or pwd is error");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "account is too shorter");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "pwd is too shorter");
        }
        // 2. 验证
        String encryptPassword = Md5Utils.getEncryptPassword(userPassword);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", userPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "account or pwd is error");
        }
        // 3. 记录登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public User getLoginUser(HttpServletRequest request){
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User cur = (User) attribute;
        if (cur == null || cur.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Long userId = cur.getId();
        cur = this.getById(userId);
        if (cur == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return cur;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        if (attribute == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "not login");
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }
}




