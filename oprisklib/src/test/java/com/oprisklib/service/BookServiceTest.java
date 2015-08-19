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
		System.out.println(msg.substring(msg.lastIndexOf(" ")+1, msg.length()-1));
		Assert.assertEquals("success", msg.substring(msg.lastIndexOf(" ")+1, msg.length()-1));
	}
	
	@Test
	public void testFetchAllBookList(){
		String booklist = this.opriskBookService.fetchAllBookList();
		System.out.println(booklist);
		Assert.assertTrue(!"".equals(booklist));
	}
	
	@Test
	public void testSaveBookSuccess() throws Exception{
		String scanResult = "ISBN,9787111423140";
		
		String msg =  this.opriskBookService.saveBook(scanResult);
		
		Assert.assertEquals("In", msg.substring(0, 2));
		
	}
	
	@Test
	public void testSaveBookNotFound() throws Exception{
		String scanResult = "ISBN,1111";
		
		String msg =  this.opriskBookService.saveBook(scanResult);
		
		Assert.assertEquals("No", msg.substring(0, 2));
		
	}
	
	
}
