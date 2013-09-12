<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="form.securityquestions.title" var="title"/>
<spring:message code='form.submit' var="submit"/>

<h3 class="ttl">${title}</h3>
			
<div class="alert alert-danger" style="display:none">
	Please correct errors below.
</div>

<form>
	<div class="fldWrp">
		<select ng-model="question1" ng-options="q.id as q.text for q in questionList1">
			<option value="">- Select a question -</option>
		</select>
		<div error-response field="question1" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<div class="fldWrp">
		<input class="form-control" ng-model="answer1" placeholder="Your answer to question" autocomplete="off"/>
		<div error-response field="answer1" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<div class="fldWrp">
		<select ng-model="question2" ng-options="q.id as q.text for q in questionList2">
			<option value="">- Select a second question -</option>
		</select>
		<div error-response field="question2" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	<div class="fldWrp">
		<input class="form-control" ng-model="answer2" placeholder="Your answer to second question" autocomplete="off"/>
		<div error-response field="answer2" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
	</div>
	
	<div class="submit">
		<button ng-click="save()" type="button" class="btn btn-primary grn">Submit</button>
	</div>
</form>