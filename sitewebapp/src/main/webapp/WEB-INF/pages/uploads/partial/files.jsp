<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="UploadsCtrl">
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn upload">
		<div class="bg"></div><i class="icon"></i>
		<b>Upload Files</b>
	</a>
	
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn download">
		<div class="bg"></div><i class="icon"></i>
		<b>Download Files</b>
	</a>
	
	<style>
	
		.fileRows {
			display:none;
		}
	</style>
	<script>
		function toggle(link) {
			var i, lastRow, rows = $(link).closest('tbody').find('tr');
			lastRow = rows[rows.length - 1];
			
			if ($(lastRow).is(':visible')) {
				for (i=1; i<rows.length; i++) {
					$(rows[i]).hide();
				}
			}
			else {
				for (i=1; i<rows.length; i++) {
					$(rows[i]).show();
				}
			}
		}
	
	</script>
	
	<table class="table  uploadTable">
		<tr>
			<th>Uploaded Files</th>
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
							<input type="checkbox"/>
							{{uploadsRes.fileMap[job.jobId][0].name}}
						</span>
						
						<span ng-switch-default>
							<input type="checkbox"/>
							<b class="folderIcon" onclick="toggle(this)"></b>
							<span onclick="toggle(this)">
								<span ng-repeat="file in uploadsRes.fileMap[job.jobId]">
									{{file.name}}
								</span>
							</span>
							<%-- 
							{{uploadsRes.fileMap[job.jobId][0].fileId}}--%>
						</span>
					</div>
				</td>
				<td>{{job.jobId}}</td>
				<td>{{job.status}}</td>
				<td>{{job.createdDate}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
			<%--
			<tr ng-repeat="file in uploadsRes.fileMap[job.jobId]" ng-show="!$first" class="fileRows">
			--%>
			<tr ng-repeat="file in uploadsRes.fileMap[job.jobId]" class="fileRows">
				<td><input type="checkbox" style="margin-left:15px"/> {{file.name}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>{{}}</td>
			</tr>
		</tbody>
	</table>
	
	
	<ul class="pagination">
		<li ng-class="{'disabled': getPreviousPage() == null}"><a href="#/files/{{getPreviousPage()}}">&laquo;</a></li>
		<li ng-repeat="i in makeArray(getNumOfPages())"
			ng-class="{'active': uploadsRes.paginationOutput.currentPage == ($index + 1)}">
			<a  href="#/files/{{$index + 1}}">{{$index + 1}}</a>
		</li>
		<li ng-class="{'disabled': getNextPage() == null}"><a href="#/files/{{getNextPage()}}">&raquo;</a></li>
	</ul>

</div>


