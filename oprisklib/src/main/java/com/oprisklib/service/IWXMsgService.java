package com.oprisklib.service;

import org.json.JSONObject;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.common.model.WXRequestModel;
import com.qq.weixin.mp.aes.AesException;

public interface IWXMsgService {
	
	String verifyUrl(String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce, String sVerifyEchoStr) throws AesException;
	
	String verifyUrl(WXRequestModel wxReq) throws AesException;
	
	String decryptWXMsg(WXRequestModel wxReq) throws AesException;
	
	WXReceiveXmlModel decodeMessage(WXRequestModel wxReq) throws Exception;
	
	JSONObject sendMsgByToken(String msg, String groupName) throws Exception;
	
	String generateReplyEncryMsg(String to, String from, String content) throws Exception;
	
	String asyncResponse(WXReceiveXmlModel wxXML) throws Exception;

}
