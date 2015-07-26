package com.oprisklib.service;

import java.util.List;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.jpa.model.OpriskBookStoreDTO;

public interface IOpriskBookStoreService {
	List<OpriskBookStoreDTO> findByISBN(String isbnNumber); 
	void save(WXReceiveXmlModel wxXML);
}
