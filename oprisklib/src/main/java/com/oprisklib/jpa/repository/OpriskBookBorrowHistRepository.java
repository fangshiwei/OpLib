package com.oprisklib.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oprisklib.jpa.model.OpriskBookBorrowHistDTO;

public interface OpriskBookBorrowHistRepository extends JpaRepository<OpriskBookBorrowHistDTO, Integer> {
	
	
}
