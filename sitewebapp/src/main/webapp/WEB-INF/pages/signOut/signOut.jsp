<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>

<t1:page title="${signouttitle}" currentPage="signin" email="${email}">
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
	    <div class="reg main input-group bx-rnd-shdw">
	    	<spring:message code="form.signout.signout" var="signouttitle"/>

	    	<h3 class="ttl"><c:out value="${signouttitle}" /></h3>
	    	<h5 class="ttl"><spring:message code="form.signout.signoutmsg"/></h5>
	    	<span><a href="signIn"><spring:message code="form.signin.signin"/></a></span>
	    	
		</div>	    	
	</jsp:body>
</t1:page>	