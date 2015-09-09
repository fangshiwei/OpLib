package com.oprisklib.task;

import com.oprisklib.jpa.model.OpriskWXMessageDTO;

public abstract class AbstractTask implements Runnable{
	OpriskWXMessageDTO wxMessage;

	public OpriskWXMessageDTO getWxMessage() {
		return wxMessage;
	}

	public void setWxMessage(OpriskWXMessageDTO wxModel) {
		this.wxMessage = wxModel;
	}

	

}
