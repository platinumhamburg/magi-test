package com.ocean.wy.magi.auth.server.web.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ocean.wy.magi.auth.server.web.bind.annotation.CurrentUser;

/**
 * <p>用于绑定@FormModel的方法参数解析器
 * <p>User: Ocean.wy
 * <p>Date: 13-1-12 下午5:01
 * <p>Version: 1.0
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentUserMethodArgumentResolver() {
    }

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
	}

	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
	}

    
}
