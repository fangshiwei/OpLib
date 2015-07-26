package com.oprisklib.common.model;

public class WXResponseModel {
	
    private String msgSignature;
	
    private String timestamp;
	
    private String nonce;
    
	private String echostr;
	
	private String sRespData;

	public String getMsgSignature() {
		return msgSignature;
	}

	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getsRespData() {
		return sRespData;
	}

	public void setsRespData(String sRespData) {
		this.sRespData = sRespData;
	}
	
}
