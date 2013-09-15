<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="UploadsCtrl">
	
	<a ng-click="openPopup()" href="javascript:;" type="button" class="btn btn-primary iconBtn uploads">
		<div class="bg"></div><i class="icon"></i>
		<b>Upload Files</b>
	</a>

	
	<table class="table table-striped uploadTable">
		<tr>
			<th>Files</th>
			<th>Job Id</th>
			<th>Status</th>
			<th>Upload Date</th>
			<th>Processed Files</th>
			<th>Job Complete Date</th>
		</tr>
	</table>

</div>


