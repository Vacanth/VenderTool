package com.vendertool.sharedtypes.error;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class FTSErrorCode extends VTErrorCode implements Serializable {

	private static final long serialVersionUID = 1969354337348054733L;
	
	private static Set<String> ALL_ERROR_CODES = new HashSet<String>();
	
	public FTSErrorCode(){super("UNKNOWN");}
	
	private FTSErrorCode(String errorCode) {
		super(errorCode);
	}
	
	@Override
	public Set<String> getCachedErrorCodes() {
		return ALL_ERROR_CODES;
	}

	public static VTErrorCode INVALID_FILE_FORMAT = new FTSErrorCode(
			"INVALID_FILE_FORMAT");
	
	public static VTErrorCode FILE_TOO_BIG = new FTSErrorCode(
			"FILE_TOO_BIG");
	
	public static VTErrorCode FILE_CORRUPTED = new FTSErrorCode(
			"FILE_CORRUPTED");
	
	public static VTErrorCode EMPTY_FILE = new FTSErrorCode(
			"EMPTY_FILE");
	
	public static VTErrorCode INVALID_FILE_STRUCTURE = new FTSErrorCode(
			"INVALID_FILE_STRUCTURE");
	
	public static VTErrorCode UNAUTHORIZED_FILE_ACCESS = new FTSErrorCode(
			"UNAUTHORIZED_FILE_ACCESS");
}