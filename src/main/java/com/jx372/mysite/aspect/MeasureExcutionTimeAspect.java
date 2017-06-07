package com.jx372.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExcutionTimeAspect {
	
	@Around("execution(* *..repository.*.*(..) ) || execution(* *..service.*.*(..) )")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		//before advice
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		// 메소드 실행
		Object result = pjp.proceed();
		
		//afteradvice
		stopwatch.stop();
		Long totaltime = stopwatch.getLastTaskTimeMillis();
		
		String className= pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "."+ methodName;
		
		//System.out.println("[executiontime]"+taskName+" "+totaltime);
		
		
		return result;
		
	}

}
