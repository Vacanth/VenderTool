<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:page title="VendorTool fileUpload">
	<jsp:attribute name="header">
		<t:header email="${email}" currentPage="registersuccessconfirm" />
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	</jsp:attribute>

	<jsp:attribute name="css">
		<link href="<c:url value='/resources/css/register.css' />"
			rel="stylesheet" type="text/css" />
	</jsp:attribute>

	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">Upload file!</h3>
	        <div class="msg">
	            <form:form method="post" commandName="fileUpload"
					modelAttribute="uploadFile" enctype="multipart/form-data">
 
    <p>Select files to upload. Press Add button to add more file inputs.</p>
 
<!--     <input id="addFile" type="button" value="Add another File" />
 -->    <table id="fileTable">
        <tr>
            <td><form:input path="file" type="file" /></td>
        </tr>
    </table>
    <br />
					<input type="submit" value="Upload" />
</form:form>
	        </div>
	    </div>
	</jsp:body>
</t:page>
