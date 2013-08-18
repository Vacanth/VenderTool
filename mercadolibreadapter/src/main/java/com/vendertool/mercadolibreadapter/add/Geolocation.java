
package com.vendertool.mercadolibreadapter.add;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geolocation{
   	private String latitude;
   	private String longitude;

 	public String getLatitude(){
		return this.latitude;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
 	public String getLongitude(){
		return this.longitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
}
