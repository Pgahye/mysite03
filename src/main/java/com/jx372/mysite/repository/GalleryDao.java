package com.jx372.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GalleryVo;
import com.jx372.mysite.vo.guestBookVo;



@Repository
public class GalleryDao {
	
	
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	public boolean insert(GalleryVo vo) {

		int count = sqlSession.insert("gallery.insert", vo);

		return count ==1 ;
		
	}
	
public List<GalleryVo> getList(){
		
		
		List<GalleryVo> list=sqlSession.selectList("gallery.getList");

		
		return list;
		
	}

public void delete(Long no){
	
	
	sqlSession.delete("gallery.delete", no);
	
}

}
