package com.vendertool.sharedtypes.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

	@XmlEnum
	public enum AddressTypeEnum{
		RESENTIAL(1), BUSINESS(2);
		
		private int id;
		
		AddressTypeEnum(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
		
		public static AddressTypeEnum get(int id) {
			AddressTypeEnum[] values = AddressTypeEnum.values();
			for(AddressTypeEnum e : values) {
				if(e.getId() == id) {
					return e;
				}
			}
			return null;
		}
	}
	
	@XmlEnum
	public enum AddressUsecaseEnum{
		REGISTRATION(1), BILLING(2), SHIPPING(3);
		
		private int id;
		
		AddressUsecaseEnum(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static AddressUsecaseEnum get(int id) {
			AddressUsecaseEnum[] values = AddressUsecaseEnum.values();
			for(AddressUsecaseEnum e : values) {
				if(e.getId() == id) {
					return e;
				}
			}
			return null;
		}
	}
	
	@XmlEnum
	public enum AddressStatusEnum{
		VERIFIED(1), DELETED(2);
		
		private int id;
		
		AddressStatusEnum(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static AddressStatusEnum get(int id) {
			AddressStatusEnum[] values = AddressStatusEnum.values();
			for(AddressStatusEnum e : values) {
				if(e.getId() == id) {
					return e;
				}
			}
			return null;
		}
	}
	
	private Long id;
	private String firstName;
	private String lastName;
	private String company;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String postalCode;
	private CountryEnum country;
	private AddressTypeEnum addressType;
	private AddressUsecaseEnum addressUseCase;
	private AddressStatusEnum status;
	private Date createdDate;
	
	public Address(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public AddressTypeEnum getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressTypeEnum addressType) {
		this.addressType = addressType;
	}

	public AddressUsecaseEnum getAddressUseCase() {
		return addressUseCase;
	}

	public void setAddressUseCase(AddressUsecaseEnum addressUseCase) {
		this.addressUseCase = addressUseCase;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AddressStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AddressStatusEnum status) {
		this.status = status;
	}
	
	@JsonIgnore
	public boolean locationEquals(Address a) {	
		if(a == null) {
			return false;
		}
		
		Long id1 = this.getId();
		Long id2 = a.getId();
		if(!objEquals(id1, id2)) {
			return false;
		}
		
		String s1 = this.getAddressLine1();
		String s2 = a.getAddressLine1();
		if(!objEquals(s1, s2)) {
			return false;
		}
		
		s1 = this.getAddressLine2();
		s2 = a.getAddressLine2();
		if(!objEquals(s1, s2)) {
			return false;
		}
		
		s1 = this.getCity();
		s2 = a.getCity();
		if(!objEquals(s1, s2)) {
			return false;
		}
		
		s1 = this.getPostalCode();
		s2 = a.getPostalCode();
		if(!objEquals(s1, s2)) {
			return false;
		}
		
		s1 = this.getState();
		s2 = a.getState();
		if(!objEquals(s1, s2)) {
			return false;
		}
		
		CountryEnum c1 = this.getCountry();
		CountryEnum c2 = a.getCountry();
		if(!objEquals(c1, c2)) {
			return false;
		}
		
		return true;
	}
	
	@JsonIgnore
	private boolean objEquals(Object s1, Object s2) {
		if((s1 == s2) || ((s1 != null) && (s1.equals(s2)))) {
			return true;
		}
		return false;
	}
	
	@JsonIgnore
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nADDRESS=[")
			.append("\n\tID=").append(getId())
			.append("\n\tFIRST NAME=").append(getFirstName())
			.append("\n\tLAST NAME=").append(getLastName())
			.append("\n\tCOMPANY=").append(getCompany())
			.append("\n\tADDR LINE1=").append(getAddressLine1())
			.append("\n\tADDR LINE2=").append(getAddressLine2())
			.append("\n\tCITY=").append(getCity())
			.append("\n\tSTATE=").append(getState())
			.append("\n\tCOUNTRY=").append((getCountry() != null) ? getCountry().getIso3Code() : null)
			.append("\n\tPOSTAL CODE=").append(getPostalCode())
			.append("\n\tADDRESS TYPE=").append(getAddressType())
			.append("\n\tUSECASE=").append(getAddressUseCase())
			.append("\n\tSTATUS=").append(getStatus())
			.append("\n\tCREATED DATE=").append(getCreatedDate())
		.append("]]");
		
		return sb.toString();
	}
}
