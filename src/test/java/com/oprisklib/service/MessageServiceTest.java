package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

public class MessageServiceTest extends BaseServiceTest {

	@Resource(name="opriskMessageService")
	private IOpriskMessageService messageService;
	
	@Test
	public void testGetMessage(){
		String msg = messageService.getMessage("lib.message.none.params.test");
		
		Assert.assertEquals("None", msg.substring(0, 4));
	}
	
	@Test
	public void testGetErrorMessage(){
		String msg = messageService.getMessage("lib.message.exception.none.params.test");
		
		Assert.assertEquals("Exception", msg.substring(0, 9));
	}
	
	
}
