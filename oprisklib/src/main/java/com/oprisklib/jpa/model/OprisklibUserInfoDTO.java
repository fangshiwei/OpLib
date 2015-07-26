package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPRISK_USER_INFO", catalog ="OPRISKLIB")
public class OprisklibUserInfoDTO {
	@Id
	@Column(name="OPRISK_USER_INFO_ID")
	private Integer opriskUserInfoId;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="SOEID")
	private String soeId;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public Integer getOpriskUserInfoId() {
		return opriskUserInfoId;
	}

	public void setOpriskUserInfoId(Integer opriskUserInfoId) {
		this.opriskUserInfoId = opriskUserInfoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSoeId() {
		return soeId;
	}

	public void setSoeId(String soeId) {
		this.soeId = soeId;
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
