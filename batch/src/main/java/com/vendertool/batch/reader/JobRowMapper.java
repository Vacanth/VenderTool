package com.vendertool.batch.reader;

import com.vendertool.batch.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JobRowMapper implements RowMapper<Job> {

	@Override
	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
		Job job = new Job();
		job.setStatus(rs.getInt("status"));
		job.setJob_id(rs.getInt("job_id"));
		return job;
	}

}
