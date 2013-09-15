<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/errorResponse" %>

<t1:page title="${title}" currentPage="register" email="${email}">
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/register.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/register.js' />"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		$('input').placeholder();
	</jsp:attribute>
	
	<jsp:body>
		<div id="pgBg"><img src="resources/img/cafe.jpg" alt=""></div>
	
		<spring:message code="form.registration.title" var="title"/>
		<c:set var="email" value="${account.emailId}"/>
		<div class="reg main input-group">
			<h3 class="ttl"><spring:message code="form.registration.signup"/></h3>
			<spring:message code='form.registration.firstname' var="firstname"/>
			<spring:message code='form.registration.lastname' var="lastname"/>
			<spring:message code='form.registration.email' var="emailId"/>
			<spring:message code='form.registration.password' var="password"/>
			<spring:message code='form.registration.confirmpassword' var="confirmpassword"/>
			<spring:message code='form.submit' var="submit"/>
			
			<spring:message code='form.registration.email.info' var="emailInfo"/>
			<spring:message code='form.registration.password.info' var="pwdInfo"/>
			<spring:message code='form.registration.confirmpassword.info' var="confPwdInfo"/>
	
			<form:form method="post" commandName="account">
				<c:if test="${errorResponse.hasErrors()}">
					<div class="pg-msg">
						<div class="alert alert-danger">
							<c:forEach items="${errorResponse.getVTErrors()}" var="vterror">
								${vterror.message}<br />
							</c:forEach>
						</div>
					</div>
				</c:if>
				
				<div class="fldWrp">
					<form:input class="form-control" placeholder="${firstname}"  path="contactDetails.firstName"/>
					<t2:errorResponse model="${errorResponse}" field="firstName" clss="com.vendertool.sharedtypes.core.ContactDetails"/>
				</div>
				<div class="fldWrp">
					<form:input class="form-control" placeholder="${lastname}"  path="contactDetails.lastName"/>
					<t2:errorResponse model="${errorResponse}" field="lastName" clss="com.vendertool.sharedtypes.core.ContactDetails"/>
				</div>
				<div class="fldWrp">
					<c:set var="emailIdpath" value="emailId" />
					<form:input id="emailId" class="form-control info-msg-available" placeholder="${emailId}" path="${emailIdpath}" data-content="${emailInfo}"/>
					<t2:errorResponse model="${errorResponse}" field="${emailIdpath}" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>
				<div class="fldWrp">
					<c:set var="passwordpath" value="password" />
					<form:password id="password" class="form-control info-msg-available" placeholder="${password}" path="${passwordpath}" data-content="${pwdInfo}"/>
					<t2:errorResponse model="${errorResponse}" field="${passwordpath}" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>
				<div class="fldWrp">
					<c:set var="confirmPasswordPath" value="confirmPassword" />
					<form:password id="confirmpassword" class="form-control info-msg-available" placeholder="${confirmpassword}" path="${confirmPasswordPath}"  data-content="${confPwdInfo}"/>
					<t2:errorResponse model="${errorResponse}" field="${confirmPasswordPath}" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>

				<div class="submit">
					<input type="submit" class="btn btn-primary grn" value="${submit}" />
				</div>

				<%-- 
				<c:if test="${errorResponse.hasErrors()}">
					<div>
						<div>
							<label for="jsonAccountOutput">JSON Account Output: </label>	
							<textarea id="jsonAccountOutput" readonly rows="8" cols="55"><c:out value="${json_account_output}" /></textarea>
						</div>
						<div>
							<label for="jsonErrorResponseOutput">JSON Error Response Output: </label>	
							<textarea id="jsonErrorResponseOutput" readonly rows="8" cols="55"><c:out value="${json_err_res_output}"/></textarea>
						</div>
					</div>
				</c:if>
				--%>
				
			</form:form>
		</div>
	</jsp:body>
	
</t1:page>
