package com.vendertool.listing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.common.MarketCountryKey;
import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.mercadolibreadapter.IMarketListingAdapter;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.AdjustListingQuantityRequest;
import com.vendertool.sharedtypes.rnr.AdjustListingQuantityResponse;
import com.vendertool.sharedtypes.rnr.DuplicateListingResponse;
import com.vendertool.sharedtypes.rnr.EndListingResponse;
import com.vendertool.sharedtypes.rnr.GetAuctionBidCountResponse;
import com.vendertool.sharedtypes.rnr.GetAuctionHighBidderResponse;
import com.vendertool.sharedtypes.rnr.GetListingPriceQuantityResponse;
import com.vendertool.sharedtypes.rnr.GetListingResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingPriceQuanityResponse;
import com.vendertool.sharedtypes.rnr.UpdateListingPriceQuantityRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingRequest;
import com.vendertool.sharedtypes.rnr.UpdateListingResponse;
import com.vendertool.sharedtypes.rnr.VerifyListingRequest;
import com.vendertool.sharedtypes.rnr.VerifyListingResponse;

@Controller
@RequestMapping("/listing")
public class ListingServiceimpl extends BaseVenderToolServiceImpl implements
		IListingService {

	@RequestMapping(value = "/getListing", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	public @ResponseBody
	GetListingResponse getListing(
			@RequestParam(value = "listingId", required = false) String id) {
		CountryEnum countryId = CountryEnum.ALL;
		MarketEnum marketId = MarketEnum.MERCADO_LIBRE;

		IMarketListingAdapter adapter = ListingMarketAdapterRegistry
				.getInstance().getMarketListingAdapter(
						new MarketCountryKey(countryId, marketId));

		return adapter.getListing(id);
	}

	@RequestMapping(value = "/addListing", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	AddListingResponse addListing(@RequestBody AddListingRequest request) {
		Listing listing = request.getListing();
		IMarketListingAdapter adapter = ListingMarketAdapterRegistry
				.getInstance().getMarketListingAdapter(
						new MarketCountryKey(listing.getCountry(), listing
								.getMarket()));
		adapter.addListing(request);

		AddListingResponse response = new AddListingResponse();
		response.setListingId("L987654321");

		return response;
	}

	@RequestMapping(value = "/getListingPriceQuantity", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	public @ResponseBody
	GetListingPriceQuantityResponse getListingPriceQuantity(
			@RequestParam(value = "listingId", required = false) String listingId) {
		return null;
	}

	@RequestMapping(value = "/endListing", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	EndListingResponse endListing(@RequestBody String listingId) {
		return null;
	}

	@RequestMapping(value = "/updateListing", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	UpdateListingResponse updateListing(
			@RequestBody UpdateListingRequest request) {
		return null;
	}

	@RequestMapping(value = "/updateListingPriceQuantity", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	UpdateListingPriceQuanityResponse updateListingPriceQuantity(
			@RequestBody UpdateListingPriceQuantityRequest request) {
		return null;
	}

	@RequestMapping(value = "/adjustListingQuantity", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	AdjustListingQuantityResponse adjustListingQuantity(
			@RequestBody AdjustListingQuantityRequest request) {
		return null;
	}

	@RequestMapping(value = "/adjustListingQuantity", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" })
	public @ResponseBody
	DuplicateListingResponse duplicateListing(
			@RequestParam(value = "listingId", required = false) String listingId) {
		return null;
	}

	@RequestMapping(value = "/getAuctionBidCount", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" })
	public @ResponseBody
	GetAuctionBidCountResponse getAuctionBidCount(
			@RequestParam(value = "listingId", required = false) String listingId) {
		return null;
	}

	@RequestMapping(value = "/getAuctionHighBidder", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" })
	public @ResponseBody
	GetAuctionHighBidderResponse getAuctionHighBidder(
			@RequestParam(value = "listingId", required = false) String listingId) {
		return null;
	}

	@RequestMapping(value = "/verifyListing", method = RequestMethod.POST, produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public @ResponseBody
	VerifyListingResponse verifyListing(
			@RequestBody VerifyListingRequest request) {
		return null;
	}
}
