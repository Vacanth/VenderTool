<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--============
Page fragments
================--%>
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
		<html ng-app="${angularAppName}" ng-cloak>
	</c:when>
	<c:otherwise>
		<html>
	</c:otherwise>
</c:choose>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${title}</title>
		<link href="<c:url value='/css/tag/popup.css' />" rel="stylesheet" type="text/css"/>

		<%-- Page specific CSS tags --%>
		<jsp:invoke fragment="css"/>
		
	</head>
	<body>
		<div id="wrap">
			<div id="content">
				<jsp:doBody/>
			</div>
		</div>

		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<c:if test="${!empty angularAppName}">
			<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
		</c:if>
		
		<%-- Page specific javascript tags --%>
		<jsp:invoke fragment="scripts"/>
		
		<%-- Inline JS --%>
		<jsp:invoke fragment="inlineJs"/>

	</body>
</html>

