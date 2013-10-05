<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/popup" %>



<t1:popup title="File uploader" angularAppName="uploaderModule">
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/uploader.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/uploader.js' />" type="text/javascript"></script>
	</jsp:attribute>

	<jsp:body>
		<div uploader group-id="${groupId}" upload-url="uploadFile" all-done-url="uploadDone">
			<div class="upldr">
				<div class="upldr-content" ng-cloak>
					<div class="upldr-body">
						<div ng-show="dupeNames.length" class="alert alert-danger">
							<button ng-click="dupeNames = !dupeNames" type="button" class="close" aria-hidden="true">&times;</button>
							Could not add file because a file with the same name has already been added.
							(Duplicate names: <b ng-repeat="name in dupeNames">{{name}}{{{true: '', false: ', '}[$last]}}</b>)
						</div>
						<div ng-show="errors" class="alert alert-danger">
							<button ng-click="errors = !errors"  type="button" class="close" aria-hidden="true">&times;</button>
							Sorry, some errors occurred. We could not upload the following files:
							<b ng-repeat="(key, value) in errors">{{value}}{{{true: '', false: ', '}[$last]}}</b>
							<br/><br/>
							Please try uploading those files again.
						</div>
						<div ng-show="retrySendUploadsDoneMessage" class="alert alert-danger">
							Sorry, some errors occurred. We could not complete the upload.
							<br/><br/>
							Please try clicking the "Upload Files" button again.
						</div>
						
						<div class="upldr-inp-wrp">
							<div ng-class="{disabledAddBtn: uploadInProgress || allUploadsSuccessful}"></div>
			
							<button type="button" class="btn btn-primary iconBtn addFiles">
								<i class="icon"></i>
								<b>Add Files</b>
							</button>
			
							<input class="qry-upldr-inp upldr-inp-main" type="file" multiple="multiple" />
						</div>
						
						<label class="upldr-title">Upload Title: <input ng-model="uploadTitle"/></label>
				
						<div style="border:1px solid #ccc; min-height:150px">
							<table class="ftable">
								<tr>
									<th class="c1">File</th>
									<th class="c2">Status</th>
									<th class="c3">Remove</th>
								</tr>
								<tr ng-repeat="wrap in fileWrappers" ng-hide="wrap.kbSize == null">
									<td>{{wrap.file.name}} <span class="size">({{wrap.kbSize}}KB)</span></td>
									<td>{{wrap.status}}</td>
									<td>
										<a ng-show="wrap.status != 'success' && !uploadInProgress" href="javascript:;" ng-click="remove(wrap.id)">Remove</a>
										<span ng-show="wrap.status == 'success' || uploadInProgress" class="disabled">Remove</span>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="upldr-footer">
						<div class="progress progress-striped active">
							<div class="progress-bar"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:{{percentDone}}%">
								<span>{{percentDone}}% Complete</span>
							</div>
						</div>
						
						<button ng-click="closePopup()" type="button" class="btn btn-default">Cancel</button>
						<button ng-class="{disabled: !fileWrappers || uploadInProgress}" type="button" class="qry-upldr-upBtn btn btn-primary">Upload Files</button>
					</div>
				</div><!-- upldr-content -->
			</div><!-- End of file-uploader directive -->
		</div>
	</jsp:body>
</t1:popup>
