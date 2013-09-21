package com.vendertool.fps.fileupload.validator;

import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.exception.VTRuntimeException;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;

public class UploadFileValidator implements com.vendertool.common.validation.Validator {
	
	private static final Logger logger = Logger.getLogger(UploadFileValidator.class);
	private static ValidationUtil validationUtil = ValidationUtil.getInstance();
	private static int MIN_FILESIZE_LENGTH = 0;
	private static long MAX_FILESIZE_LENGTH = 100000000L;
	//private static String EXTENSION_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{1,50}(.jpg|.csv|.txt)$";
	
	
	public UploadFileValidator() {
	}
	
	public void validate(BaseRequest _request, BaseResponse response) {
		UploadFileRequest request = (UploadFileRequest) _request;
		
		if (validationUtil.isNull(request)
				|| validationUtil.isNull(response)
				|| validationUtil.isNull(request.getFiles())
				|| validationUtil.isNull(request.getFiles().isEmpty())
				)  {
			VTRuntimeException ex = new VTRuntimeException("NULL value passed to upload files");
			logger.debug("NULL value passed to upload files", ex);
			throw ex;
		}
		
        List<FileInformation> files = request.getFiles();
        for (FileInformation file : files) {
            validateFileSize(file, response);
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

	
