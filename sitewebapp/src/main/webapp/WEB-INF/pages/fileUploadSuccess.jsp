<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>



<t:page title="VendorTool fileUpload">
	<jsp:attribute name="header">
		<t:header email="${email}" currentPage="registersuccessconfirm"/>
	</jsp:attribute>
	
	<jsp:attribute name="css">
		<link href="<c:url value='/resources/css/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">Thanks for uploading file!</h3>
	        <div class="msg">
	            <a href="accounthub">Click here to go to your new Hub page.</a>
	        </div>
	    </div>
	</jsp:body>
</t:page>
