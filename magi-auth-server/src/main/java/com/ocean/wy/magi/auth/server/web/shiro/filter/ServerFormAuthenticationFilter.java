package com.ocean.wy.magi.auth.server.web.shiro.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ocean.wy.magi.auth.server.Constants;
import com.ocean.wy.magi.auth.server.entity.User;
import com.ocean.wy.magi.auth.server.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-3-16
 * <p>Version: 1.0
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {
	@Autowired
	private UserService userService;
	
	protected Log logger = LogFactory.getLog(this.getClass());
	/**
     * 表示当访问拒绝时
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                logger.debug("Login submission detected.  Attempting to execute login.");
                return this.executeLogin(request, response);
            } else {
                logger.debug("Login page view.");
                return true;
            }
        } else {
            logger.debug("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    /**
     * 当登录成功
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //将user对象放入session，这里你可能用不到，可以删除
        User user=userService.findByUsername(token.getPrincipal().toString());
        //----------以上代码你可以删除-------------------
        ((HttpServletRequest)request).getSession().setAttribute(Constants.CURRENT_USER,user);

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
                .getHeader("X-Requested-With"))) {// 登录请求不是Ajax类型
            issueSuccessRedirect(request, response);
        } else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.println("{\"success\":true,\"message\":\"登入成功\"}");
            out.flush();
            out.close();
        }
        return false;
    }

    /**
     * 当登录失败
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
                .getHeader("X-Requested-With"))) {// 登录请求不是ajax请求
            setFailureAttribute(request, e);
            return true;
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String message = e.getClass().getSimpleName();
            if ("IncorrectCredentialsException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"账号或密码错误\"}");
            } else if ("UnknownAccountException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"账号不存在\"}");
            } else if ("LockedAccountException".equals(message)) {
                out.println("{\"success\":false,\"message\":\"账号被锁定\"}");
            } else {
                out.println("{\"success\":false,\"message\":\"未知错误\"}");
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }

}
