package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class DoubanServiceTest extends BaseServiceTest{

	@Resource(name="doubanService")
	IDoubanService doubanService;
	
	@Mock
	private IOpriskMessageService messageService;
	
	@Before
	public void setUp(){
		messageService = Mockito.mock(IOpriskMessageService.class);
	}
	
	
	
	@Test
	public void testGetBookInfoByIsbn(){
		String isbn = "9787111423140";
		try {
			JSONObject jsonObject = doubanService.getBookInfoByIsbn(isbn);
			Assert.assertEquals(isbn, jsonObject.getString("isbn13"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBookInfoByIsbnNotExits(){
		String isbn = "97840";
		try {
			JSONObject jsonObject = doubanService.getBookInfoByIsbn(isbn);
			Assert.assertEquals("book_not_found", jsonObject.get("msg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
