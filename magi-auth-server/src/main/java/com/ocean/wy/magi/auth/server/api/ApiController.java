package com.ocean.wy.magi.auth.server.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 类ApiController.java的实现描述：api统一入口
 * 
 * @author caojun.cj 2015年12月9日 下午6:00:54
 */

@Controller
@RequestMapping("/api")
public class ApiController extends ApiBaseController implements ApplicationContextAware {

    private static Map<String, Method> methodCache      = new ConcurrentHashMap<String, Method>();

    private static Map<String, Object> serviceCache     = new ConcurrentHashMap<String, Object>();

    private ApplicationContext         applicationContext;

    private static final String        ApiServicePrefix = "com.ocean.api.service.";

    private static final String        ApiServiceSuffix = "Service";

    private static final String        ActionName       = "action";

    protected Log                      logger           = LogFactory.getLog(this.getClass());


    @RequestMapping("/dispatcher")
    public void dispatcher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApiResult result = new ApiResult();
        try {
            String action = request.getParameter(ActionName);
            Assert.hasText(action, "miss parametre action!");
            invokeService(request, action, result);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("parameters:" + parameterMapTolog(request) + e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
         }
        outputToJSON(response, result);
    }

    private void invokeService(HttpServletRequest request, String action,
                               ApiResult result) throws ClassNotFoundException, SecurityException,
                                                 NoSuchMethodException, IllegalArgumentException,
                                                 IllegalAccessException, InvocationTargetException {
        Method methodBean = methodCache.get(action);
        if (null == methodBean) {
            String[] invokeInfo = action.split("\\.");
            Assert.isTrue(invokeInfo.length == 2, "action format error,action:" + action);
            Class<?> clazz = Class.forName(appendSeviceName(invokeInfo[0]));
            methodBean = clazz.getDeclaredMethod(invokeInfo[1], new Class[] { HttpServletRequest.class });
            methodCache.put(action, methodBean);
            serviceCache.put(action, applicationContext.getBean(clazz));
        }

//        //鉴权处理
//        AuthCheck annotation = methodBean.getAnnotation(AuthCheck.class);
//        if (null != annotation) {
//            logger.info("AuthAction-" + action + "workId-" + request.getParameter("workId"));
//            boolean ifHasAuth = authWayService.authCheck(request, annotation.resourceType(),
//                                                         annotation.targetValueType());
//            Assert.isTrue(ifHasAuth, "没有权限");
//        }
        
        
        Object data = methodBean.invoke(serviceCache.get(action), new Object[] { request });
        result.setData(data);
    }

    private String parameterMapTolog(HttpServletRequest request) {
        Set<String> keys = request.getParameterMap().keySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : keys) {
            stringBuilder.append(key).append(":").append(request.getParameter(key)).append(",");
        }
        return stringBuilder.toString();
    }

    private String appendSeviceName(String actionServiceName) {
        return new StringBuffer().append(ApiServicePrefix).append(actionServiceName).append(ApiServiceSuffix).toString();
    }


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
