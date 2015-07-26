package com.oprisklib.service;

import org.springframework.data.repository.query.Param;

import com.oprisklib.jpa.model.OprisklibUserInfoDTO;

public interface IOpriskUserInfoService {
	OprisklibUserInfoDTO findBySoeId(@Param("soeId") String soeId); 
}
