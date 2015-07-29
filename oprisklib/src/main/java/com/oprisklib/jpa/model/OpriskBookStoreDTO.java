package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="OPRISK_BOOK_STORE", catalog ="OPRISKLIB")
public class OpriskBookStoreDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPRISK_BOOK_STORE_ID")
	private Integer opriskBookStoreId;
	
	@Column(name="CN_NAME")
	private String cnName;
	
	@Column(name="EN_NAME")
	private String enName;
	
	@Column(name="CN_AUTHOR")
	private String cnAuthor;
	
	@Column(name="EN_AUTHOR")
	private String enAuthor;
	
	@Column(name="PUBLISH_FIRM")
	private String publishPlace;
	
	@Column(name="PUBLISH_VERSION")
	private String publishVersion;
	
	@Column(name="PRICE")
	private Float price;
	
	@Column(name="ISBN_NUMBER")
	private String isbnNumber;
	
	@Column(name="BOOK_OWNER")
	private String bookOwner;
	
	@Column(name="IS_IN_LIBRARY")
	private String isInLibrary;
	
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public Integer getOpriskBookStoreId() {
		return opriskBookStoreId;
	}

	public void setOpriskBookStoreId(Integer opriskBookStoreId) {
		this.opriskBookStoreId = opriskBookStoreId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnAuthor() {
		return cnAuthor;
	}

	public void setCnAuthor(String cnAuthor) {
		this.cnAuthor = cnAuthor;
	}

	public String getEnAuthor() {
		return enAuthor;
	}

	public void setEnAuthor(String enAuthor) {
		this.enAuthor = enAuthor;
	}

	public String getPublishPlace() {
		return publishPlace;
	}

	public void setPublishPlace(String publishPlace) {
		this.publishPlace = publishPlace;
	}

	public String getPublishVersion() {
		return publishVersion;
	}

	public void setPublishVersion(String publishVersion) {
		this.publishVersion = publishVersion;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getBookOwner() {
		return bookOwner;
	}

	public void setBookOwner(String bookOwner) {
		this.bookOwner = bookOwner;
	}

	public String getIsInLibrary() {
		return isInLibrary;
	}

	public void setIsInLibrary(String isInLibrary) {
		this.isInLibrary = isInLibrary;
	}
	
	

}
