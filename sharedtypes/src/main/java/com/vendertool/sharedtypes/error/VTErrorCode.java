package com.vendertool.sharedtypes.error;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({CommonErrorCode.class, RegistrationErrorCode.class, 
	MetadataErrorCode.class, SigninErrorCode.class, InventoryErrorCode.class,
	ListingErrorCode.class, FTSErrorCode.class, SystemErrorCode.class})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommonErrorCode.class),
        @JsonSubTypes.Type(value = FTSErrorCode.class),
        @JsonSubTypes.Type(value = InventoryErrorCode.class),
        @JsonSubTypes.Type(value = FTSErrorCode.class),
        @JsonSubTypes.Type(value = ListingErrorCode.class),
        @JsonSubTypes.Type(value = MetadataErrorCode.class),
        @JsonSubTypes.Type(value = RegistrationErrorCode.class),
        @JsonSubTypes.Type(value = SigninErrorCode.class),
        @JsonSubTypes.Type(value = SystemErrorCode.class)})
public abstract class VTErrorCode implements Serializable{
	
	private String errorCode;

	public VTErrorCode() {
	}
	public VTErrorCode(String code) {
		setErrorCode(code);
	}
	
	public abstract Set<String> getCachedErrorCodes() ;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		/*if((errorCode == null) || (errorCode.trim().isEmpty())){
			throw new DuplicateException("Error Code cannot be empty or null");
		}
		
		Set<String> allcodes = getCachedErrorCodes();
		
		if(allcodes.contains(errorCode)) {
			throw new DuplicateException("Duplicate error code: " + errorCode);
		}
		
		allcodes.add(errorCode);*/
		this.errorCode = errorCode;
	}
	
	public boolean hasErrorCode(String errorCode) {
		return getCachedErrorCodes().contains(errorCode);
	}
	
	private static final long serialVersionUID = 7704847642408908964L;
}