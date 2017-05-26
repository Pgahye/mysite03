package com.jx372.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.UserDao;
import com.jx372.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void join(UserVo userVo){
		
		// 1.db에 사용정보 저장
		
		userDao.insert(userVo);
		
		// 2. 인증메일보내기 
		
		
	}
	
	public UserVo getUser(String email, String password){
		
		return userDao.get(email, password);
		
	}
	public UserVo getUser(Long no){
		
		
		return userDao.get(no);
	}
	
	public boolean outUser(UserVo vo){
		
		
		return userDao.update(vo);
	}
	
	public boolean smallupdate(UserVo vo){
		
		
		return userDao.smallupdate(vo);
	}

}
