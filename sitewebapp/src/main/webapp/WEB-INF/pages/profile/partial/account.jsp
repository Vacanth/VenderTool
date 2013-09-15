<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table ng-controller="AccountCtrl" class="info" cellspacing="0" cellpadding="0">
	<%-- 
	<tr>
		<td class="col1"><label>Email:</label></td>
		<td class="col2"><input ng-model="accountOrig.emailId" class="form-control readonly" readonly/></td>
	</tr>--%>
	<tr class="fldWrp">
		<td class="col1"><label>First name:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.firstName" class="form-control"/>
			<div error-response field="firstName" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Last name:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.lastName" class="form-control"/>
			<div error-response field="lastName" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Address 1:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.address.addressLine1" class="form-control" />
			<div error-response field="addressLine1" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Address 2:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.address.addressLine2" class="form-control" />
			<div error-response field="addressLine2" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>City:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.address.city" class="form-control" />
			<div error-response field="city" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>State:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.address.state" class="form-control" />
			<div error-response field="state" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Zip:</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.address.zip" class="form-control" />
			<div error-response field="zip" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Country:</label></td>
		<td class="col2">
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
    		<div error-response field="country" clss="com.vendertool.sharedtypes.core.ContactDetails"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Phone (work):</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.phones['WORK'].number" class="form-control" />
			<div error-response field="number" clss="com.vendertool.sharedtypes.core.Phone"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Phone (mobile):</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.phones['MOBILE'].number" class="form-control" />
			<div error-response field="number" clss="com.vendertool.sharedtypes.core.Phone"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Phone (company):</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.phones['PUBLIC'].number" class="form-control" />
			<div error-response field="number" clss="com.vendertool.sharedtypes.core.Phone"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Phone (fax):</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.phones['FAX'].number" class="form-control" />
			<div error-response field="number" clss="com.vendertool.sharedtypes.core.Phone"></div>
		</td>
	</tr>
	<tr class="fldWrp">
		<td class="col1"><label>Phone (home):</label></td>
		<td class="col2">
			<input ng-model="accountEdit.contactDetails.phones['HOME'].number" class="form-control" />
			<div error-response field="number" clss="com.vendertool.sharedtypes.core.Phone"></div>
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
			<input ng-model="accountOrig.password" class="form-control" type="password"/>
			<div error-response field="password" clss="com.vendertool.sharedtypes.core.Account"></div>
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


