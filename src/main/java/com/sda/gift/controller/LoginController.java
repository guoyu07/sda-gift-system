package com.sda.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
public class LoginController {
    @RequestMapping("/")
    public String login(){
        return "Login";
    }
}
