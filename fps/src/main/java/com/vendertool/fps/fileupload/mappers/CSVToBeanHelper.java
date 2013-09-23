package com.vendertool.fps.fileupload.mappers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class CSVToBeanHelper<T> {


	public List<T> getMappedBean(
			HeaderColumnNameTranslateMappingStrategy<T> strategy, String fileUrl) {
		CsvToBean<T> csvToBean = new CsvToBean<T>();
		List<T> list = null;
		CSVReader reader = null;
		try {
			reader = new CSVReader(new InputStreamReader(new FileInputStream(fileUrl)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();//TODO log this
		}
		if (reader != null) {
			list = csvToBean.parse(strategy, reader);
		}
		return list;
	}
}
