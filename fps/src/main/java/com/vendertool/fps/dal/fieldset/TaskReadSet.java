package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QTask;

public class TaskReadSet {
	private static final QTask TASK = QTask.task;
	
	private static class FileReadSetHolder {
		private static final TaskReadSet INSTANCE = new TaskReadSet();
	}
	
	public static TaskReadSet getInstance() {
		return FileReadSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = TASK.all();
	
	public final Path<?>[] STATUS = {
			TASK.accountId, 
			TASK.status,
	};
}	
	