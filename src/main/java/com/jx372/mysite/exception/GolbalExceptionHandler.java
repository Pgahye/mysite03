package com.jx372.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jx372.mysite.dto.JSONResult;



@ControllerAdvice
public class GolbalExceptionHandler {
	
	@ExceptionHandler(Exception.class) // 여러개 하려면 Exception.class, 각각하려면 UserDaoException.class 
	public void handleException(
			HttpServletResponse response,
			HttpServletRequest request, Exception e)throws Exception { //컨트롤러가 아니라서 String을 던질수가 없음 !!
		
		//1.로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		// 2. 요청종류 알아내기 
		
		String accept = request.getHeader("accept");
		System.out.println(accept);
		
		
		// 3. 응답
		
		if(accept.matches(".* application/json.*") == true){
			
			// json 요청 (ajax request, xmlhttpRequest)
			
			response.setStatus(HttpServletResponse.SC_OK);
			
			OutputStream out = response.getOutputStream();
			
			out.write( new ObjectMapper().writeValueAsString( JSONResult.error( errors.toString() ) ).getBytes() );
			out.flush();
			out.close();
			
		}else{
			
			// web 요청 ( html, image, xml, js, css.....) 
			
			//2. 안내페이지 가기 
			//ModelAndView mav=new ModelAndView();
			//mav.addObject("exception",errors.toString());
			//mav.setViewName("error/exception");
			//System.out.println(e);
			
			//return mav;
			
			request.setAttribute("url", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
			
			
		}
		
	
	}
	

}
