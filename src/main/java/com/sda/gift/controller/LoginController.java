package com.sda.gift.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@Slf4j
public class LoginController {
    @GetMapping("/")
    public String login(){
        return "login";
    }
    @PostMapping("/doLogin")
    public String doLogin(String userId, String password){
      log.info("登陆用户id"+userId +"密码：" + password);
        return "product";
    }
}
