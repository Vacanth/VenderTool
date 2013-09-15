package com.vendertool.sitewebapp.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.core.HttpMethodEnum;
import com.vendertool.sharedtypes.rnr.FileUploadRequest;
import com.vendertool.sitewebapp.common.RestServiceClientHelper;
import com.vendertool.sitewebapp.common.URLConstants;

@Controller
public class UploaderController {
	
	private static final Logger logger = Logger.getLogger(UploaderController.class);
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	protected @ResponseBody ModelMap saveFile(HttpServletRequest request) 
		throws IOException {
		
		Response serviceRes = null;
		
		if (ServletFileUpload.isMultipartContent(request)) {
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload upload = new ServletFileUpload(factory);

		    try {
		        List<FileItem> items = upload.parseRequest(request);
		        Iterator<FileItem> iter = items.iterator();
		        while (iter.hasNext()) {
		            FileItem item = iter.next();
	
		            if (!item.isFormField()) {
		            	
		            	FileInformation fileInfo = new FileInformation();
		    			fileInfo.setFileData(item.get());
		    			fileInfo.setFileName(item.getName());
		    			fileInfo.setFileSize(item.getSize());
		    			
		    			List<FileInformation> fileInfoList = new ArrayList<FileInformation>();
		    			FileUploadRequest fileUploadReq = new FileUploadRequest();
		    			
		    			fileInfoList.add(fileInfo);
		    			fileUploadReq.setFiles(fileInfoList);
		    			
		    			String hostName = RestServiceClientHelper.getServerURL(request);
		    			String url = hostName + URLConstants.WEB_SERVICE_PATH + URLConstants.FILE_UPLOAD_PATH;
		    			serviceRes = RestServiceClientHelper.invokeRestService(
		    							url,
		    							fileUploadReq,
		    							null,
		    							MediaType.APPLICATION_JSON_TYPE,
		    							HttpMethodEnum.POST);
		    			
		    			break; // Only expecting one file
		            }
		        }
		    }
		    catch (FileUploadException e) {
		    	logger.log(Level.ERROR, e.getMessage(), e);
		        e.printStackTrace();
		    }
		}
		
		ModelMap modelMap = new ModelMap();
		
		if (serviceRes == null || serviceRes.getStatus() != 200) {
			modelMap.put("statusMessage", "error");
		}
		else {
			modelMap.put("statusMessage", "success");
		}
		
		return modelMap;
	}
	
	@RequestMapping(value=URLConstants.UPLOADER, method=RequestMethod.GET)
	public String getUploaderPopup(ModelMap modelMap, Principal principal) {
		logger.info("getUploaderPopup controller invoked");
		return "uploader/uploader";
	}
	
	@RequestMapping(value = "uploader/partial/uploaderModule", method = RequestMethod.GET)
	public String getUploaderTemplate() {
		logger.info("getUploaderTemplate controller invoked");
		return "uploader/partial/uploaderModule";
	}
	
}
