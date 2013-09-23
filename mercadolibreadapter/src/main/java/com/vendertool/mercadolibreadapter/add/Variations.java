
package com.vendertool.mercadolibreadapter.add;

import java.util.List;

public class Variations{
   	private List attribute_combinations;
   	private Number available_quantity;
   	private Number id;
   	private List picture_ids;
   	private Number price;
   	private String seller_custom_field;
   	private Number sold_quantity;

 	public List getAttribute_combinations(){
		return this.attribute_combinations;
	}
	public void setAttribute_combinations(List attribute_combinations){
		this.attribute_combinations = attribute_combinations;
	}
 	public Number getAvailable_quantity(){
		return this.available_quantity;
	}
	public void setAvailable_quantity(Number available_quantity){
		this.available_quantity = available_quantity;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public List getPicture_ids(){
		return this.picture_ids;
	}
	public void setPicture_ids(List picture_ids){
		this.picture_ids = picture_ids;
	}
 	public Number getPrice(){
		return this.price;
	}
	public void setPrice(Number price){
		this.price = price;
	}
 	public String getSeller_custom_field(){
		return this.seller_custom_field;
	}
	public void setSeller_custom_field(String seller_custom_field){
		this.seller_custom_field = seller_custom_field;
	}
 	public Number getSold_quantity(){
		return this.sold_quantity;
	}
	public void setSold_quantity(Number sold_quantity){
		this.sold_quantity = sold_quantity;
	}
}
