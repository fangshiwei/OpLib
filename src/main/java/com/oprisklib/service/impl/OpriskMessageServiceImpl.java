package com.oprisklib.service.impl;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.oprisklib.service.IOpriskMessageService;

@Service(value="opriskMessageService")
public class OpriskMessageServiceImpl implements IOpriskMessageService {

	@Resource(name="messageSource")
	private MessageSource messageSource;
	
	@Override
	public String getMessage(String msg){
		return messageSource.getMessage(msg, null, null);
	}
	
	@Override
	public String getMessage(String msg, Object[] objs){
		return messageSource.getMessage(msg, objs, null);
	}
	
	@Override
	public String getMessage(String msg, Object[] objs, Locale locale){
		return messageSource.getMessage(msg, objs, locale);
	}
	
	
}
