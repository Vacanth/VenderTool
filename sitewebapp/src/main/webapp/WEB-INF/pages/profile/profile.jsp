<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/layoutTable" %>

<spring:message code='HTTP.ERROR' var="c_httpError"/>

<t1:page title="VendorTool" angularAppName="profileApp" currentPage="profile" email="${account.email}" >
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/profile.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/profile.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		profileApp.factory('Data', function() {
			return ${modelMapJson};
		});

		profileApp.factory('Content', function() {
			return {"httpError":"${c_httpError}"};
		});
	</jsp:attribute>

	<jsp:body>
	
		<t2:layoutTable>
			
			<jsp:attribute name="pageTitle">Profile</jsp:attribute>
			
			<jsp:attribute name="leftNav">
				<div class="list-group" ng-controller="NavCtrl">
					<a href="#/info"		ng-class="getClass('/info')" 		class="list-group-item">Change info</a>
					<a href="#/email"		ng-class="getClass('/email')" 		class="list-group-item">Change email</a>
					<a href="#/password"	ng-class="getClass('/password')"	class="list-group-item">Change password</a>
					<a href="#/questions"	ng-class="getClass('/questions')"	class="list-group-item">Change security questions</a>
				</div>
			</jsp:attribute>
			
			<jsp:attribute name="content">
				<div ng-view></div>
			</jsp:attribute>
		</t2:layoutTable>
		
	</jsp:body>

</t1:page>



