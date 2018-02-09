package com.llbt.app.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@Component
public class UpdLoginNumJob implements Job{

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("***********************定时任务***************************");
		System.out.println("执行定时任务：批量修改登录错误次数"+new Date() + " by " + arg0.getTrigger().getName());
		// 使得job对象可以通过注解实现依赖注入
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		updLoginNum();
		System.out.println("***********************定时任务***************************");
	}
	
	public void updLoginNum(){
		//gateLoginService.updLoginErrorNum();
	}
	

}
