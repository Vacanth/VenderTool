<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div ng-controller="AccountCtrl">
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
					<%--
					<input ng-click="saveAccount()" type="submit" class="btn lg grn" value="Submit"/> --%>
					<a ng-click="resetAccount()" class="cncl lg" href="javascript:;">Cancel</a>
				</div>
			</td>
		</tr>
		
	</table>
</div>

