
package com.vendertool.mercadolibreadapter.add;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipping{
   	private String dimensions;
   	private boolean free_shipping;
   	private boolean local_pick_up;
   	private List methods;
   	private String mode;
   	private String profile_id;

 	public String getDimensions(){
		return this.dimensions;
	}
	public void setDimensions(String dimensions){
		this.dimensions = dimensions;
	}
 	public boolean getFree_shipping(){
		return this.free_shipping;
	}
	public void setFree_shipping(boolean free_shipping){
		this.free_shipping = free_shipping;
	}
 	public boolean getLocal_pick_up(){
		return this.local_pick_up;
	}
	public void setLocal_pick_up(boolean local_pick_up){
		this.local_pick_up = local_pick_up;
	}
 	public List getMethods(){
		return this.methods;
	}
	public void setMethods(List methods){
		this.methods = methods;
	}
 	public String getMode(){
		return this.mode;
	}
	public void setMode(String mode){
		this.mode = mode;
	}
 	public String getProfile_id(){
		return this.profile_id;
	}
	public void setProfile_id(String profile_id){
		this.profile_id = profile_id;
	}
}
