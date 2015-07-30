package com.oprisklib.service.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oprisklib.service.IDoubanService;
import com.oprisklib.util.HttpsUtils;

@Service(value="doubanService")
public class DoubanServiceImpl implements IDoubanService {

	public JSONObject getBookInfoByIsbn(String isbn) throws Exception{
		String doubanUrl = "https://api.douban.com/v2/book/isbn/" + isbn;
		JSONObject json = HttpsUtils.get(doubanUrl);

		
		return json;
	}

}
