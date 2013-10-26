package com.vendertool.batch.processor;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vendertool.batch.model.JobModel;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.TaskDao;
import com.vendertool.sharedapp.RestServiceClientHelper;
import com.vendertool.sharedapp.URLConstants;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.core.fps.Task;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.fieldset.JobReadSet;
import com.vendertool.fps.dal.fieldset.JobUpdateSet;
import com.vendertool.fps.dal.fieldset.TaskReadSet;
import com.vendertool.fps.dal.fieldset.TaskUpdateSet;

public class JobProcessor implements ItemProcessor<JobModel, JobModel>{
	private static final Logger logger = Logger.getLogger(JobProcessor.class);
	public JobModel process(JobModel jobModel) throws Exception
	{
		logger.log(Level.DEBUG, "BatchJobProcessor:: Processing jobId: "+jobModel.getJob_id());	
		JobDao jobDao = FpsDaoFactory.getInstance().getJobDao();
		TaskDao taskDao = FpsDaoFactory.getInstance().getTaskDao();
		Job job = jobDao.findByJobId(jobModel.getJob_id(), JobReadSet.getInstance().FULL);
		if(job!=null)
		{
			// check whether the job ran successfully. Get all tasks for the job and verify whether all Tasks ran successfully
			List<Task> tasks = taskDao.findByJobId(jobModel.getJob_id(), TaskReadSet.getInstance().FULL);
			if(tasks!=null)  
			{
				// Check whether the process failed. If failed, re-process
				boolean bInCompleteTask = false;
				if((job.getStatus() == FPSJobStatusEnum.FAILED) || (job.getStatus() == FPSJobStatusEnum.PARTIAL_SUCCESS))
				{
					// reset Task status to CREATED if not SUCCESS, and fire TASK event
					for (Task task : tasks) {
						// For all tasks which failed to created listing, 
						if((task.getStatus() == FPSTaskStatusEnum.FAILED) || (task.getStatus() == FPSTaskStatusEnum.ABANDONED))
						{
							task.setStatus(FPSTaskStatusEnum.CREATED);
							taskDao.update(task, TaskUpdateSet.getInstance().STATUS);
							// create the listing call
							Response response = resetTaskEvent(task.getTaskId());
							if(response.getStatus() == Response.Status.OK.getStatusCode())
							{
								logger.log(Level.INFO, "BatchJobProcessor:: Reset TaskID: " + task.getTaskId());
								bInCompleteTask = true;
							}
							else {
								bInCompleteTask = false;
							}
						}
					}
					if(bInCompleteTask)
					{
						// if all Tasks are success, set The Job status to SUCCESS
						job.setStatus(FPSJobStatusEnum.SUCCESS);
						jobDao.update(job, JobUpdateSet.getInstance().STATUS);
					}
				}
				else if((job.getStatus() == FPSJobStatusEnum.SUCCESS))
				{
					// verify whether  Task status to CREATED if not SUCCESS, and fire TASK event
					for (Task task : tasks) {
						// For all tasks which failed to created listing, 
						if(task.getStatus() != FPSTaskStatusEnum.SUCCESS)
						{
							task.setStatus(FPSTaskStatusEnum.CREATED);
							taskDao.update(task, TaskUpdateSet.getInstance().STATUS);
							// create the listing call
							Response response = resetTaskEvent(task.getTaskId());
							if(response.getStatus() == Response.Status.OK.getStatusCode())
							{
								logger.log(Level.INFO, "BatchJobProcessor:: Reset TaskID: " + task.getTaskId());
								bInCompleteTask = true;
							}
							else {
								bInCompleteTask = false;
							}
						}
					}
					if(!bInCompleteTask)
					{
						// if a task fails, set the Job status to FAILED
						job.setStatus(FPSJobStatusEnum.FAILED);
						jobDao.update(job, JobUpdateSet.getInstance().STATUS);
					}
				}
			}
			else
			{
				// set Job status to Abandoned as there are no tasks for the JOB
				job.setStatus(FPSJobStatusEnum.ABANDONED);
				jobDao.update(job, JobUpdateSet.getInstance().FULL);			
				// Task event was not consumed, create the Job event so that Tasks will be recreated
				logger.log(Level.DEBUG, "BatchJobProcessor:: Recreating the JOB event for JobId: "+job.getJobId());
				Response response = resetJobEvent(job.getJobId());
				if(response.getStatus() == Response.Status.OK.getStatusCode())
				{
					logger.log(Level.DEBUG, "BatchJobProcessor:: Recreating the JOB event for JobId: "+job.getJobId());
				}
			}	
		}
			return null;  // return null so that the writer doesn't do any work
		}
		
		private Response resetJobEvent(long jobId) {
			String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.JOB_PROCESS_PATH;
			ProcessJobRequest jobRequest = new ProcessJobRequest();
			jobRequest.setJobId(jobId);
			Response response = RestServiceClientHelper.invokeRestService(url,jobRequest,null, MediaType.APPLICATION_JSON_TYPE, HttpMethodEnum.POST);
			if(response.getStatus() == Response.Status.OK.getStatusCode()) 
			{
				logger.log(Level.INFO, "BatchJobProcessor:: Creating the JOB event for jobID: " + jobId);
			}
			return response;
		}
		
		private Response resetTaskEvent(long taskId) {
			String url = URLConstants.WS_URL_ENDPOINT + URLConstants.WEB_SERVICE_PATH + URLConstants.TASK_PROCESS_PATH;
			ProcessTaskRequest jobTask = new ProcessTaskRequest();
			jobTask.setTaskId(taskId);
			Response response = RestServiceClientHelper.invokeRestService(url,jobTask,null, MediaType.APPLICATION_JSON_TYPE, HttpMethodEnum.POST);
			if(response.getStatus() == Response.Status.OK.getStatusCode()) 
			{
				logger.log(Level.INFO, "BatchJobProcessor:: Creating the JOB event for taskID: " + taskId);
			}
			return response;
		}
}

