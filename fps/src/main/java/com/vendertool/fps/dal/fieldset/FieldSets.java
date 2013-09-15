package com.vendertool.fps.dal.fieldset;


public interface FieldSets {
	public static JobReadSet JOB_READSET = JobReadSet.getInstance();
	public static JobUpdateSet JOB_UPDATESET = JobUpdateSet.getInstance();
	
	public static TaskReadSet TASK_READSET = TaskReadSet.getInstance();
	public static TaskUpdateSet TASK_UPDATESET = TaskUpdateSet.getInstance();
	
	public static FileReadSet FILE_READSET = FileReadSet.getInstance();
	public static FileUpdateSet FILE_UPDATESET = FileUpdateSet.getInstance();
}
