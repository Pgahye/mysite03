package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx372.mysite.service.UserService;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		
		session.removeAttribute("authUser");
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/main");

		return false;
	}

}
