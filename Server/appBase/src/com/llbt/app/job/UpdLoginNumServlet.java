package com.llbt.app.job;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class UpdLoginNumServlet extends HttpServlet {

	private static final long serialVersionUID = -2981672344643340101L;

	//括号里面的*号依此为秒，分，时，天，周，月，年  
	//"0 15 10 L * ?" 每个月最后一天的10点15分0秒触发任务
	//"0 15 10 LW * ?" 每个月最后一个工作日的10点15分0秒触发任务
	public void init(ServletConfig config) throws ServletException {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	        Scheduler scheduler = schedulerFactory.getScheduler();

	        JobDetail jobDetail = new JobDetail("UpdLoginNumJob", 
	                Scheduler.DEFAULT_GROUP, UpdLoginNumJob.class);

	        String cronExpression = "0 59 23 * * ?"; 
	        CronTrigger cronTrigger = new CronTrigger("cronTrigger", 
	                Scheduler.DEFAULT_GROUP, cronExpression);

	        scheduler.scheduleJob(jobDetail, cronTrigger);

	        scheduler.start();
	        
	        
		} catch (Exception e) {
			e.printStackTrace();// throws SchedulerException, ParseException
		}

	}

}
