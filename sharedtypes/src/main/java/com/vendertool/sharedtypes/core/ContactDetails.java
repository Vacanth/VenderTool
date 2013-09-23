package com.vendertool.sharedtypes.core;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendertool.sharedtypes.core.Phone.PhoneType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactDetails {
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailId;
	private String alternateEmailId;
	private Address address;
	private Map<PhoneType, Phone> phones;
	
	public ContactDetails(){}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Map<PhoneType, Phone> getPhones() {
		return phones;
	}

	public void setPhones(Map<PhoneType, Phone> phones) {
		this.phones = phones;
	}

	@JsonIgnore
	public void addPhone(PhoneType type, Phone phone) {
		if((type == null) || (phone == null)) {
			return;
		}
		
		Map<PhoneType, Phone> phones = getPhones();
		if(phones == null) {
			phones = new HashMap<Phone.PhoneType, Phone>();
			setPhones(phones);
		}
		
		phones.put(type, phone);
	}
	
	public Phone getPhone(PhoneType type) {
		if((getPhones() == null) || (type == null)) {
			return null;
		}
		
		return getPhones().get(type);
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("CONTACT DETAILS=[")
			.append("\n\tFIRST NAME=").append(getFirstName())
			.append("\n\tMIDDLE NAME=").append(getMiddleName())
			.append("\n\tLAST NAME=").append(getLastName())
			.append("\n\tEMAIL=").append(getEmailId())
			.append("\n\tALTERNATE EMAIL=").append(getAlternateEmailId())
			.append("\n\tADDRESS=").append((getAddress() != null) ? getAddress().toString() : null)
			.append("\n\tPhones=").append((getPhones() != null) ? getPhones().toString() : null)
		.append("]]");
		return sb.toString();
	}
}
