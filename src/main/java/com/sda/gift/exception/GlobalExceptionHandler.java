package com.sda.gift.exception;

import com.sda.gift.framework.common.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/28.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return new RestResult(false,e.getMessage(),null,null);
    }

}
