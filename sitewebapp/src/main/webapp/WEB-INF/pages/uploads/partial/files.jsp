<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="UploadsCtrl">
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn uploads">
		<div class="bg"></div><i class="icon"></i>
		<b>Upload Files</b>
	</a>
	
	<style>
	
		.fileRows {
			display:none;
		}
	</style>
	<script>
		function toggle(link) {
			var rows = $(link).closest('tbody').find('tr');
			var lastRow = rows[rows.length - 1];
			
			if ($(lastRow).is(':visible')) {
				for (var i=2; i<rows.length; i++) {
					$(rows[i]).hide();
				}
			}
			else {
				for (var i=2; i<rows.length; i++) {
					$(rows[i]).show();
				}
			}
		}
	
	</script>
	
	<table class="table  uploadTable">
		<tr>
			<th>Files</th>
			<th>Job Id</th>
			<th>Status</th>
			<th>Upload Date</th>
			<th>Processed Files</th>
			<th>Job Complete Date</th>
		</tr>
		<tbody ng-repeat="job in uploadsRes.jobs">
			<tr>
				<td>
					<div ng-switch on="uploadsRes.fileMap[job.jobId].length">
						<span ng-switch-when="1">
							{{uploadsRes.fileMap[job.jobId][0].fileId}}
						</span>
						<a ng-switch-default href="javascript:;" onclick="toggle(this)">
							{{uploadsRes.fileMap[job.jobId][0].fileId}}
						</a>
					</div>
				</td>
				<td>{{job.jobId}}</td>
				<td>{{job.status}}</td>
				<td>{{job.createdDate}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
			<tr ng-repeat="file in uploadsRes.fileMap[job.jobId]" ng-show="!$first" class="fileRows">
				<td>{{file.fileId}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
		</tbody>
	</table>
	
	currentPage:{{uploadsRes.paginationOutput.currentPage}}<br/>
	entriesPerPage:{{uploadsRes.paginationOutput.entriesPerPage}}<br/>
	totalResults:{{uploadsRes.paginationOutput.totalResults}}<br/>

</div>


