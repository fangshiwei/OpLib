package com.oprisklib.service;

import com.oprisklib.jpa.model.OprisklibUserInfoDTO;

public interface IOpriskUserInfoService {
	OprisklibUserInfoDTO findBySoeId(String soeId); 
}
