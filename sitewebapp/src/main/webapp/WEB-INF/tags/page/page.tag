<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/page" %>

<%--============
Page fragments
================--%>
<%@ attribute name="scripts"	fragment="true" %>
<%@ attribute name="css"		fragment="true" %>
<%@ attribute name="inlineJs"	fragment="true" %>

<%--============
Attributes
================--%>
<%@ attribute name="title" required="false" %>
<%@ attribute name="email" required="false" %>
<%@ attribute name="currentPage" required="false" %>
<%@ attribute name="angularAppName" required="false" %>

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

		<link href='<c:url value="/wro/pageTag.css" />' rel="stylesheet" type="text/css"/>
		
		<%-- Page specific CSS tags --%>
		<jsp:invoke fragment="css"/>
		
	</head>
	<body>
		<div id="wrap">
			<div id="content">
				<t:header email="${email}" currentPage="${currentPage}"/>
				
				<%-- Main contents --%>
				<jsp:doBody/>
			</div>
		</div>
		<div id="footer">
			<div class="ftr">
				<a href="#"><spring:message code="form.header.aboutus"/></a>
				<a href="#"><spring:message code="form.header.contact"/></a>
				<a href="#"><spring:message code="form.header.faq"/></a>
				<a href="#"><spring:message code="form.header.privacy"/></a>
			</div>
		</div>

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<c:if test="${!empty angularAppName}">
			<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
		</c:if>

		<script src='<c:url value="/wro/pageTag.js" />'></script>
		
		<%-- Page specific javascript tags --%>
		<jsp:invoke fragment="scripts"/>
		
		<%-- Inline JS --%>
		<script type="text/javascript">
			<jsp:invoke fragment="inlineJs"/>
		</script>
		
		<div id="pageSpinner">
			<div class="mask"></div>
			<div class="spinnerWrp">
				<div class="spinnerBg"></div>
				<div class="spinner"><img src="resources/img/spnnr38w.gif"/><b>Loading...</b></div>
			</div>
		</div>
		
	</body>
</html>

