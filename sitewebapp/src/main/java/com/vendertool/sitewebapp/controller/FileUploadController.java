package com.vendertool.sitewebapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;
import com.vendertool.sitewebapp.model.FileUploadDataModel;

@Controller
public class FileUploadController {

	private static final Logger logger = Logger
			.getLogger(FileUploadController.class);

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

		MultipartFile files = uploadForm.getFile();

		List<String> fileNames = new ArrayList<String>();

		MultipartFile multipartFile = files;

		String fileName = multipartFile.getOriginalFilename();
		fileNames.add(fileName);
		// Handle file content - multipartFile.getInputStream()
		/*
		 *  * final String response = resource .entity(request,
		 * MediaType.MULTIPART_FORM_DATA) .accept("text/plain")
		 * .post(String.class);
		 */
		FormDataMultiPart form = new FormDataMultiPart().field("file",
				multipartFile.getInputStream(),
				MediaType.MULTIPART_FORM_DATA_TYPE);
		FormDataContentDisposition cd = FormDataContentDisposition.name("file")
				.fileName(multipartFile.getOriginalFilename())
				.size(multipartFile.getSize()).build();
		form.contentDisposition(cd);
		String hostName = RestServiceClientHelper.getServerURL(request);
		String url = hostName + URLConstants.WEB_SERVICE_PATH
				+ URLConstants.FILE_UPLOAD_PATH;

		WebResource webResource = Client.create().resource(url);
		webResource.type(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_PLAIN).post(form);
		// System.out.println("Uploaded"+response);
		map.addAttribute("files", fileNames);
		return "fileUploadSuccess";
	}
}