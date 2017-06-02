package com.jx372.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.SiteVo;
import com.jx372.mysite.vo.UserVo;

@Repository
public class SiteDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean update(SiteVo vo) {

		int count = sqlSession.update("sitemain.update", vo);

		return count ==1 ;
		
	}
	
public SiteVo get(){ //수정폼
		
		
	SiteVo sitevo = sqlSession.selectOne("sitemain.getByNo");
	
	
		return sitevo;
	}
}
