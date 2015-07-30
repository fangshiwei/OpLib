package com.oprisklib.util;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONObject;
import org.junit.Test;

public class HtppsUtilsTest {

	@Test
	public void testGet(){
		String url ="https://api.douban.com/v2/book/isbn/7543639130";
		try {
			JSONObject json = HttpsUtils.get(url);
			
			assertNotNull(json);
			
		} catch (KeyManagementException | NoSuchAlgorithmException
				| IOException e) {
			e.printStackTrace();
		}
	}
}
