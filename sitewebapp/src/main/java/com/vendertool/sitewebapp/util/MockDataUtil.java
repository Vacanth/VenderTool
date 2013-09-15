package com.vendertool.sitewebapp.util;

import java.util.HashMap;
import java.util.Map;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Phone;
import com.vendertool.sharedtypes.core.Phone.PhoneType;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ErrorResponse;


public class MockDataUtil {
	
	public static Account getAccount() {
		Address address = new Address();
		address.setAddressLine1("123 Main St");
		address.setAddressLine2("Apt. B");
		address.setCity("San Jose");
		address.setState("CA");
		address.setPostalCode("95125");
		address.setCountry(CountryEnum.UNITED_STATES);
		ContactDetails contact = new ContactDetails();
		contact.setAddress(address);
		contact.setFirstName("Ted");
		contact.setLastName("Szeto");
		Account acct = new Account();
		acct.setEmailId("ted@gmail.com");
		acct.setContactDetails(contact);
		
		Phone workPhone = new Phone();
		workPhone.setAreaCode(408);
		workPhone.setCountryCode(011);
		workPhone.setNumber(1111111);
		workPhone.setType(PhoneType.WORK);
		
		Phone mobilePhone = new Phone();
		mobilePhone.setAreaCode(408);
		mobilePhone.setCountryCode(011);
		mobilePhone.setNumber(2222222);
		mobilePhone.setType(PhoneType.MOBILE);
		
		Phone homePhone = new Phone();
		homePhone.setAreaCode(408);
		homePhone.setCountryCode(011);
		homePhone.setNumber(3333333);
		homePhone.setType(PhoneType.HOME);
		
		Phone faxPhone = new Phone();
		faxPhone.setAreaCode(408);
		faxPhone.setCountryCode(011);
		faxPhone.setNumber(4444444);
		faxPhone.setType(PhoneType.FAX);
		
		Phone publicPhone = new Phone();
		publicPhone.setAreaCode(408);
		publicPhone.setCountryCode(011);
		publicPhone.setNumber(5555555);
		publicPhone.setType(PhoneType.PUBLIC);
		
		Map<PhoneType, Phone> phones = new HashMap<PhoneType, Phone>();
		phones.put(workPhone.getType(), workPhone);
		phones.put(mobilePhone.getType(), mobilePhone);
		phones.put(homePhone.getType(), homePhone);
		phones.put(faxPhone.getType(), faxPhone);
		phones.put(publicPhone.getType(), publicPhone);
		
		contact.setPhones(phones);
		
		return acct;
	}
	
	public static ErrorResponse getUpdateAccountErrors(Account account) {
		
		ErrorResponse response = new ErrorResponse();
		
		//combine both errors together before returning
		if(account.getContactDetails().getFirstName() == null || account.getContactDetails().getFirstName().isEmpty()) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_FIRSTNAME, 
					account.getContactDetails().getClass().getName(),
					"firstName");
		}
		
		if(account.getContactDetails().getLastName() == null || account.getContactDetails().getLastName().isEmpty()) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_LASTNAME,
					account.getContactDetails().getClass().getName(),
					"lastName");
		}

		return response;
	}
	
	public static ChangeEmailRequest getEmail(){ 
		ChangeEmailRequest req = new ChangeEmailRequest();
		req.setOldEmailId("ted@gmail.com");
		return req;
	}
	
	public static ErrorResponse getUpdateEmailErrors(ChangeEmailRequest changeEmailRequest) {
		
		ErrorResponse response = new ErrorResponse();
		
		if(changeEmailRequest.getNewEmail() == null || changeEmailRequest.getNewEmail().isEmpty()) {
			response.addFieldBindingError(
					Errors.REGISTRATION.EMAIL_MISSING,
					changeEmailRequest.getClass().getName(),
					"newEmail");
		}
		
		return response;
	}
	
	public static ErrorResponse getUpdatePasswordErrors(ChangePasswordRequest changePasswordRequest) {
		
		ErrorResponse response = new ErrorResponse();
		
		if(changePasswordRequest.getNewPassword() == null || changePasswordRequest.getNewPassword().isEmpty()) {
			response.addFieldBindingError(
					Errors.REGISTRATION.PASSWORD_LENGTH_INCORRECT,
					changePasswordRequest.getClass().getName(),
					"newPassword");
		}
		
		return response;
	}
	
}