package com.vendertool.fps.fileupload.mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.exception.SuperCsvException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.listing.ListingServiceimpl;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Task;
import com.vendertool.sharedtypes.rnr.AddListingRequest;

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
					String json = ow.writeValueAsString(lBean);
					task.setRequest(json.getBytes());
					System.out.println("JSON String :"+ json);
					
					ListingBean oBean = new ObjectMapper().readValue(json, ListingBean.class);
					
					System.out.println("TESTING  :"+ json);
					String json1 = ow.writeValueAsString(oBean);
					System.out.println("JSON String :"+ json1);
					
					Listing listing = new CSVBeanHelper().beanToListing(oBean);
					AddListingRequest input = new AddListingRequest();
					input.setListing(listing);
					ListingServiceimpl impl = new ListingServiceimpl();
					impl.addListing(input);

				} catch (JsonProcessingException je) {
					System.out.println("Exception occurred");
					je.printStackTrace();
				} catch (IOException ie) {
					System.out.println("Exception occurred");
					ie.printStackTrace();
				}
				lTask.add(task);
			}
			FpsDALService.getInstance().insertTaskWithEvents(lTask);
		}
	}
}

