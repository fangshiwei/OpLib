package com.oprisklib.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskBookStoreDTO;
import com.oprisklib.service.IDoubanService;
import com.oprisklib.service.IOpriskBookStoreService;

@Service(value="opriskBookStoreService")
public class OpriskBookStoreServiceImpl implements IOpriskBookStoreService {
	
	Logger log = Logger.getLogger(OpriskBookStoreServiceImpl.class);
	
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
	public String save(WXReceiveXmlModel wxXML) throws Exception {
		OpriskBookStoreDTO bookStore = null;
		String isbn = wxXML.getScanResult().substring(wxXML.getScanResult().indexOf(","));
		log.info("isbn:" + isbn);
		JSONObject json = doubanService.getBookInfoByIsbn(isbn);
		bookStore = parseJsonToBookStore(json, "oprisk");
		this.opriskRepositoryPoint.getOpriskBookStoreRep().save(bookStore);
		
		return bookStore.getTitle();
	}
	
	
	private OpriskBookStoreDTO parseJsonToBookStore(JSONObject json, String bookOwner){
		
		OpriskBookStoreDTO bookStore = new OpriskBookStoreDTO();
		bookStore.setTitle(json.getString("title"));
		bookStore.setAuthor(json.getString("author"));
		bookStore.setPublishDate(json.getString("pubdate"));
		bookStore.setOriginTitle(json.getString("origin_title"));
		bookStore.setImage(json.getString("image"));
		bookStore.setTranslator(json.getString("translator"));
		bookStore.setPages(Integer.valueOf(json.getString("translator")));
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
