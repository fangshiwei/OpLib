package com.oprisklib.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLogHandler {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionLogHandler.class);
	
	@Pointcut("execution(@com.oprisklib.aspect.annotation.ExceptionLog * *(..))")
	public void pointcut(){}
	
	@AfterThrowing(pointcut="pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {  
		Signature signture = joinPoint.getSignature();
		Object object = joinPoint.getTarget();
		
    	logger.info("An exception occur: ------------------class:" + object.getClass().getSimpleName() );  
    	
    	logger.info("An exception occur:-------------------signture" + signture.getName() );
    	
    	logger.info("An exception occur:-------------------Args" + joinPoint.getArgs());
    }


}
