<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<spring:message code='form.submit' var="submit"/>

<div ng-controller="QuestionsCtrl">
	<div class="pg-msg">
		<div class="alert alert-success questions  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			Questions and answers have been updated.
		</div>
		<div class="alert alert-danger qry-httpError"></div>
		<div page-error></div>
	</div>

	<table class="info" cellspacing="0" cellpadding="0">
		<tr class="fldWrp">
			<td class="col1"><label>Question 1:</label></td>
			<td class="col2">
				<select ng-model="question1" ng-options="q.questionCode as q.questionDisplayName for q in questionList1">
					<option value="">- Select a question -</option>
				</select>
				<div field-error field="question1" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>Answer:</label></td>
			<td class="col2">
				<input class="form-control" ng-model="answer1" autocomplete="off"/>
				<div field-error field="answer1" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
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
				<select ng-model="question2" ng-options="q.questionCode as q.questionDisplayName for q in questionList2">
					<option value="">- Select a second question -</option>
				</select>
				<div field-error field="question2" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>Answer:</label></td>
			<td class="col2">
				<input class="form-control" ng-model="answer2" autocomplete="off"/>
				<div field-error field="answer2" type="com.vendertool.sitewebapp.controller.SecurityQuestionAnswer"></div>
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
				<input ng-model="password" type="password" autocomplete="off" class="form-control" />
				<div field-error field="password" type="com.vendertool.sharedtypes.rnr.ChangeEmailRequest"></div>
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

</div>
 
 