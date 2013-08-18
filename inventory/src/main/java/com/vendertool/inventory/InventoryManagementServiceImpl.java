package com.vendertool.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.AddProductImageRequest;
import com.vendertool.sharedtypes.rnr.AddProductImageResponse;
import com.vendertool.sharedtypes.rnr.AddProductRequest;
import com.vendertool.sharedtypes.rnr.AddProductResponse;
import com.vendertool.sharedtypes.rnr.AddProductVariationRequest;
import com.vendertool.sharedtypes.rnr.AddProductVariationResponse;
import com.vendertool.sharedtypes.rnr.AdjustProductQuantityRequest;
import com.vendertool.sharedtypes.rnr.AdjustProductQuantityResponse;
import com.vendertool.sharedtypes.rnr.DuplicateProductResponse;
import com.vendertool.sharedtypes.rnr.GetProductResponse;
import com.vendertool.sharedtypes.rnr.GetProductVariationResponse;
import com.vendertool.sharedtypes.rnr.RemoveProductResponse;
import com.vendertool.sharedtypes.rnr.RemoveProductVariationResponse;
import com.vendertool.sharedtypes.rnr.UpdateProductPriceQuanityResponse;
import com.vendertool.sharedtypes.rnr.UpdateProductPriceQuantityRequest;
import com.vendertool.sharedtypes.rnr.UpdateProductRequest;
import com.vendertool.sharedtypes.rnr.UpdateProductResponse;

@Controller
@RequestMapping("/inventory")
public class InventoryManagementServiceImpl extends BaseVenderToolServiceImpl
		implements IInventoryManagementService {

	@RequestMapping(value = "/getProduct", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	GetProductResponse getProduct(
			@RequestParam(value = "productId", required = false) String id) {
		GetProductResponse response = new GetProductResponse();
		Product product = new Product("iPhone 5");
		String pid = "P123456789";
		if (id == null) {
			id = pid;
		}
		product.setProductId(id);
		response.setProduct(product);
		response.addError(Errors.INVENTORY.INVALID_PRODUCT_CODE);

		return response;
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	AddProductResponse addProduct(@RequestBody AddProductRequest request) {
		Product product = request.getProduct();
		System.out.println("/inventory/addproduct/ call. Adding product ... "
				+ product.toString());

		AddProductResponse response = new AddProductResponse();
		response.setProductId("P987654321");
		return response;
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	UpdateProductResponse updateProduct(
			@RequestBody UpdateProductRequest request) {
		return null;
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.DELETE, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	RemoveProductResponse removeProduct(
			@RequestParam(value = "productId", required = false) String productId) {
		return null;
	}

	@RequestMapping(value = "/updateProductPriceQuantity", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	UpdateProductPriceQuanityResponse updateProductPriceQuantity(
			@RequestBody UpdateProductPriceQuantityRequest request) {
		return null;
	}

	@RequestMapping(value = "/adjustQuantity", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	AdjustProductQuantityResponse adjustQuantity(
			@RequestBody AdjustProductQuantityRequest request) {
		return null;
	}

	@RequestMapping(value = "/duplicateProduct", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	DuplicateProductResponse duplicateProduct(@RequestBody String productId) {
		return null;
	}

	@RequestMapping(value = "/addProductVariation", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	AddProductVariationResponse addProductVariation(
			@RequestBody AddProductVariationRequest request) {
		return null;
	}

	@RequestMapping(value = "/removeProductVariation", method = RequestMethod.DELETE, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	RemoveProductVariationResponse removeProductVariation(String productId,
			String variationId) {
		return null;
	}

	@RequestMapping(value = "/getProductVariation", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody
	GetProductVariationResponse getProductVariation(
			@RequestParam(value = "productId", required = false) String productId,
			@RequestParam(value = "variationId", required = false) String variationId) {
		return null;
	}

	@RequestMapping(value = "/addProductImage", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public @ResponseBody
	AddProductImageResponse addProductImage(
			@RequestBody AddProductImageRequest request) {
		return null;
	}
}