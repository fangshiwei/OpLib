package com.oprisklib.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oprisklib.jpa.model.OpriskBookBorrowHistDTO;

public interface OpriskBookBorrowHistRepository extends JpaRepository<OpriskBookBorrowHistDTO, Integer> {
	
	@Query("select u from OpriskBookBorrowHistDTO u inner join u.bookStore b"
			+ " where (b.isbn13 = :isbn or b.isbn10 = :isbn) "
			+ " and u.createdBy = :createdBy "
			+ " and u.returnDate is null") 
	public OpriskBookBorrowHistDTO findBorrowBookByISBNAndBorrowBy(@Param("isbn") String isbn,
			@Param("createdBy") String createdBy);
}
