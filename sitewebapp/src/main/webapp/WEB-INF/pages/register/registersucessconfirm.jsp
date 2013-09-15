<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>



<t1:page title="VendorTool registration" currentPage="registersuccessconfirm"  email="${email}">
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div class="reg main input-group bx-rnd-shdw">
	        <h3 class="ttl">Thanks for registering!</h3>
	        <div class="msg">
	            <a href="accounthub">Click here to go to your new Hub page.</a>
	        </div>
	    </div>
	</jsp:body>
</t1:page>
