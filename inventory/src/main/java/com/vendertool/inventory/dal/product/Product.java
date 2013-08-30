package com.vendertool.inventory.dal.product;

// Generated Aug 29, 2013 5:07:44 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "invdb")
public class Product implements java.io.Serializable {

	private Long productId;
	private String title;
	private String sku;
	private String descriptionText;
	private BigDecimal price;
	private Integer availbleQuantity;
	private Long accountId;
	private String productCode;
	private Byte productCodeType;
	private BigDecimal weight;
	private BigDecimal height;
	private BigDecimal length;
	private BigDecimal width;
	private Byte dimensionUnit;
	private Byte weightUnit;
	private String productUrl;
	private Date createdDate;
	private Date lastModifiedDate;
	private Byte lastModifiedApp;
	private String productcol;

	public Product() {
	}

	public Product(String title, String sku, String descriptionText,
			BigDecimal price, Integer availbleQuantity, Long accountId,
			String productCode, Byte productCodeType, BigDecimal weight,
			BigDecimal height, BigDecimal length, BigDecimal width,
			Byte dimensionUnit, Byte weightUnit, String productUrl,
			Date createdDate, Date lastModifiedDate, Byte lastModifiedApp,
			String productcol) {
		this.title = title;
		this.sku = sku;
		this.descriptionText = descriptionText;
		this.price = price;
		this.availbleQuantity = availbleQuantity;
		this.accountId = accountId;
		this.productCode = productCode;
		this.productCodeType = productCodeType;
		this.weight = weight;
		this.height = height;
		this.length = length;
		this.width = width;
		this.dimensionUnit = dimensionUnit;
		this.weightUnit = weightUnit;
		this.productUrl = productUrl;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifiedApp = lastModifiedApp;
		this.productcol = productcol;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "title", length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "sku", length = 64)
	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "description_text", length = 65535)
	public String getDescriptionText() {
		return this.descriptionText;
	}

	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	@Column(name = "price", precision = 18, scale = 4)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "availble_quantity")
	public Integer getAvailbleQuantity() {
		return this.availbleQuantity;
	}

	public void setAvailbleQuantity(Integer availbleQuantity) {
		this.availbleQuantity = availbleQuantity;
	}

	@Column(name = "account_id")
	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "product_code", length = 64)
	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "product_code_type")
	public Byte getProductCodeType() {
		return this.productCodeType;
	}

	public void setProductCodeType(Byte productCodeType) {
		this.productCodeType = productCodeType;
	}

	@Column(name = "weight", precision = 8)
	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "height", precision = 8)
	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	@Column(name = "length", precision = 8)
	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	@Column(name = "width", precision = 8)
	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	@Column(name = "dimension_unit")
	public Byte getDimensionUnit() {
		return this.dimensionUnit;
	}

	public void setDimensionUnit(Byte dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	@Column(name = "weight_unit")
	public Byte getWeightUnit() {
		return this.weightUnit;
	}

	public void setWeightUnit(Byte weightUnit) {
		this.weightUnit = weightUnit;
	}

	@Column(name = "product_url", length = 4000)
	public String getProductUrl() {
		return this.productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_modified_date", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Column(name = "last_modified_app")
	public Byte getLastModifiedApp() {
		return this.lastModifiedApp;
	}

	public void setLastModifiedApp(Byte lastModifiedApp) {
		this.lastModifiedApp = lastModifiedApp;
	}

	@Column(name = "productcol", length = 45)
	public String getProductcol() {
		return this.productcol;
	}

	public void setProductcol(String productcol) {
		this.productcol = productcol;
	}

}