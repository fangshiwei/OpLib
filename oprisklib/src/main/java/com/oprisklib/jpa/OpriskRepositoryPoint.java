package com.oprisklib.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oprisklib.jpa.repository.OpriskBookBorrowHistRepository;
import com.oprisklib.jpa.repository.OpriskBookOwnerMapRepository;
import com.oprisklib.jpa.repository.OpriskBookStoreRepository;
import com.oprisklib.jpa.repository.OpriskUserInfoRepository;
import com.oprisklib.jpa.repository.OpriskWXConfigRepository;
import com.oprisklib.jpa.repository.OpriskWXGroupSecretRepository;

@Component(value="opriskRepositoryPoint")
public class OpriskRepositoryPoint {
	@Autowired
	private OpriskBookBorrowHistRepository opriskBookBorrowHistRep;
	
	@Autowired
	private OpriskBookOwnerMapRepository opriskBookOwnerMapRep;
	
	@Autowired
	private OpriskBookStoreRepository opriskBookStoreRep;
	
	@Autowired
	private OpriskWXConfigRepository opriskWXConfigRep;
	
	@Autowired
	private OpriskWXGroupSecretRepository opriskWXGroupSecretRep;
	
	@Autowired
	private OpriskUserInfoRepository opriskUserInfoRep;

	
	
	public OpriskBookBorrowHistRepository getOpriskBookBorrowHistRep() {
		return opriskBookBorrowHistRep;
	}

	public void setOpriskBookBorrowHistRep(
			OpriskBookBorrowHistRepository opriskBookBorrowHistRep) {
		this.opriskBookBorrowHistRep = opriskBookBorrowHistRep;
	}

	public OpriskBookOwnerMapRepository getOpriskBookOwnerMapRep() {
		return opriskBookOwnerMapRep;
	}

	public void setOpriskBookOwnerMapRep(
			OpriskBookOwnerMapRepository opriskBookOwnerMapRep) {
		this.opriskBookOwnerMapRep = opriskBookOwnerMapRep;
	}

	public OpriskBookStoreRepository getOpriskBookStoreRep() {
		return opriskBookStoreRep;
	}

	public void setOpriskBookStoreRep(OpriskBookStoreRepository opriskBookStoreRep) {
		this.opriskBookStoreRep = opriskBookStoreRep;
	}

	public OpriskWXConfigRepository getOpriskWXConfigRep() {
		return opriskWXConfigRep;
	}

	public void setOpriskWXConfigRep(OpriskWXConfigRepository opriskWXConfigRep) {
		this.opriskWXConfigRep = opriskWXConfigRep;
	}

	public OpriskWXGroupSecretRepository getOpriskWXGroupSecretRep() {
		return opriskWXGroupSecretRep;
	}

	public void setOpriskWXGroupSecretRep(
			OpriskWXGroupSecretRepository opriskWXGroupSecretRep) {
		this.opriskWXGroupSecretRep = opriskWXGroupSecretRep;
	}

	public OpriskUserInfoRepository getOpriskUserInfoRep() {
		return opriskUserInfoRep;
	}

	public void setOpriskUserInfoRep(OpriskUserInfoRepository opriskUserInfoRep) {
		this.opriskUserInfoRep = opriskUserInfoRep;
	}
	
}
