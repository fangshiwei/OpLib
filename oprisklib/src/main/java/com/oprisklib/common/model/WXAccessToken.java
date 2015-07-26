package com.oprisklib.common.model;


public class WXAccessToken {
	private String accessToken;
	private String wxGroup;
	private Integer expiresIn;
	private Long createdTime;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getWxGroup() {
		return wxGroup;
	}
	public void setWxGroup(String wxGroup) {
		this.wxGroup = wxGroup;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	
}
