package com.vendertool.mercadolibreadapter.factory;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.vendertool.mercadolibreadapter.add.Item;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
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

	private static String VERIFY_LISTING_URL = "https://api.mercadolibre.com/items/validate?access_token=$APP_USR-9773-041316-013619f30678959c46bbda5f211aff75__C_N__-107245974";
	private static String LISTING_URL = "https://api.mercadolibre.com/items?access_token=$APP_USR-9773-041316-013619f30678959c46bbda5f211aff75__C_N__-107245974";
	private static MercadolibreListingAdapter uniqInstance;

	private MercadolibreListingAdapter() {
	}

	public static synchronized MercadolibreListingAdapter getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new MercadolibreListingAdapter();
		}
		return uniqInstance;
	}

	public BaseResponse execute(BaseRequest request) {
		AddListingRequest listingRequest = (AddListingRequest) request;
		Item item = adaptToRequest(listingRequest);
		//Call Verify
		/*MercadolibreCommunicatorVO communicatorVO = new MercadolibreCommunicatorVO();
		communicatorVO.setRequestObject(item);
		communicatorVO.setMethodEnum(HttpMethodEnum.POST);
		communicatorVO.setTargetURL(VERIFY_LISTING_URL);
		MercadolibreCommunicator communicator = MercadolibreCommunicator.getInstance();
		ClientResponse response = communicator.call(communicatorVO);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = (String) response.getEntity(String.class);
		Item responseItem = readItem(output);
		
		//Call Add listing
		communicatorVO.setTargetURL(LISTING_URL);
		AddListingResponse addListingResponse = adaptTOResponse(responseItem);*/
		return null;
	}

	private AddListingResponse adaptTOResponse(Item responseItem) {
		AddListingResponse response = new AddListingResponse();
		response.setListingId(responseItem.getId());
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
			item.setCurrency_id("ARS");// TODO remove this hardcoding
		}

		ListingFormatEnum listingFormate = listing.getListingFormat();
		if (listingFormate != null) {
			String buyingMode = "buy_it_now";
			if (listingFormate == ListingFormatEnum.AD) {
				buyingMode = "classified";
			} else if (ListingFormatEnum.AUCTION == listingFormate) {
				buyingMode = "auction";
			}
			item.setBuying_mode(buyingMode);
		}

		item.setCondition(listing.getCondition());
		item.setListing_type_id(listing.getListingId());// TODO listing
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
		return item;
	}

	private Item prepareItemFromInventory(String productId) {
		Item item = new Item();
		// TODO make DB call and prepare this.
		return item;
	}

	private boolean isProductIDAvailalbe(Product product) {
		return product != null && product.getProductId() != null
				&& product.getProductId().length() > 0;
	}
}