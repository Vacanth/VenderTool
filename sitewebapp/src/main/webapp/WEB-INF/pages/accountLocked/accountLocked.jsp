<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="error" tagdir="/WEB-INF/tags/errorResponse" %>

<t1:page title="Forgot password" currentPage="forgotPassword" email="">

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/register.js' />" type="text/javascript"></script>
	</jsp:attribute>

	<jsp:body>
		<div id="pgBg"><img src="resources/img/cafe2.jpg" alt=""></div>
	    <div class="reg main" style="padding-bottom:15px;">
   			<h3 class="ttl">Account locked</h3>
			<p>Sorry, your account has been locked.</p>
	    </div>
	</jsp:body>
	
</t1:page>
