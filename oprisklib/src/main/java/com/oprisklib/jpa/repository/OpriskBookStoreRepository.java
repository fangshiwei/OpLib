package com.oprisklib.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oprisklib.jpa.model.OpriskBookStoreDTO;

public interface OpriskBookStoreRepository extends JpaRepository<OpriskBookStoreDTO, Integer> {
	
	@Query("select u from OpriskBookStoreDTO u where u.isbn13 = :isbn or u.isbn10 = :isbn") 
	List<OpriskBookStoreDTO> findByISBN(@Param("isbn") String isbn); 
	
	@Query("select u from OpriskBookStoreDTO u "
			+ " where (u.isbn13 = :isbn or u.isbn10 = :isbn)"
			+ " and u.isInLibrary = :isInLibrary " ) 
	List<OpriskBookStoreDTO> findByISBNAndLibraryFlag(@Param("isbn") String isbn, 
			@Param("isInLibrary") String isInLibrary); 
	
}
