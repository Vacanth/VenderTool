package com.vendertool.fps.fileupload.mappers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.exception.SuperCsvException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.Task;

public class CSVProductReader extends CSVBaseReader{
	private static int CHUNK_SIZE = 100;
	private long recordCount=0;
	
	public CSVProductReader(String filePath) {
		super.initHeader(filePath);
	}
	
	public CSVProductReader(InputStream iStream) {
		super.initHeader(iStream);
	}

	public ProductBean readDataFromFile() throws SuperCsvException,IOException { 
		ProductBean prodBean = getBeanReader().read(ProductBean.class, getHeader(), getProcessors());
		return prodBean;
	}
	
	public void processData(long jobId, String fileGrpId, long accountId, long fileId) throws Exception {                            
		List<ProductBean> prodList = readNProductDataFromFile(CHUNK_SIZE);
		
		Task task = new Task();
		task.setJobId(jobId);;
		task.setAccountId(accountId);
		task.setRequestGroupId(fileGrpId);
		task.setRequestFileId(fileId);
		
		while (prodList != null && !prodList.isEmpty()) {
			processTask(prodList, task);
			prodList = readNProductDataFromFile(CHUNK_SIZE);
		}
	}
	
	public void processTask(List<ProductBean> lBean, Task iTask) {
		if (lBean != null && !lBean.isEmpty()) {
			List<Task> lTask = new ArrayList<Task>(lBean.size());
			
			for (ProductBean pBean: lBean) {
				Task task = new Task();
				task.setJobId(iTask.getJobId());;
				task.setAccountId(iTask.getAccountId());
				task.setRequestGroupId(iTask.getRequestGroupId());
				task.setRequestFileId(iTask.getRequestFileId());
				task.setRecordId(recordCount++);
				//task.setRequest(new JSONObject(pBean).toString().getBytes());
				
				ObjectWriter ow = new ObjectMapper().writer()
						.withDefaultPrettyPrinter();
				try {
					String json = ow.writeValueAsString(pBean);
					task.setRequest(json.getBytes());
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
				}
				task.setStatus(FPSTaskStatusEnum.CREATED);
				task.setIsoCountryCode(pBean.getCurrencyCode());
				
				lTask.add(task);
			}
			FpsDALService.getInstance().insertTaskWithEvents(lTask);
		}
	}
}
