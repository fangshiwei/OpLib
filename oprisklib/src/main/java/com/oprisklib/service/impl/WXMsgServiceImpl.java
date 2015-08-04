package com.oprisklib.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oprisklib.common.model.WXAccessToken;
import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.common.model.WXRequestModel;
import com.oprisklib.constant.WXEventKeyType;
import com.oprisklib.constant.WXEventType;
import com.oprisklib.constant.WXMsgType;
import com.oprisklib.service.IWXConfigService;
import com.oprisklib.service.IWXMsgService;
import com.oprisklib.util.HttpsUtils;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Service(value="wxService")
public class WXMsgServiceImpl implements IWXMsgService {
	
	@Resource(name="wxConfigService")
	private IWXConfigService wxConfigService;
	
	@Resource(name="opriskBookStoreService")
	private OpriskBookStoreServiceImpl opriskBookStoreService;
	
	private static String createdTime = "";
	
	public String verifyUrl(WXRequestModel wxReq) throws AesException{
		
		return verifyUrl(wxReq.getMsgSignature(), wxReq.getTimestamp(), wxReq.getNonce(), wxReq.getEchostr());
	}
	
	public String verifyUrl(String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce, String sVerifyEchoStr) throws AesException{
		String sEchoStr = null;
		
		WXBizMsgCrypt wxcpt = this.wxConfigService.getWXBizMsgCrypt();
		sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
		System.out.println("verifyurl echostr: " + sEchoStr);
		return sEchoStr;
	}
	
	
	
	public String decryptWXMsg(WXRequestModel wxReq) throws AesException{
		
		//String responseMsg = decryptWXMsg(wxReq.getMsgSignature(), wxReq.getTimestamp(), wxReq.getNonce(), wxReq.getsReqData());
		
		WXBizMsgCrypt wxcpt = this.wxConfigService.getWXBizMsgCrypt();
		String responseMsg = wxcpt.DecryptMsg(wxReq.getMsgSignature(), wxReq.getTimestamp(), wxReq.getNonce(), wxReq.getsReqData());
		System.out.println("after decrypt msg: " + responseMsg);
		String reply = this.parseAndReplyMsg(responseMsg);
		String encryMsg = wxcpt.EncryptMsg(reply, String.valueOf(System.currentTimeMillis()), wxReq.getNonce());
		
		
		return encryMsg;
	}
	
	private String parseAndReplyMsg(String responseMsg){
		String reply = "welcome --:)" ;
		try {
			
			WXReceiveXmlModel wxXML = this.parseXML(responseMsg);
			if(createdTime.equals(wxXML.getCreateTime())){
				reply = "inputing, please wait...";
			}else{
				createdTime = wxXML.getCreateTime();
				reply = parseReply(reply, wxXML);
			} 
			
			reply = formatXmlAnswer(wxXML.getFromUserName(), wxXML.getToUserName(),reply, String.valueOf(System.currentTimeMillis()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reply;
	}

	/**
	 * @param reply
	 * @param wxXML
	 * @return
	 * @throws Exception
	 */
	private String parseReply(String reply, WXReceiveXmlModel wxXML)
			throws Exception {
		if(wxXML.getMsgType().equalsIgnoreCase(WXMsgType.TEXT.getName())){
			reply = "welcome to oprisk library::--" + wxXML.getContent();
		}else if(wxXML.getMsgType().equalsIgnoreCase(WXMsgType.EVENT.getName())){
			if(wxXML.getEvent().equalsIgnoreCase(WXEventType.SCANCODE_PUSH.getName())){
				if(wxXML.getEventKey().equalsIgnoreCase(WXEventKeyType.SCAN_BORROW.getName())){
					reply ="welcome to oprisk library-scan borrow:" + wxXML.getScanCodeInfo();
				}else if(wxXML.getEventKey().equalsIgnoreCase(WXEventKeyType.SCAN_RETURN.getName())){
					reply ="welcome to oprisk library-scan return:" + wxXML.getScanResult();
				}
		}else if(wxXML.getEvent().equalsIgnoreCase(WXEventType.SCANCODE_WAITMSG.getName())){
			WXEventKeyType eventTkeyType = WXEventKeyType.getWXEventKeyTypeByName(wxXML.getEventKey());
			
			switch(eventTkeyType){
				case SCAN_BORROW:  
					reply ="library-scan borrow:" + wxXML.getScanResult();
					break;
				case SCAN_RETURN:
					reply ="library-scan return:" + wxXML.getScanResult();
					break;
				case SEARCH_BY_AUTHOR:
					reply ="library-scan by author:" + wxXML.getScanResult();
					break;
				case SEARCH_BY_OWNER:
					reply ="library-scan by owner:" + wxXML.getScanResult();
					break;
				case SCAN_INPUT_BOOK:
					String bookTitle = opriskBookStoreService.save(wxXML);;
					reply ="书籍:" + bookTitle +", ISBN:"+wxXML.getScanResult() +", 已经录入";
					break;
				default:
					reply ="library-scan no response:" + wxXML.getScanResult();
					break;
				}
			}
		}
		return reply;
	}
	
	private WXReceiveXmlModel parseXML(String strXml) throws Exception {
		WXReceiveXmlModel msg = new WXReceiveXmlModel();  
		if (strXml.length() <= 0 || strXml == null){
			return null;
		}
		Document document = DocumentHelper.parseText(strXml);
		Element root = document.getRootElement(); 
		Class<?> c = Class.forName("com.oprisklib.common.model.WXReceiveXmlModel"); 
		msg = (WXReceiveXmlModel)c.newInstance();
		Iterator<?> iter = root.elementIterator();
		while(iter.hasNext()){  
            Element ele = (Element)iter.next();  
            if(ele.hasMixedContent()){
            	Iterator<?> iterj = ele.elementIterator();
            	while(iterj.hasNext()){
            		Element elej = (Element)iterj.next(); 
            		Field field = c.getDeclaredField(elej.getName());   
                    Method method = c.getDeclaredMethod("set"+elej.getName(), field.getType());   
                    method.invoke(msg, elej.getText());  
            	}
            }

            Field field = c.getDeclaredField(ele.getName());   
            Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());   
            method.invoke(msg, ele.getText());  
        } 
		
		return msg;
	}
	
    private String formatXmlAnswer(String to, String from, String content, String sReqTimeStamp ) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(sReqTimeStamp);  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content></xml>");  
        return sb.toString();  
    }  
	
	
    public JSONObject sendMsgByToken(String msg, String groupName) throws Exception{
    	String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=:access_token";
    	WXAccessToken accessToken = this.wxConfigService.getAccessTokenByGroup(groupName);
    	url = url.replace(":access_token", accessToken.getAccessToken());
    	
    	JSONObject json = HttpsUtils.postJson(url,  mokeMsg());
    	
    	return json;
    	
    }
    
    private String mokeMsg(){
    	String msg = "{ "+
    		  " \"touser\": \"fsw\", " +
    		  " \"msgtype\": \"text\", "+
    		  " \"agentid\": \"6\", "+
    		  " \"text\": {  "+
    		  "     \"content\": \"test...........\" "+
    		  " }, " +
    		"}";
    	
    	return msg;
    }
}
