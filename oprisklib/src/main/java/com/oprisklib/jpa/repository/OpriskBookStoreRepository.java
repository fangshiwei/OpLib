package com.oprisklib.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oprisklib.jpa.model.OpriskBookStoreDTO;

public interface OpriskBookStoreRepository extends JpaRepository<OpriskBookStoreDTO, Integer> {
	
	
}
