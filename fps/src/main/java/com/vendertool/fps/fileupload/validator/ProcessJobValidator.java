package com.vendertool.fps.fileupload.validator;

import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;

public class ProcessJobValidator implements com.vendertool.common.validation.Validator {
	
	private static final Logger logger = Logger.getLogger(ProcessJobValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	public ProcessJobValidator() {
	}
	
	public void validate(BaseRequest _request, BaseResponse response) {
		ProcessJobRequest request = (ProcessJobRequest) _request;
		
		if (VUTIL.isNull(request)
				|| VUTIL.isNull(response)
				)  {
			VTRuntimeException ex = new VTRuntimeException("NULL value passed to process files");
			logger.debug("NULL value passed to process files", ex);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
	}

	public void validateJobId(BaseRequest _request, BaseResponse response) {
		ProcessJobRequest request = (ProcessJobRequest) _request;
		validate(_request, response);
		
		if(VUTIL.isNull(request.getJobId()) || request.getJobId()<=0) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_JOBID, 
					"EMPTY Job ID",
					"groupId");
		}
	}
	
	public void validateGroupId(String groupId, BaseResponse response) {
		
		if(VUTIL.isNull(groupId) || VUTIL.isEmpty(groupId)) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_GROUPID_JOB, 
					"EMPTY GROUP ID",
					"groupId");
		}
	}
}