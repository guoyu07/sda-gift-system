package com.sda.gift.controller;

import com.auth0.jwt.internal.org.bouncycastle.math.raw.Mod;
import com.sda.gift.entity.ProductEntity;
import com.sda.gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ModelAndView product(){
        List<ProductEntity> pros = productService.list();
        ModelAndView mv = new ModelAndView("product");
        mv.addObject("productList",pros);
        return mv;
    }
}
