package com.vendertool.sharedtypes.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListingVariation {

	private int quantity;
	private String condition;
	private int lastModifiedApp;
	private Date createdDate;
	private Date lastModifiedDate;
	private Long listingId;
	private Long listingVariationId;
	private Long productVariationId;
	private Amount price;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getLastModifiedApp() {
		return lastModifiedApp;
	}
	public void setLastModifiedApp(int lastModifiedApp) {
		this.lastModifiedApp = lastModifiedApp;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Long getListingId() {
		return listingId;
	}
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}
	public Long getListingVariationId() {
		return listingVariationId;
	}
	public void setListingVariationId(Long listingVariationId) {
		this.listingVariationId = listingVariationId;
	}
	public Long getProductVariationId() {
		return productVariationId;
	}
	public void setProductVariationId(Long productVariationId) {
		this.productVariationId = productVariationId;
	}
	public Amount getPrice() {
		return price;
	}
	public void setPrice(Amount price) {
		this.price = price;
	}
}