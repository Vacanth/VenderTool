<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code='confirmation.failed_title' var="title"/>

<t1:page title="${title}" currentPage="confirmationFailed" email="" >
	
	<jsp:attribute name="css">
		<link href="<c:url value='/css/page/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">${title}</h3>
	        <div class="msg">
	        	<c:choose>
	        		<c:when test="${type == 'email'}">
	        			<spring:message code='confirmation.failed_email'/>
	        		</c:when>
	        		<c:when test="${type == 'account'}">
	        			<spring:message code='confirmation.failed_account'/>
	        		</c:when>
	        	</c:choose>
	        </div>
	    </div>
	</jsp:body>
</t1:page>

