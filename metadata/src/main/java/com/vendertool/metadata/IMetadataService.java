package com.vendertool.metadata;

import java.util.Locale;

import com.vendertool.common.service.IVenderToolService;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.rnr.GetSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.GetSupportedCountriesResponse;
import com.vendertool.sharedtypes.rnr.GetSupportedLanguagesResponse;
import com.vendertool.sharedtypes.rnr.GetSupportedLocalesResponse;

public interface IMetadataService extends IVenderToolService {
	
	/**
	 * HTTP GET to get all the supported locale's for the site
	 * 
	 * @return
	 */
	public GetSupportedLocalesResponse getSupportedLocales();
	
	/**
	 * HTTP GET to fetch all the supported countries for the market
	 * 
	 * @param market
	 * @return
	 */
	public GetSupportedCountriesResponse getSupportedCountries(MarketEnum market);
	
	/**
	 * HTTP GET call to get all the security questions with its display names
	 * 
	 * @return
	 */
	public GetSecurityQuestionsResponse getSecurityQuestions(Locale locale);
	
	/**
	 * HTTP GET call to get all the supported Languages
	 * 
	 * @return
	 */
	public GetSupportedLanguagesResponse getSupportedLanguages();
}
