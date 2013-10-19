package com.vendertool.sharedtypes.error;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FPSErrorCode extends VTErrorCode implements Serializable {

	private static final long serialVersionUID = 1969354337348054733L;
	
	private static Set<String> ALL_ERROR_CODES = new HashSet<String>();
	
	public FPSErrorCode(){super("UNKNOWN");}
	
	private FPSErrorCode(String errorCode) {
		super(errorCode);
	}
	
	@Override
	public Set<String> getCachedErrorCodes() {
		return ALL_ERROR_CODES;
	}

	public static VTErrorCode INVALID_FILE_FORMAT = new FPSErrorCode("INVALID_FILE_FORMAT");	
	public static VTErrorCode FILE_TOO_BIG = new FPSErrorCode("FILE_TOO_BIG");	
	public static VTErrorCode FILE_CORRUPTED = new FPSErrorCode("FILE_CORRUPTED");	
	public static VTErrorCode EMPTY_FILE = new FPSErrorCode("EMPTY_FILE");	
	public static VTErrorCode INVALID_FILE_STRUCTURE = new FPSErrorCode("INVALID_FILE_STRUCTURE");	
	public static VTErrorCode UNAUTHORIZED_FILE_ACCESS = new FPSErrorCode("UNAUTHORIZED_FILE_ACCESS");
	public static VTErrorCode DB_UPDATE_ERROR = new FPSErrorCode("DB Update Error");
	public static VTErrorCode ACCOUNT_NOT_FOUND = new FPSErrorCode("Unable to Find Account");
	public static VTErrorCode EMPTY_GROUPID_JOB = new FPSErrorCode("Empty groupId is passed for job create");
	public static VTErrorCode EMPTY_JOBID = new FPSErrorCode("Empty jobId is passed for job create");
	public static VTErrorCode DB_FIND_ERROR = new FPSErrorCode("Unable to find object from DB");
	public static VTErrorCode EMPTY_TASKID = new FPSErrorCode("Empty taskId is passed for task create");
}
