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
profileApp.controller('AccountCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', function($scope, $http, $routeParams, $location, Data) {
	
	// Hide error or success messages that may be left over from previous view.
	hidePageMsg();
	
	$scope.accountOrig = Data.account;
	$scope.accountEdit = angular.copy(Data.account);
	$scope.errorResponse = Data.errorResponse;
	$scope.countryOptions = Data.countryOptions;
	$scope.changeEmailRequest = {};
	$scope.changePasswordRequest = {};

	$scope.saveAccount = function() {
		
		$http.post('profile/save', $scope.accountEdit).
			success(function (data, status, headers, config) {

				$scope.accountEdit = data.account;
				$scope.errorResponse = data.errorResponse;
				
				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.accountOrig = data.account;
					$scope.errorResponse = undefined;
					
					showPageSuccessMsg('profile');
				}
			}).
			error(function(data, status, headers, config) {
				
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
	hidePageMsg();
	
	$scope.changeEmailRequest = {};

	$http.get('profile/email').success(function(data) {
		$scope.changeEmailRequest = data.changeEmailRequest;
	});

	$scope.saveEmail = function() {
		
		$http.post('profile/email/save', $scope.changeEmailRequest).
			success(function (data, status, headers, config) {
				
				$scope.changeEmailRequest = data.changeEmailRequest;
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.changeEmailRequest.oldEmailId = data.changeEmailRequest.newEmail;
					$scope.changeEmailRequest.newEmail = '';
					$scope.changeEmailRequest.confirmEmail = '';
					$scope.errorResponse = undefined;

					showPageSuccessMsg('email');
				}
			}).
			error(function(data, status, headers, config) {
				
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
	hidePageMsg();
	
	$scope.changePasswordRequest = {};

  	$scope.savePassword = function() {
		
		$http.post('profile/password/save', $scope.changePasswordRequest).
			success(function (data, status, headers, config) {
				
				$scope.changePasswordRequest = data.changePasswordRequest;
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					showPageErrorMsg();
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
profileApp.controller('QuestionsCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', function($scope, $http, $routeParams, $location, Data) {
	
	// Hide error or success messages that may be left over from previous view.
	hidePageMsg();
	
	$scope.securityQuestionsResponse;
	$scope.questionList1;
	$scope.questionList2;
	$scope.question1;
	$scope.question2;
	$scope.answer1;
	$scope.answer2;


	$http.get('profile/questions').success(function(data) {
		
		$scope.securityQuestionsResponse = data;
		var r = $scope.securityQuestionsResponse;
		
		$scope.questionList1 = angular.copy(r.questions);
		$scope.questionList2 = angular.copy(r.questions);
		$scope.question1 = angular.copy(r.questionAnswers[0].questionId);
		$scope.question2 = angular.copy(r.questionAnswers[1].questionId);
		$scope.answer1 = angular.copy(r.questionAnswers[0].answer);
		$scope.answer2 = angular.copy(r.questionAnswers[1].answer);
	});

	//
	// Need to watch the questions, because we don't want users
	// selecting the same question more than once. When user selects
	// a question we need to remove that question from the other
	// list of options.
	//
	$scope.$watch("question1", function() {
		if ($scope.securityQuestionsResponse) {
			if ($scope.question1) {
				$scope.questionList2 = removeQuestion($scope.question1,  $scope.securityQuestionsResponse.questions);
			}
			else {
				$scope.questionList2 = angular.copy($scope.securityQuestionsResponse.questions);
			}
		}
	});

	$scope.$watch("question2", function() {
		if ($scope.securityQuestionsResponse) {
			if ($scope.question2) {
				 $scope.questionList1 = removeQuestion($scope.question2, $scope.securityQuestionsResponse.questions);
			}
			else {
				$scope.questionList1 = angular.copy($scope.securityQuestionsResponse.questions);
			}
		}
	});

	$scope.saveQuestionAnswers = function() {
		
		var questionsResp = {};
		
		questionsResp.questionAnswers = [{"questionId":$scope.question1, "answer":$scope.answer1}, {"questionId":$scope.question2, "answer":$scope.answer2}];
		
		console.log($scope.question1 + ":" + $scope.question2);
		
		$http.post('questions/save', questionsResp).
			success(function (data, status, headers, config) {

				var r = data.securityQuestionsResponse;
				$scope.errorResponse = data.errorResponse;
				
				$scope.question1 = r.questionAnswers[0].questionId;
				$scope.question2 = r.questionAnswers[1].questionId;
				$scope.answer1 = r.questionAnswers[0].answer;
				$scope.answer2 = r.questionAnswers[1].answer;
				
				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.errorResponse = undefined;

					// Show success message
					showPageSuccessMsg('questions');
				}
			}).
			error(function(data, status, headers, config) {
				alert('error');
			});
	};
	
	$scope.resetQuestionAnswers = function() {
  		var r = $scope.securityQuestionsResponse;
		
		$scope.question1 = angular.copy(r.questionAnswers[0].questionId);
		$scope.question2 = angular.copy(r.questionAnswers[1].questionId);
		$scope.answer1 = angular.copy(r.questionAnswers[0].answer);
		$scope.answer2 = angular.copy(r.questionAnswers[1].answer);
		
		// Clear errors
		$scope.errorResponse = undefined;
		hidePageMsg();
  	};
  	
  	var removeQuestion = function(questionId, origQuestionList) {
		// Start with full original list
		var questions = angular.copy(origQuestionList);
		
		if (questions) {
			for (var i=0, n=questions.length; i<n; i++) {
				if (questionId === questions[i].id) {
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

function showPageErrorMsg() {
	$('.alert-success').hide();
	$('.alert-danger').show();
};

function hidePageMsg() {
	$('.alert-success').hide();
	$('.alert-danger').hide();
};







/*******************
Using the array notation requires the listing of
all the function params as strings in same order.

profileApp.controller('ProfileCtrl', ['$scope', '$http', '$routeParams', '$location', 'Data', function($scope, $http, $routeParams, $location, Data) {
	
	$scope.accountOrig = Data.account;
	$scope.accountEdit = angular.copy(Data.account);
	$scope.errorResponse = Data.errorResponse;
	$scope.countryOptions = Data.countryOptions;
	$scope.changeEmailRequest = {};
	$scope.changePasswordRequest = {};

	if ($location.path() === '/email' || $location.path() === '/password') {
		
		$('.alert-danger').hide();
		
		$http.get('profile/email').success(function(data) {
			$scope.changeEmailRequest = data.changeEmailRequest;
		});
	}

	$scope.saveAccount = function() {
		
		$http.post('profile/save', $scope.accountEdit).
			success(function (data, status, headers, config) {

				$scope.accountEdit = data.account;
				$scope.errorResponse = data.errorResponse;
				
				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.accountOrig = data.account;
					$scope.errorResponse = undefined;
					
					// Take user to profile page
					//$location.path('/profile'); // path not hash
					showPageSuccessMsg('profile');
				}
			}).
			error(function(data, status, headers, config) {
				
			});
	};

	$scope.resetAccount = function() {
    	$scope.accountEdit = angular.copy($scope.accountOrig);
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();

    	//$location.path('/'); // path not hash
  	};

	$scope.saveEmail = function() {
		
		$http.post('profile/email/save', $scope.changeEmailRequest).
			success(function (data, status, headers, config) {
				
				$scope.changeEmailRequest = data.changeEmailRequest;
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.changeEmailRequest.oldEmailId = data.changeEmailRequest.newEmail;
					$scope.changeEmailRequest.newEmail = '';
					$scope.changeEmailRequest.confirmEmail = '';
					$scope.errorResponse = undefined;

					showPageSuccessMsg('email');
				}
			}).
			error(function(data, status, headers, config) {
				
			});
	};
	
	$scope.resetEmail = function() { 
    	$scope.changeEmailRequest.newEmail = '';
    	$scope.changeEmailRequest.confirmEmail = '';
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();
  	};
  	
  	$scope.savePassword = function() {
		
		$http.post('profile/password/save', $scope.changePasswordRequest).
			success(function (data, status, headers, config) {
				
				$scope.changePasswordRequest = data.changePasswordRequest;
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					// Show error message at the top of page
					showPageErrorMsg();
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
				
			});
	};
	
	$scope.resetPassword = function() { 
    	$scope.changePasswordRequest.newPassword = '';
    	$scope.changePasswordRequest.confirmPassword = '';
    	$scope.errorResponse = undefined;
    	$('.alert-danger').hide();
  	};

  	var showPageErrorMsg = function() {
  		$('.alert-success').hide();
  		$('.alert-danger').show();
  	};
  	
  	var hidePageMsg = function() {
  		$('.alert-success').hide();
  		$('.alert-danger').hide();
  	};
  	
	var showPageSuccessMsg = function(type) {
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
  	};

}]);
********************/




