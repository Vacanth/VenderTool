package com.vendertool.sharedtypes.error;

public class CommonErrors {
	private static final VTErrorDomainEnum DOMAIN = VTErrorDomainEnum.COMMON;
	private static CommonErrors INSTANCE = null;

	public static synchronized CommonErrors getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CommonErrors();
		}
		return INSTANCE;
	}

	private CommonErrors() {
	}

	// Define errors here
	public VTError NULL_ARGUMENT_PASSED = new VTError(
			CommonErrorCode.NULL_ARGUMENT_PASSED,
			"Null value passed as arguments", VTErrorSeverityEnum.ERROR, DOMAIN);

	public VTError UNMAPPED_ERROR = new VTError(
			CommonErrorCode.UNMAPPED_ERROR,
			"Unmapper error. This happens if mercadolibre introduces new error codes.",
			VTErrorSeverityEnum.ERROR, DOMAIN);
}
