package com.oprisklib.service.impl;

import org.springframework.stereotype.Service;

import com.oprisklib.aspect.annotation.ExceptionLog;
import com.oprisklib.service.IExceptionService;

@Service(value="exceptionService")
public class ExceptionServiceImpl implements IExceptionService {

	@Override
	@ExceptionLog
	public void exceptionHandler(Long id) throws Exception {
		
		if (id == 1l){
			System.out.println("------------------------------------");
			throw new Exception("id = 1");
		}else{
			System.out.println("ggg");
		}
		
	}

}
