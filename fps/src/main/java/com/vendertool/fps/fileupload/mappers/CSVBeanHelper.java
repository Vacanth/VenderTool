package com.vendertool.fps.fileupload.mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.core.ProductCodeTypeEnum;

public class CSVBeanHelper {
	
	private Amount getAmount(Currency currency, String value) {
		if (currency == null || value == null  || value.isEmpty()) {
			return null;
		}
		Amount amount = new Amount();
		amount.setValue(new BigDecimal(value));
		amount.setCurrency(currency);		
		return amount;
	}
	
	
	private Product beanToProduct(ListingBean lBean) {
		Product product = new Product();
		product.setTitle(lBean.getTitle());
		product.setDescription(lBean.getDescription());
		//product.setDimension(lBean.getDimension());
		product.setProductCode(lBean.getProductCode());
		product.setProductCodeType(ProductCodeTypeEnum.valueOf(lBean.getProductCodeType()));
		product.setSku(lBean.getSKU());
		//Continue....
		
		return product;
		
	}
	
	public Listing beanToListing(ListingBean lBean) {
		Listing listing = new Listing();
		Currency lCurrency = Currency.getInstance(lBean.getListingCurrency());
		
		listing.setListingCurrency(lCurrency);		
		List<Classification> classList = new ArrayList<Classification>();
		Classification clasif = new Classification();
		clasif.setClassifierId(lBean.getCategoryId());
		clasif.setClassificationType(ClassificationTypeEnum.get(Integer.valueOf(lBean.getClassification())));
		classList.add(clasif);
		listing.setClassifications(classList);

		listing.setFixedPrice(getAmount(lCurrency, lBean.getFixedPrice()));
		listing.setPrice(getAmount(lCurrency, lBean.getPrice()));
		listing.setProduct(beanToProduct(lBean));
		listing.setQuantity(Integer.valueOf(lBean.getListingQty()));
		listing.setCondition(lBean.getItemCondition());
		listing.setMarket(MarketEnum.MERCADO_LIBRE);
		listing.setCountry(CountryEnum.ALL);

		return listing;
	}
}
