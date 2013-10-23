package com.vendertool.batch.dao;

import org.springframework.stereotype.Repository;
import com.vendertool.batch.model.*;

@Repository("Job.jobDao")
public class JobDaoImpl extends BaseDao implements JobDao{

	private String updateStatement="UPDATE job set status=1 where status=2 and job_id=(?)";
	
	public void save(Job job){
		System.out.println("Trying to UPDATE:::--->"+job.getJob_id());
		simpleJdbcTemplate.update(updateStatement, new Object[]{job.getJob_id()});
	}
}