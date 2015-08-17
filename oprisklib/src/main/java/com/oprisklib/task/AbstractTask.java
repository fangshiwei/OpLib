package com.oprisklib.task;

import com.oprisklib.common.model.WXReceiveXmlModel;

public abstract class AbstractTask implements Runnable{
	WXReceiveXmlModel wxModel;

	public WXReceiveXmlModel getWxModel() {
		return wxModel;
	}

	public void setWxModel(WXReceiveXmlModel wxModel) {
		this.wxModel = wxModel;
	}

	

}
