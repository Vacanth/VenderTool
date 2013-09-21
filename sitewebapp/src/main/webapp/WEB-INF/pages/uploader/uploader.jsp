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
		<div uploader upload-url="uploadFile" all-done-url="uploadDone"></div>
	</jsp:body>
</t1:popup>
