package com.vendertool.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.vendertool.common.MsgSource;
import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.core.MarketEnum;
import com.vendertool.sharedtypes.core.SecurityQuestion;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.GetSupportedCountriesResponse;
import com.vendertool.sharedtypes.rnr.GetSupportedLocalesResponse;

@Path("/metadata")
public class MetadataServiceImpl extends BaseVenderToolServiceImpl implements
		IMetadataService {

	@GET
	@Path("/getSupportedLocales")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public GetSupportedLocalesResponse getSupportedLocales() {
		GetSupportedLocalesResponse response = new GetSupportedLocalesResponse();
		Set<Locale> locales = MarketCountryConfig.getInstance().getSupportedLocales();
		response.setLocales(locales);
		return response;
	}

	@GET
	@Path("/getSupportedCountries")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public GetSupportedCountriesResponse getSupportedCountries(@QueryParam("market") MarketEnum market) {
		GetSupportedCountriesResponse response = new GetSupportedCountriesResponse();
		
		if(market == null) {
			response.addFieldBindingError(Errors.METADATA.MARKET_DATA_MISSING, null, (String[])null);
			return response;
		}
		
		response.setCountries(MarketCountryConfig
					.getInstance()
					.getMarketSupportedCountries(market));
		
		return response;
	}

	@Override
	public List<SecurityQuestion> getSecurityQuestions(Locale locale) {
		SecurityQuestionCodeEnum[] questionEnums = SecurityQuestionCodeEnum.values();
		MsgSource msgsrc = new MsgSource();
		List<SecurityQuestion> questions = new ArrayList<SecurityQuestion>();
		for(SecurityQuestionCodeEnum questionEnum : questionEnums) {
			String code = questionEnum.getCode();
			String localizedDisplayName = msgsrc.getMessage(code, null, locale);
			
			SecurityQuestion question = new SecurityQuestion();
			question.setQuestionCode(questionEnum);
			question.setQuestionDisplayName(localizedDisplayName);
			questions.add(question);
		}
		
		return questions;
	}

	@Override
	public Set<Language> getSupportedLanguages() {
		//TODO trim this to the ones we support
		return Language.getLanguages();
	}
}
