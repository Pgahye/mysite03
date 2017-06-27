package com.jx372.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.UserService;

@Controller("userApiController") //동일한 클래스 이름이 존재하기 때문에 충돌이 발생함 
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public Map<String, Object> checkEmail(@RequestParam(value="email",required=true, defaultValue="") String email){
		
	
	
		
		boolean exist=userService.existEmail(email);
		
		System.out.println(exist);
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", exist);
		
		return map;
	}
	

}
