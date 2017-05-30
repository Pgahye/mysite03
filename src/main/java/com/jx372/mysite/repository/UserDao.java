package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource datasource;
	

	
	public UserVo get (Long no){ //수정폼
		
		//맵을 mresultType으로 사용하는 예제 , 맵은 vo가 없을때 사용한다. 
		//Map map=sqlSession.selectOne("user.getByNo", no);
		//List<Map> list=sqlSession.selectList("user.getByNo, no);
		
		
		UserVo uservo = sqlSession.selectOne("user.getByNo", no);
	
		return uservo;
	}
	//로그인 처리 
	public UserVo get(String email,String password ) throws UserDaoException {
		
		Map<String, Object> map= new HashMap<String, Object>(); //vo가 없을경우 map을 사용
		
		map.put("email", email);
		map.put("password", password);
		
		UserVo uservo = sqlSession.selectOne("user.getByNo1", map);
				
		
		return uservo;
		
	}
	
	public boolean insert(UserVo vo) {
		
	
		int count = sqlSession.insert("user.insert",vo);
		
		return count ==1;
	}
	
public boolean update(UserVo vo) {
		
		int count = sqlSession.update("user.update",vo);
		
		return count ==1;
	}
	
public boolean smallupdate(UserVo vo) {
		
	int count = sqlSession.update("user.smallupdate",vo);
		
		return count ==1;
	}

}
