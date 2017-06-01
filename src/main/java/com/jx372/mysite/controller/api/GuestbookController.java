package com.jx372.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.guestBookService;

@Controller("guestApiController") //동일한 클래스 이름이 존재하기 때문에 충돌이 발생함 
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private guestBookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(){
		
		return guestbookService.getList();
	}

}
