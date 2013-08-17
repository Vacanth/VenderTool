<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<t:page title="VendorTool" angularAppName="accountApp">

	<jsp:attribute name="header">
		<t:header email="${accFieldSet.email.value}" currentPage="account"/>
	</jsp:attribute>
	
	<jsp:attribute name="scripts">
		<script src="<c:url value='/resources/js/account/app.js' />" type="text/javascript"></script>
		<script src="<c:url value='/resources/js/account/controllers.js' />" type="text/javascript"></script>
	</jsp:attribute>
	
	<jsp:attribute name="inlineJs">
		<script type="text/javascript">
			accountApp.factory('Data', function() {
				return ${acctFieldSetJson};
			});
		</script>
	</jsp:attribute>

	<jsp:attribute name="css">
		<link href="<c:url value='/resources/css/account.css' />" rel="stylesheet" type="text/css" />
	</jsp:attribute>
	
	<jsp:body>
		<div ng-controller="AccountCtrl" class="acct contact main input-group bx-rnd-shdw">
			<h3 class="ttl">Account</h3>
			
			<div class="list-group">
				<a href="#/contact" 			ng-class="getClass('/contact')" 			class="list-group-item">Contact info</a>
				<a href="#/contact/:change"		ng-class="getClass('/contact/:change')" 	class="list-group-item">Change contact info</a>
				<a href="#/email/:change"		ng-class="getClass('/email/:change')" 		class="list-group-item">Change email</a>
				<a href="#/password/:change"	ng-class="getClass('/password/:change')"	class="list-group-item">Change password</a>
			</div>
			
			<div ng-view class="info-wrp">
				
	
				<%-- 
				
				<table id="info" class="info readonly" cellspacing="0" cellpadding="0">
					<tr>
						<td class="col1"><label>Email:</label></td>
						<td class="col2"><input ng-model="account.emailId" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Address 1:</label></td>
						<td class="col2"><input ng-model="account.contact.address.addressLine1" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Address 2:</label></td>
						<td class="col2"><input ng-model="account.contact.address.addressLine2" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>City:</label></td>
						<td class="col2"><input ng-model="account.contact.address.city" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>State:</label></td>
						<td class="col2"><input ng-model="account.contact.address.state" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Zip:</label></td>
						<td class="col2"><input ng-model="account.contact.address.zip" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Country:</label></td>
						<td class="col2"><input ng-model="account.contact.address.country" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Phone (work):</label></td>
						<td class="col2"><input ng-model="account.contact.address.phones[0]" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Phone (mobile):</label></td>
						<td class="col2"><input ng-model="account.contact.address.phones[1]" class="form-control" readonly /></td>
					</tr>
					<tr>
						<td class="col1"><label>Phone (home):</label></td>
						<td class="col2"><input ng-model="account.contact.address.phones[2]" class="form-control" readonly /></td>
					</tr>
					
				</table>
				--%>
				
				<%-- 
				<!--===================================
				Edit profile
				======================================= -->
				<form method="post" id="profile-form">
					<table border="0" id="edit-info" class="info edit input-group" cellspacing="0" cellpadding="0">
						<c:forEach var="entry" items="${labels}">
							<tr>
								<td class="ng-class:{'err': field.hasError}; col2">
									<label>${entry.value}:</label>
								</td>
								<td class="col2">
									<input ng-model="fieldMap['${entry.key}'].value" name="${entry.key}" class="form-control" />
									<div class="err-msg">
										<span ng-repeat="msg in fieldMap['${entry.key}'].errorMessages">
											{{msg}}<br/>
										</span>
									</div>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td>
							</td>
							<td class="col2">
								<input ng-click="saveProfile()" type="submit" class="btn lg" value="Submit"/>
							</td>
						</tr>
					</table>
				</form>
				
				<!--===================================
				Edit email
				======================================= -->
				<table border="0" id="edit-email" class="info edit input-group" cellspacing="0" cellpadding="0">
					<tr>
						<td class="col1">
							<label>${profile.email.label}:</label>
						</td>
						<td class="col2">
							<input class="form-control" name="${profile.email.name}" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col1">
							<label>Re-enter ${profile.email.label}:</label>
						</td>
						<td class="col2">
							<input class="form-control" name="${profile.email.name}" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col1">
							<label>${profile.password.label}:</label>
						</td>
						<td class="col2">
							<input class="form-control" name="${profile.password.name}" value=""/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td class="col2">
							<input type="submit" class="btn lg" value="Submit"/>
						</td>
					</tr>
				</table>
				
				
				--%>
			</div><!-- end of class="info-wrp" -->
			  
			
			
		</div><!-- end of ng-controller="ProfileCtrl" -->
	</jsp:body>

</t:page>



