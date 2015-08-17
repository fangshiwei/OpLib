package com.oprisklib.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oprisklib.common.model.WXAccessToken;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskWXConfigDTO;
import com.oprisklib.jpa.model.OpriskWXGroupSecretDTO;
import com.oprisklib.service.IWXConfigService;
import com.oprisklib.util.HttpsUtils;
import com.oprisklib.util.cache.Cache;
import com.oprisklib.util.cache.CacheManager;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Service("wxConfigService")
public class WXConfigServiceImpl implements IWXConfigService{
	
	Logger log = Logger.getLogger(WXConfigServiceImpl.class);
	
	private static final String ACCESS_TOKEN = "access_token";

	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;
	
	static WXBizMsgCrypt wxcpt = null;
	
	public WXBizMsgCrypt getWXBizMsgCrypt() throws AesException{
		
		if(null == wxcpt){
			OpriskWXConfigDTO wxConfig = this.opriskRepositoryPoint.getOpriskWXConfigRep().findActiveOne();	
			wxcpt = new WXBizMsgCrypt(wxConfig.getToken(), wxConfig.getEncodingAesKey(), wxConfig.getCorpId());
		}
		
		return wxcpt;
		
	}
	

	public WXAccessToken getAccessTokenByGroup(String groupName) throws Exception{
		Cache cache = CacheManager.getCacheInfo(groupName);
		if(cacheAvalible(cache)){
			log.info("cache access token:" + ((WXAccessToken)cache.getValue()).getAccessToken());
			return (WXAccessToken) cache.getValue();
		}
		
		cache = generateAccesToken(groupName);
		return (WXAccessToken) cache.getValue();
	}
	
	private boolean cacheAvalible(Cache cache){
		if(cache == null)
			return false;
		if(cache.isExpired())
			return false;
		return true;
	}
	
	private Cache generateAccesToken(String groupName) throws Exception{
		OpriskWXGroupSecretDTO wxsecret = this.opriskRepositoryPoint.getOpriskWXGroupSecretRep().findByWxGroup(groupName);
		String url = wxsecret.getAccessTokenUrl();
		url = url.replace(":corpid", wxsecret.getCorpId());
		url = url.replace(":corpsecret", wxsecret.getCorpSecret());
		
		JSONObject json = HttpsUtils.get(url);
		
		WXAccessToken accessToken = contructWXAccessToken(wxsecret.getWxGroup(), json);
		
		Cache cache = new Cache();
		cache.setKey(wxsecret.getWxGroup());
		cache.setValue(accessToken);
		long expiresIn = accessToken.getExpiresIn();
		long timeOut = System.currentTimeMillis() + expiresIn * 1000;
		cache.setTimeOut(timeOut);
		cache.setExpired(false);
		CacheManager.putCache(wxsecret.getWxGroup(), cache);
		
		return cache;
	}


	/**
	 * @param wxsecret
	 * @param json
	 * @throws Exception 
	 */
	private WXAccessToken contructWXAccessToken(String groupName, JSONObject json) throws Exception {
		WXAccessToken accessToken = new WXAccessToken();
		accessToken.setWxGroup(groupName);
		if(json.has(ACCESS_TOKEN)){
			accessToken.setAccessToken(json.getString(ACCESS_TOKEN));
			accessToken.setCreatedTime(System.currentTimeMillis());
			accessToken.setExpiresIn(json.getLong("expires_in"));
		}else{
			throw new Exception(String.valueOf(json.get("errcode"))+":"+json.getString("errmsg"));
		};
		
		return accessToken;
	}
}
