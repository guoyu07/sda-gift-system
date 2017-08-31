package com.sda.gift.controller;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.entity.ProductEntity;
import com.sda.gift.entity.UserEntity;
import com.sda.gift.framework.cache.CacheManager;
import com.sda.gift.framework.common.RestResult;
import com.sda.gift.framework.tool.CookieTool;
import com.sda.gift.framework.tool.GuidGenerator;
import com.sda.gift.framework.tool.JwtTool;
import com.sda.gift.service.OrderService;
import com.sda.gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @Value("${gift.activityName}")
    private String activityName;

    @GetMapping("/")
    public ModelAndView product(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("product");
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        UserEntity user = (UserEntity)CacheManager.getCacheInfo(jwtToken).getValue();
        List<ProductEntity> pros = productService.list();
        List<OrderEntity> odrs = orderService.getOrder(user.getUserId());
        if(odrs.size()>0){
            for (OrderEntity order:odrs) {
                ProductEntity pro = pros.stream().filter(c->c.getProId().equalsIgnoreCase(order.getProId())).findFirst().get();
                pro.setProNum(order.getProNum());
            }
            mv.addObject("isChosed",true);
        }
        mv.addObject("userName",user.getName());
        mv.addObject("productList",pros);
        return mv;
    }
    @PostMapping("/chooseProduct")
    @ResponseBody
    public RestResult chooseProduct(HttpServletRequest request){
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        UserEntity user = (UserEntity)CacheManager.getCacheInfo(jwtToken).getValue();
        List<ProductEntity> pros = productService.list();
        List<OrderEntity> odrList = new ArrayList<>();
        String takePlace = request.getParameter("takePlace");
        String takeTime = request.getParameter("takeTime");
        String totalPrice = request.getParameter("totalPrice");

        for (ProductEntity pro:pros) {
            int proNum = Integer.parseInt(request.getParameter(pro.getProId()));
            if(proNum > 0){
                OrderEntity orderEntity = new OrderEntity(
                        GuidGenerator.newGuid(),
                        user.getUserId(),
                        pro.getProId(),
                        pro.getProName(),
                        proNum, takePlace, takeTime, totalPrice,activityName);
                odrList.add(orderEntity);
            }
        }
        orderService.addOrder(odrList);
        return new RestResult(true,"礼品选择成功",null,null);
    }
}
