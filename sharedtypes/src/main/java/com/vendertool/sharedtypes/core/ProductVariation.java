package com.vendertool.sharedtypes.core;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductVariation {
	
	private Long productVariationId;
	private String title;
	private String sku;
	private Amount price;
	private ProductCodeTypeEnum productCodeType;
	private String productCode;
	private Integer quantity;
	private List<Image> images;
	private List<NameValuePair> properties;
	private String productUrl;
	
	public ProductVariation(){
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public Long getProductVariationId() {
		return productVariationId;
	}

	public void setProductVariationId(Long productVariationId) {
		this.productVariationId = productVariationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Amount getPrice() {
		return price;
	}

	public void setPrice(Amount price) {
		this.price = price;
	}

	public ProductCodeTypeEnum getProductCodeType() {
		return productCodeType;
	}

	public void setProductCodeType(ProductCodeTypeEnum productCodeType) {
		this.productCodeType = productCodeType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<NameValuePair> getProperties() {
		return properties;
	}

	public void setProperties(List<NameValuePair> properties) {
		this.properties = properties;
	}
}
