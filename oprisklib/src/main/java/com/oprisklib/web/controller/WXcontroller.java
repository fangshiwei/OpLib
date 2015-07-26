package com.oprisklib.web.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oprisklib.common.model.WXRequestModel;
import com.oprisklib.service.impl.WXMsgService;
import com.qq.weixin.mp.aes.AesException;

@Controller
@RequestMapping("/wx")
public class WXcontroller {
	
	Logger log = Logger.getLogger(WXcontroller.class);
	
	@Resource(name="wxService")
	private WXMsgService wxService;
	
	@RequestMapping("/getWXMenu")
	public ModelAndView getWXMenu(HttpServletRequest req, HttpServletResponse resp){
		
		
		return null;
		
	}
	
	@RequestMapping("/verifyUrl")
	public ModelAndView verifyUrl(HttpServletRequest req, HttpServletResponse resp) throws AesException, IOException{
		 String sVerifyMsgSig = req.getParameter("msg_signature");
		 String sVerifyTimeStamp = req.getParameter("timestamp");
		 String sVerifyNonce = req.getParameter("nonce");
		 String sVerifyEchoStr = req.getParameter("echostr");
		 
		 
		 String sEchoStr = wxService.verifyUrl(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, sVerifyEchoStr);
		 resp.getWriter().write(sEchoStr);
		return null;
		
	}
	
	@RequestMapping("/receviceMsgFromWechat")
	public ModelAndView receviceMsgFromWechat(HttpServletRequest req, HttpServletResponse resp) throws AesException, IOException{
		
		log.info("-------111-----------");
		WXRequestModel wxReq = constructWXRequestModel(req);
		String reqStr = null;
		if(null != wxReq.getEchostr() && !"".equalsIgnoreCase(wxReq.getEchostr())){
			reqStr = wxService.verifyUrl(wxReq);
			log.info("-------222-----------");
		}else{
			reqStr = wxService.decryptWXMsg(wxReq);
			log.info("-------333-----------");
		}
		  
		resp.getWriter().write(reqStr);
		
		return null;
	}
	
	private WXRequestModel constructWXRequestModel(HttpServletRequest req) throws IOException{
		WXRequestModel wxReq = new WXRequestModel();
		
		String sMsgSignature = req.getParameter("msg_signature");
		String sTimestamp = req.getParameter("timestamp");
		String sNonce = req.getParameter("nonce");
		String sEchostr = req.getParameter("echostr");
		
		wxReq.setMsgSignature(sMsgSignature);
		wxReq.setTimestamp(sTimestamp);
		wxReq.setNonce(sNonce);
		wxReq.setEchostr(sEchostr);
		System.out.println("sMsgSignature" + sMsgSignature);
		System.out.println("sTimestamp" + sTimestamp);
		System.out.println("sNonce" + sNonce);
		System.out.println("sEchostr" + sEchostr);
		
		InputStream in = req.getInputStream(); 
		StringBuilder xmlMsg = new StringBuilder();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
        }  
        wxReq.setsReqData(xmlMsg.toString());
		
		
		return wxReq;
	}
	
}
