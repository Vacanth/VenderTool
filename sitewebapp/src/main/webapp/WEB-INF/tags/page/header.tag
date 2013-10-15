<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Get URLs
================--%>
<%@ tag import="com.vendertool.sitewebapp.common.URLHelper" %>
<%
	request.setAttribute("homeUrl", URLHelper.getHomeUrl());
	request.setAttribute("accountHubUrl", URLHelper.getAccountHubUrl());
	request.setAttribute("profileUrl", URLHelper.getProfileUrl());
%>


<%--============
Attributes
================--%>
<%@ attribute name="email"			required="false"%>
<%@ attribute name="currentPage"	required="false"%>

<div id="header">
	<div class="bg"></div>
	<div class="hdr">
		<div class="topnav inline">
			<a href="${homeUrl}" class="home"><spring:message code="form.header.logo"/></a>
			
			<c:if test="${!empty email}">
				<a href="${accountHubUrl}" class="accounthub">Account Hub</a>
				<a href="settings" class="settings">Settings</a>
			</c:if>
			
			<%-- 
			<a href="#"><spring:message code="form.header.platform"/></a>
			<a href="#"><spring:message code="form.header.tools"/></a>
			<a href="#"><spring:message code="form.header.pricing"/></a>--%>
		</div>
		<div class="ctls">
			
			<c:choose>
				<c:when test="${empty email}">
					<div class="sign-in inline">
					
						<%--=================
						Sign In 
						=====================--%>
						<c:choose>
							<c:when test="${currentPage == 'signin'}">
								<spring:message code="form.signin.signin"/>
							</c:when>
							<c:otherwise>
								<a href="signIn"><spring:message code="form.signin.signin"/></a>
							</c:otherwise>
						</c:choose>
						|
						
						<%--=================
						Sign Up
						=====================--%>
						<c:choose>
							<c:when test="${currentPage == 'register'}">
								<spring:message code="form.registration.signup"/>
							</c:when>
							<c:otherwise>
								<a href="register"><spring:message code="form.registration.signup"/></a>
							</c:otherwise>
						</c:choose>
					</div>
				</c:when>
				<c:otherwise>
					<%--=================
					User Menu
					=====================--%>
					<div class="user inline">
					
						<%-- Remove later 
						<a id="profileBtn" href="javascript:;">${email} <b class="arw"></b></a>
						<ul id="profileMenu" class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu">
						  <li><a tabindex="-1" href="${profileUrl}">Profile</a></li>
						  <li class="divider"></li>
						  <li><a tabindex="-1" href="<c:url value="j_spring_security_logout" />">Sign-out</a></li>
						</ul>
						--%>
						
						<div class="dropdown">
							<a data-toggle="dropdown" href="#"> ${email} <span class="caret"></span></a>
							<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dLabel">
								<li role="presentation"><a role="menuitem" tabindex="-1" href="${profileUrl}">Profile</a></li>
								<li role="presentation" class="divider"></li>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="<c:url value="/j_spring_security_logout" />">Sign-out</a></li>
							</ul>
						</div>
					
					</div>
					<%--=================
					Search Box
					=====================--%>
					<form class="srch inline">
						<input/>
						<button title="Search"></button>
					</form>
				</c:otherwise>
			</c:choose>
			
			<%--=================
			Language Menu
			=====================--%>
			<c:if test="${empty email && !empty languages}">
				<div class="lang inline">
					<c:forEach var="lang" items="${languages}">
						<c:if test="${lang.isoLangCode == selectedLang}">
							<c:set var="selectedLangText" value="${lang.nativeName}"/>
						</c:if>
					</c:forEach>
					<c:if test="${empty selectedLangText}">
						<c:set var="selectedLangText" value="Select language"/>
					</c:if>
					
					<%-- Remove later
					<a id="langBtn" href="javascript:;"><span class="lbl"><spring:message code="form.registration.lang"/>:</span> ${selectedLangText} <b class="arw"></b></a>
					<ul id="languageMenu" name="lang" class="dropdown-menu pull-left" role="menu" aria-labelledby="dropdownMenu">
						<c:forEach var="lang" items="${languages}">
							<c:set var="selected" value="${lang.isoLangCode == selectedLang ? 'selected' : ''}"/>
							<li><a tabindex="-1" href="?lang=${lang.isoLangCode}">${lang.nativeName}</a></li>
						</c:forEach>
					</ul>
					--%>
					
					<div class="dropdown">
						<span class="lbl"><spring:message code="form.registration.lang"/>:</span><a data-toggle="dropdown" href="#"> ${selectedLangText} <span class="caret"></span></a>
						<ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dLabel">
							<c:forEach var="lang" items="${languages}">
								<c:set var="selected" value="${lang.isoLangCode == selectedLang ? 'selected' : ''}"/>
								<li role="presentation"><a role="menuitem" tabindex="-1" href="?lang=${lang.isoLangCode}">${lang.nativeName}</a></li>
							</c:forEach>
						</ul>
					</div>
				
				</div>
			</c:if>
			
		</div>
	</div>
</div>

