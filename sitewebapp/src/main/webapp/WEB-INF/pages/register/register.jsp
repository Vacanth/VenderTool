<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/errorResponse" %>

<spring:message code='form.registration.firstname' var="c_fname"/>
<spring:message code='form.registration.lastname' var="c_lname"/>
<spring:message code='form.registration.email' var="c_email"/>
<spring:message code='form.registration.password' var="c_password"/>
<spring:message code='form.registration.confirmpassword' var="c_confirmpassword"/>
<spring:message code='form.submit' var="c_submit"/>
<spring:message code='form.registration.email.tip' var="c_emailTip"/>
<spring:message code='form.registration.password.tip' var="c_pwdTip"/>
<spring:message code='form.registration.confirmpassword.tip' var="c_confPwdTip"/>
			
<t1:page title="Registration" currentPage="register" email="${account.email}">
	
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
		<c:set var="email" value="${account.email}"/>
		<div class="reg main input-group">
			<h3 class="ttl"><spring:message code="form.registration.signup"/></h3>

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
					<form:input class="form-control" placeholder="${c_fname}"  path="contactDetails.firstName"/>
					<t2:errorResponse model="${errorResponse}" field="firstName" clss="com.vendertool.sharedtypes.core.ContactDetails"/>
				</div>
				<div class="fldWrp">
					<form:input class="form-control" placeholder="${c_lname}"  path="contactDetails.lastName"/>
					<t2:errorResponse model="${errorResponse}" field="lastName" clss="com.vendertool.sharedtypes.core.ContactDetails"/>
				</div>
				<div class="fldWrp">
					<form:input id="email" class="form-control info-msg-available" placeholder="${c_email}" path="email" data-content="${c_emailTip}"/>
					<t2:errorResponse model="${errorResponse}" field="email" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>
				<div class="fldWrp">
					<form:password id="password" class="form-control info-msg-available" placeholder="${c_password}" path="password" data-content="${c_pwdTip}"/>
					<t2:errorResponse model="${errorResponse}" field="password" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>
				<div class="fldWrp">
					<form:password id="confirmpassword" class="form-control info-msg-available" placeholder="${c_confirmpassword}" path="confirmPassword"  data-content="${c_confPwdTip}"/>
					<t2:errorResponse model="${errorResponse}" field="confirmPassword" clss="com.vendertool.sharedtypes.core.Account"/>
				</div>

				<div class="submit">
					<input type="submit" class="btn btn-primary grn" value="${c_submit}" />
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
