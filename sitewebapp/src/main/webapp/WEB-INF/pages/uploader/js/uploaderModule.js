angular.module('uploaderModule', []).directive("uploader", function() {
	
	_css = {
		'fileInput':'qry-upldr-inp',
		'uploadBtn':'qry-upldr-upBtn'
	};

	return {
		link: function(scope, element, attrs) {

			var uploadUrl = attrs.uploadUrl,
				allDoneUrl = attrs.allDoneUrl,
				fileInput,
				uploadBtn,
				
				/** functions **/
				initScopeVars,
				addFiles,
				addMoreFiles,
				uploadFiles,
				send,
				clearFileInput,
				findDupes,
				makeId,
				getMapSize
			;

			//
			// Find the elements
			//
			fileInput = $(element).find('.' + _css.fileInput);
			uploadBtn = $(element).find('.' + _css.uploadBtn);
			
			initScopeVars = function() {
				scope.percentDone = 0;
				scope.fileWrappers = [];
				scope.dupeNames = [];
				scope.errors = undefined; // map of fileId to fileName
				scope.allUploadsSuccessful = false;
				scope.retrySendUploadsDoneMessage = false;
				scope.groupId = makeGroupId();
				scope.uploadProgress = {}; // map of fileId to {'loaded':999, 'total':999}
				scope.uploadInProgress = false;
			};
			
			//
			// If user tries to close popup, show warning if there
			// are files in the list that have not been uploaded.
			//
			$(window).bind('beforeunload', function() {
				if (!scope.allUploadsSuccessful || scope.retrySendUploadsDoneMessage) {
					
					var notUploadedCount = 0;
					
					for (var i=0, n=scope.fileWrappers.length; i<n; i++) {
						if (!scope.fileWrappers[i].status || scope.fileWrappers[i].status!='success') {
							notUploadedCount++;
						}
					}
					
					if (scope.retrySendUploadsDoneMessage) {
						return 'Upload was not completed.';
					}
					else if (notUploadedCount == 1) {
						return 'A file has not been uploaded.';
					}
					else if (notUploadedCount > 1) {
						return 'Some files have not been uploaded.';
					}
				}
			});

			initialAdd = function() {

				initScopeVars();
				addFiles(this.files);
				scope.$apply();
				
				// Clear and rebind fileInput to add additional files
				clearFileInput(_css.fileInput, addMoreFiles);
			};
			
			addFiles = function(files, dupeNames) {
				var filesToAdd = [], fileWrap, dupes;
				
				// Remove files that are dupes
				if (dupeNames) {
					for (var i=0, n=files.length; i<n; i++) {
						if (dupeNames.indexOf(files[i].name) === -1) {
							filesToAdd.push(files[i]);
						}
					}
				}
				else {
					filesToAdd = files;
				}
				
				for (var i=0, n=filesToAdd.length; i<n; i++) {
					fileWrap = {};
					fileWrap.id = makeId();
					fileWrap.file = filesToAdd[i];
					fileWrap.kbSize = parseInt(filesToAdd[i].size/1024);
					scope.fileWrappers.push(fileWrap);
				}
			};
			
			addMoreFiles = function() {
				
				var dupeNames;
				scope.dupeNames = [];
				
				dupeNames = findDupes(scope.fileWrappers, this.files);
				
				if (dupeNames && dupeNames.length > 0) {
					scope.dupeNames = dupeNames;
					addFiles(this.files, dupeNames);
				}
				else {
					addFiles(this.files);
				}
				
				updateUploadStatus();
				clearFileInput(_css.fileInput, addMoreFiles);
			};
			
			uploadFiles = function() {
				
				var fileWrap, formData;
				
				if (scope.retrySendUploadsDoneMessage) {
					handleUploadsDone();
				}
				else {
					// Reset the progress bar and errors
					scope.errors = undefined;
					scope.dupeNames = [];
					scope.percentDone = 2; // Give progress bar a head start. Otherwise there's no movement for a second or two.
					scope.uploadProgress = {};
					scope.uploadCount = 0;
				
					scope.uploadInProgress = true;
					scope.$apply(); // This disables the upload button right away.
					
					if (window.FormData){
						for (var i=0, n=scope.fileWrappers.length; i<n; i++) {
							fileWrap = scope.fileWrappers[i];
							
							// Only upload if not already successfully uploaded.
							// We allow user to hit uploadBtn again to upload any
							// files that errored in a previous upload attempt.
							if (!fileWrap.status || fileWrap.status !== 'success') {
								
								// Setup for progress bar
								scope.uploadProgress[fileWrap.id] = {'loaded':0, 'total':0};
								
								formData = new FormData();
								formData.append(scope.groupId, fileWrap.file);
								send(formData, fileWrap);
							}
						}
					}
				}
			};
			
			send = function(formData, fileWrap) {
				
				var progressPercentToWaitAt = 95;
				
				$.ajax({
					type: 'post',
					url: uploadUrl,
					data: formData,
					cache: false,
			        contentType: false,
			        processData: false,
					success: function(data, textStatus, jqXHR) {						
						
						fileWrap.status = 'success';
						scope.uploadCount++;
						
						if (scope.uploadCount === getMapSize(scope.uploadProgress)) {
							
							scope.uploadInProgress = false;
							updateUploadStatus();
							
							// This moved the progress bar to 100%
							scope.percentDone = 100;
							scope.$apply();
							
							// Pause for 2 seconds after progress bar hits 100%
							setTimeout( function(){
								//
								// If all uploads are successful, send message
								// to backend that upload is complete.
								//
								if (scope.allUploadsSuccessful) {
									
									handleUploadsDone();
								}
								
							}, 2000);
						}
					},
					error: function(jqXHR, textStatus, errorThrown) {
						
						fileWrap.status = 'error';
						scope.uploadCount++;
						
						if (scope.uploadCount === getMapSize(scope.uploadProgress)) {
							scope.uploadInProgress = false;
							updateUploadStatus();
						}
						
						if (!scope.errors) {
							scope.errors = {};
						}
						scope.errors[fileWrap.id] = fileWrap.file.name;
					},
					xhr: function() {
						var xhr = new window.XMLHttpRequest();
						
						xhr.upload.addEventListener("progress", function(evt){
							var progress, loaded = 0, total = 0;
							
							if (evt.lengthComputable) {
								
								progress = scope.uploadProgress[fileWrap.id];
								if (progress) {
									progress['loaded'] = evt.loaded;
									progress['total'] = evt.total;
									
									console.log('loaded:' + progress['loaded']);
									console.log('total:' + progress['total']);
								}

								loaded = 0;
								total = 0;
								for (var key in scope.uploadProgress) {
									if (scope.uploadProgress.hasOwnProperty(key)) {
										progress = scope.uploadProgress[key];
										loaded += progress.loaded;
										total += progress.total;
									}
								}
								
								scope.percentDone = parseInt(100.0 * (loaded/total));
								
								// Only move progess bar close to the end.
								// Need to let the request return 'success'
								// before moving all the way to 100%.
								if (scope.percentDone <= progressPercentToWaitAt) {
									scope.$apply();
								}
							}
						}, false);
						
						return xhr;
					}
				});
			};
			
			//
			// Send message to signal that this group of files has finished uploading.
			//
			// If message is successfully sent, then call refreshFileList() in parent
			// window to refresh the list of files shown. It should now have the newly
			// uploaded files.
			//
			// Then close popup.
			//
			// If message is not successfully sent, then show error message in popup.
			//
			handleUploadsDone = function() {			
				
				var msg = {"groupId":scope.groupId};
				
				$.ajax({
					type: 'post',
					url: 'xxxxxxxxxxxxxxx' + allDoneUrl,
					data: msg,
					dataType:'json',
					success: function(data, textStatus, jqXHR) {						
						//alert('success:' + textStatus);
						
						// Send message back to parent window
						if (window.opener) {
							scope.parentWindow = window.opener.$windowScope;
							if (scope.parentWindow) {
								scope.parentWindow.refreshFileList();
							}
						}

						window.close();
					},
					error: function(jqXHR, textStatus, errorThrown) {
						// If error then backend doesn't know that upload has
						// completed and thus will not process the files.
						//
						// At this point all the files have uploaded successfully,
						// but the "all Done" message was not received.
						//
						// Need to set a flag saying that uploadsDone message was
						// not sent successfully.
						//
						// Need to show error message in popup asking user to 
						// try clicking upload button again.
						//
						
						alert('error sending all done message');
						
						scope.retrySendUploadsDoneMessage = true;
						scope.$apply();
					}
				});
			};
			
			//
			// Browser prevents altering the file input value, so we need
			// to replace the file input element with a new one instead.
			// We need a cleared file input to allow user to upload same
			// file twice in a row.
			//
			// This is because our js is triggered by the 'change' event.
			// If the value of the file input is the same, then there is
			// no 'change', thus no event.
			//
			clearFileInput = function(fileInputClass, bindAction) {
				var oldInp, newInp;
				
				oldInp = $(element).find('.' + fileInputClass);
				newInp = $(document.createElement('INPUT'));
				$.each(oldInp[0].attributes, function() {
					// this.attributes is not a plain object, but an array
					// of attribute nodes, which contain both the name and value
					if (this.specified) {
						newInp.attr(this.name, this.value);
					}
				});
				
				newInp.bind('change', bindAction);
				oldInp.replaceWith(newInp);
			};
			
			findDupes = function(fileWrappers, moreFiles) {
				var existingNames = [], dupes = [];
				
				for (var i=0, n=fileWrappers.length; i<n; i++) {
					existingNames.push(fileWrappers[i].file.name);
				}

				for (var i=0, n=moreFiles.length; i<n; i++) {
					if (existingNames.indexOf(moreFiles[i].name) > -1) {
						dupes.push(moreFiles[i].name);
					}
				}
				
				return dupes;
			};

			makeId = function() {
				var rand = Math.floor((Math.random()*100000000) + 1);
				var millis = new Date().getTime();
				return rand + millis;
			};
			
			makeGroupId = function() {
				return makeId();
			};
			
			updateUploadStatus = function() {
				var doneCount = 0;
				scope.allUploadsSuccessful = false;
				
				for (var i=0, n=scope.fileWrappers.length; i<n; i++) {
					if (scope.fileWrappers[i].status === 'success') {
						doneCount++;
					}
				}
				
				if (doneCount === scope.fileWrappers.length) {
					scope.allUploadsSuccessful = true;
				}

				scope.$apply();
			};

			getMapSize = function(map) {
			    var count = 0, key;
			    for (key in map) {
			        if (map.hasOwnProperty(key)) {
			            count++;
			        }
			    }
			    return count;
			};
			
			scope.remove = function(fileId) {

				for (var i=0, n=scope.fileWrappers.length; i<n; i++) {
					var fileWrap = scope.fileWrappers[i];

					if (fileWrap.id === fileId) {
						scope.fileWrappers.splice(i, 1);
						
						// Update error object since an error file might have been removed.
						if (scope.errors) {
							delete scope.errors[fileId];
							if (getMapSize(scope.errors) === 0) {
								scope.errors = undefined;
							}
						}
						
						// Update dupeNames object since an dupe file might have been removed.
						for (var j=0, m=scope.dupeNames.length; j<m; j++) {
							if (scope.dupeNames[j] === fileWrap.file.name) {
								scope.dupeNames.splice(j, 1);
							}
						}

						break;
					}
				}
				
				updateUploadStatus();
			};
			
			scope.closePopup = function() {
				window.close();
			};

			//
			// Bind the elements
			//
			fileInput.bind('change', initialAdd);
			uploadBtn.bind('click', uploadFiles);
		
		},
		templateUrl:'uploader/partial/uploaderModule',
		scope: true
		
	};// End of returned statement

});

