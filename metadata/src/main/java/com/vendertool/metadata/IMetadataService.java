package com.vendertool.metadata;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.vendertool.common.service.IVenderToolService;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.rnr.GetSupportedCountriesResponse;
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
	public List<SecurityQuestion> getSecurityQuestions(Locale locale);
	
	/**
	 * HTTP GET call to get all the supported Languages
	 * 
	 * @return
	 */
	public Set<Language> getSupportedLanguages();
}
