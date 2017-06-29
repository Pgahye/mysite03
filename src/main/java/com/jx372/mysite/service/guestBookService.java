package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.guestBookDao;
import com.jx372.mysite.vo.UserVo;
import com.jx372.mysite.vo.guestBookVo;

@Service
public class guestBookService {

	
	@Autowired
	private guestBookDao guestBookDao;
	
	
	public List<guestBookVo> getList() {

		return guestBookDao.getList();

	}
	
	
	public List<guestBookVo> getList(Long startNo) {

		return guestBookDao.getList(startNo);

	}
	
	public boolean insert(guestBookVo vo){
		
		
		return guestBookDao.insert(vo);
	}
	
	public boolean delete(guestBookVo vo){
		
		
		return guestBookDao.delete(vo);
	}
	
	
}
