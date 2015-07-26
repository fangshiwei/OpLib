package com.oprisklib.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OprisklibUserInfoDTO;
import com.oprisklib.service.IOpriskUserInfoService;

@Service(value="opriskUserInfoService")
public class OpriskUserInfoService implements IOpriskUserInfoService {
	
	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;

	@Transactional
	public OprisklibUserInfoDTO findBySoeId(String soeId) {
		return this.opriskRepositoryPoint.getOpriskUserInfoRep().findBySoeId(soeId);
	}

}
