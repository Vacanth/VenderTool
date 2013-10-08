package com.vendertool.sharedtypes.rnr;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vendertool.sharedtypes.error.VTError;
import com.vendertool.sharedtypes.error.VTErrorCode;
import com.vendertool.sharedtypes.error.VTErrorFieldBindingMap;

public class BaseResponse implements Response {
	public enum ResponseAckStatusEnum{ SUCCESS, FAILURE, PARTIAL_SUCCESS_WITH_FAILURE; }
	
	private List<VTErrorFieldBindingMap> fieldBindingErrors;
	private ResponseAckStatusEnum status;
	private String signedInEmail;

	
	public BaseResponse() {
		fieldBindingErrors = new ArrayList<VTErrorFieldBindingMap>();
	}

	
	/**** START - Default accessor methods ****/
	public List<VTErrorFieldBindingMap> getFieldBindingErrors() {
		return fieldBindingErrors;
	}

	public void setFieldBindingErrors(List<VTErrorFieldBindingMap> errors) {
		if(errors == null) {
			throw new IllegalArgumentException(
					"Trying to set null to error, use clearErrors() instead");
		}
		this.fieldBindingErrors = errors;
	}
	/**** END - Default accessor methods ****/
	
	
//	public BaseResponse addError(VTError vterror) {
//		if(vterror == null) {
//			return this;
//		}
//		
//		return addFieldBindingError(vterror, null, (String[])null);
//	}
	
//	public BaseResponse addErrors(List<VTError> vterrors) {
//		if(vterrors == null) {
//			return this;
//		}
//		
//		for(VTError vterror : vterrors) {
//			addError(vterror);
//		}
//		
//		return this;
//	}
	
	public BaseResponse addFieldBindingErrors(List<VTErrorFieldBindingMap> _errors) {
		if(_errors == null) {
			return this;
		}
		
		this.fieldBindingErrors.addAll(_errors);
		return this;
	}
	
	public BaseResponse addFieldBindingError(VTError vterror, String fullFieldRootClassName, String... fieldNames) {
		if(vterror == null) {
			return this;
		}
		
		VTErrorFieldBindingMap errFieldBindMap = getFieldBindingErrorMap(vterror.getErrorCode());
		if(errFieldBindMap == null) {
				errFieldBindMap = new VTErrorFieldBindingMap(vterror, fullFieldRootClassName, fieldNames);
				this.fieldBindingErrors.add(errFieldBindMap);
				return this;
		}
		
		errFieldBindMap.bindFields(fullFieldRootClassName, fieldNames);
		return this;
	}
	
	public boolean hasError(VTErrorCode code) {
		if(code == null) {
			return false;
		}
		
		for(VTErrorFieldBindingMap errBFieldBind : this.fieldBindingErrors) {
			VTError err = errBFieldBind.getError();
			if((err != null) && (err.equals(code))) {
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public VTErrorFieldBindingMap getFieldBindingErrorMap(VTErrorCode code) {
		if(code == null) {
			return null;
		}
		
		for(VTErrorFieldBindingMap errBFieldBind : this.fieldBindingErrors) {
			VTError err = errBFieldBind.getError();
			if((err != null) && (err.equals(code))) {
				return errBFieldBind;
			}
		}
		
		return null;
	}
	
	@JsonIgnore
	public List<VTError> getVTErrors() {
		List<VTError> vterrors = new ArrayList<VTError>();
		if(!hasErrors()) {
			return vterrors;
		}
		List<VTErrorFieldBindingMap> bindErrors = getFieldBindingErrors();
		if(bindErrors == null) {
			return vterrors;
		}
		
		for(VTErrorFieldBindingMap binderr : bindErrors) {
			vterrors.add(binderr.getError());
		}
		
		return vterrors;
	}
	
	public boolean hasFieldError(String rootClassName, String fieldName) {
		List<VTErrorFieldBindingMap> bindErrors = getFieldBindingErrors();
		if(bindErrors == null) {
			return false;
		}
		
		for(VTErrorFieldBindingMap binderr : bindErrors) {
			if(binderr.hasFieldError(rootClassName, fieldName)) {
				return true;
			}
		}
		return false;
	}
	
	@JsonIgnore
	public List<VTError> getFieldErrors(String rootClassName, String fieldName) {
		List<VTError> vterrors = new ArrayList<VTError>();
		List<VTErrorFieldBindingMap> bindErrors = getFieldBindingErrors();
		if(bindErrors == null) {
			return vterrors;
		}
		
		for(VTErrorFieldBindingMap binderr : bindErrors) {
			if(binderr.hasFieldError(rootClassName, fieldName)) {
				vterrors.add(binderr.getError());
			}
		}
		
		return vterrors;
	}
	
	public void clearErrors() {
		this.fieldBindingErrors.clear();
	}
	
	public boolean hasErrors(){
		return !fieldBindingErrors.isEmpty();
	}

	public ResponseAckStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ResponseAckStatusEnum status) {
		this.status = status;
	}

	public String getSignedInEmail() {
		return signedInEmail;
	}

	public void setSignedInEmail(String signedInEmail) {
		this.signedInEmail = signedInEmail;
	}
}