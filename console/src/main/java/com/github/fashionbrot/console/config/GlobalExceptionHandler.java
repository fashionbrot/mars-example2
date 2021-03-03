package com.github.fashionbrot.console.config;


import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.CurdException;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.vo.RespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MarsException.class)
    @ResponseStatus(HttpStatus.OK)
    public RespVo marsException(MarsException e) {
        return RespVo.fail(e.getMsg(), e.getCode());
    }

    @ExceptionHandler(CurdException.class)
    @ResponseStatus(HttpStatus.OK)
    public RespVo curdException(CurdException e) {
        return RespVo.fail(e.getMsg(), e.getCode());
    }


    @ExceptionHandler(Exception.class)
    public Object globalException(HttpServletRequest request, HandlerMethod handlerMethod, Exception ex) {
        log.error("exception error:",ex);
        if(isAjax(request)){
            return RespVo.fail(RespCode.FAIL.getMsg());
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error/500");
            modelAndView.addObject("errorMsg",ex.getMessage());
            return modelAndView;
        }
    }


    public boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }




}
