package com.dal.dao;

import java.util.List;


public interface BatchWorkLogDao {

	void insert (BatchWorkLog batchWorkLog);

	void update(BatchWorkLog batchWorkLog);

	void delete(BatchWorkLog batchWorkLog);

	List<BatchWorkLog> findByBatchJobId(long batchId);
	List<BatchWorkLog> findByBatchWorkLogId(long batchWorkLogId);
	
}