package com.vendertool.fps.fileupload.mappers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class CSVBaseReader {
	private ICsvBeanReader beanReader = null;
    private String[] header;
    private CellProcessor[] processors;
    private String[] dynamicHeader;
    private InputStream reader;
    
	public ICsvBeanReader getBeanReader() {
		return beanReader;
	}

	public void setBeanReader(ICsvBeanReader beanReader) {
		this.beanReader = beanReader;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public CellProcessor[] getProcessors() {
		return processors;
	}

	public void setProcessors(CellProcessor[] processors) {
		this.processors = processors;
	}

	public String[] getDynamicHeader() {
		return dynamicHeader;
	}

	public void setDynamicHeader(String[] dynamicHeader) {
		this.dynamicHeader = dynamicHeader;
	}
	
	public void initHeader(InputStream inputStream) {
		try {                                        
			reader = new BufferedInputStream(inputStream);                       
 			beanReader = new CsvBeanReader(new InputStreamReader(reader), CsvPreference.STANDARD_PREFERENCE);                                   
			String[] headerInFile = beanReader.getHeader(true);                                   
			                                                
			dynamicHeader = new String[headerInFile.length];                                       
			processors = new CellProcessor[headerInFile.length];                                  
                                         
			int colCnt = 0;                                      
			for (String header: headerInFile) { 
 				dynamicHeader[colCnt] = header;                                                                              
				processors[colCnt++] = new Optional();                                                         
			}                                              
		} catch (Exception e) {                                    
                         
		}     
	}
    
	public void initHeader(String filePath) {                              
		try {                                        
			reader = new BufferedInputStream(new FileInputStream((new File(filePath))));                       
 			beanReader = new CsvBeanReader(new InputStreamReader(reader), CsvPreference.STANDARD_PREFERENCE);                                   
			String[] headerInFile = beanReader.getHeader(true);                                   
			                                                
			dynamicHeader = new String[headerInFile.length];                                       
			processors = new CellProcessor[headerInFile.length];                                  
                                         
			int colCnt = 0;                                      
			for (String header: headerInFile) { 
 				dynamicHeader[colCnt] = header;                                                                              
				processors[colCnt++] = new Optional();                                                         
			}                                              
		} catch (Exception e) {                                    
                         
		}                              
	}     
	
	public List<ProductBean> readNProductDataFromFile(int num) throws SuperCsvException,IOException { 
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
	
	public List<ListingBean> readNListingDataFromFile(int num) throws SuperCsvException,IOException { 
		int i=0;
		List<ListingBean> lBeanList = new ArrayList<ListingBean>();
		ListingBean lstBean;

		try {
			while (i<num) {
				lstBean = getBeanReader().read(ListingBean.class, getDynamicHeader(), getProcessors());
				if (lstBean == null) {
					break;
				}
				lBeanList.add(lstBean);
				i++;
			}
		}catch (SuperCsvException e) {                                  
		}catch (Exception e) {       
			System.out.println("Message "+e.getMessage());
		} 
		return lBeanList;
	}
}
