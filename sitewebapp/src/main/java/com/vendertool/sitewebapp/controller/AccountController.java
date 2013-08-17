package com.vendertool.sitewebapp.controller;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.sitewebapp.model.AccountFieldSet;
import com.vendertool.sitewebapp.test.util.MockDataUtil;

@Controller
public class AccountController {
	private static final Logger logger = Logger.getLogger(AccountController.class);
	

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String getAccount(ModelMap modelMap) {
		logger.info("account GET controller invoked");
		
		AccountFieldSet acctFieldSet = MockDataUtil.getAccountFieldSet();

		modelMap.put("acctFieldSet", acctFieldSet);
		
		// Add JSON for Angular
		try {
			ObjectMapper mapper = new ObjectMapper();
			String acctFieldSetJson= mapper.writeValueAsString(acctFieldSet);
			modelMap.put("acctFieldSetJson", acctFieldSetJson);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "account";
	}
	
	@RequestMapping(value="account/save", method=RequestMethod.POST)
	public @ResponseBody AccountFieldSet saveAccount(@RequestBody AccountFieldSet acctFieldSet) {
		

		System.err.println("Address1: " + acctFieldSet.getAddressLine1().getValue());
		System.err.println("Country: " + acctFieldSet.getCountry().getValue());
		
		return acctFieldSet;
	}
	
		
	
	
	
	
}
