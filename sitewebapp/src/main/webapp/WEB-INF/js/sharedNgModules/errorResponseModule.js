
/**
 * Needs the scope.errorResponse to be defined in the controller.
 * scope.errorResponse is the json model of com.vendertool.sharedtypes.rnr.ErrorResponse.
 */
angular.module('errorResponseModule', []).directive("errorResponse", function() {
	'use strict';
	
	var _css = {
		pageAlert:'alert',
		pageError:'alert-danger',
		fieldError:'err-msg',
		fieldWrapError:'err',
		fieldWrap:'fldWrp'
	};
	
	return {
		link: function(scope, element, attrs) {
			var getErrorMsgs, wrap;
			
			getErrorMsgs = function() {

				//
				// Field errors
				//
				if (attrs.clss && attrs.field) { 
					alert('aaaaaaa');
					
					alert(attrs.clss);
					
					alert(attrs.field);
					
					
					scope.errorMsgs = ErrorUtil.getFieldErrorMsgs(attrs.clss, attrs.field, scope.errorResponse);
					
					//
					// For field errors, this directive is a child of an element with _css.fieldWrap.
					// Adding _css.fieldWrapError to wrapping element will display the field errors
					//
					if (scope.errorMsgs.length > 0) {
						wrap = element.closest('.' + _css.fieldWrap);
						if (wrap) {
							wrap.addClass(_css.fieldWrapError);
						}
					}
					else {
						// Remove error style to fieldWrapper
						if (wrap) {
							wrap.removeClass(_css.fieldWrapError);
						}
					}
				}
				//
				// Page level errors
				//
				else {  alert('cccccc');
					scope.errorMsgs = ErrorUtil.getPageErrorMsgs(scope.errorResponse);
					
					alert(scope.errorMsgs[0]);
					
					//
					// For page errors, this directive is a child of an element with _css.pageAlert.
					// Need to change the display attribute of that element to show the page error.
					//
					if (scope.errorMsgs.length > 0) {
						wrap = element.closest('.' + _css.pageAlert);
						if (wrap) {
							wrap.css('display', 'block');
						}
					}
					else {
						// Remove error style to wrap
						if (wrap) {
							wrap.css('display', 'none');
						}
					}
					
				}
		    };
		    
			// If $scope.errorResponse in main app changes then run getErrorMsgs()
		    scope.$watch("errorResponse", getErrorMsgs);
		},
		scope: true,
		replace: true,
		template:'<div><div class="err-msg" ng-repeat="errorMsg in errorMsgs">{{errorMsg}}</div></div>'
		
	};
});

/************************************
 * Sample page level errorResponse
 *
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
		}
	],
	"status":null,
	"signedInEmail":null}
}
*************************************/
var ErrorUtil = {
	
	getPageErrors: function(errorResponse) {
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
	},
	
	getPageErrorMsgs: function(errorResponse) {
		var errorMsgs = [], pageErrors, i, j;
		
		pageErrors = ErrorUtil.getPageErrors(errorResponse);
		for (i=0, j=pageErrors.length; i<j; i++) {
			errorMsgs.push(pageErrors[i].message);
		}
		
		return errorMsgs;
	},
		
	getFieldErrors: function(className, fieldName, errorResponse) {
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
	},
	
	getFieldErrorMsgs: function(className, fieldName, errorResponse) {
		var i, j, fldErrors, errorMsgs = [];
		
		fldErrors = ErrorUtil.getFieldErrors(className, fieldName, errorResponse);
		for (i=0, j=fldErrors.length; i<j; i++) {
			errorMsgs.push(fldErrors[i].error.message);
		}
		
		return errorMsgs;
	},
	
	getErrorMap: function(className, fieldNames, errorResponse) {
		var i, j, fldName, fldErrors, errorMap = {};
		
		for (i=0, j=fieldNames.length; i<j; i++) {
			fldName =  fieldNames[i];
			fldErrors = ErrorUtil.getFieldErrorMsgs(className, fldName, errorResponse);
			if (fldErrors.length > 0) {
				errorMap[fldName] = fldErrors;
			}
		}
		
		return errorMap;
	}
};