package com.oprisklib.service;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.oprisklib.common.model.WXAccessToken;

public class WXConfigServiceTest extends BaseServiceTest {

	@Resource(name="wxConfigService")
	IWXConfigService wxConfigService;
	
	@Test
	public void testGetAccessTokenByGroup() throws Exception{
		
		String groupName = "oprisk";
		WXAccessToken accessToken = wxConfigService.getAccessTokenByGroup(groupName);
		System.out.println(accessToken.toString());
		Assert.assertNotNull(accessToken.getAccessToken());
	}
}
