package com.oprisklib.service;

import com.oprisklib.common.model.WXAccessToken;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public interface IWXConfigService {
	public WXBizMsgCrypt getWXBizMsgCrypt() throws AesException;
	
	public WXAccessToken getAccessTokenByGroup(String groupName) throws Exception;
}
