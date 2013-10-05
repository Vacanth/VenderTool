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
	
	<table class="table  uploadTable" ng-click="handleCheckboxCtrls($event); toggleRows($event)" cellspacing="0" cellpadding="0">
		<tr class="tableHdr">
			<th>Job Id</th>
			<th>Uploaded Files</th>
			<th>Upload Date</th>
			<th>Proccessing Status</th>
			<th>Processed Files</th>
			<th>Processsing Complete</th>
		</tr>
		<tbody ng-repeat="job in uploadsRes.jobs">
			<tr ng-class="{'rowColor': $index % 2 == 1, 'folderRow': job.uploadedFiles.length > 1}" class="q-folderRow">
				<td>{{job.jobId}}</td>
				<td>
					<%--=============================
					Uploaded files
					=================================--%>
					<div ng-switch on="job.uploadedFiles.length">
					
						<%--
						//Single file
						--%>
						<span ng-switch-when="1">
							<b class="fileIcon"></b>
							
							<input type="checkbox" id="{{job.jobId}}"/>

							<span ng-switch on="isNullOrEmpty(job.title)">
								<label ng-switch-when="false" for="{{job.jobId}}">{{job.title}}</label>
								<label ng-switch-default for="{{job.jobId}}">{{job.uploadedFiles[0].name}}</label>
							</span>
						</span>
						
						<%--
						// Folder
						--%>
						<span ng-switch-default>
							<b class="folderIcon q-folderIcon"></b>
							
							<input type="checkbox" class="q-uploadedFldrCbx" id="{{job.jobId}}">
							
							<span ng-switch on="isNullOrEmpty(job.title)">
								<label ng-switch-when="false" for="{{job.jobId}}">{{job.title}}</label>
								<label ng-switch-default for="{{job.jobId}}">{{job.uploadedFiles[0].name}}...</label>
							</span>
						</span>
					</div>
				</td>
				<td>{{job.createdDate}}</td>
				<td>{{job.status}}</td>
				<td>
					<%--=============================
					Processed files
					=================================--%>
					<div ng-switch on="job.processedFiles.length">
						<span ng-switch-when="0">
							--
						</span>
						<span ng-switch-when="1">
							<b class="fileIcon"></b>
							<input type="checkbox" id="{{job.jobId}}_p"/>
							
							
							<span ng-switch on="isNullOrEmpty(job.title)">
								<label ng-switch-when="false" for="{{job.jobId}}_p">{{job.title}}</label>
								<label ng-switch-default for="{{job.jobId}}_p">{{job.processedFiles[0].name}}</label>
							</span>
						</span>
						<span ng-switch-default>
							<b class="folderIcon q-folderIcon"></b>
							<input type="checkbox" class="q-processedFldrCbx" id="{{job.jobId}}_p"/>
							
							
							<span ng-switch on="isNullOrEmpty(job.title)">
								<label ng-switch-when="false" for="{{job.jobId}}_p">{{job.title}}</label>
								<label ng-switch-default for="{{job.jobId}}_p">{{job.processedFiles[0].name}}...</label>
							</span>
						</span>
					</div>
					
				</td>
				<td>{{}}</td>
			</tr>
			<%--=============================================
			// Hidden subRows
			=================================================--%>
			<tr ng-repeat="file in job.filesInFolder" class="subRow q-subRow">
				<td>{{}}</td>
				<td>
					<b class="fileIcon"></b>
					<input type="checkbox" ng-model="file.checked" class="q-uploadedCbx" id="{{file.fileId}}"/>
					<label for="{{file.fileId}}">{{file.name}}</label>
				</td>
				<td>{{}}</td>
				<td>{{}}</td>
				<td>
					<b class="fileIcon"></b>
					<input type="checkbox" ng-model="job.processedFiles[$index].checked" class="q-processedCbx" id="{{file.fileId}}_p" />
					<label for="{{file.fileId}}_p">{{job.processedFiles[$index].name}}</label>
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


