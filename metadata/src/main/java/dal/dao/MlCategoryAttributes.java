package dal.dao;

// Generated May 10, 2013 11:43:56 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MlCategoryAttributes generated by hbm2java
 */
@Entity
@Table(name = "ml_category_attributes", catalog = "stool")
public class MlCategoryAttributes implements java.io.Serializable {

	private Integer attributeId;
	private int categoryId;
	private String meliAttributeId;
	private String meliAttributeName;
	private String meliAttributeType;
	private Boolean required;

	public MlCategoryAttributes() {
	}

	public MlCategoryAttributes(int categoryId) {
		this.categoryId = categoryId;
	}

	public MlCategoryAttributes(int categoryId, String meliAttributeId,
			String meliAttributeName, String meliAttributeType, Boolean required) {
		this.categoryId = categoryId;
		this.meliAttributeId = meliAttributeId;
		this.meliAttributeName = meliAttributeName;
		this.meliAttributeType = meliAttributeType;
		this.required = required;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "attribute_id", unique = true, nullable = false)
	public Integer getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "meli_attribute_id", length = 200)
	public String getMeliAttributeId() {
		return this.meliAttributeId;
	}

	public void setMeliAttributeId(String meliAttributeId) {
		this.meliAttributeId = meliAttributeId;
	}

	@Column(name = "meli_attribute_name", length = 100)
	public String getMeliAttributeName() {
		return this.meliAttributeName;
	}

	public void setMeliAttributeName(String meliAttributeName) {
		this.meliAttributeName = meliAttributeName;
	}

	@Column(name = "meli_attribute_type", length = 200)
	public String getMeliAttributeType() {
		return this.meliAttributeType;
	}

	public void setMeliAttributeType(String meliAttributeType) {
		this.meliAttributeType = meliAttributeType;
	}

	@Column(name = "required")
	public Boolean getRequired() {
		return this.required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

}