package com.vendertool.mercadolibreadapter.client;

/**
 * Make sure to add entry @below files
 * 
 * --> mercadolibreadapter\src\main\resources\client\prod\consumer.xml
 * --> mercadolibreadapter\src\main\resources\client\qa\consumer.xml
 * 
 *	The bean id should match with the 'com.vendertool.mercadolibreadapter.client.MLAPIEnum'.
 *
 * @author Girish
 *
 */
public enum MLAPIEnum {
	ADD_LISTING("ml-addListing");
	
	private String token;
	
	private MLAPIEnum(String token){
		this.token = token; 
	}
	
	public String getToken(){
		return this.token;
	}
}