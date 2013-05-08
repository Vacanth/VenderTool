package dal.dao;

// Generated May 8, 2013 9:53:22 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MercadolibreCategoryAttributeValues generated by hbm2java
 */
@Entity
@Table(name = "mercadolibre_category_attribute_values", catalog = "stool")
public class MercadolibreCategoryAttributeValues implements
		java.io.Serializable {

	private Integer valueId;
	private int attributeId;
	private String meliValueId;
	private String meliValueName;
	private String meliValueNameExtended;

	public MercadolibreCategoryAttributeValues() {
	}

	public MercadolibreCategoryAttributeValues(int attributeId) {
		this.attributeId = attributeId;
	}

	public MercadolibreCategoryAttributeValues(int attributeId,
			String meliValueId, String meliValueName,
			String meliValueNameExtended) {
		this.attributeId = attributeId;
		this.meliValueId = meliValueId;
		this.meliValueName = meliValueName;
		this.meliValueNameExtended = meliValueNameExtended;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "value_id", unique = true, nullable = false)
	public Integer getValueId() {
		return this.valueId;
	}

	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}

	@Column(name = "attribute_id", nullable = false)
	public int getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "meli_value_id", length = 200)
	public String getMeliValueId() {
		return this.meliValueId;
	}

	public void setMeliValueId(String meliValueId) {
		this.meliValueId = meliValueId;
	}

	@Column(name = "meli_value_name", length = 100)
	public String getMeliValueName() {
		return this.meliValueName;
	}

	public void setMeliValueName(String meliValueName) {
		this.meliValueName = meliValueName;
	}

	@Column(name = "meli_value_name_extended", length = 100)
	public String getMeliValueNameExtended() {
		return this.meliValueNameExtended;
	}

	public void setMeliValueNameExtended(String meliValueNameExtended) {
		this.meliValueNameExtended = meliValueNameExtended;
	}

}
