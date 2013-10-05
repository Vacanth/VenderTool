'use strict';


/*******************
Using the array notation requires the listing of
all the function params as strings in same order.
********************/
uploadsApp.controller('UploadsCtrl', ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	var _ns = {},
		_css = {
			uploadedCheckbox:'q-uploadedCbx',
			processedCheckbox:'q-processedCbx',
			uploadedFolderCheckbox:'q-uploadedFldrCbx',
			processedFolderCheckbox:'q-processedFldrCbx',
			subRow:'q-subRow',
			folderIcon:'q-folderIcon',
			open:'open',
			folderRow:'q-folderRow'
		}
	;
	
	
	//$scope.uploadsRes = angular.copy(Data.uploadsResponse);
	//$scope.errorResponse = Data.errorResponse;
	
	//
	// Creating filesInFolder array to help render subRows for jobs
	// with more than one file (jobs that have folder icons).
	//
	// Jobs with only one file will not have a filesInFolder array
	// created. This is to prevent a subRow from being rendered.
	// 
	// Only needed because can't figure out another way to PREVENT
	// rendering a subRow if job has only one file.
	//
	/*
	$($scope.uploadsRes.jobs).each(function(index, job) {
		if (job.uploadedFiles.length > 1) {
			job.filesInFolder = [];
			
			$(job.uploadedFiles).each(function(index, file) {
				job.filesInFolder.push(file);
			});
		}
	});*/

	
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
	
	$scope.isNullOrEmpty = function(someString) {
		if (someString) {
			someString = someString.replace(/ /g, '+');
			if (someString.length <= 0) {
				return true;
			}
		}
		return false;
	};
	
	$scope.toggleRows = function($event) { 
		var target, tbody, subRows, folderIcons, isHide;
		
		target = $($event.target);
		
		if (target.prop('type') !== 'checkbox' && $event.target.nodeName !== 'LABEL') {
			if (target.hasClass(_css.folderRow) || target.closest('.' + _css.folderRow).length > 0) {
				
				tbody = target.closest('tbody');
				subRows = tbody.find('.' + _css.subRow);
				folderIcons = tbody.find('.' + _css.folderIcon);
				isHide = false;
			
				// Toggle this hidden rows
				subRows.each(function(index, el) {
					if ($(el).is(':visible')) {
						$(el).hide();
						isHide = true;
					}
					else {
						$(el).show();
						isHide = false;
					}
				});
	
				// Toggle the folder icons
				if (isHide) {
					folderIcons.each(function(index, el) {
						$(el).removeClass(_css.open);
					});
				}
				else {
					folderIcons.each(function(index, el) {
						$(el).addClass(_css.open);
					});
				}
			}
		}
	};

	$scope.handleCheckboxCtrls = function($event) {
		var input, files, folder, fileClass, folderClass, allChecked;

		if ($event.target.nodeName === 'INPUT' && $event.target.type === 'checkbox') {
			input = $($event.target);
			
			//
			// Find the checked folder
			//
			if (input.hasClass(_css.uploadedFolderCheckbox)) {
				fileClass = _css.uploadedCheckbox;
			}
			else if (input.hasClass(_css.processedFolderCheckbox)) {
				fileClass = _css.processedCheckbox;
			}
			//
			// Check/un-check the uploaded files for this folder
			//
			if (fileClass) {
				files = input.closest('tbody').find('.' + fileClass);
				if (files.length > 0) {
					$(files).each(function(index, el) {
						$(el).prop('checked',  input.prop('checked'));
					});
				}
			}
			
			//
			// Check/un-check the folder via the files
			//
			if (input.hasClass(_css.uploadedCheckbox)) {
				folderClass = _css.uploadedFolderCheckbox;
				fileClass = _css.uploadedCheckbox;
			}
			else if (input.hasClass(_css.processedCheckbox)) {
				folderClass = _css.processedFolderCheckbox;
				fileClass = _css.processedCheckbox;
			}
			//
			// Check/un-check the uploaded files for this folder
			//
			if (folderClass) {
				folder = input.closest('tbody').find('.' + folderClass);
				files = input.closest('tbody').find('.' + fileClass);
				
				allChecked = true;
				$(files).each(function(index, el) {
					if ($(el).prop('checked') === false) {
						allChecked = false;
					}
				});
				
				if (folder.length > 0) {
					folder.prop('checked',  allChecked);
				}
			}
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




