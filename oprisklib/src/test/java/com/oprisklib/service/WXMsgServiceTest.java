package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

import com.oprisklib.jpa.model.OpriskWXMessageDTO;

public class WXMsgServiceTest extends BaseServiceTest {

	@Resource(name="wxService")
	IWXMsgService wxService;
	
	@Test
	public void testSendMsgByToken() throws Exception{
		String groupName = "oprisk";
		String msg = "from accessToken。。。。。。。。。。";
		
		JSONObject json = wxService.sendMsgByToken(msg, groupName);
		System.out.print(json.toString());
		
		Assert.assertEquals(String.valueOf(json.get("errcode")), "0");
		
		
	}
	
	@Test
	public void testAsyncResponse() throws Exception{
		OpriskWXMessageDTO wxXML = new OpriskWXMessageDTO();
		wxXML.setAgentID(6);
		wxXML.setFromUserName("corpId");
		wxXML.setToUserName("fsw");
		wxXML.setMsgType("text");
		wxXML.setContent("111111111");
		String str = wxService.asyncResponse(wxXML);
		System.out.println(str);
		
		Assert.assertEquals("ok", str);;
		
	}
	
	
}
