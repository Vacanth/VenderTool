package com.vendertool.sitewebapp.model;

import java.util.ArrayList;
import java.util.List;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.Address;
import com.vendertool.sharedtypes.core.ContactDetails;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sitewebapp.common.FieldEnum;

public class AccountFieldSet {

	private Field email = new Field().setType(FieldEnum.EMAIL);
	private Field password = new Field().setType(FieldEnum.PASSWORD);
	private Field passwordConfirm = new Field().setType(FieldEnum.PASSWORD_CONFIRM);
	private Field addressLine1 = new Field().setType(FieldEnum.ADDRESS_LINE_1);
	private Field addressLine2 = new Field().setType(FieldEnum.ADDRESS_LINE_2);
	private Field city = new Field().setType(FieldEnum.CITY);
	private Field state = new Field().setType(FieldEnum.STATE);
	private Field zip = new Field().setType(FieldEnum.ZIP);
	private Field country = new Field().setType(FieldEnum.COUNTRY);
	private Field phoneWork = new Field().setType(FieldEnum.PHONE_WORK);
	private Field phoneMobile = new Field().setType(FieldEnum.PHONE_MOBILE);
	private Field phoneHome = new Field().setType(FieldEnum.PHONE_HOME);
	

	public AccountFieldSet() {
		for (CountryEnum c : CountryEnum.values()) {
			FieldOption opt = new FieldOption();
			opt.setLabel(c.getDisplayName());
			opt.setValue(c.getId() + "");
			country.addOption(opt);
		}
	}
	
	public void setAccount(Account account) {
		email.setValue(account.getEmailId());
		password.setValue(account.getPassword());
	
		ContactDetails contact = account.getContact();
		if (contact != null) {
			Address address = contact.getAddress();
			if (address != null) {
				addressLine1.setValue(address.getAddressLine1());
				addressLine2.setValue(address.getAddressLine2());
				city.setValue(address.getCity());
				state.setValue(address.getState());
				zip.setValue(address.getZip());
				
				CountryEnum countryEnum = address.getCountry();
				if (countryEnum != null) {
					country.setValue(countryEnum.getId() + "");
				}
			}
		}
	}

	public Field getEmail() {
		return email;
	}
	public void setEmail(Field email) {
		this.email = email;
	}
	public Field getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(Field addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public Field getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(Field addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public Field getCity() {
		return city;
	}
	public void setCity(Field city) {
		this.city = city;
	}
	public Field getState() {
		return state;
	}
	public void setState(Field state) {
		this.state = state;
	}
	public Field getZip() {
		return zip;
	}
	public void setZip(Field zip) {
		this.zip = zip;
	}
	public Field getCountry() {
		return country;
	}
	public void setCountry(Field country) {
		this.country = country;
	}
	public Field getPhoneWork() {
		return phoneWork;
	}
	public void setPhoneWork(Field phoneWork) {
		this.phoneWork = phoneWork;
	}
	public Field getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(Field phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public Field getPhoneHome() {
		return phoneHome;
	}
	public void setPhoneHome(Field phoneHome) {
		this.phoneHome = phoneHome;
	}
	
	


	
		
}
