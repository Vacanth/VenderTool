
/*************************************
Needs the scope.errorResponse to be defined in the controller.
scope.errorResponse is the json model of com.vendertool.sharedtypes.rnr.ErrorResponse.

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

angular.module('pageErrorModule', []).directive("pageError", function() {
	'use strict';
	
	var _css = {
			pageAlert:'alert',
			pageError:'alert-danger',
			errorResponse:'qry-errResp'
		}
	;
	
	return {
		link: function(scope, element, attrs) {
			var getErrorMsgs, wrap;
			
			getErrorMsgs = function() {
				
				var pageErrors = ErrorResponseUtil.getPageErrorMsgs(scope.errorResponse);
				var fieldErrors = ErrorResponseUtil.getAllFieldErrorMsgs(scope.errorResponse);
				pageErrors.push.apply(pageErrors, fieldErrors);
				scope.errorMsgs = pageErrors;

				//
				// For page errors, this directive is a child of an element with _css.pageAlert.
				// Need to change the display attribute of that element to show the page error.
				//
				if (scope.errorMsgs.length > 0) {
					wrap = element.closest('.' + _css.errorResponse);
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
		template:'<div class="' + _css.pageAlert + ' ' +  _css.pageError + ' ' + _css.errorResponse + '"><div ng-repeat="errorMsg in errorMsgs">{{errorMsg}}</div></div>'
		
	};
});


