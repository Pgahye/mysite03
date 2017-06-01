package com.jx372.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.mysite.service.BoardService;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.mysite.vo.guestBookVo;
import com.jx372.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService BoardService;
	
	

	
	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
			@RequestParam(value = "keyword", required = true, defaultValue = "") String keyword

	) {

		int sum = BoardService.getsize(keyword);

		Map<String, Object> map = BoardService.getList(page, keyword, sum);

		model.addAttribute("map", map);

		model.addAttribute("sum", sum);

		return "board/list"; // 해당페이지로 가게 바꾸기......키워드랑 페이지 번호 ..... 글쓰다가 로그인하면
								// 다시 원래페이지로 리다이렉트 시키는 것
	}
	
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(
			@RequestParam( value="page", required=true, defaultValue="1") Integer page, 
			@RequestParam( value="keyword", required=true, defaultValue="") String keyword  

	) {

		return "board/write";
	}  
	
	@Auth
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET) //답변 
	public String replywrite( @PathVariable("no") Long no, BoardVo vo,Model model){
		
		
		vo = BoardService.getNo(no);
		model.addAttribute("vo", vo);
		
		
		
		return "board/write";
	}
	

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo, @ModelAttribute BoardVo boardVo,
			@RequestParam(value="no", required=true, defaultValue="") String no){


		UserVo authUser = (UserVo) session.getAttribute("authUser");
	
		vo.setUser_no(authUser.getNo());

		BoardService.insert(boardVo);

		if (no.equals("")) { // 답글의 유무

		} else {

			BoardService.replyupdate(vo);

		}

		return "redirect:/board/list";
	}
	

	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(Model model, @PathVariable("no") Long no){
		
		BoardVo vo = BoardService.get(no);
		
		BoardService.hitupdate(vo);
		
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, @PathVariable("no") Long no, BoardVo vo) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");
	
		vo = BoardService.getNo(no);

		if (authUser.getNo() == vo.getUser_no()) { // 사용자와 글쓴이가 같을 경우, 사용자 정보 보호를 위해 주소로 주고 받으면 안됨 

			BoardService.delete(vo);

		}

		return "redirect:/board/list";

	}

	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(HttpSession session, Model model, @PathVariable("no") Long no) {

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		
		BoardVo vo = BoardService.get(no);
		model.addAttribute("vo", vo);

		if (authUser.getNo() == vo.getUser_no()) { // 사용자와 글쓴이가 같을 경우

			return "board/modify";

		}

		return "redirect:/board/list";

	}
	
	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify( Model model,
			@RequestParam(value="title", required=true, defaultValue="")String title,
			@RequestParam(value="content", required=true, defaultValue="") String content,
		    @PathVariable("no") Long no, BoardVo vo) {
 
		
		vo.setTitle(title);
		vo.setNo(no);
		vo.setContent(content);
		
		BoardService.update(vo);
	

		return "redirect:/board/list";

	}
	
	

}
