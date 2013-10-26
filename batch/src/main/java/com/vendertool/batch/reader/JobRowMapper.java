package com.vendertool.batch.reader;

import com.vendertool.batch.model.JobModel;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JobRowMapper implements RowMapper<JobModel> {

	@Override
	public JobModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobModel job = new JobModel();
		job.setStatus(rs.getInt("status"));
		job.setJob_id(rs.getInt("job_id"));
		return job;
	}

}
