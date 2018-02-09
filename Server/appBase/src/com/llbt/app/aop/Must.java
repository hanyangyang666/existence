package com.llbt.app.aop;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/** 
 * 必须的 
 * @author 邓重阳
 * @date 2016年12月20日 
 */  
@Target(ElementType.FIELD)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Must {  
    public boolean isMust() default true;  
}  
