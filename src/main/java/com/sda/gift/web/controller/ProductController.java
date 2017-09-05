package com.sda.gift.web.controller;

import com.sda.gift.model.entity.OrderEntity;
import com.sda.gift.model.entity.ProductEntity;
import com.sda.gift.model.entity.UserEntity;
import com.sda.gift.web.exception.PriceException;
import com.sda.gift.framework.cache.CacheManager;
import com.sda.gift.framework.common.RestResult;
import com.sda.gift.framework.tool.CookieTool;
import com.sda.gift.framework.tool.GuidGenerator;
import com.sda.gift.service.OrderService;
import com.sda.gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
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
    @Value("${gift.quota}")
    private String quota;

    @GetMapping("/")
    public ModelAndView product(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("product");
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        UserEntity user = (UserEntity)CacheManager.getCacheInfo(jwtToken).getValue();
        List<ProductEntity> pros = productService.getAllAvailable();
        List<OrderEntity> odrs = orderService.getOrder(user.getUserId(),activityName);
        if(odrs.size()>0){
            for (OrderEntity order:odrs) {
                ProductEntity pro = pros.stream().filter(c->c.getProId().equalsIgnoreCase(order.getProId())).findFirst().get();
                pro.setProNum(order.getProNum());
            }
            OrderEntity orderEntity = odrs.get(0);
            mv.addObject("takePlace",orderEntity.getTakePlace());
            mv.addObject("takeTime",orderEntity.getTakeTime());
            mv.addObject("totalPrice",orderEntity.getTotalPrice());
            mv.addObject("isChosed",true);
        }
        mv.addObject("userName",user.getName());
        mv.addObject("quota",quota);
        mv.addObject("activityName",activityName);
        mv.addObject("productList",pros);
        return mv;
    }
    @PostMapping("/chooseProduct")
    @ResponseBody
    public RestResult chooseProduct(HttpServletRequest request){

        String takePlace = request.getParameter("takePlace");
        String takeTime = request.getParameter("takeTime");
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        UserEntity user = (UserEntity)CacheManager.getCacheInfo(jwtToken).getValue();
        List<ProductEntity> pros = productService.getAllAvailable();
        List<OrderEntity> odrList = new ArrayList<>();
        for (ProductEntity pro:pros) {
            int proNum = Integer.parseInt(request.getParameter(pro.getProId()));
            if(proNum > 0){
                OrderEntity orderEntity = new OrderEntity(
                        GuidGenerator.newGuid(),
                        user.getUserId(),
                        pro.getProId(),
                        pro.getProName(),
                        proNum, takePlace, takeTime, pro.getProPrice().multiply(new BigDecimal(proNum)),activityName);
                odrList.add(orderEntity);
            }
        }
        checkPrice(odrList,totalPrice);
        orderService.saveOrder(odrList,user.getUserId(),activityName);
        return new RestResult(true,"礼品选择成功",null,null);
    }

    private void checkPrice(List<OrderEntity> orderEntities, BigDecimal totalPrice){
        BigDecimal addResult = BigDecimal.ZERO;
        for (OrderEntity odr: orderEntities) {
            addResult.add(odr.getTotalPrice());
        }
        if(addResult.compareTo(totalPrice)!=0){
            throw new PriceException("金额有误，请不要搞鬼！");
        }
        if(totalPrice.compareTo(new BigDecimal(quota))==1){
            throw new PriceException("超出额度，请重新选择！");
        }
    }
}
