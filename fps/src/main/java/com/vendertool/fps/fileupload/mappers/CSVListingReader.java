package com.vendertool.fps.fileupload.mappers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.supercsv.exception.SuperCsvException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.sharedtypes.core.Amount;
import com.vendertool.sharedtypes.core.Classification;
import com.vendertool.sharedtypes.core.Classification.ClassificationTypeEnum;
import com.vendertool.sharedtypes.core.CountryEnum;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.core.ProductCodeTypeEnum;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Task;

public class CSVListingReader extends CSVBaseReader{
	private static int CHUNK_SIZE = 100;
	private long recordCount=0;
	
	public CSVListingReader(String filePath) {
		super.initHeader(filePath);
	}

	public ListingBean readDataFromFile() throws SuperCsvException,IOException { 
		ListingBean prodBean = getBeanReader().read(ListingBean.class, getHeader(), getProcessors());
		return prodBean;
	}
	
	public void processData(long jobId, String fileGrpId, long accountId, long fileId) throws Exception {                            
		List<ListingBean> prodList = readNListingDataFromFile(CHUNK_SIZE);
		
		Task task = new Task();
		task.setJobId(jobId);;
		task.setAccountId(accountId);
		task.setRequestGroupId(fileGrpId);
		task.setRequestFileId(fileId);
		
		while (prodList != null && !prodList.isEmpty()) {
			processTask(prodList, task);
			prodList = readNListingDataFromFile(CHUNK_SIZE);
		}
	}
	
	public void processTask(List<ListingBean> lstBean, Task iTask) {
		if (lstBean != null && !lstBean.isEmpty()) {
			List<Task> lTask = new ArrayList<Task>(lstBean.size());
			
			for (ListingBean lBean: lstBean) {
				Task task = new Task();
				task.setJobId(iTask.getJobId());;
				task.setAccountId(iTask.getAccountId());
				task.setRequestGroupId(iTask.getRequestGroupId());
				task.setRequestFileId(iTask.getRequestFileId());
				task.setRecordId(recordCount++);
				//task.setRequest(new JSONObject(lstBean).toString().getBytes());
				task.setStatus(FPSTaskStatusEnum.CREATED);
				task.setIsoCountryCode(lBean.getCurrencyCode());
				
				ObjectWriter ow = new ObjectMapper().writer()
						.withDefaultPrettyPrinter();
				try {
					String json = ow.writeValueAsString(lstBean);
					task.setRequest(json.getBytes());
					System.out.println("JSON String :"+ json);
					
					ListingBean oBean = new ObjectMapper().readValue(json, ListingBean.class);
					json = ow.writeValueAsString(oBean);
					System.out.println("JSON String :"+ json);

				} catch (JsonProcessingException je) {
					// TODO Auto-generated catch block
				} catch (IOException ie) {
				}
				lTask.add(task);
			}
			FpsDALService.getInstance().insertTaskWithEvents(lTask);
		}
	}
	
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
		clasif.setClassificationType(ClassificationTypeEnum.get(lBean.getClassification()));
		classList.add(clasif);
		listing.setClassifications(classList);

		listing.setFixedPrice(getAmount(lCurrency, lBean.getFixedPrice()));
		listing.setPrice(getAmount(lCurrency, lBean.getPrice()));
		listing.setProduct(beanToProduct(lBean));
		listing.setQuantity(lBean.getListingQty());
		listing.setCondition(lBean.getItemCondition());
		listing.setMarket(MarketEnum.MERCADO_LIBRE);
		listing.setCountry(CountryEnum.ALL);

		return listing;
	}
}

