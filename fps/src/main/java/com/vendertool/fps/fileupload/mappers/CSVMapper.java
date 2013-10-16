package com.vendertool.fps.fileupload.mappers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVMapper<T> {
	private CSVReader m_csvReader = null;
	private String m_fileLocation = null;
	private HeaderColumnNameTranslateMappingStrategy<T> m_strategy;
	
	
	public CSVMapper(String fileLocation, HeaderColumnNameTranslateMappingStrategy<T> strategy) {
		m_fileLocation = fileLocation;
		m_strategy = strategy;

		InputStream input;
		try {
			input = new BufferedInputStream(new FileInputStream(new File(fileLocation)));
			m_csvReader = new CSVReader(new InputStreamReader(input));
		} catch (FileNotFoundException e) {
		}
	}
	/*
	public List<T> getBeans(String batchReqFileLocation, HeaderColumnNameTranslateMappingStrategy<T> strategy) throws IOException {
		List<T> list = null;
		CsvToBean<T> csvToBean = new CsvToBean<T>();
    	InputStream input = new BufferedInputStream(new FileInputStream(
    			new File("C:\\Users\\Gnanasekar\\Downloads\\testfile_dynamic_variations.csv")));
 		
		reader = new CSVReader(new InputStreamReader(input));
		if (reader != null) {
			list = csvToBean.parse(strategy, reader);
		}
		// ReadHeader again
		reader = new CSVReader(new InputStreamReader(input));
		processorHeaders(reader, list);
		input.close();
		return list;
	}
	*/
	
}
