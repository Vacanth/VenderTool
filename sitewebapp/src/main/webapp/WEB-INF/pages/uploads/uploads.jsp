<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/layoutTable" %>
<%@ taglib prefix="t3" tagdir="/WEB-INF/tags/navbarSection" %>


<t1:page title="Uploads" angularAppName="uploadsApp" currentPage="accounthub" email="${email}">

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/uploads.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>

	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/uploads.js' />" type="text/javascript"></script>
	</jsp:attribute>

	
	<jsp:body>
		<t2:layoutTable>
		
			<jsp:attribute name="pageTitle">Uploads</jsp:attribute>

			<jsp:attribute name="topNav">
				<t3:navbarSection current="uploads"/>
			</jsp:attribute>
			
			<jsp:attribute name="content">
				<div ng-controller="UploadsCtrl">
				
					<div class="msg">
						<div class="alert alert-success" style="display:none;">Profile information updated.</div>
						<div class="alert alert-danger"  style="display:none;">Sorry, there's been an error.</div>
					</div>
	
					<div>
						<a ng-click="openPopup()" type="button" class="btn btn-primary iconBtn upload">
							<i class="icon"></i>
							<b>Upload Files</b>
						</a>
						<a href="javascript:;" type="button" class="btn btn-primary iconBtn download">
							<i class="icon"></i>
							<b>Download Files</b>
						</a>
						
						<table class="table uploadTable" cellspacing="0" cellpadding="0" border="0">
							<tr class="tableHdr">
								<th>Job Id</th>
								<th>Uploaded Files</th>
								<th>Upload Date</th>
								<th>Proccessing Status</th>
								<th>Processed Files</th>
								<th>Processsing Complete</th>
							</tr>
							<c:forEach var="job" items="${uploadsResponse.jobs}"  varStatus="status">
								
								<c:set var="rowColor" value="${status.index % 2 == 1? 'rowColor' : ''}"/>
								
								<tbody>
									<tr class="q-folderRow ${rowColor}">
										<td>${job.jobId}</td>
										<td>
											<%--=============================
											Uploaded files
											=================================--%>
											<c:choose>
												<c:when test="${fn:length(job.uploadedFiles) == 0}">
													--
												</c:when>
												<c:when test="${fn:length(job.uploadedFiles) == 1}">
													<%--
													//Single file
													--%>
													<b class="fileIcon"></b>
													<input type="checkbox" id="${job.jobId}"/>
													
													<c:choose>
														<c:when test="${!empty job.title}">
															<label for="${job.jobId}">${job.title}</label>
														</c:when>
														<c:otherwise>
															<label for="${job.jobId}">${job.uploadedFiles[0].name}</label>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<%--
													// Folder
													--%>
													<b class="folderIcon q-folderIcon"></b>
													<input type="checkbox" class="q-uploadedFldrCbx" id="${job.jobId}"/>
													
													<c:choose>
														<c:when test="${!empty job.title}">
															<label for="${job.jobId}">${job.title}</label>
														</c:when>
														<c:otherwise>
															<label for="${job.jobId}">${job.uploadedFiles[0].name}...</label>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</td>
										<td>${job.createdDate}</td>
										<td>${job.status}</td>
										<td>
											<%--=============================
											Processed files
											=================================--%>
											<c:choose>
												<c:when test="${fn:length(job.processedFiles) == 0}">
													--
												</c:when>
												<c:when test="${fn:length(job.processedFiles) == 1}">
													<b class="fileIcon"></b>
													<input type="checkbox" id="${job.jobId}_p"/>
	
													<c:choose>
														<c:when test="${!empty job.title}">
															<label for="${job.jobId}_p">${job.title}</label>
														</c:when>
														<c:otherwise>
															<label for="${job.jobId}_p">${job.processedFiles[0].name}</label>
														</c:otherwise>
													</c:choose>					
												</c:when>
												<c:otherwise>
													<b class="folderIcon q-folderIcon"></b>
													<input type="checkbox" class="q-processedFldrCbx" id="${job.jobId}_p"/>
													
													<c:choose>
														<c:when test="${!empty job.title}">
															<label for="${job.jobId}_p">${job.title}</label>
														</c:when>
														<c:otherwise>
															<label for="${job.jobId}_p">${job.processedFiles[0].name}...</label>
														</c:otherwise>
													</c:choose>	
												</c:otherwise>
											</c:choose>
	
										</td>
										<td></td>
									</tr>
									<%--=============================================
									// Hidden subRows
									=================================================--%>
									<c:forEach var="file" items="${job.uploadedFiles}" varStatus="status">
										<tr class="subRow q-subRow">
											<td></td>
											<td>
												<b class="fileIcon"></b>
												<input type="checkbox"  class="q-uploadedCbx" id="${file.fileId}"/>
												<label for="${file.fileId}">${file.name}</label>
											</td>
											<td></td>
											<td></td>
											<td>
												<b class="fileIcon"></b>
												<input type="checkbox" class="q-processedCbx" id="${file.fileId}_p" />
												<label for="${file.fileId}_p">${file.name}</label>
											</td>
											<td></td>
										</tr>
									</c:forEach>
	
								</tbody>
							</c:forEach>
							
						</table>
						<%--
						<ul class="pagination">
							<li ng-class="{'disabled': getPreviousPage() == null}"><a href="#/files/{{getPreviousPage()}}">&laquo;</a></li>
							<li ng-repeat="i in makeArray(getNumOfPages())"
								ng-class="{'active': uploadsRes.paginationOutput.currentPage == ($index + 1)}">
								<a  href="#/files/{{$index + 1}}">{{$index + 1}}</a>
							</li>
							<li ng-class="{'disabled': getNextPage() == null}"><a href="#/files/{{getNextPage()}}">&raquo;</a></li>
						</ul>
						 --%>
					</div>

				</div>
			</jsp:attribute>
		</t2:layoutTable>
	
	</jsp:body>
</t1:page>
