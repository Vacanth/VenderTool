<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div>

	<div class="fup" file-uploader>
		<div class="form-wrp">
			<a href="#" class="upload-btn btn lg grn">Upload</a>
			<input class="qry-fup-inp fup-inp-main" type="file" multiple="multiple" />
		</div>
		<div class="qry-fup-dlg modal fade" >
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Files to upload</h4>
					</div>
					<div class="modal-body">
						<div ng-show="dupeNames.length" class="alert alert-warning" style="display:none">
							Duplicate file names not allowed.<br/>
							Please rename the following files and try adding them again:
							<b ng-repeat="name in dupeNames">{{name}}{{{true: '', false: ', '}[$last]}}</b>
						</div>
						<div ng-show="errors.length" class="alert alert-warning" style="display:none">
							Sorry, some errors occurred. We could not upload the following files:
							<b ng-repeat="error in errors">{{error}}{{{true: '', false: ', '}[$last]}}</b>
							<br/><br/>
							Please try uploading those files again.
						</div>
						<table class="ftable">
							<tr>
								<th>File name</th>
								<th>Size (KB)</th>
								<th>Status</th>
							</tr>
							<tr ng-repeat="wrap in fileWrappers" ng-hide="wrap.kbSize == null">
								<td class="c1">{{wrap.file.name}}</td>
								<td class="c2">{{wrap.kbSize}}</td>
								<td class="c2">{{wrap.status}}</td>
							</tr>
						</table>
						<input name="fup-inp-dlg" class="qry-fup-inp-more" type="file" multiple="multiple" />
					</div>
					<div class="modal-footer">
						<div class="progress progress-striped active">
							<div class="progress-bar"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: {{percentDone}}%">
								<span class="sr-only">{{percentDone}}% Complete</span>
							</div>
						</div>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="qry-fup-upBtn btn btn-primary">Upload</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</div><!-- End of file-uploader directive -->



	a list of uploaded files
</div>


