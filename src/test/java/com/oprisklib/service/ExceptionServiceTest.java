package com.oprisklib.service;

import javax.annotation.Resource;

import org.junit.Test;

public class ExceptionServiceTest extends BaseServiceTest {

	
	@Resource(name="exceptionService")
	private IExceptionService exceptionService;
	
	@Test
	public void exceptionHandler(){
		try {
			exceptionService.exceptionHandler(1l);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
