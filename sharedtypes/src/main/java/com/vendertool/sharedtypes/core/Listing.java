package com.vendertool.sharedtypes.core;

import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

@XmlAccessorType(XmlAccessType.FIELD)
public class Listing {
	
	@XmlEnum
	public enum ListingFormatEnum{
		FIXED_PRICE(1), AUCTION(2), AD(3);
		private int id;

		private ListingFormatEnum(int id) {
			this.id = id; 
		}

		public int getId() {
			return id;
		}

		public static ListingFormatEnum get(int id) {
			ListingFormatEnum[] values = ListingFormatEnum.values();
			for (ListingFormatEnum typeEnum : values) {
				if (typeEnum.getId() == id) {
					return typeEnum;
				}
			}
			return null;
		}
	}
	
	private Long listingId;
	private Long parentListingId;
	private Currency listingCurrency;
	private CountryEnum country;
	private Product product;
	private Amount price;
	private int quantity;
	private String condition;
	private List<PaymentMethod> paymentMethods;
	private ReturnPolicy returnPolicy;
	private ShippingPolicy shippingPolicy;
	private MarketEnum market;
	private List<Classification> classifications;
	private ListingFormatEnum listingFormat;
	private int duration;
	private Date startDate;
	private Date createDate;
	private Date lastModifiedDate;
	private Long createOwnerId;
	private Amount auctionCurrrentPrice;
	private Amount auctionReservedPrice;
	private Amount auctionStartPrice;
	private Amount fixedPrice;
	private Date listingEndTime;
	private Date listingStartTime;
	private int lastModifiedApp;
	private String marketPlaceListingId;
	private Long masterTemplateId;
	private String warranty;
	
	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public Long getParentListingId() {
		return parentListingId;
	}

	public void setParentListingId(Long parentListingId) {
		this.parentListingId = parentListingId;
	}

	public Long getMasterTemplateId() {
		return masterTemplateId;
	}

	public void setMasterTemplateId(Long masterTemplateId) {
		this.masterTemplateId = masterTemplateId;
	}

	public String getMarketPlaceListingId() {
		return marketPlaceListingId;
	}

	public void setMarketPlaceListingId(String marketPlaceListingId) {
		this.marketPlaceListingId = marketPlaceListingId;
	}

	public int getLastModifiedApp() {
		return lastModifiedApp;
	}

	public void setLastModifiedApp(int lastModifiedApp) {
		this.lastModifiedApp = lastModifiedApp;
	}

	public Date getListingStartTime() {
		return listingStartTime;
	}

	public void setListingStartTime(Date listingStartTime) {
		this.listingStartTime = listingStartTime;
	}

	public Date getListingEndTime() {
		return listingEndTime;
	}

	public void setListingEndTime(Date listingEndTime) {
		this.listingEndTime = listingEndTime;
	}

	public Amount getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(Amount fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public Amount getAuctionCurrrentPrice() {
		return auctionCurrrentPrice;
	}

	public void setAuctionCurrrentPrice(Amount auctionCurrrentPrice) {
		this.auctionCurrrentPrice = auctionCurrrentPrice;
	}

	public Amount getAuctionReservedPrice() {
		return auctionReservedPrice;
	}

	public void setAuctionReservedPrice(Amount auctionReservedPrice) {
		this.auctionReservedPrice = auctionReservedPrice;
	}

	public Amount getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(Amount auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public Listing(){}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Amount getPrice() {
		return price;
	}
	public void setPrice(Amount price) {
		this.price = price;
	}
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
	
	public Long getListingId() {
		return listingId;
	}
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}
	
	public String toString() {
		return "LISTING ID = '" + getListingId() + "' \n PRODUCT = [" + getProduct().toString() + "]\n"
				+ "CONDITION = '" + getCondition() + "' QUANTITY = '" + getQuantity() + 
				"' PRICE = '" + ((getPrice() != null) ? getPrice().getValueFormat() : 0) + "'";
	}
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}
	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	public ReturnPolicy getReturnPolicy() {
		return returnPolicy;
	}
	public void setReturnPolicy(ReturnPolicy returnPolicy) {
		this.returnPolicy = returnPolicy;
	}
	public ShippingPolicy getShippingPolicy() {
		return shippingPolicy;
	}
	public void setShippingPolicy(ShippingPolicy shippingPolicy) {
		this.shippingPolicy = shippingPolicy;
	}
	public MarketEnum getMarket() {
		return market;
	}
	public void setMarket(MarketEnum market) {
		this.market = market;
	}
	public List<Classification> getClassifications() {
		return classifications;
	}
	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Long getCreateOwnerId() {
		return createOwnerId;
	}
	public void setCreateOwnerId(Long createOwnerId) {
		this.createOwnerId = createOwnerId;
	}
	public ListingFormatEnum getListingFormat() {
		return listingFormat;
	}
	public void setListingFormat(ListingFormatEnum listingFormat) {
		this.listingFormat = listingFormat;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Currency getListingCurrency() {
		return listingCurrency;
	}

	public void setListingCurrency(Currency listingCurrency) {
		this.listingCurrency = listingCurrency;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}
}
