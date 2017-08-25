package com.sda.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
public class ProductController {

    @GetMapping("/product")
    public String product(){
        return "Product";
    }
}
