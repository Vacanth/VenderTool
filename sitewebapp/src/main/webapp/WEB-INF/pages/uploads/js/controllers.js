'use strict';


/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
uploadsApp.controller('UploadsCtrl', ['Data', '$scope', '$http', '$routeParams', '$location', function(Data, $scope, $http, $routeParams, $location) {
	
	var _ns = {};
	
	
	$scope.uploadsRes = angular.copy(Data.uploadsResponse);
	$scope.errorResponse = Data.errorResponse;
	


	
	//
	// Assign scope to window so that the popup can get
	// a handle on the refreshFileList() function.
	//
	window.$windowScope = $scope;
	
	//
	// This is called from the popup when uploads are done.
	//
	$scope.refreshFileList = function() {

		$.ajax({
			type: 'get',
			url: 'uploadsResponse',
			data: {},
	        dataType: 'json',
			success: function(data, textStatus, jqXHR) {
				
				$scope.uploadsRes = angular.copy(data);
				$scope.$apply();
				
				alert('success refreshing file list');
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				
				alert('errer refreshing file list:' + errorThrown);
			}
		});
			
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
	
	$scope.downloadFiles = function() {
		
		var fileIds = _ns.gatherDownloadFileIds();
		
		for (var i=0, n=fileIds.length; i<n; i++) {
			console.log(fileIds[i]);
		}
		
	};
	
	
	_ns.gatherDownloadFileIds = function() {
		var jobs = $scope.uploadsRes.jobs;
		var fileIds = [];
		
		for (var i=0, n=jobs.length; i<n; i++) {
			var uploaded = jobs[i].uploadedFiles;
			var processed = jobs[i].processedFiles;
			
			for (var j=0, m=uploaded.length; j<m; j++) {
				if (uploaded[j].checked) {
					fileIds.push(uploaded[j].fileId);
				}
				if (processed[j].checked) {
					fileIds.push(processed[j].fileId);
				}
			}
		}
		
		return fileIds;
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
        	 
        	 //alert($routeParams.pageNum);
        	 
        	 
             //$scope.ticketSelected($routeParams.ticketId);
         }
     };

     // fire on controller loaded
     init();
}]);




