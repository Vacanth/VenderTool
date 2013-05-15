package dal.dao;

// Generated May 10, 2013 11:09:53 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MerchantProduct generated by hbm2java
 */
@Entity
@Table(name = "merchant_product", catalog = "stool")
public class MerchantProduct implements java.io.Serializable {

	private int productId;
	private String productTitle;
	private Integer productDescriptionId;
	private String productName;
	private String productSku;
	private String productType;
	private String productCode;
	private String productUrl;
	private Integer availableQuantity;
	private BigDecimal productCost;
	private Date createDate;
	private Date modifiedDate;
	private String changeWho;
	private Set<ProductVariation> productVariations = new HashSet<ProductVariation>(
			0);
	private Set<Image> images = new HashSet<Image>(0);
	private Set<MerchantProductDescription> merchantProductDescriptions = new HashSet<MerchantProductDescription>(
			0);

	public MerchantProduct() {
	}

	public MerchantProduct(int productId) {
		this.productId = productId;
	}

	public MerchantProduct(int productId, String productTitle,
			Integer productDescriptionId, String productName,
			String productSku, String productType, String productCode,
			String productUrl, Integer availableQuantity,
			BigDecimal productCost, Date createDate, Date modifiedDate,
			String changeWho, Set<ProductVariation> productVariations,
			Set<Image> images,
			Set<MerchantProductDescription> merchantProductDescriptions) {
		this.productId = productId;
		this.productTitle = productTitle;
		this.productDescriptionId = productDescriptionId;
		this.productName = productName;
		this.productSku = productSku;
		this.productType = productType;
		this.productCode = productCode;
		this.productUrl = productUrl;
		this.availableQuantity = availableQuantity;
		this.productCost = productCost;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.changeWho = changeWho;
		this.productVariations = productVariations;
		this.images = images;
		this.merchantProductDescriptions = merchantProductDescriptions;
	}

	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "PRODUCT_TITLE", length = 45)
	public String getProductTitle() {
		return this.productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	@Column(name = "PRODUCT_DESCRIPTION_ID")
	public Integer getProductDescriptionId() {
		return this.productDescriptionId;
	}

	public void setProductDescriptionId(Integer productDescriptionId) {
		this.productDescriptionId = productDescriptionId;
	}

	@Column(name = "PRODUCT_NAME", length = 45)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCT_SKU", length = 45)
	public String getProductSku() {
		return this.productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	@Column(name = "PRODUCT_TYPE", length = 45)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "PRODUCT_CODE", length = 45)
	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "PRODUCT_URL", length = 45)
	public String getProductUrl() {
		return this.productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Column(name = "AVAILABLE_QUANTITY")
	public Integer getAvailableQuantity() {
		return this.availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Column(name = "PRODUCT_COST", precision = 8)
	public BigDecimal getProductCost() {
		return this.productCost;
	}

	public void setProductCost(BigDecimal productCost) {
		this.productCost = productCost;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchantProduct")
	public Set<ProductVariation> getProductVariations() {
		return this.productVariations;
	}

	public void setProductVariations(Set<ProductVariation> productVariations) {
		this.productVariations = productVariations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchantProduct")
	public Set<Image> getImages() {
		return this.images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "merchantProduct")
	public Set<MerchantProductDescription> getMerchantProductDescriptions() {
		return this.merchantProductDescriptions;
	}

	public void setMerchantProductDescriptions(
			Set<MerchantProductDescription> merchantProductDescriptions) {
		this.merchantProductDescriptions = merchantProductDescriptions;
	}

}