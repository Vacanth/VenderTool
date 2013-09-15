package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QJob;

public class JobUpdateSet {
	private static final QJob JOB = QJob.job;
	
	private static class JobUpdateSetHolder {
		private static final JobUpdateSet INSTANCE = new JobUpdateSet();
	}
	
	public static JobUpdateSet getInstance() {
		return JobUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = JOB.all();
	
	public final Path<?>[] STATUS = {
		JOB.status,
		JOB.lastModifiedDate
	};
}
