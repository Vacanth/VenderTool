package com.vendertool.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.sharedtypes.rnr.FileUploadRequest;
import com.vendertool.sharedtypes.rnr.FileUploadResponse;

@Controller
@RequestMapping("/file")
public class FPSService extends BaseVenderToolServiceImpl {

	@RequestMapping(value="/upload", method = RequestMethod.POST, produces = {"application/json","application/xml"}, consumes = {"application/json","application/xml"})
	public @ResponseBody FileUploadResponse uploadFile(FileUploadRequest fileUploadRequest) {

		/*// TODO file path.
		String uploadedFileLocation = "d://uploaded/"
				+ fileDetail.getFileName();// TODO Amazon
		// save it
		FPSHelper.getInstance().writeToFile(uploadedInputStream,
				uploadedFileLocation);
		String output = "File uploaded to : " + uploadedFileLocation;*/
		return new FileUploadResponse();
	}
}