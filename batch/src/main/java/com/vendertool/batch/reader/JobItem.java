package com.vendertool.batch.reader;

import java.util.List;
import com.vendertool.fps.dal.dao.FpsDaoFactory;
import com.vendertool.fps.dal.dao.JobDao;
import com.vendertool.fps.dal.fieldset.JobReadSet;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;

public class JobItem   {
	List<Job> job;
	
	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) throws DBConnectionException, FinderException, DatabaseException {
		this.job = job;
	}

	public JobItem() throws DBConnectionException, FinderException, DatabaseException {
		JobDao jobDao 	= FpsDaoFactory.getInstance().getJobDao();
	}
}
