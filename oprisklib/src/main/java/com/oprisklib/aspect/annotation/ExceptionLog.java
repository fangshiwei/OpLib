package com.oprisklib.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.oprisklib.jpa.model.OpriskExceptionDTO;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionLog {

	Class<?> exceptionClass() default OpriskExceptionDTO.class;
}
