package com.vendertool.fps.test.csv;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vendertool.fps.fileupload.mappers.CSVProductReader;
import com.vendertool.fps.rmq.AWSRmqMetadataFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/dev/aws-credentials/AWSRmqConfig.xml","classpath:dal/Datasource.xml","classpath:dal/CommonDAL.xml","classpath:dal-module.xml","classpath:FpsDAL.xml"})
public class CSVProcessingTest implements ApplicationContextAware {
	@Test
	public void csvProductProcessingTest() {
	}
	private static ApplicationContext ctx;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
		try {
			//setApplicationContext(SpringApplicationContextUtils.getApplicationContext());
			AWSRmqMetadataFactory.getInstance().init();
			
			CSVProductReader csvReader = new CSVProductReader("C:\\Users\\Gnanasekar\\Downloads\\testfile_dynamic_variations.csv");
			csvReader.processData(100,"testing", 100,100);
			//CSVProductMapper.getInstance().getProductBeans("Testing");
		} catch (IOException e) {
		} catch (Exception ex){
			
		}
	}
	
	public ApplicationContext getApplicationContext(){
		return ctx;
	}

}