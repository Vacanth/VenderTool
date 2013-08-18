package com.vendertool.inventory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public GetProductResponse getProduct(
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
	public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
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
	public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest request) {
		return null;
	}

	
	@RequestMapping(value = "/removeProduct", method = RequestMethod.DELETE, produces = {
			"application/xml", "application/json" })
	public RemoveProductResponse removeProduct(
			@RequestParam(value = "productId", required = false) String productId) {
		return null;
	}

	public UpdateProductPriceQuanityResponse updateProductPriceQuantity(
			UpdateProductPriceQuantityRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public AdjustProductQuantityResponse adjustQuantity(
			AdjustProductQuantityRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public DuplicateProductResponse duplicateProduct(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	public AddProductVariationResponse addProductVariation(
			AddProductVariationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public RemoveProductVariationResponse removeProductVariation(
			String productId, String variationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public GetProductVariationResponse getProductVariation(String productId,
			String variationId) {
		// TODO Auto-generated method stub
		return null;
	}

	public AddProductImageResponse addProductImage(
			AddProductImageRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@POST
	@Path("/updateProductPriceQuantity")
	@ConsumeMime({ "application/xml", "application/json" })
	@ProduceMime({ "application/xml", "application/json" })
	public UpdateProductPriceQuanityResponse updateProductPriceQuantity(
			UpdateProductPriceQuantityRequest request) {
		return null;
	}

	@POST
	@Path("/adjustQuantity")
	@ConsumeMime({ "application/xml", "application/json" })
	@ProduceMime({ "application/xml", "application/json" })
	public AdjustProductQuantityResponse adjustQuantity(
			AdjustProductQuantityRequest request) {
		return null;
	}

	@POST
	@Path("/duplicateProduct")
	@ProduceMime({ "application/xml", "application/json" })
	public DuplicateProductResponse duplicateProduct(String productId) {
		return null;
	}

	@POST
	@Path("/addProductVariation")
	@ConsumeMime({ "application/xml", "application/json" })
	@ProduceMime({ "application/xml", "application/json" })
	public AddProductVariationResponse addProductVariation(
			AddProductVariationRequest request) {
		return null;
	}

	@DELETE
	@Path("/removeProductVariation")
	@ProduceMime({ "application/xml", "application/json" })
	public RemoveProductVariationResponse removeProductVariation(
			String productId, String variationId) {
		return null;
	}

	@GET
	@Path("/getProductVariation")
	@ProduceMime({ "application/xml", "application/json" })
	public GetProductVariationResponse getProductVariation(
			@QueryParam("productId") String productId,
			@QueryParam("variationId") String variationId) {
		return null;
	}

	@POST
	@Path("/addProductImage")
	@ConsumeMime({ "application/xml", "application/json" })
	@ProduceMime({ "application/xml", "application/json" })
	public AddProductImageResponse addProductImage(
			AddProductImageRequest request) {
		return null;
	}*/
}
