<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code='HTTP.ERROR' var="title"/>


<t1:page title="${title}" currentPage="confirmationFailed" email="" >
	
	<jsp:attribute name="css">
		<link href="<c:url value='/css/page/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">${title} ${errorCode}</h3>
	        <div class="msg">
	        	
	        </div>
	    </div>
	</jsp:body>
</t1:page>

