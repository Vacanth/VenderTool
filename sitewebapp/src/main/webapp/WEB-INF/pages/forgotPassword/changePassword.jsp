<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="pg" tagdir="/WEB-INF/tags/pageError" %>

<t1:page title="Forgot password" currentPage="forgotPassword" email="">

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
		
		<spring:message code='form.submit' var="submit"/>

	    <div class="reg main input-group" style="padding-bottom:15px;">

	        <h3 class="ttl">Please create a new password</h3>
			
			<pg:pageError model="${errorResponse}"/>
			
			<form:form method="post" action="processChangePassword" commandName="forgotPassword">
				<form:hidden path="emailConfirmation.email"/>
				<form:hidden path="emailConfirmation.confirmCode"/>
				<form:hidden path="emailConfirmation.confirmSessionId"/>
	            <div class="fldWrp">
	            	<form:input path="newPassword" type="password" class="form-control" placeholder="Password" />
	            </div>
	             <div class="fldWrp">
	            	<form:input path="confirmPassword" type="password"  class="form-control" placeholder="Confirm password" />
	            </div>
	            <div class="submit">
	            	<input type="submit" class="btn btn-primary grn" value="${submit}" />
	            </div>
	        </form:form>
	    </div>

	</jsp:body>
</t1:page>
