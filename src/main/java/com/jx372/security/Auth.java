package com.jx372.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //타겟지정
@Retention(RetentionPolicy.RUNTIME) //지속기간을 지정 
public @interface Auth {
	
	
	

}
