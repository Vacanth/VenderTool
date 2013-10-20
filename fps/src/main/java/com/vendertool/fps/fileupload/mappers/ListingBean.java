package com.vendertool.fps.fileupload.mappers;

import java.util.Map;

public class ListingBean {
	private String listingCurrency;
	private String listingCountry;
	private String listingPrice;
	private int  listingQty;
	private int paymentMethod;
	private int classification;
	private int listingFormat;
	private int duration;
	private String auctionStartPrice;
	private String acutionReservedPrice;
	private String fixedPrice;

	private long refId;
	private long userAccountId;
	private String productCode;
	private String productCodeType;
	private String categoryId;
	private String SKU;
	private String description;
	private String title;
	private String price;
	private String currencyCode;
	private String quantity;
	private String itemCondition;
	private String weight;
	private String dimension;
	private String imagesUrl;
	
	private String variationColor;
	private String variationSize;
	private String listingTypeId;
	private Map<String,String> variations;
	private Map<String,String> specifications;


	public long getRefId() {
		return refId;
	}
	public void setRefId(long refId) {
		this.refId = refId;
	}
	public String getListingTypeId() {
		return listingTypeId;
	}
	public void setListingTypeId(String listingTypeId) {
		this.listingTypeId = listingTypeId;
	}
	public long getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(long userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCodeType() {
		return productCodeType;
	}
	public void setProductCodeType(String productCodeType) {
		this.productCodeType = productCodeType;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getItemCondition() {
		return itemCondition;
	}
	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getImagesUrl() {
		return imagesUrl;
	}
	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}
	public Map<String, String> getVariations() {
		return variations;
	}
	public void setVariations(Map<String, String> variations) {
		this.variations = variations;
	}
	public Map<String, String> getSpecifications() {
		return specifications;
	}
	public void setSpecifications(Map<String, String> specifications) {
		this.specifications = specifications;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sku) {
		this.SKU = sku;
	}
	public String getVariationColor() {
		return variationColor;
	}
	public void setVariationColor(String variationColor) {
		this.variationColor = variationColor;
	}
	public String getVariationSize() {
		return variationSize;
	}
	public void setVariationSize(String variationSize) {
		this.variationSize = variationSize;
	}
	
	public String getListingCurrency() {
		return listingCurrency;
	}
	public void setListingCurrency(String listingCurrency) {
		this.listingCurrency = listingCurrency;
	}
	public String getListingCountry() {
		return listingCountry;
	}
	public void setListingCountry(String listingCountry) {
		this.listingCountry = listingCountry;
	}
	public String getListingPrice() {
		return listingPrice;
	}
	public void setListingPrice(String listingPrice) {
		this.listingPrice = listingPrice;
	}
	public int getListingQty() {
		return listingQty;
	}
	public void setListingQty(int listingQty) {
		this.listingQty = listingQty;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getClassification() {
		return classification;
	}
	public void setClassification(int classification) {
		this.classification = classification;
	}
	public int getListingFormat() {
		return listingFormat;
	}
	public void setListingFormat(int listingFormat) {
		this.listingFormat = listingFormat;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAuctionStartPrice() {
		return auctionStartPrice;
	}
	public void setAuctionStartPrice(String auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}
	public String getAcutionReservedPrice() {
		return acutionReservedPrice;
	}
	public void setAcutionReservedPrice(String acutionReservedPrice) {
		this.acutionReservedPrice = acutionReservedPrice;
	}
	public String getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(String fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public String toString() {
        return "ProdBean [refId=" + refId + ", userAccountId=" + userAccountId + ", productCode=" + productCode
                + ", productCodeType=" + productCodeType + ", categoryId=" + categoryId
                + ", SKU=" + SKU + ", description=" + description
                + ", title=" + title + ", price=" + price 
                + ", currencyCode=" + currencyCode + ", quantity=" + quantity  
                + ", itemCondition=" + itemCondition +  ", weight=" + weight 
                + ", dimension=" + dimension +  ", imagesUrl=" + imagesUrl 
                + ", variationColor=" + variationColor + ", variationSize=" + variationSize
                + "]";
    }

}
