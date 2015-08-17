package com.oprisklib.task;

import org.apache.log4j.Logger;

import com.oprisklib.service.IWXMsgService;

public class BookTask extends AbstractTask {
	
	Logger log = Logger.getLogger(BookTask.class);
	
	IWXMsgService wxService;
	
	public IWXMsgService getWxService() {
		return wxService;
	}

	public void setWxService(IWXMsgService wxService) {
		this.wxService = wxService;
	}



	@Override
	public void run() {
		log.info("book task");
		
		try {
			wxService.asyncResponse(this.getWxModel());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
