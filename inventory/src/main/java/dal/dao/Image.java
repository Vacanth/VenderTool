package dal.dao;

// Generated May 10, 2013 11:09:53 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Image generated by hbm2java
 */
@Entity
@Table(name = "image", catalog = "stool")
public class Image implements java.io.Serializable {

	private int imageId;
	private MerchantProduct merchantProduct;
	private ProductVariation productVariation;
	private String imageName;
	private Byte isDefault;
	private String imageRefType;
	private Byte imageFormat;
	private String extRefImageId;
	private String imageRefUrl;
	private String md5hash;
	private String size;
	private String changeWho;
	private Date lastModifiedDate;
	private Date createDate;

	public Image() {
	}

	public Image(int imageId) {
		this.imageId = imageId;
	}

	public Image(int imageId, MerchantProduct merchantProduct,
			ProductVariation productVariation, String imageName,
			Byte isDefault, String imageRefType, Byte imageFormat,
			String extRefImageId, String imageRefUrl, String md5hash,
			String size, String changeWho, Date lastModifiedDate,
			Date createDate) {
		this.imageId = imageId;
		this.merchantProduct = merchantProduct;
		this.productVariation = productVariation;
		this.imageName = imageName;
		this.isDefault = isDefault;
		this.imageRefType = imageRefType;
		this.imageFormat = imageFormat;
		this.extRefImageId = extRefImageId;
		this.imageRefUrl = imageRefUrl;
		this.md5hash = md5hash;
		this.size = size;
		this.changeWho = changeWho;
		this.lastModifiedDate = lastModifiedDate;
		this.createDate = createDate;
	}

	@Id
	@Column(name = "IMAGE_ID", unique = true, nullable = false)
	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_REF_ID")
	public MerchantProduct getMerchantProduct() {
		return this.merchantProduct;
	}

	public void setMerchantProduct(MerchantProduct merchantProduct) {
		this.merchantProduct = merchantProduct;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IMAGE_REF_ID", insertable = false, updatable = false)
	public ProductVariation getProductVariation() {
		return this.productVariation;
	}

	public void setProductVariation(ProductVariation productVariation) {
		this.productVariation = productVariation;
	}

	@Column(name = "IMAGE_NAME", length = 45)
	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Column(name = "IS_DEFAULT")
	public Byte getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	@Column(name = "IMAGE_REF_TYPE", length = 45)
	public String getImageRefType() {
		return this.imageRefType;
	}

	public void setImageRefType(String imageRefType) {
		this.imageRefType = imageRefType;
	}

	@Column(name = "IMAGE_FORMAT")
	public Byte getImageFormat() {
		return this.imageFormat;
	}

	public void setImageFormat(Byte imageFormat) {
		this.imageFormat = imageFormat;
	}

	@Column(name = "EXT_REF_IMAGE_ID", length = 45)
	public String getExtRefImageId() {
		return this.extRefImageId;
	}

	public void setExtRefImageId(String extRefImageId) {
		this.extRefImageId = extRefImageId;
	}

	@Column(name = "IMAGE_REF_URL", length = 45)
	public String getImageRefUrl() {
		return this.imageRefUrl;
	}

	public void setImageRefUrl(String imageRefUrl) {
		this.imageRefUrl = imageRefUrl;
	}

	@Column(name = "MD5HASH", length = 45)
	public String getMd5hash() {
		return this.md5hash;
	}

	public void setMd5hash(String md5hash) {
		this.md5hash = md5hash;
	}

	@Column(name = "SIZE", length = 45)
	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "CHANGE_WHO", length = 45)
	public String getChangeWho() {
		return this.changeWho;
	}

	public void setChangeWho(String changeWho) {
		this.changeWho = changeWho;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE", length = 19)
	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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