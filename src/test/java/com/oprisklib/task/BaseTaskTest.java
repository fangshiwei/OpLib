package com.oprisklib.task;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class) 
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@ContextConfiguration(locations={"classpath:lib-service.xml",
		"classpath:lib-dao.xml"})
public class BaseTaskTest {
	
	

}
