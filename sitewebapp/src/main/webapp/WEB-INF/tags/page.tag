<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Page fragments
================--%>
<%@ attribute name="header"		fragment="true"%>
<%@ attribute name="scripts"	fragment="true"%>
<%@ attribute name="css"		fragment="true"%>
<%@ attribute name="inlineJs"	fragment="true" %>

<%--============
Attributes
================--%>
<%@ attribute name="title" required="false"%>
<%@ attribute name="angularAppName" required="false"%>

<%--============
Variables
================--%>
<c:set var="title" value="${empty title ? 'Vendor Tool' : title}"/>


<!doctype html>
<c:choose>
	<c:when test="${!empty angularAppName}">
		<html ng-app="${angularAppName}">
	</c:when>
	<c:otherwise>
		<html>
	</c:otherwise>
</c:choose>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${title}</title>
		<link href='<c:url value="/resources/css/main.css" />' rel="stylesheet" type="text/css"/>
		<link href='<c:url value="/resources/css/lib/bootstrap.min.css" />' rel="stylesheet" type="text/css"/>
		
		<%-- Page specific CSS tags --%>
		<jsp:invoke fragment="css"/>
		
	</head>
	<body>
		<div id="wrap">
			
			<%-- Page specific header section --%>
			<jsp:invoke fragment="header"/>
			
			<div id="body">
				
				<%-- Main contents --%>
				<jsp:doBody/>
			
				<div class="clr"></div>
			</div>
			
			<div id="footer">
				<div class="ftr">
					<a href="#"><spring:message code="form.header.aboutus"/></a>
					<a href="#"><spring:message code="form.header.contact"/></a>
					<a href="#"><spring:message code="form.header.faq"/></a>
					<a href="#"><spring:message code="form.header.privacy"/></a>
				</div>
			</div>
		</div>

		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src='<c:url value="/resources/js/lib/bootstrap.min.js" />'></script>
		<c:if test="${!empty angularAppName}">
			<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
		</c:if>
		<script src='<c:url value="/resources/js/header.js" />'></script>
		
		<%-- Page specific javascript tags --%>
		<jsp:invoke fragment="scripts"/>
		
		<%-- Inline JS --%>
		<jsp:invoke fragment="inlineJs"/>

	</body>
</html>

