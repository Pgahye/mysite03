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
import org.springframework.web.multipart.MultipartFile;

import com.jx372.mysite.service.AdminService;
import com.jx372.mysite.service.GalleryService;
import com.jx372.mysite.vo.GalleryVo;
import com.jx372.mysite.vo.SiteVo;
import com.jx372.mysite.vo.guestBookVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private AdminService AdminService;
	
	@Autowired
	private GalleryService GalleryService;
	
	
	@RequestMapping("")
	public String index(Model model){
		
		
		List<GalleryVo> list=GalleryService.getList();
		model.addAttribute("list", list);
		
		return "gallery/index";
		
	}
	
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(
			@ModelAttribute GalleryVo galleryvo,
			@RequestParam(value="file") MultipartFile file,
			Model model
			){
		
	
		String url =  AdminService.restore(file);
		galleryvo.setUrl(url);
		
		//System.out.println(galleryvo.getUrl());
		//System.out.println(galleryvo.getContext());
		
		GalleryService.insert(galleryvo);
		
	
		
		
		return "redirect:/gallery";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no){
		
		GalleryService.delete(no);
		
		return "redirect:/gallery";
	}
	

}
