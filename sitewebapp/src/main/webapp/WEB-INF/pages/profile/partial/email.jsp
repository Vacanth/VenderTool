<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="EmailCtrl">
	<div class="pg-msg">
		<div class="alert alert-success email  alert-dismissable">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<p>Please check your new email account for our message to confirm email change.</p>
			<p>After you confirm the email change, begin using your new email to login.</p>
			<br/>
			<p><b>Note</b>: The email change will not take affect until your confirm the change.</p>
		</div>
		<div class="alert alert-danger qry-httpError"></div>
		<div page-error></div>
	</div>
	<table class="info" cellspacing="0" cellpadding="0">
		<tr>
			<td class="col1"><label>Email:</label></td>
			<td class="col2">
				<input ng-model="changeEmail.currentEmail" class="form-control readonly" readonly/>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>New email:</label></td>
			<td class="col2">
				<input ng-model="changeEmail.newEmail" class="form-control"/>
				<div field-error field="newEmail" type="com.vendertool.sharedtypes.core.ChangeEmail"></div>
			</td>
		</tr>
		<tr class="fldWrp">
			<td class="col1"><label>Confirm new email:</label></td>
			<td class="col2">
				<input ng-model="changeEmail.confirmEmail" class="form-control"/>
				<div field-error field="confirmEmail" type="com.vendertool.sharedtypes.core.ChangeEmail"></div>
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
				<input ng-model="changeEmail.password" class="form-control" type="password"/>
				<div field-error field="password" type="com.vendertool.sharedtypes.core.ChangeEmail"></div>
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

</div>
