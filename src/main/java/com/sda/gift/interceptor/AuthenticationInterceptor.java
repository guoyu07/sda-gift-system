package com.sda.gift.interceptor;

import com.sda.gift.entity.UserEntity;
import com.sda.gift.framework.tool.CookieTool;
import com.sda.gift.framework.tool.JwtTool;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2017/8/25.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(request.getServletPath().toLowerCase().contains("login")){
            return true;
        }
        String jwtToken = CookieTool.getCookieValue(request,"token");
        if(StringUtils.isEmpty(jwtToken)){
            response.sendRedirect("/login");
            return false;
        }
        UserEntity user = JwtTool.unsign(jwtToken,UserEntity.class);
        if(null == user){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
