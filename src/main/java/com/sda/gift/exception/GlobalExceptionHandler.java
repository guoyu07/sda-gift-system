package com.sda.gift.exception;

import com.sda.gift.framework.common.RestResult;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/28.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public RestResult restErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        return new RestResult(false,e.getMessage(),null,null);
    }
}
