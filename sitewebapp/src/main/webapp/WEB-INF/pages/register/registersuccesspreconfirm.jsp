<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>

<t1:page title="${title}" currentPage="registersuccesspreconfirm" email="${email}">

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
	<spring:message code="form.registration.title"  var="title"/>
		<div class="reg main input-group bx-rnd-shdw">
		<spring:message code="form.signin.signin" var="signintitle"/>
		<spring:message code="form.registration.regpreconfirm.title" var="regpreconfirmtitle"/>
		<spring:message code="form.registration.regpreconfirm.msg1" var="msg1"/>
		<spring:message code="form.registration.regpreconfirm.msg2" var="msg2"/>
	        <h3 class="ttl"><c:out value="${regpreconfirmtitle}" /></h3>
	        <div class="msg">
	            <c:out value="${msg1}" /> <br/>
	            <c:out value="${msg2}" /> <a href="<c:url value="signin" />"> <c:out value="${signintitle}" /> </a>
	        </div>
	    </div>
	</jsp:body>
</t1:page>