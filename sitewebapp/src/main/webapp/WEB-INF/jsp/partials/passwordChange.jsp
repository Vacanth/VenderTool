<table id="info" class="info" cellspacing="0" cellpadding="0">
	<tr>
		<td class="col1"><label>New password:</label></td>
		<td class="col2"><input ng-model="acctFieldSet.newPassword.value" type="password" class="form-control" /></td>
	</tr>
	<tr>
		<td class="col1"><label>Confirm new password:</label></td>
		<td class="col2"><input ng-model="acctFieldSet.newPasswordEdit.value" type="password" class="form-control" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<hr class="sep"/>
		</td>
	</tr>
	<tr>
		<td class="col1"><label>Current Password:</label></td>
		<td class="col2"><input ng-model="acctFieldSet.password.value" type="password" class="form-control" /></td>
	</tr>
	<tr>
		<td colspan="2" class="actns">
			<div class="sub-cncl">
				<input  type="submit" class="btn lg" value="Submit"/>
				<a ng-click="reset()" class="cncl lg" href="javascript:;">Cancel</a>
			</div>
		</td>
	</tr>
	
</table>


