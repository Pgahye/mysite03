package com.jx372.mysite.dto;

public class JSONResult {
	
	private String result; // "success", "fail"
	private String message; // result가 "fail" 일때 원인
	private Object data; //result가 "success" 일때 전달해야 할 데이터 
	
	private JSONResult(String result, String message, Object data){
		
		this.result=result;
		this.message=message;
		this.data= data;
		
	}
	
	public static JSONResult error(String message){
		
		
		return new JSONResult("fail", message, null);
	}
	

	public static JSONResult success(Object data){
		
		return new JSONResult("success", null, data);
		
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	

	
	
	

	
	
}
