package com.vendertool.mercadolibreadapter.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendertool.mercadolibreadapter.MLConstants;
import com.vendertool.mercadolibreadapter.add.Item;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Listing.ListingFormatEnum;
import com.vendertool.sharedtypes.core.PaymentMethod;
import com.vendertool.sharedtypes.core.PaymentMethod.PaymentTypeEnum;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;

public class MercadolibreListingAdapter implements
		IBaseMercadolibreOperationAdapter {

	private static String LISTING_URL = "https://api.mercadolibre.com/items?access_token=APP_USR-6965385537109061-100922-284582fa55b9dc63c1f4a9ff69c8b8d4__F_I__-141983227";

	private MercadolibreListingAdapter() {
	}

	private static class MercadolibreListingAdapterHolder {
		private static final MercadolibreListingAdapter INSTANCE = new MercadolibreListingAdapter();
	}

	public static MercadolibreListingAdapter getInstance() {
		return MercadolibreListingAdapterHolder.INSTANCE;
	}

	private static MercadolibreCommunicator s_communicator = MercadolibreCommunicator
			.getInstance();

	public void execute(BaseRequest request, BaseResponse response) {
		AddListingRequest listingRequest = (AddListingRequest) request;
		Item item = adaptToRequest(listingRequest);
		// Call Verify
		MercadolibreCommunicatorVO communicatorVO = new MercadolibreCommunicatorVO();
		communicatorVO.setRequestObject(item);
		communicatorVO.setMethodEnum(HttpMethodEnum.POST);
		communicatorVO.setTargetURL(LISTING_URL);
		communicatorVO.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		Response resp = s_communicator.call(communicatorVO);

		// Handle the error codes
		if (resp.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = resp.readEntity(String.class);
		Item responseItem = readItem(output);

		// Call Add listing
		adaptTOResponse(responseItem, (AddListingResponse) response);
	}

	private void adaptTOResponse(Item responseItem, AddListingResponse response) {
		response.setListingId(responseItem.getId());
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

	private Item adaptToRequest(AddListingRequest listingRequest) {
		if (listingRequest == null) {
			return null;
		}
		Listing listing = listingRequest.getListing();

		if (listing == null) {
			return null;
		}
		Item item = null;

		Product product = listing.getProduct();

		if (isProductIDAvailalbe(product)) {
			item = prepareItemFromInventory(product.getProductId());// TODO
		} else {
			item = getFromRequest(listing);
		}
		return item;
	}

	private Item getFromRequest(Listing listing) {
		Product product = listing.getProduct();

		if (product == null) {
			return null;
		}
		// TODO validate the request?
		Item item = new Item();
		item.setTitle(product.getTitle());
		List<Classification> classifications = listing.getClassifications();

		// Set Category
		if (classifications != null) {
			for (Classification classification : classifications) {
				if (classification == null) {
					continue;
				}
				ClassificationTypeEnum classificationType = classification
						.getClassificationType();
				if (ClassificationTypeEnum.CATEGORY == classificationType) {
					item.setCategory_id(classification.getClassifierId());
				}
			}
		}

		// Set Price
		if (listing.getPrice() != null) {
			item.setPrice(listing.getPrice().getValue());
			item.setCurrency_id(listing.getPrice().getCurrency()
					.getCurrencyCode());// TODO remove this hardcoding
		}
		item.setListing_type_id("bronze");
		ListingFormatEnum listingFormate = listing.getListingFormat();
		if (listingFormate != null) {
			String buyingMode = MLConstants.ListingConstants.BUY_IT_NOW;
			if (listingFormate == ListingFormatEnum.AD) {
				buyingMode = MLConstants.ListingConstants.CLASSIFIEDS;
			} else if (ListingFormatEnum.AUCTION == listingFormate) {
				buyingMode = MLConstants.ListingConstants.AUCTION;
			}
			item.setBuying_mode(buyingMode);
		}

		item.setCondition(listing.getCondition());
		// item.setListing_type_id(listing.getListingId());// TODO listing
		item.setAvailable_quantity(listing.getQuantity());
		// Set Payment menthods.
		List<PaymentMethod> paymentMethods = listing.getPaymentMethods();
		if (paymentMethods != null) {
			for (PaymentMethod paymentMethod : paymentMethods) {
				if (paymentMethod == null) {
					continue;
				}
				PaymentTypeEnum paymentMethodType = paymentMethod.getMethod();
				if (PaymentTypeEnum.MERCADO_PAGO == paymentMethodType) {
					item.setAccepts_mercadopago(true);
				}
			}
		}

		// item.setVariations(variations);
		return item;
	}

	private Item prepareItemFromInventory(Long productId) {
		Item item = new Item();
		// TODO make DB call and prepare this.
		return item;
	}

	private boolean isProductIDAvailalbe(Product product) {
		return product != null && product.getProductId() != null && product.getProductId() > 0;
	}

	// ********** Dev Testing *******
	public static void main(String args[]) {
		AddListingRequest input = new AddListingRequest();
		Listing listing = new Listing();
		listing.setWarranty("Yes");
		
		Product product = new Product();
		product.setTitle("Anteojos Ray Ban Wayfare");

		List<Classification> classList = new ArrayList<Classification>();
		Classification clasif = new Classification();
		clasif.setClassifierId("MLA5529");
		clasif.setClassificationType(ClassificationTypeEnum.CATEGORY);
		classList.add(clasif);
		listing.setClassifications(classList);
		listing.setProduct(product);
		input.setListing(listing);

		/*
		 * Response r = m.post("/items", params,
		 * "{\"title\":\"Anteojos Ray Ban Wayfare\",\"subtitle\":\"Some subtitle here\",\"category_id\":\"MLA5529\",
		 * \"price\":10,\"currency_id\":\"ARS\",\"available_quantity\":1,\"buying_mode\":\"buy_it_now\",\"listing_type_id\":\"bronze\",
		 * \"condition\":\"new\",\"description\": 
		 * \"Item:, <strong> Ray-Ban WAYFARER Gloss Black RB2140 901 </strong> Model: RB2140. 
		 * Size: 50mm. Name: WAYFARER. Color: Gloss Black. Includes Ray-Ban Carrying Case and Cleaning Cloth. New in Box\",
		 * \"video_id\":\"YOUTUBE_ID_HERE\",\"warranty\":\"12 month by Ray Ban\",
		 * \"pictures\":[{\"source\":\"http://upload.wikimedia.org/wikipedia/commons/f/fd/Ray_Ban_Original_Wayfarer.jpg\"},
		 * {\"source\":\"http://en.wikipedia.org/wiki/File:Teashades.gif\"}]}"
		 * );
		 */
		MercadolibreListingAdapterHolder.INSTANCE.execute(input,
				new AddListingResponse());
	}
}