
package com.vendertool.mercadolibreadapter.add;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Descriptions{
   	private String id;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
}
