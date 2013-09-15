<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code='form.submit' var="submit"/>


<table class="info" cellspacing="0" cellpadding="0" ng-controller="QuestionsCtrl">
	<%--
	<tr>
		<td class="col1"><label>Email:</label></td>
		<td class="col2"><input ng-model="changeEmailRequest.oldEmailId" class="form-control readonly" readonly/></td>
	</tr> --%>
	<tr class="fldWrp">
		<td class="col1"><label>Question 1:</label></td>
		<td class="col2">
			<select ng-model="question1" ng-options="q.id as q.text for q in questionList1">
				<option value="">- Select a question -</option>
			</select>
			<div error-response field="question1" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Answer:</label></td>
		<td class="col2">
			<input class="form-control" ng-model="answer1" autocomplete="off"/>
			<div error-response field="answer1" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			&nbsp;
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Question 2:</label></td>
		<td class="col2">
			<select ng-model="question2" ng-options="q.id as q.text for q in questionList2">
				<option value="">- Select a second question -</option>
			</select>
			<div error-response field="question2" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Answer:</label></td>
		<td class="col2">
			<input class="form-control" ng-model="answer2" autocomplete="off"/>
			<div error-response field="answer2" clss="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr class="sep"/>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Current Password:</label></td>
		<td class="col2">
			<input ng-model="changeEmailRequest.password" class="form-control" type="password"/>
			<div error-response field="password" clss="com.vendertool.sharedtypes.rnr.ChangeEmailRequest"></div>
		</td>
	</tr>

	<tr>
		<td colspan="2" class="actns">
			<div class="sub-cncl">
				<button ng-click="saveQuestionAnswers()" type="button" class="btn btn-primary grn">Submit</button>
				<%-- 
				<input ng-click="saveEmail()" type="submit" class="btn lg grn" value="Submit"/>--%>
				<a ng-click="resetQuestionAnswers()" class="cncl lg" href="javascript:;">Cancel</a>
			</div>
		</td>
	</tr>
</table>


 
 