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
import com.vendertool.fps.dal.dao.codegen.QBeanJob;
import com.vendertool.fps.dal.dao.codegen.QJob;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.Job;

public class JobMapper implements DALMapper<Job> {
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected JobMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			Job job) {
		if(VUTIL.isNull(job)) {
			return null;
		}
		
		QJob j = QJob.job;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(j.jobId.equals(rpath)) {
				map.put(j.jobId, job.getJobId());
			}
			
			if(j.accountId.equals(rpath)) {
				map.put(j.accountId, job.getAccountId());
			}
			
			if(j.requestFileGroupId.equals(rpath)) {
				map.put(j.requestFileGroupId, job.getReqFileGroupId());
			}

			if(j.responseFileGroupId.equals(rpath)) {
				map.put(j.responseFileGroupId, job.getResFileGroupId());
			}
			
			if(j.isoCountryCode.equals(rpath)) {
				map.put(j.isoCountryCode, job.getIsoCountryCode());
			}
			if(j.status.equals(rpath)) {
				map.put(j.status, job.getStatus());
			}
			if(j.title.equals(rpath)) {
				map.put(j.title, job.getTitle());
			}
			if(j.usecase.equals(rpath)) {
				map.put(j.usecase, job.getUsecase());
			}
			if(j.error.equals(rpath)) {
				map.put(j.error, job.getError());
			}
			if(j.totalRequestFileSize.equals(rpath)) {
				map.put(j.totalRequestFileSize, job.getRequestFileSize());
			}
			if(j.totalResponseFileSize.equals(rpath)) {
				map.put(j.totalResponseFileSize, job.getResponseFileSize());
			}
		
			if(j.createdDate.equals(rpath)) {
				Date date = job.getCreatedDate();
				if(date == null) {
					date = new Date();
				}
				map.put(j.createdDate, new Timestamp(date.getTime()));
			}
				
			if(j.lastModifiedDate.equals(rpath)) {
				map.put(j.lastModifiedDate, new Timestamp(new Date().getTime()));
			}

		}
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanJob populateBean(Job job) {
		if(VUTIL.isNull(job)) {
			return null;
		}
		
		QBeanJob bean = new QBeanJob();
		bean.setJobId(job.getJobId());
		bean.setAccountId(job.getAccountId());
		bean.setRequestFileGroupId(job.getReqFileGroupId());
		bean.setResponseFileGroupId(job.getResFileGroupId());
		bean.setIsoCountryCode(job.getIsoCountryCode());
		if(job.getUsecase() != null) {
			bean.setUsecase(new Byte(job.getUsecase().getId()+""));
		}
		if(job.getStatus() != null) {
			bean.setStatus(new Byte(job.getStatus().getId()+""));
		}
		bean.setTitle(job.getTitle());
		bean.setTotalRequestFileSize(job.getRequestFileSize());
		bean.setTotalResponseFileSize(job.getResponseFileSize());

		Date cdate = job.getCreatedDate();
		if(cdate == null) {
			cdate = new Date();
		}
		bean.setCreatedDate(new Timestamp(cdate.getTime()));
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		
		return bean;
	}
	
	public Job convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QJob j = QJob.job;
		Job job = new Job();
		
		for(Path<?> rpath : paths) {
			if (j.jobId.equals(rpath)) {
				job.setJobId(row.get(j.jobId));
			}
			if (j.jobId.equals(rpath)) {
				job.setJobId(row.get(j.jobId));
			}
			if (j.accountId.equals(rpath)) {
				job.setAccountId(row.get(j.accountId));
			}
			if (j.requestFileGroupId.equals(rpath)) {
				job.setReqFileGroupId(row.get(j.requestFileGroupId));
			}
			if (j.responseFileGroupId.equals(rpath)) {
				job.setResFileGroupId(row.get(j.responseFileGroupId));
			}
			if (j.isoCountryCode.equals(rpath)) {
				job.setIsoCountryCode(row.get(j.isoCountryCode));
			}
			if (j.status.equals(rpath)) {
				job.setStatus(FPSJobStatusEnum.get(row.get(j.status)));
			}
			if (j.title.equals(rpath)) {
				job.setTitle(row.get(j.title));
			}
			if (j.usecase.equals(rpath)) {
				job.setUsecase(FPSUsecaseEnum.get(row.get(j.status)));
			}
			if (j.error.equals(rpath)) {
				job.setError(row.get(j.error));
			}
			if (j.totalRequestFileSize.equals(rpath)) {
				job.setRequestFileSize(row.get(j.totalRequestFileSize));
			}
			if (j.totalResponseFileSize.equals(rpath)) {
				job.setResponseFileSize(row.get(j.totalResponseFileSize));
			}
				
			if(j.createdDate.equals(rpath)) {
				if(row.get(j.createdDate) != null) {
					job.setCreatedDate(new Date(row.get(j.createdDate).getTime()));
				}
			}
			
			if(j.lastModifiedDate.equals(rpath)) {
				if(row.get(j.lastModifiedDate) != null) {
					job.setLastModifiedDate(new Date(row.get(j.lastModifiedDate).getTime()));
				}
			}
		}
		
		return job;
	}
}
