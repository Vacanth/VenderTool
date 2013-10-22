'use strict';

var securityQuestionsApp = angular.module('securityQuestionsApp', ['pageErrorModule', 'fieldErrorModule']);

securityQuestionsApp.config(['$routeProvider', function($routeProvider) {
	
	// When there is something after the hashtag
	$routeProvider.when('/questions',	{templateUrl: 'questions/partial/questions',	controller: 'SecurityQuestionsCtrl'});
	$routeProvider.when('/success',		{templateUrl: 'questions/partial/success',	controller: 'SecurityQuestionsCtrl'});
	
	// Otherwise when no hashtag or hashtag path can't be found, add a hashtag
	$routeProvider.otherwise({redirectTo: '/questions'});
}]);


/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
securityQuestionsApp.controller('SecurityQuestionsCtrl', ['$scope', '$http', '$location', 'Data', 'Content', function($scope, $http, $location, Data, Content) {
	
	$scope.securityQuestions = Data.secquesResponse.securityQuestions;
	
	$scope.questionList1 = angular.copy($scope.securityQuestions);
	$scope.questionList2 = angular.copy($scope.securityQuestions);

	$scope.question1;
	$scope.question2;
	$scope.answer1;
	$scope.answer2;
	
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
	$scope.save = function() {
		
		//PageUtil.showSpinner();
		
		var questionsReq = {};
		questionsReq.questions = [{"question":{"questionCode":$scope.question1}, "answer":$scope.answer1}, {"question":{"questionCode":$scope.question2}, "answer":$scope.answer2}];
		questionsReq.password = $scope.password;
		
		//console.log($scope.question1 + ":" + $scope.question2);
		
		$http.post('questions/save', questionsReq).
			success(function (data, status, headers, config) {
				
				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse && $scope.errorResponse.fieldBindingErrors.length > 0) {
					handlePageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.errorResponse = undefined;
					$scope.resetQuestionAnswers();

					// Take user to success page
					$location.path('/success');
				}
				
				$scope.password = ''; // Clear password field;
				
				//PageUtil.hideSpinner();
			}).
			error(function(data, status, headers, config) {
				handlePageErrorMsg(status, Content);
				
				$scope.password = ''; // Clear password field;
				
				//PageUtil.hideSpinner();
			});
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

//
//Errors from the errorResponse json are shown by the angular page-error directive
//and are not handled here.
//
//But request errors are handled here. The request error message is passed in and
//is inserted into the 'alert-danger' div.
//
//Also need to hide success messages if any.
//
function handlePageErrorMsg(statusCode, Content) {
	$('.alert-success').hide();
	
	if (statusCode && Content) {
		$('.pg-msg .qry-httpError').html(Content.httpError + ' ' + statusCode).show();
	}
};



