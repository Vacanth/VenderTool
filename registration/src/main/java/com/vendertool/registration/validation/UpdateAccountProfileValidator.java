package com.vendertool.registration.validation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.EmailRegexValidator;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.metadata.IMetadataService;
import com.vendertool.metadata.MetadataServiceImpl;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.core.Phone;
import com.vendertool.sharedtypes.core.Phone.PhoneType;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.GetSupportedLanguagesResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.UpdateAccountRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountResponse;

public class UpdateAccountProfileValidator implements Validator {
	
	private static final Logger logger = Logger.getLogger(UpdateAccountProfileValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	
	public UpdateAccountProfileValidator() {
	}
	
	@Override
	public void validate(BaseRequest _request, BaseResponse _response) {
		logger.info(UpdateAccountProfileValidator.class.getName() + ".validate()");
		
		UpdateAccountRequest request = (UpdateAccountRequest) _request;
		UpdateAccountResponse response = (UpdateAccountResponse) _response;
		
		if (VUTIL.isNull(request) || VUTIL.isNull(response)
				|| VUTIL.isNull(request.getAccount())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		Account account = request.getAccount();
		
		validateEmail(account, response);
		if(response.hasErrors()) {
			return;
		}
		
		validatePassword(request.getPassword(), response);
		if(response.hasErrors()) {
			return;
		}
		
		validateAlternateEmail(account, response);
		validateContactDetails(account, response);
		validateBillingAddress(account, response);
		validateCurrency(account, response);
		validateLanguage(account, response);
	}

	private void validatePassword(String password,
			UpdateAccountResponse response) {
		if (VUTIL.isEmpty(password)) {
			response.addFieldBindingError(Errors.REGISTRATION.MISSING_PASSWORD,
					null, (String[])null);
			return;
		}		
	}

	private void validateEmail(Account account, UpdateAccountResponse response) {
		if (VUTIL.isEmpty(account.getEmail())) {
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING,
					account.getClass().getName(), "email");
			return;
		}
	}

	private void validateLanguage(Account account,
			UpdateAccountResponse response) {
		Language lang = account.getLanguage();
		if(VUTIL.isNull(lang)) {
			return;
		}
		
		IMetadataService service = new MetadataServiceImpl();
		GetSupportedLanguagesResponse langResponse = service.getSupportedLanguages();
		Set<Language> languages = langResponse.getSupportedLanguages();
		if(!languages.contains(lang)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.LANGUAGE_NOT_SUPPORTED, account
							.getClass().getName(), "language");
		}
	}

	private void validateCurrency(Account account,
			UpdateAccountResponse response) {
		//TODO later if any
	}

	private void validateBillingAddress(Account account,
			UpdateAccountResponse response) {
		Address addr = account.getBillingAddress();
		
		if(VUTIL.isNull(addr)) {
			return;
		}
		
		validateAddress(response, addr);
	}

	private void validateAddress(UpdateAccountResponse response, Address addr) {
		CountryEnum country = addr.getCountry();
		
		if(VUTIL.isNull(country)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.INVALID_ADDRESS_COUNTRY_ZIP, 
					addr.getClass().getName(), 
					"country");
			return;
		}
		
		if(VUTIL.isNull(addr.getPostalCode())) {
			response.addFieldBindingError(
					Errors.REGISTRATION.INVALID_ADDRESS_COUNTRY_ZIP, 
					addr.getClass().getName(), 
					"postalCode");
			return;
		}
	}

	private void validateContactDetails(Account account,
			UpdateAccountResponse response) {
		
		ContactDetails cd = account.getContactDetails();
		
		if(VUTIL.isNull(cd) || (VUTIL.isEmpty(cd.getFirstName()))) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_FIRSTNAME, 
					account.getContactDetails().getClass().getName(),
					"firstName");
		}
		
		if(VUTIL.isEmpty(cd.getLastName())) {
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_LASTNAME,
					account.getContactDetails().getClass().getName(),
					"lastName");
		}
		
		Address contactAddr = cd.getAddress();
		if(VUTIL.isNotNull(contactAddr)) {
			validateAddress(response, contactAddr);
		}
		
		Map<PhoneType, Phone> _phones = cd.getPhones();
		if(!VUTIL.isNull(_phones) && !_phones.isEmpty()) {
			Collection<Phone> phones = _phones.values();
			for(Phone phone : phones) {
				validatePhone(phone, response);
			}
		}
	}

	private void validatePhone(Phone phone, UpdateAccountResponse response) {
		Integer num = phone.getNumber();
		if((VUTIL.isNotNull(num))) {
			
			boolean validNumFormat = isInteger(num);
			
			if(! validNumFormat) {
				response.addFieldBindingError(
						Errors.REGISTRATION.INVALID_PHONE_NUMBER,
						phone.getClass().getName(),
						"number");
			}
		}
		
//		Integer countryCode = phone.getCountryCode();
//		if(VUTIL.isNotNull(countryCode) && (countryCode <= 0)) {
//			response.addFieldBindingError(
//					Errors.REGISTRATION.INVALID_PHONE_NUMBER,
//					phone.getClass().getName(),
//					"countryCode");
//		}
//		
//		Integer areaCode = phone.getAreaCode();
//		if(VUTIL.isNotNull(areaCode) && (areaCode <= 0)) {
//			response.addFieldBindingError(
//					Errors.REGISTRATION.INVALID_PHONE_NUMBER,
//					phone.getClass().getName(),
//					"areaCode");
//		}
	}

	private boolean isInteger(Integer num) {
		boolean validNumFormat = false;
		try {
			Integer.parseInt(num+"");
			validNumFormat = true;
		} catch (NumberFormatException e) {
			validNumFormat = false;;
		}
		return validNumFormat;
	}

	private void validateAlternateEmail(Account account,
			UpdateAccountResponse response) {
		
		if(VUTIL.isEmpty(account.getAlternateEmailId())) {
			return;
		}
		
		if(!VUTIL.matchesPattern(EmailRegexValidator.SIMPLE_EMAIL_PATTERN, account.getAlternateEmailId())) {
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_EMAIL_ID, account.getClass().getName(), "alternateEmailId");
		}
	}

	
}
