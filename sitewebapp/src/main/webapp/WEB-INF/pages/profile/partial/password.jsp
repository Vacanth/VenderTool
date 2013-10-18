<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div ng-controller="PasswordCtrl">
	<div class="pg-msg">
		<div class="alert alert-success password  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			Password has been updated.
		</div>
		<div class="alert alert-danger qry-httpError"></div>
		<div page-error></div>
	</div>

	<table class="info" cellspacing="0" cellpadding="0">
		<tr class="fldWrp">
			<td class="col1"><label>Current Password:</label></td>
			<td class="col2">
				<input ng-model="changePassword.currentPassword" class="form-control" type="password"/>
				<div field-error field="currentPassword" type="com.vendertool.sharedtypes.core.ChangePassword"></div>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>New password:</label></td>
			<td class="col2">
				<input ng-model="changePassword.newPassword" class="form-control" type="password"/>
				<div field-error field="newPassword" type="com.vendertool.sharedtypes.core.ChangePassword"></div>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>Confirm new password:</label></td>
			<td class="col2">
				<input ng-model="changePassword.confirmPassword" class="form-control" type="password"/>
				<div field-error field="confirmPassword" type="com.vendertool.sharedtypes.core.ChangePassword"></div>
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
</div>

