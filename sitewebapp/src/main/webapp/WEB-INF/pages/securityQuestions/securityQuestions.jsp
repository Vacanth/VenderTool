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


<script type="text/ng-template" id="questionForm">
<spring:message code='form.submit' var="submit"/>
<spring:message code="form.securityquestions.title" var="title"/>
				
<h3 class="ttl">${title}</h3>

<div class="pg-msg sec-ques">
	<div page-error></div>
</div>
<div class="sec-ques">

	<div class="fldWrp">
		<select ng-model="question1" ng-options="q.questionCode as q.questionDisplayName for q in questionList1">
			<option value="">- Select a question -</option>
		</select>
		<div field-error field="question1" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<div class="fldWrp">
		<input class="form-control" ng-model="answer1" placeholder="Your answer to question" autocomplete="off"/>
		<div field-error field="answer1" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
		<br/>
	</div>
	<div class="fldWrp">
		<select ng-model="question2" ng-options="q.questionCode as q.questionDisplayName for q in questionList2">
			<option value="">- Select a second question -</option>
		</select>
		<div field-error field="question2" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<div class="fldWrp">
		<input class="form-control" ng-model="answer2" placeholder="Your answer to second question" autocomplete="off"/>
		<div field-error field="answer2" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<hr/>
	<div class="fldWrp">
		<input ng-model="password" type="password" autocomplete="off" placeholder="Password" class="form-control"/>
	</div>
	
	<div class="submit">
		<button ng-click="save()" type="button" class="btn btn-primary grn">Submit</button>
	</div>
</div>
</script>


	    </div>
	</jsp:body>
</t1:page>
