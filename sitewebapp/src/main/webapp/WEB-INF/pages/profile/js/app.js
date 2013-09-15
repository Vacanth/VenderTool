'use strict';

// Declare app level module which depends on filters, and services
var profileApp = angular.module('profileApp', ['errorResponseModule']);

profileApp.config(['$routeProvider', function($routeProvider) {
	
	// When there is something after the hashtag
	$routeProvider.when('/info',		{templateUrl: 'profile/partial/account',	controller: 'AccountCtrl'});
	$routeProvider.when('/email',		{templateUrl: 'profile/partial/email',		controller: 'EmailCtrl'});
	$routeProvider.when('/password',	{templateUrl: 'profile/partial/password',	controller: 'PasswordCtrl'});
	$routeProvider.when('/questions',	{templateUrl: 'profile/partial/questions',	controller: 'QuestionsCtrl'});
	
	// Otherwise when no hashtag or hashtag path can't be found, add a hashtag
	$routeProvider.otherwise({redirectTo: '/info'});
}]);