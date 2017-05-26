package com.jx372.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.jx372.mysite.repository.guestBookDao;
import com.jx372.mysite.service.guestBookService;
import com.jx372.mysite.vo.guestBookVo;



@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private guestBookService guestBookService;
	
	
	

	@RequestMapping("/list")
	public String list(Model model){
		
		List<guestBookVo> list=guestBookService.getList();
		model.addAttribute("list", list);
		
		for(guestBookVo vo : list){
			
			//System.out.println(vo);
		}

		return  "guestbook/list";
	}
	
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") Long no){
		
		
		model.addAttribute("no", no);
		
		
		return "guestbook/deleteform";
	}

	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute guestBookVo vo){
		
		guestBookService.insert(vo);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute guestBookVo vo){
		
		guestBookService.delete(vo);
		
		return "redirect:/guestbook/list";
	}
	
}
