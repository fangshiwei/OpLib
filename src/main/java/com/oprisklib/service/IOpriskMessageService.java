package com.oprisklib.service;

import java.util.Locale;


public interface IOpriskMessageService {

	String getMessage(String msg);

	String getMessage(String msg, Object[] objs);

	String getMessage(String msg, Object[] objs, Locale locale);

	
}
