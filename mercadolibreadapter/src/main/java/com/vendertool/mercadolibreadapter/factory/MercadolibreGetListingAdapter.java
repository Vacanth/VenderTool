package com.vendertool.mercadolibreadapter.factory;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.vendertool.mercadolibreadapter.add.Item;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.GetListingRequest;
import com.vendertool.sharedtypes.rnr.GetListingResponse;

public class MercadolibreGetListingAdapter implements
		IBaseMercadolibreOperationAdapter {

	private static String GET_LISTING_URL = "https://api.mercadolibre.com/items/";
	private static MercadolibreGetListingAdapter uniqInstance;

	private MercadolibreGetListingAdapter() {
	}

	public static synchronized MercadolibreGetListingAdapter getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new MercadolibreGetListingAdapter();
		}
		return uniqInstance;
	}

	public BaseResponse execute(BaseRequest request) {
		GetListingRequest itemId = (GetListingRequest)request;
		
		/*MercadolibreCommunicatorVO communicatorVO = new MercadolibreCommunicatorVO();
		communicatorVO.setMethodEnum(HttpMethodEnum.GET);
		communicatorVO.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		communicatorVO.setTargetURL(GET_LISTING_URL+itemId.getListingId());
		MercadolibreCommunicator communicator = MercadolibreCommunicator.getInstance();
		ClientResponse response = communicator.call(communicatorVO);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		String output = (String) response.getEntity(String.class);
		Item responseItem = readItem(output);
		
		GetListingResponse getListingResponse = adaptToGetListingResponse(responseItem);*/
		
		return null;
	}

	private GetListingResponse adaptToGetListingResponse(Item responseItem) {
		if(responseItem == null){
			return null;
		}
		GetListingResponse response = new GetListingResponse();
		
		Listing listing = new Listing();
		
		listing.setCondition(responseItem.getCondition());
		listing.setPrice(new Amount());
		listing.setQuantity(responseItem.getAvailable_quantity().intValue());
		Product product = new Product();
//		product.setDescription(responseItem.getDescriptions());
		product.setTitle(responseItem.getTitle());
		listing.setProduct(product);
		response.setListing(listing);
		return response;
	}

	private Item readItem(String output) {
		Item response = null;
		try {
			response = new ObjectMapper().readValue(output, Item.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}