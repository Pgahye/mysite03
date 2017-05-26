package com.jx372.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.NodeList;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join(){
		
		return "user/join";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo){
		
		
		userService.join(userVo);
		
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value="/joinsuccess")
	public String joinsuccess(){
		
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		
		return "user/login";
		
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login	
			(	HttpSession session,
				Model model,	
			@RequestParam(value="email", required=true, defaultValue="")String email,
			@RequestParam(value="password", required=true, defaultValue="") String password
			){
		
		UserVo userVo=userService.getUser(email, password);
		if(userVo ==null){
			
			model.addAttribute("result", "fail");
			return "user/login";
		}
		
		//인증
		
		session.setAttribute("authUser", userVo);
		
		return "redirect:/main";
		
	}
	@RequestMapping("/logout")
	public String losgout(HttpSession session ){
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
		
	}
	

	
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modify(HttpSession session ){
		
		
		UserVo authUser= (UserVo) session.getAttribute("authUser");
		// 인증 여부 체크 (접근제한) --> 어노테이션 방식으로 분리할꺼임 
		if(authUser ==null){
			
			return "redirect:/user/login";
		}
		//로그아웃 상태에서 수정할수 있는 걸 막아야함 
		return "user/modify";
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute UserVo userVo) {

		if (userVo.getPassword() == "") {

			userService.smallupdate(userVo);
		} else {

			userService.outUser(userVo);

		}

		return "redirect:/main";
	}
	
	/*
	@ExceptionHandler(UserDaoException.class)
	public String handleUserDaoException(){ //컨트롤러마다 만들어줘야하는 단점이 있음 
		
		//1.로깅
		
		//2. 사과페이지
		
		return "error/exception";
	} */
	
	
	
	
	
	
	
	
	
}
