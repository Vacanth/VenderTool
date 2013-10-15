<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Attributes
================ --%>
<%@ attribute name="model" required="true" type="com.vendertool.sharedtypes.rnr.ErrorResponse" %>


<c:if test="${model.hasErrors()}">
	<div class="pg-msg">
		<div class="alert alert-danger">
			<c:forEach var="vterror" items="${model.getVTErrors()}" >
				${vterror.message}<br />
			</c:forEach>
		</div>
	</div>
</c:if>