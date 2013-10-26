package com.vendertool.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vendertool.listing.ListingServiceimpl;
import com.vendertool.listing.dal.ListingDALService;
import com.vendertool.listing.processor.Module;
import com.vendertool.mercadolibreadapter.factory.MercadolibreCommunicatorVO;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.rnr.AddListingRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dal/dal-module.xml",
		"classpath:dal/InventoryDAL.xml", "classpath:dal/ListingDAL.xml",
		"classpath:client/ml/consumer.xml" })
public class ListingServiceTest implements ApplicationContextAware {
	private ApplicationContext context;
	private ListingDALService listingDalService;

	/**
	 * @param args
	 */
	@Test
	public void testInsert() {
		AddListingRequest input = new AddListingRequest();
		Listing listing = new Listing();
//		listing.setWarranty("Yes");
		Product product = new Product();
		product.setTitle("Test Test Test!");
		product.setAccountId(61234L);
		product.setProductCode("P1234");
		product.setSku("123ee");
		listing.setProduct(product);
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
		listing.setCreateOwnerId(334434L);
		listing.setQuantity(1);
//		listing.setCondition("used");
		listing.setMarket(MarketEnum.MERCADO_LIBRE);
		listing.setCountry(CountryEnum.ALL);
		input.setListing(listing);
		MercadolibreCommunicatorVO vo = new MercadolibreCommunicatorVO();
		vo.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		vo.setTargetURL("http://localhost:8080/services/listing/addListing");
		vo.setRequestObject(input);
		vo.setMethodEnum(HttpMethodEnum.POST);
		/*
		 * // Response resp = MercadolibreCommunicator.getInstance().call(vo);
		 * ClientConfig clientConfig = new
		 * ClientConfig(JacksonJsonProvider.class); Client client =
		 * ClientBuilder.newClient(clientConfig); client.register(new
		 * HttpBasicAuthFilter("7jgkcg5tts5fjp11j4e0vi2u1u",
		 * "vubunbrgubb95f844qmmutqogr9b3bl426ove5rjvk45aq57d5q")); WebTarget
		 * webtarget =
		 * client.target("http://localhost:8080/services/listing/addListing");
		 * Entity entity = Entity.entity(input, MediaType.APPLICATION_JSON);
		 * Response resp = webtarget.request(MediaType.APPLICATION_JSON)
		 * .accept(MediaType.APPLICATION_JSON) .post(entity, Response.class);
		 */
		Module.getInstance().init();
		com.vendertool.mercadolibreadapter.Module.getInstance().init();
		ListingServiceimpl impl = new ListingServiceimpl();
		impl.addListing(input);
		System.out.println("");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		context = applicationContext;
	}
}