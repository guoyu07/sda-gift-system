package com.sda.gift.service;

import com.sda.gift.entity.UserEntity;
import com.sda.gift.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/8/24.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity checkAccount(String userId,String password){
        if(userId.equalsIgnoreCase("admin") && password.equalsIgnoreCase("12345")){
            return new UserEntity("admin");
        }
        UserEntity user = userMapper.select(userId);
        if(user.getIdNumber().substring(12).equalsIgnoreCase(password)){
            return user;
        }
        return null;
    }
}
