'use strict';


/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
uploadsApp.controller('UploadsCtrl', ['Data', '$scope', '$http', '$routeParams', '$location', function(Data, $scope, $http, $routeParams, $location) {
	
	
	
	$scope.uploadsRes = angular.copy(Data.uploadsResponse);
	$scope.errorResponse = Data.errorResponse;
	


	
	//
	// Popup releated
	//
	window.$windowScope = $scope;
	
	$scope.updateFiles = function(files) {	
		//alert(files[0] + 'xxx');
	};
	
	$scope.openPopup = function() {
		
		var left,
			top,
			width = 575,
			height = 325,
			props
		;
	
		wLeft = window.screenLeft ? window.screenLeft : window.screenX;
		wTop = window.screenTop ? window.screenTop : window.screenY;
		
		left = wLeft + (window.innerWidth/2) - (width/2);
		top = wTop + (window.innerHeight/2) - (height/2);
		
		// Prevent top of popup from being above parent window
		if (top < wTop) {
			top = wTop;
		}
		
		props = 'left=' + left + ',top=' + top + ',width=' + width + ',height=' + height + ',toolbar=0,location=0,status=0,menubar=0,resizable=1';
		window.open('../uploader', 'popup', props);
	};
	
	$scope.makeArray = function(num) {
	    return new Array(num);   
	};
	
	$scope.getNumOfPages = function() {
		var num, p = $scope.uploadsRes.paginationOutput;
		num = parseInt(p.totalResults/p.entriesPerPage);
		if ( p.totalResults % p.entriesPerPage !== 0) {
			num++;
		}
		
	    return num;
	};
	
	$scope.getPreviousPage = function() {
		var p = $scope.uploadsRes.paginationOutput;
		
		if (p.currentPage === 1) {
			return null;
		}
		else {
			return p.currentPage - 1;
		}
	};
	
	$scope.getNextPage = function() {
		var p = $scope.uploadsRes.paginationOutput;
		
		if (p.currentPage === $scope.getNumOfPages()) {
			return null;
		}
		else {
			return p.currentPage + 1;
		}
	};
	
	//alert('hello' + URL.uploadsUrl);
	
	/**
	$scope.accountOrig = Data.account;
	$scope.accountEdit = angular.copy(Data.account);
	$scope.errorResponse = Data.errorResponse;
	$scope.countryOptions = Data.countryOptions;
	$scope.changeEmailRequest = {};
	$scope.changePasswordRequest = {};
	**/
	/** Do something when param is 'edit'**/
	//if ($routeParams.edit) {
		//$('#info').removeClass('readonly');
		
		// remove page messages
		//$scope.$parent.success = false;
		//$scope.$parent.error = false;
		
		//$('.alert-success').hide();
		//$('.alert-danger').hide();
	//}
	
	/** Do something when page is 'email'**/


}]);

uploadsApp.controller('PaginationCtrl', ['$scope', '$routeParams', '$http', '$location', function($scope, $routeParams, $http, $location) {
	
	 var init = function () {
         if ($routeParams.pageNum) {
        	 
        	 alert($routeParams.pageNum);
        	 
        	 
             //$scope.ticketSelected($routeParams.ticketId);
         }
     };

     // fire on controller loaded
     init();
}]);




