
/**********************************
Needs the scope.errorResponse to be defined in the controller.
scope.errorResponse is the json model of com.vendertool.sharedtypes.rnr.ErrorResponse.

This is a sample field level errorResponse:
 
"errorResponse":{
	"fieldBindingErrors":[
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
	"signedInEmail":null
}
*************************************/

angular.module('fieldErrorModule', []).directive("fieldError", function() {
	'use strict';
	
	var _css = {
			fieldError:'err-msg',
			fieldWrapError:'err',
			fieldWrap:'fldWrp'
		},
		_ns = {}
	;
	
	_ns.getErrorMap = function(className, fieldNames, errorResponse) {
		var i, j, fldName, fldErrors, errorMap = {};
		
		for (i=0, j=fieldNames.length; i<j; i++) {
			fldName =  fieldNames[i];
			fldErrors = ErrorResponseUtil.getFieldErrorMsgs(className, fldName, errorResponse);
			if (fldErrors.length > 0) {
				errorMap[fldName] = fldErrors;
			}
		}
		
		return errorMap;
	};

	
	return {
		link: function(scope, element, attrs) {
			var getErrorMsgs, wrap;
			
			getErrorMsgs = function() {
				//
				// Field errors
				//
				if (attrs.clss && attrs.field) {
					scope.errorMsgs = ErrorResponseUtil.getFieldErrorMsgs(attrs.clss, attrs.field, scope.errorResponse);
					
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
		    };
		    
			// If $scope.errorResponse in main app changes then run getErrorMsgs()
		    scope.$watch("errorResponse", getErrorMsgs);
		},
		scope: true,
		replace: true,
		template:'<div><div class="err-msg" ng-repeat="errorMsg in errorMsgs">{{errorMsg}}</div></div>'
		
	};
});


