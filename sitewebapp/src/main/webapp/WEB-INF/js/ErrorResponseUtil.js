/**********************************
This is a sample errorResponse. The first error is a page level error.
The second error is a field level error since its 'bindingFieldMap' is not null.

"errorResponse:" {
	"fieldBindingErrors":[
		{
			"error":{
				"errorCode":{
					"type":"RegistrationErrorCode",
					"errorCode":"EMAIL_REQUIRED"
				},
				"message":"Email is required",
				"severity":"ERROR",
				"domain":"REGISTRATION",
				"arguments":null
			},
			"bindingFieldMap":null
		},
		{
			"error":{
				"errorCode":{
					"type":"RegistrationErrorCode",
					"errorCode":"MISSING_LASTNAME"
				},
				"message":"Last name is required",
				"severity":"ERROR",
				"domain":"REGISTRATION",
				"arguments":null
			},
			"bindingFieldMap":{
				"com.vendertool.sharedtypes.core.ContactDetails":["lastName"]
			}
		}
	],
	"status":null,
	"signedInEmail":null}
}
*************************************/

ErrorResponseUtil = {};

//
//Get field errors with specified className and fieldName
//
ErrorResponseUtil.getFieldErrors = function(className, fieldName, errorResponse) {
	var fldErrors = [], fldError, fields, i, n, j, m;
	
	if (errorResponse && errorResponse.fieldBindingErrors) {
		for (i=0, n=errorResponse.fieldBindingErrors.length; i<n; i++) {
			fldError = errorResponse.fieldBindingErrors[i];
			
			// If bindingFieldMap is NOT null, then it's a field error
			if (fldError.bindingFieldMap !== null) {
				fields = fldError.bindingFieldMap[className];
				
				if (fields) {
					for (j=0, m=fields.length; j<m; j++) {
						if (fields[j] === fieldName) {
							fldErrors.push(fldError);
						}
					}
				}
			}
		}
	}

	return fldErrors;
};

//
// Get field error messages with specified className and fieldName
//
ErrorResponseUtil.getFieldErrorMsgs = function(className, fieldName, errorResponse) {
	var i, j, fldErrors, errorMsgs = [];
	
	fldErrors = ErrorResponseUtil.getFieldErrors(className, fieldName, errorResponse);
		
	for (i=0, j=fldErrors.length; i<j; i++) {
		errorMsgs.push(fldErrors[i].error.message);
	}
	
	return errorMsgs;
};

//
// Get all field errors regardless of className and fieldName
//
ErrorResponseUtil.getAllFieldErrors = function(errorResponse) {
	var fldErrors = [];
	
	if (errorResponse && errorResponse.fieldBindingErrors) {
		for (var i=0, n=errorResponse.fieldBindingErrors.length; i<n; i++) {
			var fldError = errorResponse.fieldBindingErrors[i];
			
			// If bindingFieldMap is NOT null, then it's a field error
			if (fldError.bindingFieldMap !== null) {
				fldErrors.push(fldError.error);
			}
		}
	}
	return fldErrors;
};

//
//Get all field errors messages regardless of className and fieldName
//
ErrorResponseUtil.getAllFieldErrorMsgs = function(errorResponse) {
	var fldErrors = ErrorResponseUtil.getAllFieldErrors(errorResponse);
	var messages = [];
	
	for (var i=0, n=fldErrors.length; i<n; i++) {
		messages.push(fldErrors[i].message);
	}
	
	return messages;
};


ErrorResponseUtil.getPageErrors = function(errorResponse) {
	var pageErrors = [], fldError, i, n;
	
	if (errorResponse && errorResponse.fieldBindingErrors) {

		for (i=0, n=errorResponse.fieldBindingErrors.length; i<n; i++) {

			fldError = errorResponse.fieldBindingErrors[i];
			
			// If bindingFieldMap is null, then it's a page level error
			if (fldError.bindingFieldMap === null) {
				pageErrors.push(fldError.error);
			}
		}
	}

	return pageErrors;
};

ErrorResponseUtil.getPageErrorMsgs = function(errorResponse) {
	var errorMsgs = [], pageErrors, i, j;
	
	pageErrors = ErrorResponseUtil.getPageErrors(errorResponse);
	for (i=0, j=pageErrors.length; i<j; i++) {
		errorMsgs.push(pageErrors[i].message);
	}
	
	return errorMsgs;
};






