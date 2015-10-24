package com.oprisklib.service.impl;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oprisklib.service.IDoubanService;
import com.oprisklib.service.IOpriskMessageService;
import com.oprisklib.util.HttpsUtils;

@Service(value="doubanService")
public class DoubanServiceImpl implements IDoubanService {

	@Resource(name="opriskMessageService")
	private IOpriskMessageService messageService;
	
	@Override
	public JSONObject getBookInfoByIsbn(String isbn) throws Exception{
		
		String doubanUrl = "https://api.douban.com/v2/book/isbn/" + isbn;
		
		JSONObject json = HttpsUtils.get(doubanUrl);

		return json;
	}
	
	@Override
	public String searchBookInfoFromDouban(String bookName){
		
		String notFoundMsg = messageService.getMessage("lib.book.not.found.in.douban", new Object[]{bookName});
		
		return notFoundMsg;
	}

}
