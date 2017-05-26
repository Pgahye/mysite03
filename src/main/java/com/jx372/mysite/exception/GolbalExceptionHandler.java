package com.jx372.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class GolbalExceptionHandler {
	
	@ExceptionHandler(Exception.class) // 여러개 하려면 Exception.class, 각각하려면 UserDaoException.class 
	public ModelAndView handleException(Exception e){ //컨트롤러가 아니라서 String을 던질수가 없음 !!
		
		//1.로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		//2. 안내페이지 가기 
		ModelAndView mav=new ModelAndView();
		mav.addObject("exception",errors.toString());
		mav.setViewName("error/exception");
		System.out.println(e);
		
		return mav;
	}
	

}
