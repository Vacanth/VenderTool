<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Attributes
================ --%>
<%@ attribute name="model" required="true" type="com.vendertool.sharedtypes.rnr.ErrorResponse" %>

<%---------------------------------
This is a sample errorResponse. The first error is a page level error.
The second error is a field level error since its 'bindingFieldMap' is not null.

"errorResponse:" {
	"fieldBindingErrors":[
		{
			"error":{
				"errorCode":{
					"type":"RegistrationErrorCode",
					"errorCode":"EMAIL_REQUIRED"
				},
				"message":"Email is required",
				"severity":"ERROR",
				"domain":"REGISTRATION",
				"arguments":null
			},
			"bindingFieldMap":null
		},
		{
			"error":{
				"errorCode":{
					"type":"RegistrationErrorCode",
					"errorCode":"MISSING_LASTNAME"
				},
				"message":"Last name is required",
				"severity":"ERROR",
				"domain":"REGISTRATION",
				"arguments":null
			},
			"bindingFieldMap":{
				"com.vendertool.sharedtypes.core.ContactDetails":["lastName"]
			}
		}
	],
	"status":null,
	"signedInEmail":null}
}
---------------------------------%>

<c:if test="${model.hasErrors()}">
	<div class="pg-msg">
		<div class="alert alert-danger">
			<c:forEach var="vterror" items="${model.getVTErrors()}" >
				<c:choose>
					<c:when test="${!empty vterror.message}">
						${vterror.message}
					</c:when>
					<c:otherwise>
						${vterror.errorCode.type ": " + vterror.errorCode.errorCode}
					</c:otherwise>
				</c:choose>
				<br />
			</c:forEach>
		</div>
	</div>
</c:if>