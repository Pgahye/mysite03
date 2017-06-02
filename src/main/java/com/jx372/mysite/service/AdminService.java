package com.jx372.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.mysite.repository.SiteDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.SiteVo;
import com.jx372.mysite.vo.UserVo;


@Service
public class AdminService {
	
	
	@Autowired
	private SiteDao SiteDao;
	
	private static final String SAVE_PATH = "/uploads"; //c드라이브 안붙인 이유는 루트 디렉토리에 만듬   
	private static final String PREFIX_URL = "/uploads/images/";
	
	
	public SiteVo get(){
		
		
		return SiteDao.get();
	}
	
	
	public boolean update(SiteVo vo) {
		
		return SiteDao.update(vo);
	}
	
	
	public String restore(MultipartFile multipartFile) {
	
		
		String url="";
		
		
		try{
		
	
		if(multipartFile.isEmpty()==true){
			
			return url;
			
		}
		
		String originalFileName = multipartFile.getOriginalFilename();
		
		String extName = originalFileName.substring(originalFileName.lastIndexOf('.'), originalFileName.length());
		Long fileSize = multipartFile.getSize();
		String saveFileName = gensaveFileName(extName);
	
		//System.out.println("#########"+originalFileName);
		//System.out.println("#########"+extName);
		//System.out.println("#########"+fileSize);
		//System.out.println("#########"+saveFileName);
		
		writeFile(multipartFile, saveFileName);
		
		url=PREFIX_URL+saveFileName;
		
		} catch( IOException e){
			
			throw new RuntimeException();
			
		}
		return url;
	}
	
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException{
		
		byte[] fileData = multipartFile.getBytes();
		
		FileOutputStream fos = new FileOutputStream( SAVE_PATH+ "/"+saveFileName);
		
		fos.write(fileData);
		
		fos.close();
		
	}
	
	
	private String gensaveFileName(String extName){
		
		String fileName= "";
		
		Calendar calendar =Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
		
		
	}

}
