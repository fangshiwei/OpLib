package com.oprisklib.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskBookBorrowHistDTO;
import com.oprisklib.jpa.model.OpriskBookStoreDTO;
import com.oprisklib.service.IDoubanService;
import com.oprisklib.service.IOpriskBookService;

@Service(value="opriskBookService")
public class OpriskBookServiceImpl implements IOpriskBookService {
	
	Logger log = Logger.getLogger(OpriskBookServiceImpl.class);
	
	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;

	@Resource(name="doubanService")
	private IDoubanService doubanService;
	
	@Override
	public List<OpriskBookStoreDTO> findByISBN(String isbnNumber) {
		return this.opriskRepositoryPoint.getOpriskBookStoreRep().findByISBN(isbnNumber);
	}
	
	@Override
	@Transactional
	public String borrowBookByISBN(String isbnNumber, String borrowBy){
		String isInLibrary = "Y";
		List<OpriskBookStoreDTO> bookList = this.opriskRepositoryPoint.getOpriskBookStoreRep().findByISBNAndLibraryFlag(isbnNumber, isInLibrary);
		if(null != bookList && bookList.size() > 0){
			OpriskBookStoreDTO book = bookList.get(0);
			book.setIsInLibrary("N");
			saveBorrowHist(borrowBy, book);
			//this.opriskRepositoryPoint.getOpriskBookStoreRep().save(book);
			
			return "Book:" + book.getTitle() +" borrow success!";
		}else{
			return "there is no book in library!";
		}
	}
	
	@Override
	@Transactional
	public String returnBookByISBN(String isbnNumber, String borrowBy){
		OpriskBookBorrowHistDTO borrowHistDTO = this.opriskRepositoryPoint.getOpriskBookBorrowHistRep().findBorrowBookByISBNAndBorrowBy(isbnNumber, borrowBy);
		if(null != borrowHistDTO){
			borrowHistDTO.getBookStore().setIsInLibrary("Y");
			borrowHistDTO.setReturnDate(new Date(System.currentTimeMillis()));
			this.opriskRepositoryPoint.getOpriskBookBorrowHistRep().save(borrowHistDTO);
			return "Book:"+borrowHistDTO.getBookStore().getTitle()+"return success";
		}else{
			return "No record found";
		}
				
	}
	
	@Override
	public String fetchAllBookList(){
		StringBuffer sb = new StringBuffer();
		
		List<OpriskBookStoreDTO> bookList = this.opriskRepositoryPoint.getOpriskBookStoreRep().findAll();
		int i = 1;
		for(OpriskBookStoreDTO book : bookList){
			sb.append(i+". :"+book.getTitle()+"\n");
			i++;
		}
				
		return sb.toString();
	}

	/**
	 * @param borrowBy
	 * @param book
	 */
	private void saveBorrowHist(String borrowBy, OpriskBookStoreDTO book) {
		OpriskBookBorrowHistDTO borrowHistDTO = new OpriskBookBorrowHistDTO();
		
		borrowHistDTO.setBookStore(book);
		borrowHistDTO.setWxUserId(borrowBy);
		borrowHistDTO.setBorrowDate(new Date(System.currentTimeMillis()));
		borrowHistDTO.setCreatedBy(borrowBy);
		borrowHistDTO.setCreatedDate(new Date(System.currentTimeMillis()));
		this.opriskRepositoryPoint.getOpriskBookBorrowHistRep().save(borrowHistDTO);
	}
	
	
	
	
	public OpriskBookStoreDTO save(OpriskBookStoreDTO book){
		book.setIsInLibrary("N");
		return this.opriskRepositoryPoint.getOpriskBookStoreRep().save(book);
	}

	@Override
	@Transactional
	public String save(WXReceiveXmlModel wxXML) throws Exception {
		OpriskBookStoreDTO bookStore = null;
		String isbn = wxXML.getScanResult().substring(wxXML.getScanResult().indexOf(",")+1);
		log.info("isbn:" + isbn);
		JSONObject json = doubanService.getBookInfoByIsbn(isbn);
		bookStore = parseJsonToBookStore(json, "oprisk");
		this.opriskRepositoryPoint.getOpriskBookStoreRep().save(bookStore);
		
		return bookStore.getTitle();
	}
	
	
	private OpriskBookStoreDTO parseJsonToBookStore(JSONObject json, String bookOwner){
		
		OpriskBookStoreDTO bookStore = new OpriskBookStoreDTO();
		bookStore.setTitle(json.getString("title"));
		bookStore.setAuthor(json.get("author").toString());
		bookStore.setPublishDate(json.getString("pubdate"));
		bookStore.setOriginTitle(json.getString("origin_title"));
		bookStore.setImage(json.getString("image"));
		bookStore.setTranslator(json.get("translator").toString());
		bookStore.setPages(Integer.valueOf(json.getString("pages")));
		bookStore.setPublisher(json.getString("publisher"));
		bookStore.setIsbn10(json.getString("isbn10"));
		bookStore.setIsbn13(json.getString("isbn13"));
		bookStore.setAuthorIntro(json.getString("author_intro"));
		bookStore.setSummary(json.getString("summary"));
		bookStore.setPrice(json.getString("price"));
		bookStore.setBookOwner(bookOwner);
		bookStore.setIsInLibrary("Y");
		bookStore.setCreatedBy("admin");
		
		return bookStore;
		
	}

	

	

}
