package com.oprisklib.service;

import org.json.JSONObject;

public interface IDoubanService {

	public JSONObject getBookInfoByIsbn(String isbn) throws Exception;

	String searchBookInfoFromDouban(String bookName);
}
