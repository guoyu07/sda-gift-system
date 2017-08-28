package com.sda.gift.controller;

import com.sda.gift.entity.UserEntity;
import com.sda.gift.exception.AuthenticationException;
import com.sda.gift.framework.tool.JwtTool;
import com.sda.gift.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/doLogin")
    public String doLogin(String userId, String password, HttpServletResponse response){
        UserEntity user = userService.checkAccount(userId, password);
        if(null==user){
            throw new AuthenticationException("用户认证失败！");
        }
        String token = JwtTool.sign(user, 30L * 24L * 3600L * 1000L);
        Cookie tokenCookie = new Cookie("accessToken", token);
        tokenCookie.setMaxAge(20*60);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
        return "redirect:product";
    }
}
