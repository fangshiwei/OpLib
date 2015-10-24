package com.oprisklib.web.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:lib-servlet.xml",
		"classpath:lib-service.xml",
		"classpath:lib-dao.xml"})
public class BaseControllerTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	protected MockMvc mockMvc;

}
