package com.oprisklib.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oprisklib.jpa.model.OpriskWXConfigDTO;

public interface OpriskWXConfigRepository extends
		JpaRepository<OpriskWXConfigDTO, Integer> {
	
	 @Query("select u from OpriskWXConfigDTO u where u.isActive = 'Y'") 
	 OpriskWXConfigDTO findActiveOne(); 

}
