<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<spring:message code='form.submit' var="submit"/>

<div ng-controller="QuestionsCtrl">
	
	<div class="pg-msg">
	</div>
	
	<table class="info" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<div class="introHd">Your current security questions are:</div>
				<div class="introQues">
					<div>1) Who was your favorite teacher?</div>
					<div>2) What was your first car?</div>
				</div>

				<a href="profile#/questions">Change security questions</a>
			</td>
		</tr>
	</table>

</div>
 
 