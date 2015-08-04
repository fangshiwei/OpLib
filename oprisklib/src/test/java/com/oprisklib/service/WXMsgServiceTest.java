package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

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
}
