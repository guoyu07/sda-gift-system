package com.sda.gift.controller;

import com.sda.gift.entity.OrderEntity;
import com.sda.gift.entity.ProductEntity;
import com.sda.gift.entity.UserEntity;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/8/24.
 */
@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    private String userId;
    @Value("${gift.activityName}")
    private String activityName;

    @GetMapping("/product")
    public ModelAndView product(HttpServletRequest request){
        List<ProductEntity> pros = productService.list();
        ModelAndView mv = new ModelAndView("product");
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        UserEntity user = JwtTool.unsign(jwtToken,UserEntity.class);
        userId = user.getUserId();
        mv.addObject("userName",user.getName());
        mv.addObject("productList",pros);
        return mv;
    }
    @PostMapping("/chooseProduct")
    public void chooseProduct(HttpServletRequest request){
        List<ProductEntity> pros = productService.list();
        List<OrderEntity> odrList = new ArrayList<>();
        String takePlace = request.getParameter("takePlace");
        String takeTime = request.getParameter("takeTime");
        String totalPrice = request.getParameter("totalPrice");

        for (ProductEntity pro:pros) {
            String proNum = request.getParameter(pro.getProId());
            if(!StringUtils.isEmpty(proNum)&&!proNum.trim().equalsIgnoreCase("0")){
                OrderEntity orderEntity = new OrderEntity(
                        GuidGenerator.newGuid(),
                        userId,
                        pro.getProId(),
                        pro.getProName(),
                        proNum, takePlace, takeTime, totalPrice,activityName);
                odrList.add(orderEntity);
            }
        }
        orderService.saveOrder(odrList);
    }
}
