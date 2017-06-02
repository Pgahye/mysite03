package com.jx372.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE }) //타겟지정
@Retention(RetentionPolicy.RUNTIME) //지속기간을 지정 
public @interface Auth {
	
	
	//String role() default "USER"; // value라고 하면 이름을 지정해주지 않아도 들어감  String[] value() default "USER"; @Auth({"ADMIN", "USER"}) 여러개 넣을수도 있음 
	Role role() default Role.USER;
	//Role[] value() default Role.USER;
	public enum Role {ADMIN, USER }
	
}
