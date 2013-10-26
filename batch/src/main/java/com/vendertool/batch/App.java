package com.vendertool.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		String[] springConfig  = 
			{	"database.xml","context.xml","batchjob.xml",
				"batch-application-context.xml","FpsDAL.xml",
				"dal-module.xml","Datasource.xml","CommonDAL.xml"
			};
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("statusCheckJob");

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Job completed..");
			// close context
		}
	}
}