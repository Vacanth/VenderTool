<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table ng-controller="EmailCtrl" class="info" cellspacing="0" cellpadding="0">
	<tr>
		<td class="col1"><label>Email:</label></td>
		<td class="col2"><input ng-model="changeEmailRequest.oldEmailId" class="form-control readonly" readonly/></td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>New email:</label></td>
		<td class="col2">
			<input ng-model="changeEmailRequest.newEmail" class="form-control"/>
			<div error-response field="newEmail" clss="com.vendertool.sharedtypes.rnr.ChangeEmailRequest"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Confirm new email:</label></td>
		<td class="col2">
			<input ng-model="changeEmailRequest.confirmEmail" class="form-control"/>
			<div error-response field="confirmEmail" clss="com.vendertool.sharedtypes.rnr.ChangeEmailRequest"></div>
		</td>
	</tr>

	<tr>
		<td colspan="2">
			<hr class="sep"/>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Current Password:</label></td>
		<td class="col2">
			<input ng-model="changeEmailRequest.password" class="form-control" type="password"/>
			<div error-response field="password" clss="com.vendertool.sharedtypes.rnr.ChangeEmailRequest"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="actns">
			<div class="sub-cncl">
				<button ng-click="saveEmail()" type="button" class="btn btn-primary grn">Submit</button>
				
				<%-- 
				<input ng-click="saveEmail()" type="submit" class="btn lg grn" value="Submit"/>--%>
				<a ng-click="resetEmail()" class="cncl lg" href="javascript:;">Cancel</a>
			</div>
		</td>
	</tr>
	
</table>


