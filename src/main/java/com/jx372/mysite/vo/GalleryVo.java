package com.jx372.mysite.vo;

public class GalleryVo {
	
	
	private Long no;
	private String context;
	private String url;
	
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", context=" + context + ", url=" + url + "]";
	}
	
	
	

}
