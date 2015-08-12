package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.oprisklib.service.impl.OpriskBookServiceImpl;

public class BookServiceTest extends BaseServiceTest {

	@Resource(name="opriskBookService")
	private OpriskBookServiceImpl opriskBookService;
	
	@Test
	public void testBorrowBookReturnNoBook(){
		String borrowBy = "fsw";
		String isbnNumber ="111";
		String msg = opriskBookService.borrowBookByISBN(isbnNumber, borrowBy);
		Assert.assertEquals("there is no book in library!", msg);
	}
	
	
	@Test
	public void testBorrowBookReturnBook(){
		String borrowBy = "fsw";
		String isbnNumber ="9787111423140";
		String msg = opriskBookService.borrowBookByISBN(isbnNumber, borrowBy);
		System.out.println(msg.substring(msg.lastIndexOf(" "), msg.length()-1));
		Assert.assertEquals("success", msg.substring(msg.lastIndexOf(" "), msg.length()-1));
	}
	
}
