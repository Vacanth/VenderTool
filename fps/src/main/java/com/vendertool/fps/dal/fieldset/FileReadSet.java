package com.vendertool.fps.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.fps.dal.dao.codegen.QFile;

public class FileReadSet {
	private static final QFile FILE = QFile.file;
	
	private static class FileReadSetHolder {
		private static final FileReadSet INSTANCE = new FileReadSet();
	}

	public static FileReadSet getInstance() {
		return FileReadSetHolder.INSTANCE;
	}
	
	public final Path<?>[] FULL = FILE.all();
	
	public final Path<?>[] STATUS = {
			FILE.accountId, 
			FILE.status,
	};
}	
	