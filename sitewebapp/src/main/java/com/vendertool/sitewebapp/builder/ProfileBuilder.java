package com.vendertool.sitewebapp.builder;

import java.util.HashMap;
import java.util.Map;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;

public class ProfileBuilder {

	public static final Map<String, String> getProfile(Account account) {
		Map<String, String> profile = new HashMap<String, String>();
		
		profile.put(Name.email, account.getEmailId());
		
		ContactDetails contact = account.getContactDetails();
		if (contact != null) {
			Address address = contact.getAddress();
			if (address != null) {
				profile.put(Name.addressLine1, address.getAddressLine1());
				profile.put(Name.addressLine2, address.getAddressLine2());
				profile.put(Name.city, address.getCity());
				profile.put(Name.state, address.getState());
				profile.put(Name.zip, address.getZip());
				
				CountryEnum countryEnum = address.getCountry();
				if (countryEnum != null) {
					profile.put(Name.country, countryEnum.getId() + "");
				}
			}
		}
		
		return profile;
	}
	
	public interface Name {
		String email = "email";
		String password = "password";
		String passwordConfirm = "passwordConfirm";
		String fname = "fname";
		String lname = "lname";
		String emailAlt = "emailAlt";
		String addressLine1 = "addressLine1";
		String addressLine2 = "addressLine2";
		String city = "city";
		String state = "state";
		String zip = "zip";
		String country = "country";
		String phoneWork = "phoneWork";
		String phoneMobile = "phoneMobile";
		String phoneHome = "phoneHome";
	}
	
	public static void main(String[] args) {

	}
}



