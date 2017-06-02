package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOG = LogFactory.getLog( AuthLoginInterceptor.class );

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String email = request.getParameter("email");
		String password=request.getParameter("password");
		
		
		//ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()); //ApplicationContext컨테이너 내용 불러오기 
		
		//UserService userService = ac.getBean(UserService.class); // 빈호출
		
		UserVo userVo = userService.getUser(email, password);
		
		if(userVo == null){
			
			LOG.error( "#userVo not - error log" );
			response.sendRedirect(request.getContextPath()+"/user/login?result=fail");
			return false;
		}
		
		// 로그인처리
		
		
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("authUser", userVo);
		response.sendRedirect(request.getContextPath()+"/main");
		
		return false;
	}
	
	//컨트롤러의 @RequestMapping(value="/login",method=RequestMethod.POST)public String login을 interceptor로 만듬 
	

}
