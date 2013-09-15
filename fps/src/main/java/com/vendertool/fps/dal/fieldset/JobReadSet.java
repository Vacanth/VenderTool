package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QJob;

public class JobReadSet {
	private static final QJob JOB = QJob.job;
	
	private static class FileReadSetHolder {
		private static final JobReadSet INSTANCE = new JobReadSet();
	}
	
	public static JobReadSet getInstance() {
		return FileReadSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = JOB.all();
	
	public final Path<?>[] STATUS = {
			JOB.accountId, 
			JOB.status,
	};
}	
	