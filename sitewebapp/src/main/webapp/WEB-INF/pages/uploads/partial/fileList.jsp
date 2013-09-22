<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="UploadsCtrl">
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn upload">
		<i class="icon"></i>
		<b>Upload Files</b>
	</a>
	
	
	<a ng-click="downloadFiles()" href="javascript:;" type="button" class="btn btn-primary iconBtn download">
		<i class="icon"></i>
		<b>Download Files</b>
	</a>
	
	<style>
		.uploadTable tbody+tbody {
			margin:0;
			padding:0;
			border-top:0;
		}
		table.uploadTable tbody tr td {
			padding-top:10px;
			padding-bottom:10px;
		}
		.tableHdr {
			background-color:#ddd;
		}
		.subRow {
			display:none;
		}
		.folder {
			cursor:pointer;
		}
		.rowColor {
			background-color:#e4f2e6;
		}
	</style>
	<script>
		function toggle(link) {
			var i, lastRow, folderIcon, rows = $(link).closest('tbody').find('tr');
			lastRow = rows[rows.length - 1];
			
			if ($(link).hasClass('folderIcon')) {
				folderIcon = $(link);
			}
			else {
				folderIcon = $(link).siblings('.folderIcon');
			}
			
			if ($(lastRow).is(':visible')) {
				for (i=1; i<rows.length; i++) {
					$(rows[i]).hide();
					folderIcon.removeClass('open');
				}
			}
			else { 
				for (i=1; i<rows.length; i++) {
					$(rows[i]).show();
					folderIcon.addClass('open');
				}
			}
		}
	
	</script>
	
	<table class="table  uploadTable" ng-click="handleCheckboxCtrls($event); toggleRows($event)">
		<tr class="tableHdr">
			<th>Job Id</th>
			<th>Uploaded Files</th>
			<th>Upload Date</th>
			<th>Proccessing Status</th>
			<th>Processed Files</th>
			<th>Processsing Complete</th>
		</tr>
		<tbody ng-repeat="job in uploadsRes.jobs">
			<tr ng-class="{'rowColor': $index % 2 == 1}">
				<td>{{job.jobId}}</td>
				<td>
					<div ng-switch on="job.uploadedFiles.length">
						<span ng-switch-when="1">
							<input type="checkbox"/>
							<b class="fileIcon"></b>
							
							<span ng-switch on="isNullOrEmpty(job.title)">
								<span ng-switch-when="false">{{job.title}}</span>
								<span ng-switch-default>{{job.uploadedFiles[0].name}}</span>
							</span>
							
						</span>
						<span ng-switch-default>
							<input type="checkbox" class="q-uploadedFldrCbx">
							
							<span class="folder q-folder">
								<b class="folderIcon q-folderIcon"></b>

								<span ng-switch on="isNullOrEmpty(job.title)">
									<span ng-switch-when="false">{{job.title}}</span>
									<span ng-switch-default>{{job.uploadedFiles[0].name}}...</span>
								</span>
								
							</span>
						</span>
					</div>
				</td>
				<td>{{job.createdDate}}</td>
				<td>{{job.status}}</td>
				<td>
					<div ng-switch on="job.processedFiles.length">
						<span ng-switch-when="0">
							--
						</span>
						<span ng-switch-when="1">
							<input type="checkbox"/>
							<b class="fileIcon"></b>
							
							<span ng-switch on="isNullOrEmpty(job.title)">
								<span ng-switch-when="false">{{job.title}}</span>
								<span ng-switch-default>{{job.processedFiles[0].name}}</span>
							</span>
							
						</span>
						<span ng-switch-default>
							<input type="checkbox" class="q-processedFldrCbx"/>
							
							<span class="folder q-folder">
								<b class="folderIcon q-folderIcon"></b>
								
								<span ng-switch on="isNullOrEmpty(job.title)">
									<span ng-switch-when="false">{{job.title}}</span>
									<span ng-switch-default>{{job.processedFiles[0].name}}...</span>
								</span>
								
							</span>
						</span>
					</div>
					
				</td>
				<td>{{}}</td>
			</tr>
			<%--
			// Hidden rows
			--%>
			<tr ng-repeat="file in job.uploadedFiles" class="subRow q-subRow">
				<td>{{}}</td>
				<td>
					<input type="checkbox" ng-model="file.checked" class="q-uploadedCbx" style="margin-left:15px"/>
					<b class="fileIcon"></b>
					{{file.name}}
				</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>
					<input type="checkbox" ng-model="job.processedFiles[$index].checked" class="q-processedCbx" style="margin-left:15px"/>
					<b class="fileIcon"></b>
					{{job.processedFiles[$index].name}}
				</td>
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


