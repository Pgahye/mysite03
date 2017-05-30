package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.guestBookVo;




@Repository
public class guestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	

	
public List<guestBookVo> getList(){
		
	
		
		List<guestBookVo> list=sqlSession.selectList("guestbook.getList");
		
		return list;
		
	}
	
	public boolean insert(guestBookVo vo) {

		int count = sqlSession.insert("guestbook.insert", vo);

		return count == 1;

	}	
	public boolean delete(guestBookVo vo){
		
		int count = sqlSession.delete("guestbook.delete", vo);
		
		return count == 1;
		
		
	}
public boolean delete2(Long no, String pwd){
		
	Map<String, Object> map= new HashMap<String, Object>(); //vo가 없을경우 map을 사용
	
	map.put("no", no);
	map.put("pwd", pwd);
	
	int count = sqlSession.delete("guestbook.delete", pwd);
		
		return count == 1;
		
		
	}
public boolean writemessage(guestBookVo vo) {

	int count = sqlSession.insert("guestbook.insert", vo);

	return count == 1;

}	


}
