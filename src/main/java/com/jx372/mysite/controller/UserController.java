package com.jx372.mysite.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.NodeList;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;
import com.jx372.security.Auth;
import com.jx372.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Log LOG = LogFactory.getLog( MainController.class );

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		
	
		

		return "user/join";
	}

	// @ResponseBody
	// @RequestMapping(value="/join",method=RequestMethod.POST)
	// public String join(@RequestBody String requestBody){

	// return requestBody;
	// }

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {

		userService.join(userVo);
		
	

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {

		LOG.warn( "#user-joinsuccess - warn log" );

		
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "user/login";

	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.GET) // 파라미터와
																	// 아규먼트는
																	// 똑같다고
																	// 생각하면댐
	public String modify(Model model, @AuthUser UserVo authUser) {

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);

		return "user/modify";
	}

	@Auth
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
	 * @ExceptionHandler(UserDaoException.class) public String
	 * handleUserDaoException(){ //컨트롤러마다 만들어줘야하는 단점이 있음
	 * 
	 * //1.로깅
	 * 
	 * //2. 사과페이지
	 * 
	 * return "error/exception"; }
	 */
	
	
	
	
	
	
	
}
