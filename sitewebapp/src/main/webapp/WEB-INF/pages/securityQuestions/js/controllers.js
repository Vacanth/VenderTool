

/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
securityQuestionsApp.controller('SecurityQuestionsCtrl', ['$scope', '$http', '$location', 'Data', function($scope, $http, $location, Data) {
	
	$scope.questionList1 = angular.copy(Data.questionList);
	$scope.questionList2 = angular.copy(Data.questionList);
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
		if ($scope.question1) {
			$scope.questionList2 = removeQuestion($scope.question1, Data.questionList);
		}
		else {
			$scope.questionList2 = angular.copy(Data.questionList);
		}
	});

	$scope.$watch("question2", function() {
		if ($scope.question2) {
			 $scope.questionList1 = removeQuestion($scope.question2, Data.questionList);
		}
		else {
			$scope.questionList1 = angular.copy(Data.questionList);
		}
	});
	
	$scope.save = function() {
		
		hidePageErrorMsg();
		
		var questionsResp = {};
		
		questionsResp.questionAnswers = [{"questionId":$scope.question1, "answer":$scope.answer1}, {"questionId":$scope.question2, "answer":$scope.answer2}];
		
		console.log($scope.question1 + ":" + $scope.question2);
		
		$http.post('questions/save', questionsResp).
			success(function (data, status, headers, config) {
				
				console.log('all good');

				$scope.errorResponse = data.errorResponse;

				if ($scope.errorResponse.fieldBindingErrors.length > 0) {
					showPageErrorMsg();
				}
				else {
					// Only update this if no errors
					$scope.errorResponse = undefined;
					
					// Take user to success page
					$location.path('/success');
					
					showPageSuccessMsg();
				}
			}).
			error(function(data, status, headers, config) {
				
				
				console.log('error');
			});
	};
	
	var removeQuestion = function(questionId, origQuestionList) {
		// Start with full original list
		var questions = angular.copy(origQuestionList);
		
		for (var i=0, n=questions.length; i<n; i++) {
			if (questionId === questions[i].id) {
				questions.splice(i, 1);
				return questions;
			}
		}
	};
	
	var showPageErrorMsg = function() {
  		$('.alert-danger').show();
  	};
  	
  	var showPageSuccessMsg = function() {
  		$('.alert-success').show();
  	};
  	
  	var hidePageErrorMsg = function() {
  		$('.alert-danger').hide();
  	};
	
	
	
	
}]);