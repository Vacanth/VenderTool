<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Attributes
================--%>
<%@ attribute name="email"			required="false"%>
<%@ attribute name="currentPage"	required="false"%>

<div id="header">
	<div class="hdr">

		<div class="topnav inline">
			<a href="#"><spring:message code="form.header.logo"/></a>
			
			<%-- 
			<a href="#"><spring:message code="form.header.platform"/></a>
			<a href="#"><spring:message code="form.header.tools"/></a>
			<a href="#"><spring:message code="form.header.pricing"/></a>--%>
		</div>
		<div class="ctls">
			
			<c:choose>
				<c:when test="${empty email}">
					<div class="sign-in inline">
						<c:choose>
							<c:when test="${currentPage == 'signin'}">
								<spring:message code="form.signin.signin"/>
							</c:when>
							<c:otherwise>
								<a href="signin"><spring:message code="form.signin.signin"/></a>
							</c:otherwise>
						</c:choose>
						|
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
					<div class="user inline">
						<a href="#">${email} </a>
						<a href="#" class="arw"></a>
						
						| <a href="">Sign-out</a>
					</div>
					<form class="srch inline">
						<input placeholder="Search"/>
						<button title="Search"></button>
					</form>
				</c:otherwise>
			</c:choose>

			<c:if test="${empty email}">
				<%-- form submits to current page --%>
				<form href="" class="lang inline">
					<span>
						<spring:message code="form.registration.lang"/>
					</span>
					<select id="home-header-lang-menu" name="lang">
						<option value="en">English</option>
						<option value="es">español</option>
					</select>
				</form>
			</c:if>
			
		</div>
	</div>
</div>

