package com.oprisklib.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oprisklib.aspect.annotation.ExceptionLog;
import com.oprisklib.service.IExceptionService;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	Logger log = Logger.getLogger(CommonController.class);
	
	@Resource(name="exceptionService")
	private IExceptionService exceptionService;
	
	
	@RequestMapping("/testException")
	@ExceptionLog
	public ModelAndView testException(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String id = req.getParameter("id");
		
		exceptionService.exceptionHandler(Long.valueOf(id));

		return null;
		
	}
}
