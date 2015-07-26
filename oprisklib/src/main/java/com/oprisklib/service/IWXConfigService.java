package com.oprisklib.service;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public interface IWXConfigService {
	public WXBizMsgCrypt getWXBizMsgCrypt() throws AesException;
}
