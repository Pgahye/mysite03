package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jx372.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		if(supportsParameter(parameter) == false){ // 지원하는 타입이냐 확인 
			
			return WebArgumentResolver.UNRESOLVED; //null을 리턴해주면 안됨 , 그냥 해석 못한다고 pass
		}
		
		// @authtuser가 붙어있고 파라미터 타입이 UServo 
		
		// 톰캣의 HttpServletRequest 인지 확인 
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		
		HttpSession session = request.getSession();
		
		if(session == null){
			
			return null;
		}
		
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @authUser가 안붙어있음
		if(authUser == null){
			
			return false;
		}
		
		// 파라미터 타입이 Uservo의 타입이 아닐때 
		if(parameter.getParameterType().equals(UserVo.class) == false){
			
			return false;
		}
		
		return true;
	}

}
