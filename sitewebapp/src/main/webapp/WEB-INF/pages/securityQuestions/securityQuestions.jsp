<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>



<t1:page title="VendorTool" angularAppName="securityQuestionsApp" currentPage="questions" email="${email}" >

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/securityQuestions.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/securityQuestions.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		securityQuestionsApp.factory('Data', function() {
			return ${modelMapJson};
		});
		
		// IE placeholder text
		$('input').placeholder();
	</jsp:attribute>
	
	<jsp:body>
		<div id="pgBg"><img src="resources/img/cafe2.jpg" alt=""></div>
		
	    <div class="reg main input-group" ng-controller="SecurityQuestionsCtrl">
	    	<spring:message code="form.securityquestions.title" var="title"/>
				
			<h3 class="ttl">${title}</h3>
			
			<div class="alert alert-danger" style="display:none">Please correct errors below.</div>
			<div class="alert alert-success" style="display:none">Your security questions have been saved.</div>

	    	<div ng-view></div>
	    </div>
	</jsp:body>
</t1:page>
