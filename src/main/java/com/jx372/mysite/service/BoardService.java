package com.jx372.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.BoardDao;
import com.jx372.mysite.repository.guestBookDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.guestBookVo;

@Service
public class BoardService {
	
	
	@Autowired
	private BoardDao BoardDao;
	
	public int getsize(String keyword){
		
		
		int sum = BoardDao.getsize(keyword);
		
		if(sum%5==0){
			sum=(sum / 5);
			
		}else{
			
			sum=(sum / 5) + 1;
			
		}
		
		return sum;
		
	}
	
	public Map<String, Object> getList(int num, String keyword, int sum){
		
		List<BoardVo> list =BoardDao.getList(num,keyword); 

		Map<String, Object> map = new HashMap<String, Object>(); 
		 
		map.put( "list", list ); 
		
		map.put( "keyword", keyword ); 
		
		if(num>1){
			
			map.put("prepage", num-1);	
		}else{
			
			map.put("prepage", num);	
		}
		
		if(num+1 <= sum){
			map.put("nextpage", num+1);		
		}else{
			map.put("nextpage", num);	
		}
		
		 
		return map; 

	}
	
	public boolean insert(BoardVo vo){
		
		if(vo.getNo() != null){
			
			vo.setDep(vo.getDep()+1);
			vo.setOrder_no(vo.getOrder_no()+1);
			
		}
		
		
		return BoardDao.insert(vo);
	}
	public BoardVo get (Long no){
		
		return BoardDao.get(no);
		
	}
	public boolean hitupdate(BoardVo vo) {
		
		return BoardDao.hitupdate(vo);
	}
	public boolean delete(BoardVo vo){
		
		return BoardDao.delete(vo);
		
	}
	
	public boolean update(BoardVo vo) {
		
		return BoardDao.update(vo);
	}
	public BoardVo getNo (Long no){
		
		return BoardDao.getNo(no);
	
	}

	public boolean replyupdate(BoardVo vo) {
		
		return BoardDao.replyupdate(vo);
	}
}
