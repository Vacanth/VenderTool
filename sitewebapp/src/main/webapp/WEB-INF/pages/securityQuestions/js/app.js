'use strict';

var securityQuestionsApp = angular.module('securityQuestionsApp', ['errorResponseModule']);

securityQuestionsApp.config(['$routeProvider', function($routeProvider) {
	
	// When there is something after the hashtag
	$routeProvider.when('/questions',	{templateUrl: 'questions/partial/questions',	controller: 'SecurityQuestionsCtrl'});
	$routeProvider.when('/success',		{templateUrl: 'questions/partial/success',	controller: 'SecurityQuestionsCtrl'});
	
	// Otherwise when no hashtag or hashtag path can't be found, add a hashtag
	$routeProvider.otherwise({redirectTo: '/questions'});
}]);