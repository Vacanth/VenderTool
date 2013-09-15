<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>

<c:set var="email" value="ted@gmail.com"/>

<t1:page title="VendorTool" currentPage="home" email="${email}">

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/home.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		home page
	</jsp:body>
</t1:page>