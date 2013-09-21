package com.vendertool.sitewebapp.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.PaginationOutput;
import com.vendertool.sharedtypes.core.Phone;
import com.vendertool.sharedtypes.core.Phone.PhoneType;
import com.vendertool.sharedtypes.core.fps.FPSFileStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sharedtypes.rnr.UploadsResponse;


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
	
	public static UploadsResponse getUploadsResponse() {
		
		UploadsResponse res = new UploadsResponse();
		
		PaginationOutput pagOut = new PaginationOutput();
		pagOut.setCurrentPage(3);
		pagOut.setEntriesPerPage(25);
		pagOut.setTotalResults(201);

		List<Job> jobs = new ArrayList<Job>();
		for (int i=0; i<25; i++) {
			Job job = new Job();
			job.setAccountId(123L);
			job.setCreatedDate(new Date());
			job.setJobId(new Long(i));
			job.setStatus(FPSJobStatusEnum.SUCCESS);
			job.setTitle("title " + i);
			
			//
			// Add uploaded files
			//
			List<File> uploadedFiles = new ArrayList<File>();
			if (i % 2 == 0)  {
				
				for (int j=0; j<3; j++) {
					File f = new File();
					f.setName("name" + j);
					f.setFileId(new Long(j));
					f.setAccountId(123L);
					f.setCreatedDate(new Date());
					f.setStatus(FPSFileStatusEnum.SUCCESS);
					uploadedFiles.add(f);
				}
			}
			else {
				File f = new File();
				f.setName("name" + i);
				f.setFileId(new Long(i));
				f.setAccountId(123L);
				f.setCreatedDate(new Date());
				f.setStatus(FPSFileStatusEnum.SUCCESS);
				uploadedFiles.add(f);
			}
			job.setUploadedFiles(uploadedFiles);
			
			//
			// Add processed files
			//
			List<File> processedFiles = new ArrayList<File>();
			if (i % 2 == 0)  {
				
				for (int j=0; j<3; j++) {
					File f = new File();
					f.setName("name" + j);
					f.setFileId(new Long(j));
					f.setAccountId(123L);
					f.setCreatedDate(new Date());
					f.setStatus(FPSFileStatusEnum.SUCCESS);
					processedFiles.add(f);
				}
			}
			else {
				File f = new File();
				f.setName("name" + i);
				f.setFileId(new Long(i));
				f.setAccountId(123L);
				f.setCreatedDate(new Date());
				f.setStatus(FPSFileStatusEnum.SUCCESS);
				processedFiles.add(f);
			}
			job.setProcessedFiles(processedFiles);
			
			jobs.add(job);
		}
		
		res.setJobs(jobs);
		res.setPaginationOutput(pagOut);
		
		return res;
	}
	
}