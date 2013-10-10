package com.vendertool.listing;

import java.util.HashMap;
import java.util.Map;

import com.vendertool.common.MarketCountryKey;
import com.vendertool.common.marketplace.IMarketListingAdapter;
import com.vendertool.mercadolibreadapter.MCLMarketListingAdapterImpl;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.exception.VTRuntimeException;

public class ListingMarketAdapterRegistry {
	  
	private final static Map<MarketCountryKey, String> REGISTRY = 
			new HashMap<MarketCountryKey, String>();
	 
	private static class ListingMarketAdapterRegistryHolder {
		private static final ListingMarketAdapterRegistry INSTANCE = new ListingMarketAdapterRegistry();
	}
	
	  public static ListingMarketAdapterRegistry getInstance() {
	    return ListingMarketAdapterRegistryHolder.INSTANCE;
	  }
	  
	  private ListingMarketAdapterRegistry() {
		  register(new MarketCountryKey(
				  	CountryEnum.ALL, 
				  	MarketEnum.MERCADO_LIBRE), 
				  	MCLMarketListingAdapterImpl.class.getName());
		  
	  }
	  
	  private void register(MarketCountryKey key, String adapterClass) {
		  if((key == null) || (adapterClass == null)) {
			  throw new VTRuntimeException("MarketServiceRegistry.register: " +
			  		"MarketCountryKey or adapterClass cannot be null");
		  }
		  
		  if(REGISTRY.containsKey(key)) {
			  throw new VTRuntimeException("MarketServiceRegistry already contains key '" + key + "'");
		  }
		  
		  REGISTRY.put(key, adapterClass);
	  }
	  
	  public IMarketListingAdapter getMarketListingAdapter(MarketCountryKey key){
		  if(key == null) {
			  return null;
		  }
		  
		  String className = REGISTRY.get(key);
		  if(className == null) {
			  return null;
		  }
		  
		  Class<?> adapterClass = null;
		  try {
			  adapterClass = Class.forName(className);
		  } catch(ClassNotFoundException cnfex) {
			  throw new VTRuntimeException(cnfex);
		  }
		  
		  Object o = null;
		  try {
			  o = adapterClass.newInstance();
		  } catch (IllegalAccessException iaex) {
			  throw new VTRuntimeException(iaex);
		  } catch (InstantiationException iex) {
			  throw new VTRuntimeException(iex);
		  }
		  
		  return (IMarketListingAdapter) o;
	  }
}
