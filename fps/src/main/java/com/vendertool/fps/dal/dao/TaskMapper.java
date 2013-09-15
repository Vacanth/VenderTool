package com.vendertool.fps.dal.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.DALMapper;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.dao.codegen.QBeanTask;
import com.vendertool.fps.dal.dao.codegen.QTask;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Task;

public class TaskMapper implements DALMapper<Task> {
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected TaskMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Task task) {
		if(VUTIL.isNull(task)) {
			return null;
		}
		
		QTask t = QTask.task;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(t.taskId.equals(rpath)) {
				map.put(t.taskId, task.getTaskId());
			}
			if(t.jobId.equals(rpath)) {
				map.put(t.jobId, task.getJobId());
			}
			if(t.requestGroupId.equals(rpath)) {
				map.put(t.requestGroupId, task.getRequestGroupId());
			}
			if(t.accountId.equals(rpath)) {
				map.put(t.accountId, task.getAccountId());
			}
			if(t.requestFileId.equals(rpath)) {
				map.put(t.requestFileId, task.getRequestFileId());
			}
			if(t.recordId.equals(rpath)) {
				map.put(t.recordId, task.getRecordId());
			}
			if(t.request.equals(rpath)) {
				map.put(t.request, task.getRequest());
			}
			if(t.response.equals(rpath)) {
				map.put(t.response, task.getResponse());
			}
			if(t.status.equals(rpath)) {
				map.put(t.status, task.getStatus());
			}
			if(t.isoCountryCode.equals(rpath)) {
				map.put(t.isoCountryCode, task.getIsoCountryCode());
			}

			if(t.createdDate.equals(rpath)) {
				Date date = task.getCreatedDate();
				if(date == null) {
					date = new Date();
				}
				map.put(t.createdDate, new Timestamp(date.getTime()));
			}
				
			if(t.lastModifiedDate.equals(rpath)) {
				map.put(t.lastModifiedDate, new Timestamp(new Date().getTime()));
			}

		}
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanTask populateBean(Task task) {
		if(VUTIL.isNull(task)) {
			return null;
		}
		
		QBeanTask bean = new QBeanTask();
		bean.setTaskId(task.getTaskId());
		bean.setJobId(task.getJobId());
		bean.setRequestGroupId(task.getRequestGroupId());
		bean.setAccountId(task.getAccountId());
		bean.setRequestFileId(task.getRequestFileId());
		bean.setRecordId(task.getRecordId());
		bean.setRequest(task.getRequest());
		bean.setResponse(task.getResponse());
		if(task.getStatus() != null) {
			bean.setStatus(new Byte(task.getStatus().getId()+""));
		}
		bean.setIsoCountryCode(task.getIsoCountryCode());

		Date cdate = task.getCreatedDate();
		if(cdate == null) {
			cdate = new Date();
		}
		bean.setCreatedDate(new Timestamp(cdate.getTime()));
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		
		return bean;
	}
	
	public Task convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QTask t = QTask.task;
		Task task = new Task();
		
		for(Path<?> rpath : paths) {
			if (t.taskId.equals(rpath)) {
				task.setTaskId(row.get(t.taskId));
			}
			if (t.jobId.equals(rpath)) {
				task.setJobId(row.get(t.jobId));
			}
			if (t.requestGroupId.equals(rpath)) {
				task.setRequestGroupId(row.get(t.requestGroupId));
			}
			if (t.accountId.equals(rpath)) {
				task.setAccountId(row.get(t.accountId));
			}			
			if (t.requestFileId.equals(rpath)) {
				task.setRequestFileId(row.get(t.requestFileId));
			}
			if (t.recordId.equals(rpath)) {
				task.setRecordId(row.get(t.recordId));
			}
			if (t.request.equals(rpath)) {
				task.setRequest(row.get(t.request));
			}
			if (t.response.equals(rpath)) {
				task.setResponse(row.get(t.response));
			}			
			if (t.status.equals(rpath)) {
				task.setStatus(FPSTaskStatusEnum.get(row.get(t.status)));
			}

			if(t.createdDate.equals(rpath)) {
				if(row.get(t.createdDate) != null) {
					task.setCreatedDate(new Date(row.get(t.createdDate).getTime()));
				}
			}
			
			if(t.lastModifiedDate.equals(rpath)) {
				if(row.get(t.lastModifiedDate) != null) {
					task.setLastModifiedDate(new Date(row.get(t.lastModifiedDate).getTime()));
				}
			}
		}
		
		return task;
	}
}
