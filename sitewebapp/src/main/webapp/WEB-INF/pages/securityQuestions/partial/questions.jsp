<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


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
		<input class="form-control" type="password" ng-model="password" placeholder="Password" autocomplete="off"/>
	</div>
	
	<div class="submit">
		<button ng-click="save()" type="button" class="btn btn-primary grn">Submit</button>
	</div>
</div>
