<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="UploadsCtrl">
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn uploads">
		<div class="bg"></div><i class="icon"></i>
		<b>Upload Files</b>
	</a>

	
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
				<td>{{uploadsRes.fileMap[job.jobId][0].fileId}}</td>
				<td>{{job.jobId}}</td>
				<td>{{job.status}}</td>
				<td>{{job.createdDate}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
			<tr ng-repeat="file in uploadsRes.fileMap[job.jobId]" ng-show="!$first">
				<td>{{file.fileId}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
		</tbody>
	</table>

</div>


