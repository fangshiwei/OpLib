package com.oprisklib.service;

import java.util.List;

import com.oprisklib.jpa.model.OpriskBookStoreDTO;

public interface IOpriskBookService {
	List<OpriskBookStoreDTO> findByISBN(String isbnNumber); 
	String saveBook(String scanResult) throws Exception;
	String borrowBookByISBN(String isbnNumber, String borrowBy);
	String returnBookByISBN(String isbnNumber, String borrowBy);
	String fetchAllBookList();
	String fetchAllAvalibleBookList();
	String fetchAllBookListByName(String bookName);
}
