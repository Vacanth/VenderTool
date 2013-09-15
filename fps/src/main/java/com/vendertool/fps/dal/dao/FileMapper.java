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
import com.vendertool.fps.dal.dao.codegen.QBeanFile;
import com.vendertool.fps.dal.dao.codegen.QFile;
import com.vendertool.sharedtypes.core.fps.FPSFileStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.File;

public class FileMapper implements DALMapper<File> {
	ValidationUtil VUTIL = ValidationUtil.getInstance();

	Path<?>[] paths;

	protected FileMapper(Path<?>[] paths) throws DatabaseException {
		if(VUTIL.isNull(paths)) {
			throw new DatabaseException("Null paths passed to the mapper");
		}
		
		this.paths = paths;
	}

	@Override
	public Map<Path<?>, Object> createMap(RelationalPath<?> path,
			File file) {
		if(VUTIL.isNull(file)) {
			return null;
		}
		
		QFile f = QFile.file;
		
		Map<Path<?>, Object> map = new HashMap<Path<?>, Object>();
		
		for(Path<?> rpath : paths) {
			if(f.fileId.equals(rpath)) {
				map.put(f.fileId, file.getFileId());
			}
			
			if(f.fileGroupId.equals(rpath)) {
				map.put(f.fileGroupId, file.getFileGroupId());
			}

			if(f.filesCountInGroup.equals(rpath)) {
				map.put(f.filesCountInGroup, file.getFilesCountInGroup());
			}
			
			if(f.accountId.equals(rpath)) {
				map.put(f.accountId, file.getAccountId());
			}
			if(f.refUrl.equals(rpath)) {
				map.put(f.refUrl, file.getRefUrl());
			}
			if(f.storageSource.equals(rpath)) {
				map.put(f.storageSource, file.getStorageSource());
			}
			if(f.useCase.equals(rpath)) {
				map.put(f.useCase, file.getUseCase());
			}
			if(f.status.equals(rpath)) {
				map.put(f.status, file.getStatus());
			}
			
			if(f.createdDate.equals(rpath)) {
				Date date = file.getCreatedDate();
				if(date == null) {
					date = new Date();
				}
				map.put(f.createdDate, new Timestamp(date.getTime()));
			}
				
			if(f.lastModifiedDate.equals(rpath)) {
				map.put(f.lastModifiedDate, new Timestamp(new Date().getTime()));
			}

		}
		return map;
	}

	public Path<?>[] getPaths() {
		return paths;
	}
	
	public QBeanFile populateBean(File file) {
		if(VUTIL.isNull(file)) {
			return null;
		}
		
		QBeanFile bean = new QBeanFile();
		bean.setFileId(file.getFileId());
		bean.setFileGroupId(file.getFileGroupId());
		bean.setFilesCountInGroup(file.getFilesCountInGroup());
		bean.setAccountId(file.getAccountId());
		bean.setRefUrl(file.getRefUrl());
		bean.setStorageSource(file.getStorageSource());
		if(file.getUseCase() != null) {
			bean.setUseCase(new Byte(file.getUseCase().getId()+""));
		}
		if(file.getStatus() != null) {
			bean.setStatus(new Byte(file.getStatus().getId()+""));
		}
		
		Date cdate = file.getCreatedDate();
		if(cdate == null) {
			cdate = new Date();
		}
		bean.setCreatedDate(new Timestamp(cdate.getTime()));
		bean.setLastModifiedDate(new Timestamp(new Date().getTime()));
		
		return bean;
	}
	
	public File convert(Tuple row, Path<?>[] paths) {
		if(VUTIL.isNull(row)) {
			return null;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			paths = this.paths;
		}
		
		if(VUTIL.isNull(paths) || (paths.length <= 0)) {
			return null;
		}
		
		QFile f = QFile.file;
		File file = new File();
		
		for(Path<?> rpath : paths) {
			if (f.fileId.equals(rpath)) {
				file.setFileId(row.get(f.fileId));
			}
			if (f.fileGroupId.equals(rpath)) {
				file.setFileGroupId(row.get(f.fileGroupId));
			}
			if (f.filesCountInGroup.equals(rpath)) {
				file.setFilesCountInGroup(row.get(f.filesCountInGroup));
			}
			if (f.accountId.equals(rpath)) {
				file.setAccountId(row.get(f.accountId));
			}
			if (f.refUrl.equals(rpath)) {
				file.setRefUrl(row.get(f.refUrl));
			}
			if (f.storageSource.equals(rpath)) {
				file.setStorageSource(row.get(f.storageSource));
			}
			if (f.useCase.equals(rpath)) {
				file.setUseCase(FPSUsecaseEnum.get(row.get(f.useCase)));
			}
			if (f.status.equals(rpath)) {
				file.setStatus(FPSFileStatusEnum.get(row.get(f.status)));
			}
				
			if(f.createdDate.equals(rpath)) {
				if(row.get(f.createdDate) != null) {
					file.setCreatedDate(new Date(row.get(f.createdDate).getTime()));
				}
			}
			
			if(f.lastModifiedDate.equals(rpath)) {
				if(row.get(f.lastModifiedDate) != null) {
					file.setLastModifiedDate(new Date(row.get(f.lastModifiedDate).getTime()));
				}
			}
		}
		
		return file;
	}
}
