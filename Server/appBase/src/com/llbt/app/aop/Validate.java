package com.llbt.app.aop;


import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

/** 
* 校验注解 
* @author michael.bai 
* @date 2016年12月20日 
*/  
@Target({ElementType.PARAMETER,ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Validate {  
  public boolean isValidate() default true;  
  public boolean isForm() default false;  
} 