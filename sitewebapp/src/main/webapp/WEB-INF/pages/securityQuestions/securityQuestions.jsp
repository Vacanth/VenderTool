<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>

<spring:message code='HTTP.ERROR' var="c_httpError"/>

<t1:page title="VendorTool" angularAppName="securityQuestionsApp" currentPage="questions" email="${email}" >

	<jsp:attribute name="css">
		<link href="<c:url value='/css/page/register.css' />" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/page/securityQuestions.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/js/lib/jquery.placeholder.js' />" type="text/javascript"></script>
		<script src="<c:url value='/js/ErrorResponseUtil.js' />" type="text/javascript"></script>
		<script src="<c:url value='/js/sharedNgModules/pageErrorModule.js' />" type="text/javascript"></script>
		<script src="<c:url value='/js/sharedNgModules/fieldErrorModule.js' />" type="text/javascript"></script>
		<script src="<c:url value='/js/page/securityQuestionsApp.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		securityQuestionsApp.factory('Data', function() {
			return ${modelMapJson};
		});
		
		securityQuestionsApp.factory('Content', function() {
			return {"httpError":"${c_httpError}"};
		});
		
		// IE placeholder text
		$('input').placeholder();
	</jsp:attribute>
	
	<jsp:body>
		<div id="pgBg"><img src="<c:url value='/img/cafe2.jpg'/>" alt=""></div>

	    <div class="reg main input-group" ng-controller="SecurityQuestionsCtrl">
	    	<div ng-view></div>
	    </div>
	</jsp:body>
</t1:page>
