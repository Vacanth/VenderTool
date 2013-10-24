package com.vendertool.batch.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.vendertool.batch.model.Job;

@Repository("Job.jobDao")
public class JobDaoImpl extends BaseDao implements JobDao{
	private static final Logger jobDaologger = Logger.getLogger(JobDaoImpl.class);
	private String updateStatement="UPDATE job set status=1 where status=2 and job_id=(?)";
	public void save(Job job){
		jobDaologger.log(Level.DEBUG, "BatchJobProcessor:: processing jobID "+job.getJob_id());
		simpleJdbcTemplate.update(updateStatement, new Object[]{job.getJob_id()});
	}
}