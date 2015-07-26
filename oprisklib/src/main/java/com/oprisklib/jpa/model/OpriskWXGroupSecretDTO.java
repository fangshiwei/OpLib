package com.oprisklib.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OPRISK_WX_GROUP_SECRET", catalog ="OPRISKLIB")
public class OpriskWXGroupSecretDTO {

	@Id
	@Column(name="OPRISK_WX_GROUP_SECRET_ID")
	private Integer opriskWxGroupSecretId;
	
	@Column(name="CORP_ID")
	private String corpId;
	
	@Column(name="CORP_SECRET")
	private String corpSecret;
	
	@Column(name="WX_GROUP")
	private String wxGroup;
	
	@Column(name="ACCESS_TOKEN_URL")
	private String accessTokenUrl;
	
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;

	public Integer getOpriskWxGroupSecretId() {
		return opriskWxGroupSecretId;
	}

	public void setOpriskWxGroupSecretId(Integer opriskWxGroupSecretId) {
		this.opriskWxGroupSecretId = opriskWxGroupSecretId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

	public String getWxGroup() {
		return wxGroup;
	}

	public void setWxGroup(String wxGroup) {
		this.wxGroup = wxGroup;
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

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	
	
}
