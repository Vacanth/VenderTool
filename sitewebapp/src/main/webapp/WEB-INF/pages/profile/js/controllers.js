'use strict';

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
profileApp.controller('NavCtrl', ['$scope', '$location',  function($scope, $location) {

  	/** Gets CSS 'curr' class to style current item in leftNav menu **/
  	$scope.getClass = function(path) {
  		if ($location.path() == path)  {
			return 'curr';
		} else {
			return '';
		}
	};
}]);

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
profileApp.controller('AccountCtrl', ['$rootScope', '$scope', '$http', '$routeParams', '$location', 'Data', 'Content', function($rootScope, $scope, $http, $routeParams, $location, Data, Content) {
	
	// Hide error or success messages that may be left over from previous view.
	//hidePageMsg();
	
	if ($rootScope.accountUpdated) {
		$scope.accountOrig = $rootScope.accountUpdated;
		$scope.accountEdit = angular.copy($rootScope.accountUpdated);
	}
	else {
		$scope.accountOrig = Data.account;
		$scope.accountEdit = angular.copy(Data.account);
	}
	
	$scope.errorResponse = Data.errorResponse;
	$scope.countryOptions = Data.countryOptions;

	$scope.saveAccount = function() {
		
		$http.post('profile/save', $scope.accountEdit).
			success(function (data, status, headers, config) {

				$scope.errorResponse = data.errorResponse;
				
				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					handlePageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.accountOrig = data.account;
					$scope.accountEdit = angular.copy(data.account);
					$scope.errorResponse = undefined;
					
					$rootScope.accountUpdated = angular.copy(data.account);
					
					showPageSuccessMsg('profile');
				}
			}).
			error(function(data, status, headers, config) {
				handlePageErrorMsg(status);
			});
	};

	$scope.resetAccount = function() {
    	$scope.accountEdit = angular.copy($scope.accountOrig);
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();
    	
    	/** TODO: Should we change path to make another request to get the account values from server?? **/
    	//$location.path('/'); // path not hash
  	};
}]);

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
profileApp.controller('EmailCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', function($scope, $http, $routeParams, $location, Data) {
	
	// Hide error or success messages that may be left over from previous view.
	//hidePageMsg();
	
	$scope.changeEmailRequest = {};
	$scope.changeEmailResponse = {};

	$http.get('profile/email').success(function(data) {
		$scope.changeEmailResponse = data.changeEmailResponse;
	});

	$scope.saveEmail = function() {
		
		$http.post('profile/email/save', $scope.changeEmailRequest).
			success(function (data, status, headers, config) {

				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					handlePageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.changeEmailResponse = data.changeEmailResponse;
					
					$scope.changeEmailRequest.newEmail = '';
					$scope.changeEmailRequest.confirmEmail = '';
					$scope.errorResponse = undefined;
					
					showPageSuccessMsg('email');
				}
			}).
			error(function(data, status, headers, config) {
				handlePageErrorMsg(status);
			});
	};
	
	$scope.resetEmail = function() { 
    	$scope.changeEmailRequest.newEmail = '';
    	$scope.changeEmailRequest.confirmEmail = '';
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();
  	};
}]);

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
profileApp.controller('PasswordCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', function($scope, $http, $routeParams, $location, Data) {
	
	// Hide error or success messages that may be left over from previous view.
	//hidePageMsg();
	
	$scope.changePasswordRequest = {};

  	$scope.savePassword = function() {
		
		$http.post('profile/password/save', $scope.changePasswordRequest).
			success(function (data, status, headers, config) {
				
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					handlePageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.changePasswordRequest.newPassword = '';
					$scope.changePasswordRequest.confirmPassword = '';
					$scope.errorResponse = undefined;

					showPageSuccessMsg('password');
				}
			}).
			error(function(data, status, headers, config) {
				handlePageErrorMsg(status);
			});
	};
	
	$scope.resetPassword = function() { 
    	$scope.changePasswordRequest.newPassword = '';
    	$scope.changePasswordRequest.confirmPassword = '';
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();
  	};
}]);

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
profileApp.controller('QuestionsCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', 'Content', function($scope, $http, $routeParams, $location, Data, Content) {
	
	// Hide error or success messages that may be left over from previous view.
	//hidePageMsg();
	
	$scope.securityQuestionsResponse;
	$scope.questionList1;
	$scope.questionList2;
	$scope.question1;
	$scope.question2;
	$scope.answer1;
	$scope.answer2;

	//
	// securityQuestions model:
	//
	// "securityQuestions":[
	//		{"questionCode":"FIRST_OWNED_PET", "questionDisplayName":"What is the name of your first owned pet?"},
	//		{"questionCode":"FAVORITE_SCHOOL_TEACHER","questionDisplayName":"Who is your favorite school teacher?"}
	//	]
	//
	$http.get('profile/questions').success(function(data) {
		
		$scope.securityQuestions = data.securityQuestions;
		
		$scope.questionList1 = angular.copy($scope.securityQuestions);
		$scope.questionList2 = angular.copy($scope.securityQuestions);
		
		$scope.question1 = angular.copy($scope.questionList1[0].questionId);
		$scope.question2 = angular.copy($scope.questionList1[1].questionId);
		
		//$scope.answer1 = angular.copy(r.questionAnswers[0].answer);
		//$scope.answer2 = angular.copy(r.questionAnswers[1].answer);
	});

	//
	// Need to watch the questions, because we don't want users
	// selecting the same question more than once. When user selects
	// a question we need to remove that question from the other
	// list of options.
	//
	$scope.$watch("question1", function() {
		if ($scope.securityQuestions) {
			if ($scope.question1) {
				$scope.questionList2 = removeQuestion($scope.question1,  $scope.securityQuestions);
			}
			else {
				$scope.questionList2 = angular.copy($scope.securityQuestions);
			}
		}
	});

	$scope.$watch("question2", function() {
		if ($scope.securityQuestions) {
			if ($scope.question2) {
				 $scope.questionList1 = removeQuestion($scope.question2, $scope.securityQuestions);
			}
			else {
				$scope.questionList1 = angular.copy($scope.securityQuestions);
			}
		}
	});
	
	//
	// UpdateAccountSecurityQuestionsRequest model:
	//
	// "questions":[{
	//		"id":"000", 
	//		"questionCode":"000",
	//		"answer":"Hello world",
	//		"createdDate":"000"
	// }]
	//
	$scope.saveQuestionAnswers = function() {
		
		var questionsReq = {};
		questionsReq.questions = [{"questionCode":$scope.question1, "answer":$scope.answer1}, {"questionCode":$scope.question2, "answer":$scope.answer2}];
		
		//console.log($scope.question1 + ":" + $scope.question2);
		
		$http.post('profile/questions/save', questionsReq).
			success(function (data, status, headers, config) {
				
				$scope.errorResponse = data.errorResponse;
				
				// data.updated will be true or false. Not sure if we need this.
				//alert(data.updated);

				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					handlePageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.errorResponse = undefined;

					// Show success message
					showPageSuccessMsg('questions');
				}
			}).
			error(function(data, status, headers, config) {
				handlePageErrorMsg(status);
			});
	};
	
	$scope.resetQuestionAnswers = function() {

		$scope.question1 = undefined;
		$scope.question2 = undefined;
		
		$scope.answer1 = '';
		$scope.answer2 = '';
		
		// Clear errors
		$scope.errorResponse = undefined;
		hidePageMsg();
  	};
  	
  	var removeQuestion = function(questionId, origQuestionList) {
		// Start with full original list
		var questions = angular.copy(origQuestionList);
		
		if (questions) {
			for (var i=0, n=questions.length; i<n; i++) {
				if (questionId === questions[i].questionCode) {
					questions.splice(i, 1);
					return questions;
				}
			}
		}
	};

}]);

function showPageSuccessMsg(type) {
	
	$('.alert-danger').hide();
	
	if (type === 'profile') {
		$('.alert-success.profile').show().delay(1500).fadeOut(300);
	}
	else if (type === 'email') {
		$('.alert-success.email').show().delay(1500).fadeOut(300);
	}
	else if (type === 'password') {
		$('.alert-success.password').show().delay(1500).fadeOut(300);
	}
	else if (type === 'questions') {
		$('.alert-success.questions').show().delay(1500).fadeOut(300);
	}
};

//
// Errors from the errorResponse json are shown by the angular page-error directive
// and are not handled here.
//
// But request errors are handled here. The request error message is passed in and
// is inserted into the 'alert-danger' div.
//
// Also need to hide success messages if any.
//
function handlePageErrorMsg(statusCode) {
	$('.alert-success').hide();
	
	if (message) {
		$('.pg-msg .qry-httpError').html(Content.httpError + ' ' + statusCode).show();
	}
};



function hidePageMsg() {
	$('.alert-success').hide();
	$('.alert-danger').hide();
};











