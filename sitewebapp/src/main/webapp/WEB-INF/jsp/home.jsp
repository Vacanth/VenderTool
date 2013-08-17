<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="email" value="ted@gmail.com"/>

<t:page title="VendorTool">
	<jsp:attribute name="header">
		<t:header email="${email}" currentPage="home"/>
	</jsp:attribute>

	<jsp:attribute name="css">
		<link href="<c:url value='/resources/css/home.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="home main input-group bx-rnd-shdw">
			home page
		</div>
	</jsp:body>
</t:page>