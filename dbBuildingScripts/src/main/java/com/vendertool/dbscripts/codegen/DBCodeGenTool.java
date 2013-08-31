package com.vendertool.dbscripts.codegen;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysema.query.sql.codegen.MetaDataExporter;

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
				MetaDataExporter exporter = new MetaDataExporter();
				exporter.setTableNamePattern(codeGen.getTableNamePattern());
				exporter.setPackageName(codeGen.getPackageName());
				exporter.setTargetFolder(new File(codeGen.getTargetFolder()));
				exporter.export(connection.getMetaData());
			}
		}

		System.out.println("Completed!");
	}
}