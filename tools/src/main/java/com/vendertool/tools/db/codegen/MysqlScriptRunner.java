package com.vendertool.tools.db.codegen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.io.Files;

/* Input file should contains only DDL and DML sql's , no scripts can be executed with this
 * Comment should start with  "--"  any line start with "--" will ignore for execution.
 * Every sql statement should end with ";"
 *  */
public class MysqlScriptRunner {
	private static final String INPUT_PATH="src/main/resources/dbscripts/input";  
	private static final String OUTPUT_PATH="src/main/resources/dbscripts/output";  

	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) throws SQLException {
		System.out.println("Arguments are :"+args[0]);
		String myhost = args[0];


		if(myhost.isEmpty()) {
			System.out.println("Please send host name as an arguments , example :invdb,jobdb"); 
		} else {
			ApplicationContext appContext = new ClassPathXmlApplicationContext(
					"MysqlScriptRunner.xml");

			List<DBConnectionData> myHostsList =  (List<DBConnectionData>) appContext.getBean(myhost);

			for ( DBConnectionData myDb : myHostsList) {
				DBConnectionData connectionData =  myDb;
				System.out.println("Connection name is :"+connectionData.getHost());

				Connection connection = null ;
				try {
					connection = DriverManager.getConnection(
							connectionData.getUrl(), connectionData.getUser(),
							connectionData.getPassword());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				try  
				{  
					File f=new File(INPUT_PATH);
					File[] allSubFiles=f.listFiles();
					for (File file : allSubFiles) {
						if(!file.isDirectory())
						{
							System.out.println("File name is :"+ file.getName());
							String s  = new String(); 
							String s2 = new String();
							StringBuffer sb = new StringBuffer();  
							FileReader fr = new FileReader(new File(file.getAbsolutePath()));  
							BufferedReader br = new BufferedReader(fr);  
							while((s = br.readLine()) != null  )  
							{  
								if(!s.startsWith("--")) {
									if(connectionData.getHost().matches("scratchdb")) {
										s2 = s.replace("invdb", "scratchdb")	;
										s = s2;
									}
									sb.append(s);  
								}
							}  
							br.close();  
							String[] inst = sb.toString().split(";");  
							Statement st = connection.createStatement();  
							for(int i = 0; i<inst.length; i++) {
								// we ensure that there is no spaces before or after the request string  
								// in order to not execute empty statements  
								if(!inst[i].trim().equals(""))  
								{  
									//st.executeUpdate(inst[i]);  
									System.out.println(inst[i]+";");  
								}  
							}  

							//Move files only if host is not invdb
							if(!connectionData.getHost().matches("invdb")) {
								file.renameTo(new File(OUTPUT_PATH, new Date().getTime()+"_"+file.getName()));
							}
						} 
					} 
				}
				catch(Exception e)  
				{  
					System.out.println("*** Error : "+e.toString());  
					e.printStackTrace();  
					System.out.println("################################################");  

				}  


			}


		}

	}
}
