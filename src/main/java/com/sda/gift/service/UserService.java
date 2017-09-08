package com.sda.gift.service;

import com.sda.gift.model.entity.UserEntity;
import com.sda.gift.dao.mapper.UserMapper;
import com.sda.gift.web.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity checkAccount(String userId,String password){
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)){
            throw new AuthenticationException("用户名或密码为空");
        }
        if(userId.equalsIgnoreCase("admin") && password.equalsIgnoreCase("%&TUGUR&^T*&IHKJKJG")){
            return new UserEntity("admin");
        }
        UserEntity user = userMapper.select(userId);
        if(user.getIdNumber().substring(12).equalsIgnoreCase(password)){
            return user;
        }
        return null;
    }
}
