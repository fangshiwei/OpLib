package com.oprisklib.service;

import org.json.JSONObject;

import com.oprisklib.common.model.WXRequestModel;
import com.qq.weixin.mp.aes.AesException;

public interface IWXMsgService {
	
	String verifyUrl(String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce, 
			String sVerifyEchoStr) throws AesException;
	
	String verifyUrl(WXRequestModel wxReq) throws AesException;
	public String decryptWXMsg(WXRequestModel wxReq) throws AesException;
	
	public JSONObject sendMsgByToken(String msg, String groupName) throws Exception;

}
