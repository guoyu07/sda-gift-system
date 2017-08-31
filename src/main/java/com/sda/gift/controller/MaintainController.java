package com.sda.gift.controller;

import com.sda.gift.entity.ProductEntity;
import com.sda.gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@RequestMapping("/maintain")
public class MaintainController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView maintain(){
        ModelAndView mv = new ModelAndView("maintain");
        List<ProductEntity> productList = productService.list();
        Map<String,ProductEntity> productMap = new HashMap<>();
        for (ProductEntity product:productList) {
            productMap.put(product.getGuid(),product);
        }
        mv.addObject("productList",productList);
        mv.addObject("productMap",productMap);
        return mv;
    }

    @PostMapping("/addProduct")
    @ResponseBody
    public String addProduct(ProductEntity productEntity){
        productService.add(productEntity);
        return "添加成功";
    }

    @PostMapping("/saveProduct")
    @ResponseBody
    public String saveProduct(ProductEntity productEntity){
        productService.save(productEntity);
        return "添加成功";
    }
}
