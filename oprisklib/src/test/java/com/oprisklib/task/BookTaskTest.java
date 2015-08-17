package com.oprisklib.task;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import com.oprisklib.common.model.WXReceiveXmlModel;
import com.oprisklib.service.impl.WXMsgServiceImpl;

public class BookTaskTest extends BaseTaskTest{ 
    @Autowired  
    @Qualifier("taskExecutor")  
    private TaskExecutor taskExecutor;  
    
    @Resource(name="wxService")
	private WXMsgServiceImpl wxService;
    
    private WXReceiveXmlModel wxXML;
    
    
    @Before
    private void constructWxModel(){
    	wxXML = new WXReceiveXmlModel();
		wxXML.setAgentID("6");
		wxXML.setFromUserName("corpId");
		wxXML.setToUserName("fsw");
		wxXML.setMsgType("text");
		wxXML.setContent("111111111");
    }
    
    
    @Test
	public void testBookTask(){
		
		BookTask bt = new BookTask();
		bt.setWxService(wxService);
		bt.setWxModel(wxXML);
		taskExecutor.execute(bt);
		
		
	}
    
    
    
}
