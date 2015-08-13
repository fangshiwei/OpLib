package com.oprisklib.service;

import java.util.List;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.jpa.model.OpriskBookStoreDTO;

public interface IOpriskBookService {
	List<OpriskBookStoreDTO> findByISBN(String isbnNumber); 
	String save(WXReceiveXmlModel wxXML) throws Exception;
	String borrowBookByISBN(String isbnNumber, String borrowBy);
	String returnBookByISBN(String isbnNumber, String borrowBy);
	String fetchAllBookList();
}
