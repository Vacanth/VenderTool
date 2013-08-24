package com.vendertool.registration.dal.address;

// Generated May 17, 2013 11:29:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name = "address", catalog = "ACCOUNTDB")
public class Address implements java.io.Serializable {

	private Long addressId;
	private Byte useCase;
	private Byte addrType;
	private String contactFirstName;
	private String contactLastName;
	private String companyName;
	private String addrLn1;
	private String addrLn2;
	private String addrLn3;
	private String city;
	private String state;
	private Byte countryCodeIso3;
	private String zip;
	private Byte status;
	private Date createdDate;
	private Date lastModifiedDate;

	public Address() {
	}

	public Address(Byte useCase, Byte addrType, String contactFirstName,
			String contactLastName, String companyName, String addrLn1,
			String addrLn2, String addrLn3, String city, String state,
			Byte countryCodeIso3, String zip, Byte status, Date createdDate,
			Date lastModifiedDate) {
		this.useCase = useCase;
		this.addrType = addrType;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.companyName = companyName;
		this.addrLn1 = addrLn1;
		this.addrLn2 = addrLn2;
		this.addrLn3 = addrLn3;
		this.city = city;
		this.state = state;
		this.countryCodeIso3 = countryCodeIso3;
		this.zip = zip;
		this.status = status;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ADDRESS_ID", unique = true, nullable = false)
	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "USE_CASE")
	public Byte getUseCase() {
		return this.useCase;
	}

	public void setUseCase(Byte useCase) {
		this.useCase = useCase;
	}

	@Column(name = "ADDR_TYPE")
	public Byte getAddrType() {
		return this.addrType;
	}

	public void setAddrType(Byte addrType) {
		this.addrType = addrType;
	}

	@Column(name = "CONTACT_FIRST_NAME", length = 64)
	public String getContactFirstName() {
		return this.contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	@Column(name = "CONTACT_LAST_NAME", length = 64)
	public String getContactLastName() {
		return this.contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	@Column(name = "COMPANY_NAME", length = 64)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "ADDR_LN1", length = 128)
	public String getAddrLn1() {
		return this.addrLn1;
	}

	public void setAddrLn1(String addrLn1) {
		this.addrLn1 = addrLn1;
	}

	@Column(name = "ADDR_LN2", length = 128)
	public String getAddrLn2() {
		return this.addrLn2;
	}

	public void setAddrLn2(String addrLn2) {
		this.addrLn2 = addrLn2;
	}

	@Column(name = "ADDR_LN3", length = 128)
	public String getAddrLn3() {
		return this.addrLn3;
	}

	public void setAddrLn3(String addrLn3) {
		this.addrLn3 = addrLn3;
	}

	@Column(name = "CITY", length = 64)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE", length = 64)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "COUNTRY_CODE_ISO3")
	public Byte getCountryCodeIso3() {
		return this.countryCodeIso3;
	}

	public void setCountryCodeIso3(Byte countryCodeIso3) {
		this.countryCodeIso3 = countryCodeIso3;
	}

	@Column(name = "ZIP", length = 32)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "STATUS")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}