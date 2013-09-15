package com.vendertool.tools.db.codegen;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysema.query.codegen.BeanSerializer;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.MySQLTemplates;
import com.mysema.query.sql.codegen.DefaultNamingStrategy;
import com.mysema.query.sql.codegen.MetaDataExporter;
import com.mysema.query.sql.codegen.NamingStrategy;

public class DBCodeGenTool {

	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws SQLException {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"dbCodeGen.xml");
		List<DBConnectionData> dbConnectionData = (List<DBConnectionData>) appContext
				.getBean("dbList");

		for (DBConnectionData connectionData : dbConnectionData) {
			if (connectionData == null) {
				continue;
			}
			Connection connection = DriverManager.getConnection(
					connectionData.getUrl(), connectionData.getUser(),
					connectionData.getPassword());
			List<DBCodeGenDetails> codeGenDetails = connectionData
					.getCodeGenDetails();
			
			for (DBCodeGenDetails codeGen : codeGenDetails) {
				Configuration config = new Configuration(new MySQLTemplates());
				NamingStrategy namingStrategy = new DefaultNamingStrategy();
				MetaDataExporter exporter = new MetaDataExporter();
				exporter.setConfiguration(config);
				exporter.setNamingStrategy(namingStrategy);
				exporter.setNamePrefix("Q");
				exporter.setBeanPrefix("QBean");
				exporter.setBeanSerializer(new BeanSerializer());
				
				//exporter.setTableNamePattern(codeGen.getTableNamePattern());
				exporter.setPackageName(codeGen.getPackageName());
				exporter.setTargetFolder(new File(codeGen.getTargetFolder()));
				System.out.println("Input Table Name Patterns :" + codeGen.getTableNamePattern());
				String tablesPattrent = codeGen.getTableNamePattern();
				for (String tablePat :tablesPattrent.split(",")){
					System.out.println("Currently generating for table pattrent :" + tablePat);
					System.out.println("Output Folder " + codeGen.getTargetFolder());
					System.out.println("Output package : "+codeGen.getPackageName() );
					exporter.setTableNamePattern(tablePat);
					exporter.export(connection.getMetaData());
					System.out.println("*******************Completed for " + tablePat +"**************** !!!");
				}
			}
			
		}

		System.out.println("All Completed!!!");
	}
}