<%@ tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--============
Attributes
================ --%>
<%@ attribute name="model" required="true" type="com.vendertool.sharedtypes.rnr.ErrorResponse" %>
<%@ attribute name="field" required="true"%>
<%@ attribute name="clss" required="true"%>


<c:if test="${model.hasFieldError(clss, field)}">
	<div class="err-msg qry-errResp">
		<c:forEach items="${model.getFieldErrors(clss, field)}" var="vterror">
			<div>${vterror.message}</div>
		</c:forEach>
	</div>
</c:if>
