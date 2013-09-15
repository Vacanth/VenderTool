package com.vendertool.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.inventory.processor.BaseInventoryProcessor;
import com.vendertool.inventory.processor.InventoryProcessorFactory;
import com.vendertool.inventory.processor.InventoryProcessorTypeEnum;
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
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.DuplicateProductResponse;
import com.vendertool.sharedtypes.rnr.GetProductResponse;
import com.vendertool.sharedtypes.rnr.GetProductVariationResponse;
import com.vendertool.sharedtypes.rnr.RemoveProductResponse;
import com.vendertool.sharedtypes.rnr.RemoveProductVariationResponse;
import com.vendertool.sharedtypes.rnr.UpdateProductPriceQuanityResponse;
import com.vendertool.sharedtypes.rnr.UpdateProductPriceQuantityRequest;
import com.vendertool.sharedtypes.rnr.UpdateProductRequest;
import com.vendertool.sharedtypes.rnr.UpdateProductResponse;

@Path("/inventory")
public class InventoryManagementServiceImpl extends BaseVenderToolServiceImpl
		implements IInventoryManagementService {

	@GET
	@Path("/getProduct")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetProductResponse getProduct(@QueryParam("productId") String id) {

		GetProductResponse response = new GetProductResponse();
		Product product = new Product("iPhone 5");
		String pid = "P123456789";
		if (id == null) {
			id = pid;
		}
//		product.setProductId(id);
		response.setProduct(product);
		response.addFieldBindingError(Errors.INVENTORY.INVALID_PRODUCT_CODE,
				product.getClass().getName(), "productCode");

		return response;
	}

	@POST
	@Path("/addProduct")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AddProductResponse addProduct(AddProductRequest request) {
		AddProductResponse response = new AddProductResponse();
		process(request, response, InventoryProcessorTypeEnum.ADD_PRODUCT);
		return response;
	}

	@POST
	@Path("/updateProduct")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UpdateProductResponse updateProduct(UpdateProductRequest request) {
		return null;
	}

	@DELETE
	@Path("/removeProduct")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public RemoveProductResponse removeProduct(
			@QueryParam("productId") String productId) {
		return null;
	}

	@POST
	@Path("/updateProductPriceQuantity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UpdateProductPriceQuanityResponse updateProductPriceQuantity(
			UpdateProductPriceQuantityRequest request) {
		return null;
	}

	@POST
	@Path("/adjustQuantity")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AdjustProductQuantityResponse adjustQuantity(
			AdjustProductQuantityRequest request) {
		return null;
	}

	@POST
	@Path("/duplicateProduct")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public DuplicateProductResponse duplicateProduct(String productId) {
		return null;
	}

	@POST
	@Path("/addProductVariation")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AddProductVariationResponse addProductVariation(
			AddProductVariationRequest request) {
		return null;
	}

	@DELETE
	@Path("/removeProductVariation")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public RemoveProductVariationResponse removeProductVariation(
			@QueryParam(value = "productId") String productId,
			@QueryParam(value = "variationId") String variationId) {
		return null;
	}

	@GET
	@Path("/getProductVariation")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetProductVariationResponse getProductVariation(
			@QueryParam("productId") String productId,
			@QueryParam("variationId") String variationId) {
		return null;
	}

	@POST
	@Path("/addProductImage")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AddProductImageResponse addProductImage(
			AddProductImageRequest request) {
		return null;
	}

	private void process(BaseRequest request, BaseResponse response,
			InventoryProcessorTypeEnum processorType) {
		BaseInventoryProcessor processor = InventoryProcessorFactory
				.getInventoryProcessor(processorType);
		processor.validate(request, response);
		if (!response.hasErrors()) {
			processor.performOperation(request, response);
		}
	}
}
