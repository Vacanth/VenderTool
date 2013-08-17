'use strict';

// Declare app level module which depends on filters, and services
var accountApp = angular.module('accountApp', []);

accountApp.config(['$routeProvider', function($routeProvider) {

	$routeProvider.when('/contact',				{templateUrl: 'partials/?n=contact', controller: 'AccountCtrl'});
	$routeProvider.when('/contact/:change',		{templateUrl: 'partials/?n=contactChange', controller: 'AccountCtrl'});
	$routeProvider.when('/email/:change',		{templateUrl: 'partials/?n=emailChange', controller: 'AccountCtrl'});
	$routeProvider.when('/password/:change',	{templateUrl: 'partials/?n=passwordChange', controller: 'AccountCtrl'});
	$routeProvider.otherwise({redirectTo: '/contact'});
}]);