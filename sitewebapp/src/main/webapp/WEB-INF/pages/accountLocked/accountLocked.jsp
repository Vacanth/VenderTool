<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>

<t1:page title="Forgot password" currentPage="forgotPassword" email="">

	<jsp:attribute name="css">
		<link href="<c:url value='/css/page/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>

	<jsp:body>
		<div id="pgBg"><img src="<c:url value='/img/cafe2.jpg'/>" alt=""></div>
	    <div class="reg main" style="padding-bottom:15px;">
   			<h3 class="ttl">Account locked</h3>
			<p>Sorry, your account has been locked.</p>
	    </div>
	</jsp:body>
	
</t1:page>
