package com.oprisklib.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oprisklib.jpa.model.OpriskWXMessageDTO;

public interface OpriskWXMessageRepository extends JpaRepository<OpriskWXMessageDTO, Integer> {
	
}
