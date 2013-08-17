package com.vendertool.fileupload;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.sharedtypes.rnr.FileUploadRequest;

@Path("/file")
public class FPSService extends BaseVenderToolServiceImpl {

	@POST
	@Path("/upload")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response uploadFile(FileUploadRequest fileUploadRequest) {

		/*// TODO file path.
		String uploadedFileLocation = "d://uploaded/"
				+ fileDetail.getFileName();// TODO Amazon
		// save it
		FPSHelper.getInstance().writeToFile(uploadedInputStream,
				uploadedFileLocation);
		String output = "File uploaded to : " + uploadedFileLocation;*/
		return Response.status(200).entity("").build();
	}
}