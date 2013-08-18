package com.vendertool.sitewebapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.rnr.FileUploadRequest;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.model.FileUploadDataModel;

@Controller
public class FileUploadController {

	private static final Logger logger = Logger
			.getLogger(FileUploadController.class);

//	private static ValidationUtil validationUtil = ValidationUtil.getInstance();

	@RequestMapping(value = "fileUpload", method = RequestMethod.GET)
	public String getFileUploadPage(ModelMap modelMap) {
		// TODO validate session
		logger.info("fileUpload GET controller invoked");
		FileUploadDataModel uploadForm = new FileUploadDataModel();

		modelMap.addAttribute("uploadFile", uploadForm);
		return "fileupload";
	}

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("uploadFile") FileUploadDataModel uploadForm,
			Model map, HttpServletRequest request) throws IOException {

		MultipartFile file = uploadForm.getFile();
		FileUploadRequest fileRequest = null;
		if (file != null) {
			fileRequest = new FileUploadRequest();
			List<FileInformation> fileInformationList = new ArrayList<>();

			FileInformation fileInfo = new FileInformation();
			fileInfo.setFileData(file.getBytes());
			fileInfo.setFileName(file.getOriginalFilename());
			fileInfo.setFileSize(file.getSize());

			fileInformationList.add(fileInfo);
			fileRequest.setFiles(fileInformationList);
		}

		if (fileRequest != null) {
			String hostName = RestServiceClientHelper.getServerURL(request);
			String url = hostName + URLConstants.WEB_SERVICE_PATH
					+ URLConstants.FILE_UPLOAD_PATH;
			/*ClientResponse response = RestServiceClientHelper
					.invokeRestService(url, fileRequest, null,
							MediaType.APPLICATION_XML_TYPE, HttpMethodEnum.POST);*/
		} else {
			// Error page.
		}
		return "fileUploadSuccess";
	}
}