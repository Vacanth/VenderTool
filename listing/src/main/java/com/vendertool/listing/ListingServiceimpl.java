package com.vendertool.listing;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.listing.processor.BaseListingProcessor;
import com.vendertool.listing.processor.ListingProcessorFactory;
import com.vendertool.listing.processor.ListingProcessorTypeEnum;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.AdjustListingQuantityRequest;
import com.vendertool.sharedtypes.rnr.AdjustListingQuantityResponse;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.DuplicateListingRequest;
import com.vendertool.sharedtypes.rnr.DuplicateListingResponse;
import com.vendertool.sharedtypes.rnr.EndListingRequest;
import com.vendertool.sharedtypes.rnr.EndListingResponse;
import com.vendertool.sharedtypes.rnr.GetAuctionBidCountRequest;
import com.vendertool.sharedtypes.rnr.GetAuctionBidCountResponse;
import com.vendertool.sharedtypes.rnr.GetAuctionHighBidderRequest;
import com.vendertool.sharedtypes.rnr.GetAuctionHighBidderResponse;
import com.vendertool.sharedtypes.rnr.GetListingPriceQuantityRequest;
import com.vendertool.sharedtypes.rnr.GetListingPriceQuantityResponse;
import com.vendertool.sharedtypes.rnr.GetListingRequest;
import com.vendertool.sharedtypes.rnr.GetListingResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingPriceQuanityResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingPriceQuantityRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingResponse;
import com.vendertool.sharedtypes.rnr.VerifyListingRequest;
import com.vendertool.sharedtypes.rnr.VerifyListingResponse;

@Path("/listing")
public class ListingServiceimpl extends BaseVenderToolServiceImpl implements
		IListingService {

	@GET
	@Path("/getListing")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetListingResponse getListing(@QueryParam("listingId") String listingId) {
		GetListingRequest request = new GetListingRequest();
		request.setListingId(listingId);
		GetListingResponse response = new GetListingResponse();
		process(request, response, ListingProcessorTypeEnum.GET_LISTING_PRICE_QTY);
		return response;
	}

	@POST
	@Path("/addListing")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AddListingResponse addListing(AddListingRequest request) {
		AddListingResponse response = new AddListingResponse();
		process(request, response, ListingProcessorTypeEnum.ADD_LISTING);
		return response;
	}

	@GET
	@Path("/getListingPriceQuantity")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetListingPriceQuantityResponse getListingPriceQuantity(
			@QueryParam("listingId") String listingId) {
		GetListingPriceQuantityRequest request = new GetListingPriceQuantityRequest();
		request.setListingId(listingId);
		GetListingPriceQuantityResponse response = new GetListingPriceQuantityResponse();
		process(request, response, ListingProcessorTypeEnum.GET_LISTING_PRICE_QTY);
		return response;
	}

	@GET
	@Path("/endListing")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public EndListingResponse endListing(
			@QueryParam("listingId") String listingId) {
		EndListingRequest request = new EndListingRequest();
		request.setListingId(listingId);
		EndListingResponse response = new EndListingResponse();
		process(request, response, ListingProcessorTypeEnum.END_LISTING);
		return response;
	}

	@PUT
	@Path("/updateListing")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UpdateListingResponse updateListing(UpdateListingRequest request) {
		UpdateListingResponse response = new UpdateListingResponse();
		process(request, response, ListingProcessorTypeEnum.UPDATE_LISTING);
		return response;
	}

	@PUT
	@Path("/updateListingPriceQuantity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UpdateListingPriceQuanityResponse updateListingPriceQuantity(
			UpdateListingPriceQuantityRequest request) {
		UpdateListingPriceQuanityResponse response = new UpdateListingPriceQuanityResponse();
		process(request, response,
				ListingProcessorTypeEnum.UPDATE_LISTING_PRICE_QTY);
		return response;
	}

	@PUT
	@Path("/adjustListingQuantity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AdjustListingQuantityResponse adjustListingQuantity(
			AdjustListingQuantityRequest request) {
		AdjustListingQuantityResponse response = new AdjustListingQuantityResponse();
		process(request, response,
				ListingProcessorTypeEnum.ADJUST_LISTING_QUANTITY);
		return response;
	}

	@GET
	@Path("/duplicateListing")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public DuplicateListingResponse duplicateListing(
			@QueryParam("listingId") String listingId) {
		DuplicateListingRequest request = new DuplicateListingRequest();
		request.setListingId(listingId);
		DuplicateListingResponse response = new DuplicateListingResponse();
		process(request, response, ListingProcessorTypeEnum.DUPLICATE_LISTING);
		return response;
	}

	@GET
	@Path("/getAuctionBidCount")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetAuctionBidCountResponse getAuctionBidCount(
			@QueryParam("listingId") String listingId) {
		GetAuctionBidCountRequest request = new GetAuctionBidCountRequest();
		request.setListingId(listingId);
		GetAuctionBidCountResponse response = new GetAuctionBidCountResponse();
		process(request, response, ListingProcessorTypeEnum.GET_AUCTION_BID_COUNT);
		return response;
	}

	@GET
	@Path("/getAuctionHighBidder")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetAuctionHighBidderResponse getAuctionHighBidder(
			@QueryParam("listingId") String listingId) {
		GetAuctionHighBidderRequest request = new GetAuctionHighBidderRequest();
		request.setListingId(listingId);
		GetAuctionHighBidderResponse response = new GetAuctionHighBidderResponse();
		process(request, response,
				ListingProcessorTypeEnum.GET_AUCTION_HIGH_BIDDER);
		return response;
	}

	@POST
	@Path("/verifyListing")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public VerifyListingResponse verifyListing(VerifyListingRequest request) {
		VerifyListingResponse response = new VerifyListingResponse();
		process(request, response, ListingProcessorTypeEnum.VERIFY_LISTING);
		return response;
	}

	private void process(BaseRequest request, BaseResponse response,
			ListingProcessorTypeEnum processorType) {
		BaseListingProcessor processor = ListingProcessorFactory
				.getListingProcessor(processorType);
		processor.validate(request, response);
		if (!response.hasErrors()) {
			processor.performOperation(request, response);
		}
	}
}