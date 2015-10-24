package com.oprisklib.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.oprisklib.common.model.WXAccessToken;
import com.oprisklib.common.model.WXRequestModel;
import com.oprisklib.constant.WXEventKeyType;
import com.oprisklib.constant.WXEventType;
import com.oprisklib.constant.WXMsgType;
import com.oprisklib.jpa.OpriskRepositoryPoint;
import com.oprisklib.jpa.model.OpriskWXMessageDTO;
import com.oprisklib.service.IOpriskBookService;
import com.oprisklib.service.IWXConfigService;
import com.oprisklib.service.IWXMsgService;
import com.oprisklib.util.HttpsUtils;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@Service(value="wxService")
public class WXMsgServiceImpl implements IWXMsgService {
	Logger log = Logger.getLogger(WXMsgServiceImpl.class);
	
	@Resource(name="wxConfigService")
	private IWXConfigService wxConfigService;
	
	@Resource(name="opriskBookService")
	private IOpriskBookService opriskBookService;
	
	@Resource(name="opriskRepositoryPoint")
	private OpriskRepositoryPoint opriskRepositoryPoint;
	
	private static Integer createdTime = -1;
	
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
	
	public OpriskWXMessageDTO decodeMessage(WXRequestModel wxReq) throws Exception{
		WXBizMsgCrypt wxcpt = this.wxConfigService.getWXBizMsgCrypt();
		String responseMsg = wxcpt.DecryptMsg(wxReq.getMsgSignature(), wxReq.getTimestamp(), wxReq.getNonce(), wxReq.getsReqData());
		System.out.println("after decrypt msg: " + responseMsg);
		OpriskWXMessageDTO wxMessage = this.parseXML(responseMsg);
		return wxMessage;
	}
	
	@Override
	public OpriskWXMessageDTO saveMessage(OpriskWXMessageDTO wxMessage){
		return this.opriskRepositoryPoint.getOpriskWXMessageRep().save(wxMessage);
	}
	
