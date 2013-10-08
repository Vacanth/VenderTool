package com.vendertool.sharedtypes.rnr;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.Language;

@XmlRootElement
public class GetSupportedLanguagesResponse extends BaseResponse {
	private Set<Language> supportedLanguages;

	public Set<Language> getSupportedLanguages() {
		return supportedLanguages;
	}

	public void setSupportedLanguages(Set<Language> supportedLanguages) {
		this.supportedLanguages = supportedLanguages;
	}
}
