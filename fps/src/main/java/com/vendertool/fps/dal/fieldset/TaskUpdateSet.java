package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QTask;

public class TaskUpdateSet {
	private static final QTask TASK = QTask.task;
	
	private static class TaskUpdateSetHolder {
		private static final TaskUpdateSet INSTANCE = new TaskUpdateSet();
	}
	
	public static TaskUpdateSet getInstance() {
		return TaskUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = TASK.all();
	
	public final Path<?>[] STATUS = {
		TASK.status,
		TASK.lastModifiedDate
	};
	
	public final Path<?>[] STATUS_RESPONSE = {
			TASK.response,
			TASK.status,
			TASK.lastModifiedDate
		};
}
