<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t1" tagdir="/WEB-INF/tags/page" %>
<%@ taglib prefix="t2" tagdir="/WEB-INF/tags/layoutTable" %>

<spring:message code='HTTP.ERROR' var="c_httpError"/>

<t1:page title="VendorTool" angularAppName="profileApp" currentPage="profile" email="${account.email}" >
	
	<jsp:attribute name="css">
		<link href="<c:url value='/wro/profile.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/wro/profile.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		profileApp.factory('Data', function() {
			return ${modelMapJson};
		});

		profileApp.factory('Content', function() {
			return {"httpError":"${c_httpError}"};
		});
	</jsp:attribute>

	<jsp:body>
	
		<t2:layoutTable>
			<jsp:attribute name="pageTitle">Profile</jsp:attribute>
			
			<jsp:attribute name="leftNav">
				<div class="list-group" ng-controller="NavCtrl">
					<a href="#/info"		ng-class="getClass('/info')" 		class="list-group-item">Change info</a>
					<a href="#/email"		ng-class="getClass('/email')" 		class="list-group-item">Change email</a>
					<a href="#/password"	ng-class="getClass('/password')"	class="list-group-item">Change password</a>
					<a href="#/questions"	ng-class="getClass('/questions')"	class="list-group-item">Change security questions</a>
				</div>
			</jsp:attribute>
			
			<jsp:attribute name="content">
				<div ng-controller="InfoCtrl">
					<div ng-view></div>

<script type="text/ng-template" id="info">
	<div class="pg-msg">
		<div class="alert alert-success profile" >Profile has been updated.</div>
		<div class="alert alert-danger qry-httpError"></div>
		<div page-error></div>
	</div>
					
	<table class="info" cellspacing="0" cellpadding="0">
		<tr>
			<td class="col1"><label>First name:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.firstName" class="form-control"/>
				<div field-error field="firstName" type="com.vendertool.sharedtypes.core.ContactDetails"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Last name:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.lastName" class="form-control"/>
				<div field-error field="lastName" type="com.vendertool.sharedtypes.core.ContactDetails"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Address 1:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.address.addressLine1" class="form-control" />
				<div field-error field="addressLine1" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Address 2:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.address.addressLine2" class="form-control" />
				<div field-error field="addressLine2" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr class="fldWrp fldWrp">
			<td class="col1"><label>City:</label></td>
			<td class="col2">
				<input ng-model="accountEdit.contactDetails.address.city" class="form-control" />
				<div field-error field="city" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>State:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.address.state" class="form-control" />
				<div field-error field="state" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Zip:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.address.postalCode" class="form-control" />
				<div field-error field="postalCode" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Country:</label></td>
			<td class="col2 fldWrp">
				<select class="cntryMenu" ng-model="accountEdit.contactDetails.address.country" ng-options="c.val as c.txt for c in countryOptions">
					<option value="">Select your country</option>
				</select>
				<%--
				<select class="cntryMenu">
					<option value="${entry.key}">Select your country</option>
					<c:forEach var="entry" items="${countryMap}">
						<option value="${entry.key}">${entry.value}</option>
					</c:forEach>
				</select>
				--%>
				<div field-error field="country" type="com.vendertool.sharedtypes.core.Address"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Phone (work):</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.phones['WORK'].number" class="form-control" />
				<div field-error field="number" type="com.vendertool.sharedtypes.core.Phone"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Phone (mobile):</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.phones['MOBILE'].number" class="form-control" />
				<div field-error field="number" type="com.vendertool.sharedtypes.core.Phone"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Phone (company):</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.phones['PUBLIC'].number" class="form-control" />
				<div field-error field="number" type="com.vendertool.sharedtypes.core.Phone"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Phone (fax):</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.phones['FAX'].number" class="form-control" />
				<div field-error field="number" type="com.vendertool.sharedtypes.core.Phone"></div>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Phone (home):</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountEdit.contactDetails.phones['HOME'].number" class="form-control" />
				<div field-error field="number" type="com.vendertool.sharedtypes.core.Phone"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<hr class="sep"/>
			</td>
		</tr>
		<tr>
			<td class="col1"><label>Current Password:</label></td>
			<td class="col2 fldWrp">
				<input ng-model="accountOrig.password" class="form-control" type="password"/>
				<div field-error field="password" type="com.vendertool.sharedtypes.core.Account"></div>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="actns">
				<div class="sub-cncl">
					<button ng-click="saveAccount()" type="button" class="btn btn-primary grn">Submit</button>
					<a ng-click="resetAccount()" class="cncl lg" href="javascript:;">Cancel</a>
				</div>
			</td>
		</tr>
		
	</table>
</script>

				</div><!-- End of AccountCtrl -->
			</jsp:attribute>
		</t2:layoutTable>
	</jsp:body>
</t1:page>



