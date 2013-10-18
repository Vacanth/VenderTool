<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags/pageError" %>

<t1:page title="Security Questions" currentPage="askSecurityQuestions" email="">

	<jsp:attribute name="css">
		<link href="<c:url value='/css/page/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/js/lib/jquery.placeholder.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		$('input').placeholder();
	</jsp:attribute>
	
	<jsp:body>
		<div id="pgBg"><img src="<c:url value='/img/cafe2.jpg'/>" alt=""></div>
		
		
	    <div class="reg main input-group" style="padding-bottom:15px;">
	    	<spring:message code='form.submit' var="submit"/>
	    	
	        <h3 class="ttl">Please submit your answers</h3>
			
			<pg:pageError model="${errorResponse}"/>
			
			<form:form method="post" action="answerSecurityQuestions" commandName="validateSecurityQuestions">
				<form:hidden path="emailConfirmation.email"/>
				<form:hidden path="emailConfirmation.confirmCode"/>
				<form:hidden path="emailConfirmation.confirmSessionId"/>
				<div>
	            	${forgotPasswordReq.questions[0].question.questionDisplayName}
	            </div>
	            <div>
			       <form:input path="questions[0].answer" class="form-control" placeholder="Answer to question 1"  />
	            </div>
	            <br/>
	            <div>
	            	${forgotPasswordReq.questions[1].question.questionDisplayName}
	            </div>
	             <div>
			       <form:input path="questions[1].answer" class="form-control" placeholder="Answer to question 2"  />
	            </div>

	            <div class="submit">
	            	<input type="submit" class="btn btn-primary grn" value="${submit}" />
	            </div>
	        </form:form>
	       
	       
	    </div>
	   
	    
	</jsp:body>
</t1:page>
