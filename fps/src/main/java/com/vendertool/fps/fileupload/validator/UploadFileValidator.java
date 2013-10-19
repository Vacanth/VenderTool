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
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;

public class UploadFileValidator implements com.vendertool.common.validation.Validator {
	
	private static final Logger logger = Logger.getLogger(UploadFileValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	private static int MIN_FILESIZE_LENGTH = 0;
	private static long MAX_FILESIZE_LENGTH = 100000000L;
	//private static String EXTENSION_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{1,50}(.jpg|.csv|.txt)$";
	
	
	public UploadFileValidator() {
	}
	
	public void validate(BaseRequest _request, BaseResponse response) {
		UploadFileRequest request = (UploadFileRequest) _request;
		
		if (VUTIL.isNull(request)
				|| VUTIL.isNull(response)
				|| VUTIL.isNull(request.getFiles())
				|| VUTIL.isNull(request.getFiles().isEmpty())
				)  {
			VTRuntimeException ex = new VTRuntimeException("NULL value passed to upload files");
			logger.debug("NULL value passed to upload files", ex);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
	}
	
	public void validateRequest(BaseRequest _request, BaseResponse response) {
		UploadFileRequest request = (UploadFileRequest) _request;
		
		if (VUTIL.isNull(request)
				|| VUTIL.isNull(response)
				)  {
			VTRuntimeException ex = new VTRuntimeException("NULL value passed to upload files");
			logger.debug("NULL value passed to upload files", ex);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
	}
	
	public void validateFiles(BaseRequest _request, BaseResponse response) {
		UploadFileRequest request = (UploadFileRequest) _request;
		validate(_request, response);
		
        List<FileInformation> files = request.getFiles();
        for (FileInformation file : files) {
            validateFileSize(file, response);
        }
		
	}
	
	public void validateGroupId(BaseRequest _request, BaseResponse response) {
		UploadFileRequest request = (UploadFileRequest) _request;
		validateRequest(_request, response);
		
		if(VUTIL.isNull(request.getGroupId()) || VUTIL.isEmpty(request.getGroupId())) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_GROUPID_JOB, 
					"EMPTY GROUP ID",
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
	
	public void validateJobId(BaseRequest _request, BaseResponse response) {
		ProcessJobRequest request = (ProcessJobRequest) _request;
		validateRequest(_request, response);
		
		if(VUTIL.isNull(request.getJobId()) || VUTIL.isPositiveLong(request.getJobId())) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_JOBID, 
					"EMPTY JOB ID",
					"jobId");
		}
	}

	public void validateTaskId(BaseRequest _request, BaseResponse response) {
		ProcessTaskRequest request = (ProcessTaskRequest) _request;
		validateRequest(_request, response);
		
		if(VUTIL.isNull(request.getTaskId()) || VUTIL.isPositiveLong(request.getTaskId())) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_TASKID, 
					"EMPTY TASK ID",
					"jobId");
		}
	}
	
	private void validateFileSize(FileInformation file, BaseResponse response) {
		//combine both errors together before returning
		if(file.getFileSize() <= MIN_FILESIZE_LENGTH) {
			response.addFieldBindingError(
					Errors.FPS.EMPTY_FILE, 
					file.getFileName(),
					"fileName");
		}
		
		if(file.getFileSize() > MAX_FILESIZE_LENGTH) {
			response.addFieldBindingError(
					Errors.FPS.FILE_TOO_BIG,
					file.getFileName(),
					"fileName");
		}
		return;
	}

}

	
