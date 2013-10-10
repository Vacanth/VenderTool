
package com.vendertool.mercadolibreadapter.add;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Item{
   	private boolean accepts_mercadopago;
   	private List attributes;
   	private boolean automatic_relist;
   	private Number available_quantity;
   	private Number base_price;
   	private String buying_mode;
   	private String catalog_product_id;
   	private String category_id;
   	private String condition;
   	private List coverage_areas;
   	private String currency_id;
   	private String date_created;
   	private List descriptions;
   	private Geolocation geolocation;
   	private String id;
   	private Number initial_quantity;
   	private String last_updated;
   	private String listing_source;
   	private String listing_type_id;
   	private String location;
   	private List non_mercado_pago_payment_methods;
   	private String parent_item_id;
   	private String permalink;
   	private List pictures;
   	private Number price;
   	private String secure_thumbnail;
   	private Seller_address seller_address;
   	private String seller_contact;
   	private Number seller_id;
   	private Shipping shipping;
   	private String site_id;
   	private Number sold_quantity;
   	private String start_time;
   	private String status;
   	private String stop_time;
   	private List sub_status;
   	private String subtitle;
   	private List tags;
   	private String thumbnail;
   	private String title;
   	private List variations;
   	private String video_id;
   	private String warranty;

 	public boolean getAccepts_mercadopago(){
		return this.accepts_mercadopago;
	}
	public void setAccepts_mercadopago(boolean accepts_mercadopago){
		this.accepts_mercadopago = accepts_mercadopago;
	}
 	public List getAttributes(){
		return this.attributes;
	}
	public void setAttributes(List attributes){
		this.attributes = attributes;
	}
 	public boolean getAutomatic_relist(){
		return this.automatic_relist;
	}
	public void setAutomatic_relist(boolean automatic_relist){
		this.automatic_relist = automatic_relist;
	}
 	public Number getAvailable_quantity(){
		return this.available_quantity;
	}
	public void setAvailable_quantity(Number available_quantity){
		this.available_quantity = available_quantity;
	}
 	public Number getBase_price(){
		return this.base_price;
	}
	public void setBase_price(Number base_price){
		this.base_price = base_price;
	}
 	public String getBuying_mode(){
		return this.buying_mode;
	}
	public void setBuying_mode(String buying_mode){
		this.buying_mode = buying_mode;
	}
 	public String getCatalog_product_id(){
		return this.catalog_product_id;
	}
	public void setCatalog_product_id(String catalog_product_id){
		this.catalog_product_id = catalog_product_id;
	}
 	public String getCategory_id(){
		return this.category_id;
	}
	public void setCategory_id(String category_id){
		this.category_id = category_id;
	}
 	public String getCondition(){
		return this.condition;
	}
	public void setCondition(String condition){
		this.condition = condition;
	}
 	public List getCoverage_areas(){
		return this.coverage_areas;
	}
	public void setCoverage_areas(List coverage_areas){
		this.coverage_areas = coverage_areas;
	}
 	public String getCurrency_id(){
		return this.currency_id;
	}
	public void setCurrency_id(String currency_id){
		this.currency_id = currency_id;
	}
 	public String getDate_created(){
		return this.date_created;
	}
	public void setDate_created(String date_created){
		this.date_created = date_created;
	}
 	public List getDescriptions(){
		return this.descriptions;
	}
	public void setDescriptions(List descriptions){
		this.descriptions = descriptions;
	}
 	public Geolocation getGeolocation(){
		return this.geolocation;
	}
	public void setGeolocation(Geolocation geolocation){
		this.geolocation = geolocation;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public Number getInitial_quantity(){
		return this.initial_quantity;
	}
	public void setInitial_quantity(Number initial_quantity){
		this.initial_quantity = initial_quantity;
	}
 	public String getLast_updated(){
		return this.last_updated;
	}
	public void setLast_updated(String last_updated){
		this.last_updated = last_updated;
	}
 	public String getListing_source(){
		return this.listing_source;
	}
	public void setListing_source(String listing_source){
		this.listing_source = listing_source;
	}
 	public String getListing_type_id(){
		return this.listing_type_id;
	}
	public void setListing_type_id(String listing_type_id){
		this.listing_type_id = listing_type_id;
	}
 	public String getLocation(){
		return this.location;
	}
	public void setLocation(String location){
		this.location = location;
	}
 	public List getNon_mercado_pago_payment_methods(){
		return this.non_mercado_pago_payment_methods;
	}
	public void setNon_mercado_pago_payment_methods(List non_mercado_pago_payment_methods){
		this.non_mercado_pago_payment_methods = non_mercado_pago_payment_methods;
	}
 	public String getParent_item_id(){
		return this.parent_item_id;
	}
	public void setParent_item_id(String parent_item_id){
		this.parent_item_id = parent_item_id;
	}
 	public String getPermalink(){
		return this.permalink;
	}
	public void setPermalink(String permalink){
		this.permalink = permalink;
	}
 	public List getPictures(){
		return this.pictures;
	}
	public void setPictures(List pictures){
		this.pictures = pictures;
	}
 	public Number getPrice(){
		return this.price;
	}
	public void setPrice(Number price){
		this.price = price;
	}
 	public String getSecure_thumbnail(){
		return this.secure_thumbnail;
	}
	public void setSecure_thumbnail(String secure_thumbnail){
		this.secure_thumbnail = secure_thumbnail;
	}
 	public Seller_address getSeller_address(){
		return this.seller_address;
	}
	public void setSeller_address(Seller_address seller_address){
		this.seller_address = seller_address;
	}
 	public String getSeller_contact(){
		return this.seller_contact;
	}
	public void setSeller_contact(String seller_contact){
		this.seller_contact = seller_contact;
	}
 	public Number getSeller_id(){
		return this.seller_id;
	}
	public void setSeller_id(Number seller_id){
		this.seller_id = seller_id;
	}
 	public Shipping getShipping(){
		return this.shipping;
	}
	public void setShipping(Shipping shipping){
		this.shipping = shipping;
	}
 	public String getSite_id(){
		return this.site_id;
	}
	public void setSite_id(String site_id){
		this.site_id = site_id;
	}
 	public Number getSold_quantity(){
		return this.sold_quantity;
	}
	public void setSold_quantity(Number sold_quantity){
		this.sold_quantity = sold_quantity;
	}
 	public String getStart_time(){
		return this.start_time;
	}
	public void setStart_time(String start_time){
		this.start_time = start_time;
	}
 	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}
 	public String getStop_time(){
		return this.stop_time;
	}
	public void setStop_time(String stop_time){
		this.stop_time = stop_time;
	}
 	public List getSub_status(){
		return this.sub_status;
	}
	public void setSub_status(List sub_status){
		this.sub_status = sub_status;
	}
 	public String getSubtitle(){
		return this.subtitle;
	}
	public void setSubtitle(String subtitle){
		this.subtitle = subtitle;
	}
 	public List getTags(){
		return this.tags;
	}
	public void setTags(List tags){
		this.tags = tags;
	}
 	public String getThumbnail(){
		return this.thumbnail;
	}
	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public List getVariations(){
		return this.variations;
	}
	public void setVariations(List variations){
		this.variations = variations;
	}
 	public String getVideo_id(){
		return this.video_id;
	}
	public void setVideo_id(String video_id){
		this.video_id = video_id;
	}
 	public String getWarranty(){
		return this.warranty;
	}
	public void setWarranty(String warranty){
		this.warranty = warranty;
	}
}
