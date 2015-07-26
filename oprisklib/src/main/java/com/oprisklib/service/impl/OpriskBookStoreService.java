package com.oprisklib.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskBookStoreDTO;
import com.oprisklib.service.IOpriskBookStoreService;

@Service(value="opriskBookStoreService")
public class OpriskBookStoreService implements IOpriskBookStoreService {
	
	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;

	@Override
	public List<OpriskBookStoreDTO> findByISBN(String isbnNumber) {
		return this.opriskRepositoryPoint.getOpriskBookStoreRep().findByISBN(isbnNumber);
	}

	@Override
	@Transactional
	public void save(WXReceiveXmlModel wxXML) {
		OpriskBookStoreDTO bookStore = new OpriskBookStoreDTO();
		
		bookStore.setIsbnNumber(wxXML.getScanResult());
		bookStore.setIsActive("Y");
		bookStore.setCreatedBy(wxXML.getFromUserName());
		bookStore.setIsInLibrary("Y");
		bookStore.setBookOwner("oprisk");
		this.opriskRepositoryPoint.getOpriskBookStoreRep().save(bookStore);
		
	}
	
	
	

	

}
