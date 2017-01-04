package com.ocean.wy.magi.auth.server.api.service;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-2-15
 * <p>Version: 1.0
 */
@Component
public class LoginApiService {
//	http://127.0.0.1:8080/api/dispatcher?action=LoginApi.login&&username=root&&password=giligili
    public String login(HttpServletRequest request) {
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        return "login";
    }
    


}
