package com.vendertool.sitewebapp.test.util;

import org.codehaus.jackson.map.ObjectMapper;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sitewebapp.model.AccountFieldSet;


public class MockDataUtil {

	public static AccountFieldSet getAccountFieldSet() {
		
		
		Address address = new Address();
		address.setAddressLine1("123 Main St");
		address.setAddressLine2("Apt. B");
		address.setCity("San Jose");
		address.setState("CA");
		address.setZip("95125");
		address.setCountry(CountryEnum.UNITED_STATES);
		
		ContactDetails contact = new ContactDetails();
		contact.setAddress(address);
		contact.setFirstName("Ted");
		contact.setLastName("Szeto");
		
		Account acct = new Account();
		acct.setEmailId("ted@gmail.com");
		acct.setContact(contact);
		
		
		AccountFieldSet fieldSet = new AccountFieldSet();
		fieldSet.setAccount(acct);

		
		return fieldSet;
	}
	
	public static void main(String[] args) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"email\":{\"value\":\"ted@gmail.com\",\"hasError\":false,\"errorMessages\":[],\"name\":\"email\"},\"addressLine1\":{\"value\":\"123 Main St\",\"hasError\":false,\"errorMessages\":[],\"name\":\"addressLine1\"},\"addressLine2\":{\"value\":\"Apt. B\",\"hasError\":false,\"errorMessages\":[],\"name\":\"addressLine2\"},\"city\":{\"value\":\"San Jose\",\"hasError\":false,\"errorMessages\":[],\"name\":\"city\"},\"state\":{\"value\":\"CA\",\"hasError\":false,\"errorMessages\":[],\"name\":\"state\"},\"zip\":{\"value\":\"95125\",\"hasError\":false,\"errorMessages\":[],\"name\":\"zip\"},\"phoneWork\":{\"value\":null,\"hasError\":false,\"errorMessages\":[],\"name\":\"phoneWork\"},\"phoneMobile\":{\"value\":null,\"hasError\":false,\"errorMessages\":[],\"name\":\"phoneMobile\"},\"phoneHome\":{\"value\":null,\"hasError\":false,\"errorMessages\":[],\"name\":\"phoneHome\"}}";
		
		AccountFieldSet set = getAccountFieldSet();
		try {
			//json = mapper.writeValueAsString(set);
			System.err.println(json);
			
			AccountFieldSet acct = mapper.readValue(json, AccountFieldSet.class);
			
			System.err.println(acct.getEmail());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		//System.out.println(acct.getEmail());
		
		
	}
}
