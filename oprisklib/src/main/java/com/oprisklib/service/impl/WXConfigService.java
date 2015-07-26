package com.oprisklib.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oprisklib.common.model.WXAccessToken;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskWXConfigDTO;
import com.oprisklib.jpa.model.OpriskWXGroupSecretDTO;
import com.oprisklib.service.IWXConfigService;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Service("wxConfigService")
public class WXConfigService implements IWXConfigService{
	
	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;
	
	static WXBizMsgCrypt wxcpt = null;
	static Map<String, WXAccessToken> accessTokenMap;
	
	public WXBizMsgCrypt getWXBizMsgCrypt() throws AesException{
		
		if(null == wxcpt){
			OpriskWXConfigDTO wxConfig = this.opriskRepositoryPoint.getOpriskWXConfigRep().findActiveOne();	
			wxcpt = new WXBizMsgCrypt(wxConfig.getToken(), wxConfig.getEncodingAesKey(), wxConfig.getCorpId());
		}
		
		return wxcpt;
		
	}
	
	public String getAccessToken(){
		if(null == accessTokenMap){
			accessTokenMap = new HashMap<String, WXAccessToken>();
		}
		
		OpriskWXGroupSecretDTO wxsecret = this.opriskRepositoryPoint.getOpriskWXGroupSecretRep()
				.findByWxGroup("oprisk");
		if(accessTokenMap.containsKey(wxsecret.getWxGroup())){
			WXAccessToken accessToken = accessTokenMap.get(wxsecret.getWxGroup());
			
			long epTime = System.currentTimeMillis() - accessToken.getCreatedTime();
			
			if((accessToken.getExpiresIn() - epTime)/1000 > 10){
				return accessToken.getAccessToken();
			}else{
				generateAccesToken(wxsecret);
			}
			
		}else{
			generateAccesToken(wxsecret);
		}
		
		return null;
	}
	
	
	private void generateAccesToken(OpriskWXGroupSecretDTO wxsecret){
		String url = wxsecret.getAccessTokenUrl();
		
		//JSONObject jsonObject = HttpRequest(url, "GET", null);
	}
}
