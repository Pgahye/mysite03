package com.jx372.mysite.vo;

import org.springframework.web.multipart.MultipartFile;

public class SiteVo {
	
	
	private Long no;
	private String title;
	private String welcomeMessage;
	private String file;
	private String description;
	
	


	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcomeMessage=" + welcomeMessage + ", file=" + file
				+ ", description=" + description + "]";
	}

	
	
	
	
	

}
