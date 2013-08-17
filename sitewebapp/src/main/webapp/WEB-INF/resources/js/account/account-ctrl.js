
/**
var myApp = angular.module('myApp', []);

myApp.config([
  	'$routeProvider', 
  	function($routeProvider) {
  		$routeProvider.
  			when('/:edit', {
  				templateUrl: 'partials/contact-edit.html',   
  				controller: 'accountCtrl'
  			});
  	}
  ]);



myApp.controller("accountCtrl", function($scope, $http, $routeParams, Data) {

	$scope.account = Data;
	
	
	alert('sss:' + $routeParams.edit);
	
	$scope.saveContactDetails = function() {
		
		$http.post('account/save', $scope.account)
			.success (function (account) {
				$scope.account = account;
			}
		);
		
	};
	
	$scope.$on(
	   "$routeChangeSuccess",
	   function($currentRoute, $previousRoute){
	        // Update the rendering.
	        //render();
		   
		   
		   
		   if ($currentRoute == '/edit') {
			   alert('it edit');
		   }
		  
	    }
	);
	
	
	$scope.showAccountForm = function() {
		alert('sdfjlkj');
	};
});


**/

myApp.directive('editContactDetails', function() {
	return function(scope, el, attrs) {
		
		el.bind('click', function() {
			$('#edit-info').show();
			$('#edit-info').animate({left: "0"}, 300);
			
		});
	};
});

myApp.directive('editEmail', function() {
	return function(scope, el, attrs) {
		
		el.bind('click', function() {
			$('#edit-email').show();
			$('#edit-email').animate({left: "0"}, 300);
			
		});
	};
});
