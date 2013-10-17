package com.vendertool.mercadolibreadapter.factory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vendertool.common.utils.AdapterUtils;
import com.vendertool.mercadolibreadapter.MLConstants;
import com.vendertool.mercadolibreadapter.add.ErrorResponse;
import com.vendertool.mercadolibreadapter.add.Item;
import com.vendertool.mercadolibreadapter.utils.ErrorMapperHelper;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.Listing.ListingFormatEnum;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.PaymentMethod;
import com.vendertool.sharedtypes.core.PaymentMethod.PaymentTypeEnum;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;

public class MercadolibreListingAdapter implements
		IBaseMercadolibreOperationAdapter {

	private static String LISTING_URL = "https://api.mercadolibre.com/items?access_token=APP_USR-6965385537109061-101621-65f1d728e89f9240031927bbbe14b529__C_G__-141983227";

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
		String output = resp.readEntity(String.class);

		if (resp.getStatus() != Response.Status.CREATED.getStatusCode()) {
			try {
				processErrorResponse(output, (AddListingResponse) response);
			} catch (IOException e) {
				//Set System Error!
			}
			return;
		}

		Item responseItem = readItem(output);

		// Call Add listing
		adaptTOResponse(responseItem, (AddListingResponse) response,(AddListingRequest) request);
	}

	private void processErrorResponse(String output, AddListingResponse response) throws JsonParseException, JsonMappingException, IOException {
		ErrorResponse error = 	new ObjectMapper().readValue(output, ErrorResponse.class);
		if(error != null){
			ErrorMapperHelper.getInstance().populateErrors(response, error);
		}
	}

	private void adaptTOResponse(Item responseItem, AddListingResponse response, AddListingRequest request) {
		Listing listing = request.getListing();
		listing.setMarketPlaceListingId(responseItem.getId());
		response.setListing(listing);
	}
	
	private Item readItem(String output) {
		Item response = null;
		try {
			response = AdapterUtils.getInstance().getObjectMapper(false).readValue(output, Item.class);
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
		product.setTitle("Test Test Test!");

		List<Classification> classList = new ArrayList<Classification>();
		Classification clasif = new Classification();
		clasif.setClassifierId("MLA5529");
		clasif.setClassificationType(ClassificationTypeEnum.CATEGORY);
		classList.add(clasif);
		listing.setClassifications(classList);
		listing.setListingCurrency(Currency.getInstance("ARS"));
		Amount amount = new Amount();
		amount.setValue(new BigDecimal(3.3));
		amount.setCurrency(Currency.getInstance("ARS"));
		listing.setFixedPrice(amount);
		listing.setPrice(amount);
		listing.setProduct(product);
		listing.setQuantity(1);
		listing.setCondition("used");
		listing.setMarket(MarketEnum.MERCADO_LIBRE);
		listing.setCountry(CountryEnum.ALL);
		input.setListing(listing);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MercadolibreListingAdapterHolder.INSTANCE.execute(input,
				new AddListingResponse());
	}
}