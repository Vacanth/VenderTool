package com.vendertool.fps.fileupload.mappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.supercsv.exception.SuperCsvException;

import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.sharedtypes.core.fps.Task;

public class CSVProductReader extends CSVBaseReader{
	private static int CHUNK_SIZE = 100;
	private long recordCount=0;
	
	public CSVProductReader(String filePath) {
		super.initHeader(filePath);
	}

	public ProductBean readDataFromFile() throws SuperCsvException,IOException { 
		ProductBean prodBean = getBeanReader().read(ProductBean.class, getHeader(), getProcessors());
		return prodBean;
	}
	
	/*public List<ProductBean> readNDataFromFile(int num) throws SuperCsvException,IOException { 
		int i=0;
		List<ProductBean> pBeanList = new ArrayList<ProductBean>();
		ProductBean prodBean;

		try {
			while (i<num) {
				prodBean = getBeanReader().read(ProductBean.class, getDynamicHeader(), getProcessors());
				if (prodBean == null) {
					break;
				}
				pBeanList.add(prodBean);
				i++;
			}
		}catch (SuperCsvException e) {                                  
		}catch (Exception e) {       
			System.out.println("Message "+e.getMessage());
		} 
		return pBeanList;
	}
	*/
	public void processData(long jobId, String fileGrpId, long accountId, long fileId) throws Exception {                            
		List<ProductBean> prodList = readNDataFromFile(CHUNK_SIZE);
		
		Task task = new Task();
		task.setJobId(jobId);;
		task.setAccountId(accountId);
		task.setRequestGroupId(fileGrpId);
		task.setRequestFileId(fileId);
		
		while (prodList != null && !prodList.isEmpty()) {
			processTask(prodList, task);
			prodList = readNDataFromFile(CHUNK_SIZE);
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
				task.setRequest(new JSONObject(pBean).toString().getBytes());
				
				System.out.println("Testing product bean"+ pBean.getCategoryId());
				System.out.println("JSON String :"+ new JSONObject(pBean).toString());
			}
			FpsDALService.getInstance().insertTaskWithEvents(lTask);
		}
	}
}
