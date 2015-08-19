package com.oprisklib.web.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.core.task.TaskExecutor;

import com.oprisklib.service.impl.WXMsgServiceImpl;

public class WCXControllerTest extends BaseControllerTest{

	@Mock
	private WXMsgServiceImpl wxService;
	
	@Mock
    private TaskExecutor taskExecutor; 
	
	@Mock
	private HttpServletRequest request;
	
	//@Mock
	//private HttpServletResponse response;
	
	@Before
	public void setUp() throws IOException{
		request = initRequest();
		//response = initResponse();
		
	}
	
	
	@Test
	public void testGetWXMenu() throws IOException{
		HttpServletResponse response = mock(HttpServletResponse.class);
		StubServletOutputStream servletOutputStream = new StubServletOutputStream();
		
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		StringWriter writer = new StringWriter();
				
		when(response.getWriter()).thenReturn(new PrintWriter(writer));
		
		WXController wxController = (WXController) this.applicationContext.getBean("WXController");
		wxController.getWXMenu(request, response);
		System.out.println(writer.toString());
		
		
		Assert.assertEquals("fsw", writer.toString());
		
	}
	
	private HttpServletRequest initRequest(){
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		when(request.getPathInfo()).thenReturn("/oprisklib/");
		when(request.getRequestURI()).thenReturn("/oprisklib");
		when(request.getContextPath()).thenReturn("/oprisklib");
		//when(request.getSession()).thenReturn("/oprisklib");
		 
		when(request.getMethod()).thenReturn("GET");
		when(request.getParameter("msg_signature")).thenReturn("fsw");
		when(request.getParameter("timestamp")).thenReturn("123");
		when(request.getParameter("nonce")).thenReturn("abc");
		when(request.getParameter("echostr")).thenReturn("abcba");
		
		
		final Map<String, Object> hash = new HashMap<String, Object>();
		
		/*Answer<String> aswser = new Answer<String>() {
			public String answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				return hash.get(args[0].toString()).toString(); 
			}
		};*/
		
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				// Object mock = invocation.getMock();  
				System.out.println(args[1]);
				hash.put(args[0].toString(), args[1]);
				return "called with arguments: " + args;
			}
		}).when(request).setAttribute(anyString(), anyString());
		
		return request;
	}
	
	
	HttpServletResponse initResponse() throws IOException{
		// http://stackoverflow.com/questions/5434419/how-to-test-my-servlet-using-junit
		HttpServletResponse response = mock(HttpServletResponse.class);
		StubServletOutputStream servletOutputStream = new StubServletOutputStream();
		
		when(response.getOutputStream()).thenReturn(servletOutputStream);
		StringWriter writer = new StringWriter();
				
		when(response.getWriter()).thenReturn(new PrintWriter(writer));
		
		return response;
	}
	
	
	class StubServletOutputStream extends ServletOutputStream {  
	    public ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    public void write(int i) throws IOException {  
	        baos.write(i);  
	    }  
	    public String getContent() {  
	        return baos.toString();  
	    }  
	}
}