	public String generateReplyEncryMsg(String to, String from, String content) throws Exception{
		WXBizMsgCrypt wxcpt = this.wxConfigService.getWXBizMsgCrypt();
		
		String formatRply = formatXmlAnswer(to, from, content, String.valueOf(System.currentTimeMillis()));
		
		String encryMsg = wxcpt.EncryptMsg(formatRply, String.valueOf(System.currentTimeMillis()), getRandomStr());
		
		return encryMsg;
	}
	
	
	String getRandomStr() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	private String parseAndReplyMsg(String responseMsg){
		String reply = "welcome --:)" ;
		try {
			
			OpriskWXMessageDTO wxXML = this.parseXML(responseMsg);
			if(createdTime.equals(wxXML.getCreateTime())){
				reply = "inputing, please wait...";
			}else{
				createdTime = wxXML.getCreateTime();
				reply = parseReply(wxXML);
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
	private String parseReply(OpriskWXMessageDTO wxXML)
			throws Exception {
		String reply = "";
		WXMsgType msgType = WXMsgType.getWXMsgTypeByName(wxXML.getMsgType()); 
		switch(msgType){
			case TEXT:
				reply = parseTextMessage(wxXML);
				break;
			case IMAGE:
				reply = "Image message::--" + wxXML.getContent();
				break;
			case VOICE:
				reply = "Voice message::--" + wxXML.getContent();
				break;
			case VIDEO:
				reply = "Video::--" + wxXML.getContent();
				break;
			case SHORTVIDEO:
				reply = "Short Video::--" + wxXML.getContent();
				break;
			case LOCATION:
				reply = "Location::--" + wxXML.getContent();
				break;
			case EVENT:
				reply = parseEventMsg(wxXML);
				break;
			default:
				break;
		}
		
		return reply;
	}

	/**
	 * @param wxXML
	 * @return
	 */
	private String parseTextMessage(OpriskWXMessageDTO wxXML) {
		String reply;
		char caseCondition = wxXML.getContent().charAt(0);
		switch(caseCondition){
		case '1':
			reply = this.opriskBookService.fetchAllBookList();
			break;
		case '2':
			reply = this.opriskBookService.fetchAllAvalibleBookList();
			break;
		case '3':
			reply = this.opriskBookService.fetchAllBookListByName( wxXML.getContent());
			break;
		default:
			reply = "Hi, " + wxXML.getFromUserName() + " Welcome to oprisk library. \n"
				    +". Replay 1 to get all book list./n"
				    + " Replay 2 to get avalible book list./n"
				    + " Replay 3-bookname(eg:3-Java) to get book summary and status";
			break;	
		
		}
		return reply;
	}

	/**
	 * @param reply
	 * @param wxXML
	 * @return
	 * @throws Exception
	 */
	private String parseEventMsg(OpriskWXMessageDTO wxXML) throws Exception {
		String reply = "";
		
		WXEventType eventType = WXEventType.getWXEventTypeByName(wxXML.getEvent()); 
		
		switch(eventType){
			case SUBSCRIBE:
				reply = "SUBSCRIBE::--" + wxXML.getContent();
				break;
			case UNSUBSCRIBE:
				reply = "UNSUBSCRIBE::--" + wxXML.getContent();
				break;
			case LOCATION:
				reply = "LOCATION::--" + wxXML.getContent();
				break;
			case CLICK:
				reply = "CLICK::--" + wxXML.getContent();
				break;
			case VIEW:
				reply = "VIEW::--" + wxXML.getContent();
				break;
			case SCANCODE_PUSH:
				reply = "SCANCODE_PUSH::--" + wxXML.getContent();
				break;
			case SCANCODE_WAITMSG:
				reply = parseScanWaitMsg(wxXML);
				break;
			case PIC_SYSPHOTO:
				reply = "PIC_SYSPHOTO::--" + wxXML.getContent();
				break;
			case PIC_PHOTO_OR_ALBUM:
				reply = "PIC_PHOTO_OR_ALBUM::--" + wxXML.getContent();
				break;
			case PIC_WEIXIN:
				reply = "PIC_WEIXIN::--" + wxXML.getContent();
				break;
			case LOCATION_SELECT:
				reply = "LOCATION_SELECT::--" + wxXML.getContent();
				break;
			case ENTER_AGENT:
				reply = "ENTER_AGENT::--" + wxXML.getContent();
				break;
			case BATCH_JOB_RESULT:
				reply = "BATCH_JOB_RESULT::--" + wxXML.getContent();
				break;
			default:
				reply = "Event::--" + wxXML.getContent();
				break;
		}
		return reply;
	}

	/**
	 * @param wxXML
	 * @return
	 * @throws Exception
	 */
	private String parseScanWaitMsg(OpriskWXMessageDTO wxXML) throws Exception {
		String reply = "";
		WXEventKeyType eventTkeyType = WXEventKeyType.getWXEventKeyTypeByName(wxXML.getEventKey());
		String isbn = wxXML.getScanResult().substring(wxXML.getScanResult().indexOf(",")+1);
		switch(eventTkeyType){
			case SCAN_BORROW: 
				reply =this.opriskBookService.borrowBookByISBN(isbn, wxXML.getFromUserName());
				break;
			case SCAN_RETURN:
				reply =this.opriskBookService.returnBookByISBN(isbn, wxXML.getFromUserName());
				break;
			case SEARCH_BY_AUTHOR:
				reply ="library-scan by author:" + wxXML.getScanResult();
				break;
			case SEARCH_BY_OWNER:
				reply ="library-scan by owner:" + wxXML.getScanResult();
				break;
			case SCAN_INPUT_BOOK:
				reply = opriskBookService.saveBook(wxXML.getScanResult());
				break;
			default:
				reply ="library-scan no response:" + wxXML.getScanResult();
				break;
			}
		return reply;
	}
	
	private OpriskWXMessageDTO parseXML(String strXml) throws Exception {
		OpriskWXMessageDTO msg = new OpriskWXMessageDTO();  
		if (strXml.length() <= 0 || strXml == null){
			return null;
		}
		Document document = DocumentHelper.parseText(strXml);
		Element root = document.getRootElement(); 
		Class<?> c = Class.forName("com.oprisklib.jpa.model.OpriskWXMessageDTO"); 
		msg = (OpriskWXMessageDTO)c.newInstance();
		Iterator<?> iter = root.elementIterator();
		while(iter.hasNext()){  
            Element ele = (Element)iter.next();  
            if(ele.hasMixedContent()){
            	Iterator<?> iterj = ele.elementIterator();
            	while(iterj.hasNext()){
            		Element elej = (Element)iterj.next(); 
            		Field field = c.getDeclaredField(elej.getName());   
                    Method method = c.getDeclaredMethod("set"+elej.getName(), field.getType());   
                    if(field.getType().getSimpleName().equals("Integer")){
                    	method.invoke(msg, Integer.valueOf(elej.getText())); 
                    }else{
                    	method.invoke(msg, elej.getText()); 
                    }
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
    	
    	JSONObject json = HttpsUtils.postJson(url, msg);
    	
    	return json;
    	
    }
    
    
    public String asyncResponse(OpriskWXMessageDTO wxXML) throws Exception{
		
		String replyContent = parseReply(wxXML);
		
		String groupName = "oprisk";
		String sendMsgUserId = wxXML.getFromUserName();
		String agentId = String.valueOf(wxXML.getAgentID());
		String replyMsg = generateJsonTextMsg(sendMsgUserId, agentId, replyContent);
		log.info(replyMsg);
		
		JSONObject json = sendMsgByToken(replyMsg, groupName);
		
		return json.getString("errmsg");
	}
    
    private String generateJsonTextMsg(String toUser, String agentId, String replyContent){
    	JSONObject json = new JSONObject();
    	String msgType = "text";
    	json.put("touser", toUser);
    	json.put("msgtype", msgType);
    	json.put("agentid", agentId);
    	
    	JSONObject conJson = new JSONObject();
    	conJson.put("content", replyContent);
    	
    	json.put("text", conJson);
    	
    	return json.toString();
    }
}
