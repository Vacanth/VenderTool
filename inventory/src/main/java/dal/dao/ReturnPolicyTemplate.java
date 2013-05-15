package dal.dao;

// Generated May 10, 2013 11:09:53 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReturnPolicyTemplate generated by hbm2java
 */
@Entity
@Table(name = "return_policy_template", catalog = "stool")
public class ReturnPolicyTemplate implements java.io.Serializable {

	private int returnPolicyId;
	private String returnPolicyName;
	private Integer returnAddrId;
	private Integer returnPayerId;
	private Integer returnDuration;
	private String returnPolicyDetails;
	private Date modifiedDate;
	private String changeWho;
	private Date createDate;

	public ReturnPolicyTemplate() {
	}

	public ReturnPolicyTemplate(int returnPolicyId) {
		this.returnPolicyId = returnPolicyId;
	}

	public ReturnPolicyTemplate(int returnPolicyId, String returnPolicyName,
			Integer returnAddrId, Integer returnPayerId,
			Integer returnDuration, String returnPolicyDetails,
			Date modifiedDate, String changeWho, Date createDate) {
		this.returnPolicyId = returnPolicyId;
		this.returnPolicyName = returnPolicyName;
		this.returnAddrId = returnAddrId;
		this.returnPayerId = returnPayerId;
		this.returnDuration = returnDuration;
		this.returnPolicyDetails = returnPolicyDetails;
		this.modifiedDate = modifiedDate;
		this.changeWho = changeWho;
		this.createDate = createDate;
	}

	@Id
	@Column(name = "RETURN_POLICY_ID", unique = true, nullable = false)
	public int getReturnPolicyId() {
		return this.returnPolicyId;
	}

	public void setReturnPolicyId(int returnPolicyId) {
		this.returnPolicyId = returnPolicyId;
	}

	@Column(name = "RETURN_POLICY_NAME", length = 45)
	public String getReturnPolicyName() {
		return this.returnPolicyName;
	}

	public void setReturnPolicyName(String returnPolicyName) {
		this.returnPolicyName = returnPolicyName;
	}

	@Column(name = "RETURN_ADDR_ID")
	public Integer getReturnAddrId() {
		return this.returnAddrId;
	}

	public void setReturnAddrId(Integer returnAddrId) {
		this.returnAddrId = returnAddrId;
	}

	@Column(name = "RETURN_PAYER_ID")
	public Integer getReturnPayerId() {
		return this.returnPayerId;
	}

	public void setReturnPayerId(Integer returnPayerId) {
		this.returnPayerId = returnPayerId;
	}

	@Column(name = "RETURN_DURATION")
	public Integer getReturnDuration() {
		return this.returnDuration;
	}

	public void setReturnDuration(Integer returnDuration) {
		this.returnDuration = returnDuration;
	}

	@Column(name = "RETURN_POLICY_DETAILS", length = 4000)
	public String getReturnPolicyDetails() {
		return this.returnPolicyDetails;
	}

	public void setReturnPolicyDetails(String returnPolicyDetails) {
		this.returnPolicyDetails = returnPolicyDetails;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE", length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "CHANGE_WHO", length = 45)
	public String getChangeWho() {
		return this.changeWho;
	}

	public void setChangeWho(String changeWho) {
		this.changeWho = changeWho;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}