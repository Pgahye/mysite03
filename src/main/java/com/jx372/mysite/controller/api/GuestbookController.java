package com.jx372.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.dto.JSONResult;
import com.jx372.mysite.service.guestBookService;
import com.jx372.mysite.vo.guestBookVo;


//@CrossOrigin({"http://localhost:8088","http://www.naver.com" })
//@CrossOrigin("*")
@Controller("guestApiController") //동일한 클래스 이름이 존재하기 때문에 충돌이 발생함 
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private guestBookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value = "sno", required = true, defaultValue="0") Long startNo ){
		

		List<guestBookVo> list = guestbookService.getList(startNo);
		
		return  JSONResult.success(list);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public JSONResult delete(@ModelAttribute guestBookVo vo){
		
		boolean result = guestbookService.delete(vo);
		
		
		
		return JSONResult.success( result? vo.getNo() : -1);
	}
	
	
	
	//@ResponseBody
	//@RequestMapping(value="/ajinsert", method=RequestMethod.POST)
	//public JSONResult ajinsert(@ModelAttribute guestBookVo vo){
		
	//	guestbookService.insert(vo);
		
	//	return JSONResult.success(vo);
	//}
	
	
	@ResponseBody
	@RequestMapping(value="/ajinsert", method=RequestMethod.POST)
	public JSONResult ajinsert(@RequestBody guestBookVo vo){
		
		guestbookService.insert(vo);
		
		return JSONResult.success(vo);
	}
}
