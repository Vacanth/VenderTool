
package com.vendertool.mercadolibreadapter.add;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Descriptions{
   	private String id;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
}
