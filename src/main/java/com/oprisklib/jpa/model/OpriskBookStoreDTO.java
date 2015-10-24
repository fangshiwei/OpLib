package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="OPRISK_BOOK_STORE", catalog ="OPRISKLIB")
public class OpriskBookStoreDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="OPRISK_BOOK_STORE_ID")
	private Integer opriskBookStoreId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="PUBLISH_DATE")
	private String publishDate;
	
	@Column(name="SUB_TITLE")
	private String subTitle;
	
	@Column(name="ORIGIN_TITLE")
	private String originTitle;
	
	@Column(name="IMAGE")
	private String image;
	
	@Column(name="TRANSLATOR")
	private String translator;
	
	@Column(name="PAGES")
	private Integer pages;
	
	@Column(name="PUBLISHER")
	private String publisher;
	
	@Column(name="ISBN_10")
	private String isbn10;
	
	@Column(name="ISBN_13")
	private String isbn13;
	
	@Column(name="AUTHOR_INTRO")
	private String authorIntro;
	
	@Column(name="SUMMARY")
	private String summary;
	
	@Column(name="PRICE")
	private String price;
	
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public Integer getOpriskBookStoreId() {
		return opriskBookStoreId;
	}

	public void setOpriskBookStoreId(Integer opriskBookStoreId) {
		this.opriskBookStoreId = opriskBookStoreId;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getOriginTitle() {
		return originTitle;
	}

	public void setOriginTitle(String originTitle) {
		this.originTitle = originTitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	
	public String getAuthorIntro() {
		return authorIntro;
	}

	public void setAuthorIntro(String authorIntro) {
		this.authorIntro = authorIntro;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
