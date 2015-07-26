package com.oprisklib.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oprisklib.jpa.model.OprisklibUserInfoDTO;

public interface OpriskUserInfoRepository extends JpaRepository<OprisklibUserInfoDTO, Integer> {
	
	 @Query("select u from OprisklibUserInfoDTO u where u.soeId = :soeId") 
	 OprisklibUserInfoDTO findBySoeId(@Param("soeId") String soeId); 

}
