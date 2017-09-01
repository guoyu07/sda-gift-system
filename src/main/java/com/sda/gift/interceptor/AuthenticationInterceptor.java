package com.sda.gift.interceptor;

import com.sda.gift.entity.UserEntity;
import com.sda.gift.framework.cache.Cache;
import com.sda.gift.framework.cache.CacheManager;
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
        String jwtToken = CookieTool.getCookieValue(request,"accessToken");
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.isEmpty(jwtToken)){
            if(requestType!=null && requestType.equalsIgnoreCase("XMLHttpRequest")){
                response.addHeader("ACCESS","1");
            }else{
                response.sendRedirect("/login/");
            }
            return false;
        }
        UserEntity user = JwtTool.unsign(jwtToken,UserEntity.class);
        if(null == user || StringUtils.isEmpty(user.getUserId())){
            response.sendRedirect("/login/");
            return false;
        }
        CacheManager.putCacheInfo(jwtToken,user,1200);
        if(user.getUserId().equalsIgnoreCase("admin")){
            if(request.getServletPath().toLowerCase().contains("/maintain/")){
                return true;
            }else{
                return false;
            }
        }else if(StringUtils.isEmpty(user.getUserId())){
            response.sendRedirect("/login/");
            return false;
        }else{
            if(request.getServletPath().toLowerCase().contains("/product/")){
                return true;
            }else{
                return false;
            }
        }
    }
}
