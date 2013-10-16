package com.vendertool.sharedtypes.error;

import java.util.HashSet;
import java.util.Set;

public class CommonErrorCode extends VTErrorCode {
	
	private static final long serialVersionUID = 776753444079464992L;
	
	private static Set<String> ALL_ERROR_CODES = new HashSet<String>();
	
	public CommonErrorCode(){super("UNKNOWN");}
	
	private CommonErrorCode(String errorCode) {
		super(errorCode);
	}

	@Override
	public Set<String> getCachedErrorCodes() {
		return ALL_ERROR_CODES;
	}

	//add error codes here
	public static VTErrorCode NULL_ARGUMENT_PASSED = new CommonErrorCode(
			"NULL_ARGUMENT_PASSED");
	public static VTErrorCode UNMAPPED_ERROR = new CommonErrorCode(
			"UNMAPPED_ERROR");
}