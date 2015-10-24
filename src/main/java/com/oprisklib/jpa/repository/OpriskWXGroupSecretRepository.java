package com.oprisklib.jpa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oprisklib.jpa.model.OpriskWXGroupSecretDTO;

public interface OpriskWXGroupSecretRepository extends
		JpaRepository<OpriskWXGroupSecretDTO, Integer> {
	
	@Query("select u from OpriskWXGroupSecretDTO u where u.isActive = 'Y'") 
	List<OpriskWXGroupSecretDTO> findActiveList(); 
	
	@Query("select u from OpriskWXGroupSecretDTO u where u.wxGroup = :wxGroup ") 
	OpriskWXGroupSecretDTO findByWxGroup(@Param("wxGroup") String  wxGroup); 

}
