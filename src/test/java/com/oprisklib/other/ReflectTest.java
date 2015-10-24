package com.oprisklib.other;

import java.lang.reflect.Field;

import org.junit.Test;

public class ReflectTest {

	
	@Test
	public void testMethodType() throws Exception{
		Class<?> c = Class.forName("com.oprisklib.jpa.model.OpriskWXMessageDTO"); 
		
		for(Field field : c.getDeclaredFields()){
			System.out.println(field.getType().getSimpleName().equals("Integer"));
		}
			
		
		
	}
}
