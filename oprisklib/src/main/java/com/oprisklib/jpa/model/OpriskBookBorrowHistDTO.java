package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OPRISK_BOOK_BORROW_HIST", catalog ="OPRISKLIB")
public class OpriskBookBorrowHistDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPRISK_BOOK_BORROW_HIST_ID")
	private Integer opriskBookBorrowHistId;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPRISK_USER_INFO_ID"))
	private OprisklibUserInfoDTO userInfo;*/
	
	@ManyToOne(cascade = { CascadeType.ALL }, targetEntity=OpriskBookStoreDTO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPRISK_BOOK_STORE_ID", updatable=true)
	private OpriskBookStoreDTO bookStore;
	
	@Column(name="WX_USER_ID")
	private String wxUserId;
	
	@Column(name="BORROW_DATE")
	private Date borrowDate;

	@Column(name="RETURN_DATE")
	private Date returnDate;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public Integer getOpriskBookBorrowHistId() {
		return opriskBookBorrowHistId;
	}

	public void setOpriskBookBorrowHistId(Integer opriskBookBorrowHistId) {
		this.opriskBookBorrowHistId = opriskBookBorrowHistId;
	}

	/*public OprisklibUserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(OprisklibUserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}*/

	public OpriskBookStoreDTO getBookStore() {
		return bookStore;
	}

	public void setBookStore(OpriskBookStoreDTO bookStore) {
		this.bookStore = bookStore;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(String wxUserId) {
		this.wxUserId = wxUserId;
	}
	
	
}
