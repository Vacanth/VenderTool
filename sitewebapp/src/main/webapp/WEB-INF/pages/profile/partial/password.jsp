<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table ng-controller="PasswordCtrl" class="info" cellspacing="0" cellpadding="0">
	<%--
	<tr>
		<td class="col1"><label>Email:</label></td>
		<td class="col2"><input ng-model="accountOrig.emailId" class="form-control readonly" readonly/></td>
	</tr>--%>
	<tr class="fldWrp">
		<td class="col1"><label>Current Password:</label></td>
		<td class="col2">
			<input ng-model="changePasswordRequest.oldPassword" class="form-control" type="password"/>
			<div error-response field="oldPassword" clss="com.vendertool.sharedtypes.rnr.ChangePasswordRequest"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>New password:</label></td>
		<td class="col2">
			<input ng-model="changePasswordRequest.newPassword" class="form-control" type="password"/>
			<div error-response field="newPassword" clss="com.vendertool.sharedtypes.rnr.ChangePasswordRequest"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Confirm new password:</label></td>
		<td class="col2">
			<input ng-model="changePasswordRequest.confirmPassword" class="form-control" type="password"/>
			<div error-response field="confirmPassword" clss="com.vendertool.sharedtypes.rnr.ChangePasswordRequest"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="actns">
			<div class="sub-cncl">
				<button ng-click="savePassword()" type="button" class="btn btn-primary grn">Submit</button>
				<%-- 
				<input ng-click="savePassword()" type="submit" class="btn lg grn" value="Submit"/>--%>
				<a ng-click="resetPassword()" class="cncl lg" href="javascript:;">Cancel</a>
			</div>
		</td>
	</tr>
	
</table>


