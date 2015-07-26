package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OPRISK_BOOK_STORE", catalog ="OPRISKLIB")
public class OpriskBookOwnerMapDTO {
	
	@Id
	@Column(name="ORPISK_BOOK_OWNER_MAP_ID")
	private Integer opriskBookOwnerMapId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "OPRISK_USER_INFO_ID", 
	        joinColumns = @JoinColumn(name = "OPRISK_USER_INFO_ID"))
	private OprisklibUserInfoDTO userInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "OPRISK_BOOK_STORE_ID", 
	        joinColumns = @JoinColumn(name = "OPRISK_BOOK_STORE_ID"))
	private OpriskBookStoreDTO bookStore;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public Integer getOpriskBookOwnerMapId() {
		return opriskBookOwnerMapId;
	}

	public void setOpriskBookOwnerMapId(Integer opriskBookOwnerMapId) {
		this.opriskBookOwnerMapId = opriskBookOwnerMapId;
	}

	public OprisklibUserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(OprisklibUserInfoDTO userInfo) {
		this.userInfo = userInfo;
	}

	public OpriskBookStoreDTO getBookStore() {
		return bookStore;
	}

	public void setBookStore(OpriskBookStoreDTO bookStore) {
		this.bookStore = bookStore;
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
	
	
}
