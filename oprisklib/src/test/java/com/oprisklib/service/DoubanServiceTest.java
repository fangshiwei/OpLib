package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

public class DoubanServiceTest extends BaseServiceTest{

	@Resource(name="doubanService")
	IDoubanService doubanService;
	
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
}
