package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QFile;

public class FileUpdateSet {
	private static final QFile FILE = QFile.file;
	
	private static class FileUpdateSetHolder {
		private static final FileUpdateSet INSTANCE = new FileUpdateSet();
	}
	
	public static FileUpdateSet getInstance() {
		return FileUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = FILE.all();
	
	public final Path<?>[] UPDATE_FULL = {
			FILE.status,
			FILE.lastModifiedDate,
			FILE.accountId,
			FILE.fileGroupId,
			FILE.refUrl,
			FILE.storageSource,
			FILE.useCase,
	};
	
	public final Path<?>[] STATUS = {
		FILE.status,
		FILE.lastModifiedDate
	};
}
