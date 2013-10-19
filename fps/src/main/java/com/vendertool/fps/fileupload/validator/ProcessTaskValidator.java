package com.vendertool.fps.fileupload.validator;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;

public class ProcessTaskValidator implements com.vendertool.common.validation.Validator {
	
	private static final Logger logger = Logger.getLogger(ProcessTaskValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	public ProcessTaskValidator() {
	}
	
	public void validate(BaseRequest _request, BaseResponse response) {
		ProcessTaskRequest request = (ProcessTaskRequest) _request;
		
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

	public void validateTaskId(BaseRequest _request, BaseResponse response) {
		ProcessTaskRequest request = (ProcessTaskRequest) _request;
		validate(_request, response);
		
		if(VUTIL.isNull(request.getTaskId()) || request.getTaskId()<=0) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_TASKID, 
					"EMPTY Task ID",
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
