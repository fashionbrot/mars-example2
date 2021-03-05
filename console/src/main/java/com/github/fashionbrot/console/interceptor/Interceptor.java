package com.github.fashionbrot.console.interceptor;


import com.alibaba.fastjson.JSON;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.service.MenuService;
import com.github.fashionbrot.core.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserLoginService userLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (log.isDebugEnabled()){
            log.debug("request url:{}",request.getRequestURI());
        }

        LoginModel model = userLoginService.getSafeLogin();
        if (model == null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (!handlerMethod.hasMethodAnnotation(ResponseBody.class)){
                response.sendRedirect(url(request)+"/login");
            }else{
                response.setHeader("login","true");
                return false;
            }
            return true;
        }
        if (!menuService.checkPermissionUrl(handler,request,model)){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if ( handlerMethod.hasMethodAnnotation(ResponseBody.class)){
                returnJson(response, RespVo.builder()
                        .code(RespVo.FAILED)
                        .msg(RespCode.NOT_PERMISSION_ERROR.getMsg())
                        .build());
                return false;
            }
            response.sendRedirect(url(request)+"/401?requestUrl="+request.getRequestURI());
            return false;
        }else{
            return true;
        }
    }

    public String url(HttpServletRequest request){
        String path = request.getContextPath();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    }



    private void returnJson(HttpServletResponse response, Object json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSON(json));
        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
