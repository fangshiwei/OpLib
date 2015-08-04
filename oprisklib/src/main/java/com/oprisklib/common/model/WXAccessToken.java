package com.oprisklib.common.model;


public class WXAccessToken {
	private String accessToken;
	private String wxGroup;
	private Long expiresIn;
	private Long createdTime;
	
	
	public WXAccessToken() {}

	public WXAccessToken(String accessToken, String wxGroup, Long expiresIn,
			Long createdTime) {
		super();
		this.accessToken = accessToken;
		this.wxGroup = wxGroup;
		this.expiresIn = expiresIn;
		this.createdTime = createdTime;
	}



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
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	
	@Override
	public String toString() {
		String wxAccessToken = "[wxGroup:"+wxGroup+", "
				+ "accessToken:"+accessToken+", "
				+ "expiresIn:" +expiresIn+", "
				+ "createdTime:" +createdTime+"]";

		return wxAccessToken;
	}
	
	
}
