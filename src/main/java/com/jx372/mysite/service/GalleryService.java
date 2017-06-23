package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.GalleryDao;
import com.jx372.mysite.vo.GalleryVo;




@Service
public class GalleryService {
	
	
	@Autowired
	private GalleryDao GalleryDao;

	
public boolean insert(GalleryVo vo) {
		
		return GalleryDao.insert(vo);
	}
	

public List<GalleryVo> getList(){
	
	
	return GalleryDao.getList();
}

public void delete(Long no){
	
	GalleryDao.delete(no);
	
}
	
	

}
