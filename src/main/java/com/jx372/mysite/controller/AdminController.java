package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.mysite.service.AdminService;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.SiteVo;
import com.jx372.security.Auth;


@Auth(role=Auth.Role.ADMIN) // @auth가 여기 달리게 변경 @Auth(role="ADMIN") 권한도 줄수 있게 
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService AdminService;
	
	
	@RequestMapping({"", "/main"})
	public String main(Model model, SiteVo sitevo){
		
		sitevo = AdminService.get();
		model.addAttribute("sitevo", sitevo);
		
		
		
		return "admin/main";
	}

	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@ModelAttribute SiteVo sitevo,
			@RequestParam(value="file1") MultipartFile file1,
			Model model
			){
		
	
		String url1 =  AdminService.restore(file1);
		sitevo.setFile(url1);
	
		AdminService.update(sitevo);
	
		
		
		return "redirect:/admin/main";
	}
	
	
	
	
	
	@RequestMapping("/guestbook")
	public String guestbook(){
		
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board(){
		
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user(){
		
		return "admin/user";
	}
	
}
