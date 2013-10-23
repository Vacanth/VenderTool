package com.vendertool.sharedapp;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.GetSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;

public class WebserviceTest {
	public static void main(String[] args) {
		
		new ClassPathXmlApplicationContext("test-app-context.xml");
		
		String hostName = "http://localhost:8080";
		
		String url = hostName + URLConstants.WEB_SERVICE_PATH + 
				URLConstants.WS_METADATA_GET_SEC_QUESTIONS_PATH;
		
		Response response = RestServiceClientHelper
				.invokeRestService(url, null, null, MediaType.APPLICATION_JSON_TYPE,
						HttpMethodEnum.GET);
		
		int responseCode = response.getStatus();
		log("Vendertool Web service status code for URL '"
				+ url + ".getSecurityQuestions() is '" + responseCode
				+ "'.");
		
		//HTTP error code 200
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			VTRuntimeException ex = new VTRuntimeException(
					"Unable to fetch security questions from metadata service, web service HTTP response code: "
							+ response.getStatus());
			log(ex);
			throw ex;
		}
		
		GetSecurityQuestionsResponse secQuestionsResponse = response
				.readEntity(GetSecurityQuestionsResponse.class);
		
		List<SecurityQuestion> secQuestions = secQuestionsResponse.getSecurityQuestions();
		if(secQuestions == null) {
			log("Null response");
			System.exit(0);
		}
		
		log(secQuestions.toString());
	}
	
	private static void log(String msg) {
		System.err.println(msg);
	}
	
	private static void log(Throwable t) {
		System.err.println(t.getMessage());
		t.printStackTrace();
	}
}
