<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>


<t1:page title="Unexpected exception" currentPage="unexpectedException" email="${email}">
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">Unexpected Exception</h3>
	        <div class="msg">
	            Sorry, an exception has occurred, please report the problem.
	        </div>
	    </div>
	</jsp:body>
</t1:page>
