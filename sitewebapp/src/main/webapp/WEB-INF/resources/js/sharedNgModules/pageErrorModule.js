
/*************************************
Needs the scope.errorResponse to be defined in the controller.
scope.errorResponse is the json model of com.vendertool.sharedtypes.rnr.ErrorResponse.

This is a sample page level errorResponse:

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

angular.module('pageErrorModule', []).directive("pageError", function() {
	'use strict';
	
	var _css = {
			pageAlert:'alert',
			pageError:'alert-danger'
		},
		_ns = {}
	;
	
	_ns.getPageErrors = function(errorResponse) {
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
	
	_ns.getPageErrorMsgs = function(errorResponse) {
		var errorMsgs = [], pageErrors, i, j;
		
		pageErrors = _ns.getPageErrors(errorResponse);
		for (i=0, j=pageErrors.length; i<j; i++) {
			errorMsgs.push(pageErrors[i].message);
		}
		
		return errorMsgs;
	};
	
	return {
		link: function(scope, element, attrs) {
			var getErrorMsgs, wrap;
			
			getErrorMsgs = function() {

				scope.errorMsgs = _ns.getPageErrorMsgs(scope.errorResponse);

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
		    };
		    
			// If $scope.errorResponse in main app changes then run getErrorMsgs()
		    scope.$watch("errorResponse", getErrorMsgs);
		},
		scope: true,
		replace: true,
		template:'<div class="alert alert-danger"><div ng-repeat="errorMsg in errorMsgs">{{errorMsg}}</div></div>'
		
	};
});


