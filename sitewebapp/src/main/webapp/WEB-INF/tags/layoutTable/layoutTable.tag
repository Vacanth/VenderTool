<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--============
Page fragments
================--%>
<%@ attribute name="pageTitle"	fragment="true"%>
<%@ attribute name="leftNav"	fragment="true"%>
<%@ attribute name="topNav"		fragment="true"%>
<%@ attribute name="content"	fragment="true"%>



<table class="layout input-group" cellpadding="0" cellspacing="0" border="0">

	<c:choose>
		<c:when test="${not empty leftNav}">
			<tr>
				<td class="c1">
					<h3 class="ttl"><jsp:invoke fragment="pageTitle"/></h3>
					<jsp:invoke fragment="leftNav"/>
				</td>
				<td class="c2">
					<jsp:invoke fragment="topNav"/>
					
					<jsp:invoke fragment="content"/>
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr class="headRow">
				<td class="c1">
					<h3 class="ttl"><jsp:invoke fragment="pageTitle"/></h3>
				</td>
				<td class="c2"> 
					<jsp:invoke fragment="topNav"/>
				</td>
			</tr>
			<tr class="mainRow">
				<td class="c1" colspan="2">
					<jsp:invoke fragment="content"/>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
	
</table>


