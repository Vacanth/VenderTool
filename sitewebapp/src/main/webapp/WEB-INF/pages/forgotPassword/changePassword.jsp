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
	
	<jsp:attribute name="inlineJs">
		$('input').placeholder();
	</jsp:attribute>
	
	<jsp:body>
		<spring:message code='form.submit' var="submit"/>
		
		<div id="pgBg"><img src="resources/img/cafe2.jpg" alt=""></div>

	    <div class="reg main input-group" style="padding-bottom:15px;">

	        <h3 class="ttl">Please create a new password</h3>

			<c:if test="${errorResponse.hasErrors()}">
				<div class="pg-msg">
					<div class="alert alert-danger">
						<c:forEach items="${errorResponse.getVTErrors()}" var="vterror">
							${vterror.message}<br />
						</c:forEach>
					</div>
				</div>
			</c:if>
			
			<form:form method="post" action="processChangePassword" commandName="forgotPasswordReq">
				<form:hidden path="emailToken"/>
	            <div class="fldWrp">
	            	<form:input path="newPassword" class="form-control" placeholder="Password" />
	            </div>
	             <div class="fldWrp">
	            	<form:input path="confirmPassword" class="form-control" placeholder="Confirm password" />
	            </div>
	            <div class="submit">
	            	<input type="submit" class="btn btn-primary grn" value="${submit}" />
	            </div>
	        </form:form>
	    </div>

	</jsp:body>
</t1:page>
