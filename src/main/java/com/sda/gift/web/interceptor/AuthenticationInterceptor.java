package com.sda.gift.web.interceptor;

import com.sda.gift.config.GiftConfig;
import com.sda.gift.framework.cache.Cache;
import com.sda.gift.model.entity.UserEntity;
import com.sda.gift.framework.cache.CacheManager;
import com.sda.gift.framework.tool.CookieTool;
import com.sda.gift.framework.tool.JwtTool;
import com.sda.gift.web.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2017/8/25.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private GiftConfig giftConfig;

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
        checkClickNum(jwtToken);//限制请求次数
        UserEntity user = JwtTool.unsign(jwtToken,UserEntity.class);
        if(null == user || StringUtils.isEmpty(user.getUserId())){
            response.sendRedirect("/login/");
            return false;
        }
        CacheManager.putCacheInfo(jwtToken,user,1000*60*30);
        if(user.getUserId().equalsIgnoreCase("admin")){
            if(request.getServletPath().toLowerCase().contains("/maintain/")){
                return true;
            }else{
                response.sendRedirect("/login/");
                return false;
            }
        }else if(StringUtils.isEmpty(user.getUserId())){
            response.sendRedirect("/login/");
            return false;
        }else{
            if(request.getServletPath().toLowerCase().contains("/product/")){
                return true;
            }else{
                response.sendRedirect("/login/");
                return false;
            }
        }
    }

    private void checkClickNum(String jwtToken) {
        String clickStr = "request"+jwtToken;
        Cache clickNumCache = CacheManager.getCacheInfo(clickStr);
        int clickNum=1;
        if(clickNumCache==null){
            CacheManager.putCacheInfo(clickStr,clickNum,1000*60*10);
        }else{
            clickNum = (int)clickNumCache.getValue();
            clickNum++;
        }
        if(clickNum < giftConfig.getRequestNum()){
            CacheManager.putCacheInfo(clickStr,clickNum,1000*60*10);
        }else{
            throw new AuthenticationException("请求过于频繁！");
        }
    }
}
