<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/layoutTable" %>
<%@ taglib prefix="t3" tagdir="/WEB-INF/tags/navbarSection" %>

<t1:page title="Account hub" currentPage="accounthub" email="${email}">

	<jsp:attribute name="css">
		<link href="<c:url value='/wro/accounthub.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
	
		<t2:layoutTable>
			
			<jsp:attribute name="pageTitle">Summary</jsp:attribute>
			
			<jsp:attribute name="leftNav">
				<div class="list-group">
					<a href="${accountHubUrl}" 	ng-class="getClass('/accounthub')" 	class="list-group-item">Overview</a>
					<a href="#/selling"		ng-class="getClass('/selling')" 	class="list-group-item">Selling</a>
					<a href="#/selling"		ng-class="getClass('/selling')" 	class="list-group-item">Orders</a>
					<a href="#/selling"		ng-class="getClass('/selling')" 	class="list-group-item">Selling</a>
					<a href="#/inventory"	ng-class="getClass('/inventory')"	class="list-group-item">Inventory</a>
				</div>
			</jsp:attribute>
			
			<jsp:attribute name="topNav">
				<t3:navbarSection current="summary"/>
			</jsp:attribute>

			<jsp:attribute name="content">
				<div class="msg">
					<div class="alert alert-success" style="display:none;">Profile information updated.</div>
					<div class="alert alert-danger"  style="display:none;">Sorry, there's been an error.</div>
				</div>
				
				<div ng-view></div>
			</jsp:attribute>
		</t2:layoutTable>
	
	</jsp:body>
</t1:page>
