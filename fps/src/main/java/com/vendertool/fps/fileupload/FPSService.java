package com.vendertool.fps.fileupload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.fps.fileupload.helper.AWSHelper;
import com.vendertool.fps.fileupload.helper.FPSJobEvent;
import com.vendertool.fps.fileupload.mappers.CSVProductReader;
import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.core.fps.FPSFileStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSStorageSourceEnum;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.core.fps.Task;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.fps.GetJobsRequest;
import com.vendertool.sharedtypes.rnr.fps.GetJobsResponse;
import com.vendertool.sharedtypes.rnr.fps.JobDetails;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;

@Path("/fps")
public class FPSService extends BaseVenderToolServiceImpl {

//	@POST
//	@Path("/upload")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response uploadFile(FileUploadRequest fileUploadRequest) {
//		
//		uploadFile2AWS(fileUploadRequest);
//
//		/*// TODO file path.
//		String uploadedFileLocation = "d://uploaded/"
//				+ fileDetail.getFileName();// TODO Amazon
//		// save it
//		FPSHelper.getInstance().writeToFile(uploadedInputStream,
//				uploadedFileLocation);
//		String output = "File uploaded to : " + uploadedFileLocation;*/
//		return Response.status(200).entity("").build();
//	}
//	
//	private void uploadFile2AWS (FileUploadRequest fileRequest) {
//        AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
//		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
//		s3.setRegion(usWest2);
//		
//		String bucketName = "uploadfiles-" + "username";
//		
//		try {
//			//TODO find the bucket already exists then don't create it
//			//if (s3.listObjects(bucketName) == null) {
//			if (!isBucketExists(s3.listBuckets(), bucketName)) {
//	            System.out.println("Creating bucket " + bucketName + "\n");
//	            s3.createBucket(bucketName);
//			}
//
//            System.out.println("Uploading a new object to S3 from a file\n");
//            List<FileInformation> files = fileRequest.getFiles();
//            for (FileInformation file : files) {
//                Long contentLength = Long.valueOf(file.getFileSize());
//
//                ObjectMetadata metadata = new ObjectMetadata();
//                metadata.setContentLength(contentLength);
//                InputStream fileIStream = new ByteArrayInputStream(file.getFileData());
//            	s3.putObject(new PutObjectRequest(bucketName, file.getFileName(), fileIStream, metadata));
//            }
//			
//		} catch (AmazonServiceException ase) {
//	            System.out.println("Caught an AmazonServiceException, which means your request made it "
//	                    + "to Amazon S3, but was rejected with an error response for some reason.");
//	            System.out.println("Error Message:    " + ase.getMessage());
//	            System.out.println("HTTP Status Code: " + ase.getStatusCode());
//	            System.out.println("AWS Error Code:   " + ase.getErrorCode());
//	            System.out.println("Error Type:       " + ase.getErrorType());
//	            System.out.println("Request ID:       " + ase.getRequestId());
//	    } catch (AmazonClientException ace) {
//	            System.out.println("Caught an AmazonClientException, which means the client encountered "
//	                    + "a serious internal problem while trying to communicate with S3, "
//	                    + "such as not being able to access the network.");
//	            System.out.println("Error Message: " + ace.getMessage());
//	    }
//
//	}
//
//	private boolean isBucketExists(List<Bucket> buckets, String bucketName) {
//        for (Bucket bucket : buckets) {
//            if (bucket != null && bucket.getName().equals(bucketName)) {
//            	return true;
//            }
//        }
//        return false;
//	}
	
	@POST
	@Path("/upload")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response uploadFile(UploadFileRequest uploadRequest) {
		
		Map<String,String> refMap = AWSHelper.getInstance().uploadFile2AWS(uploadRequest);
		
        List<FileInformation> files = uploadRequest.getFiles();
        List<File> dbFiles = new ArrayList<File>();
        
        long accountId = 10000;     //Need to change this one once baserequest populates
        for (FileInformation file : files) {
        	File fileObj = new File();
        	fileObj.setFileName(file.getFileName());
        	fileObj.setAccountId(Long.valueOf(accountId));
        	fileObj.setFileGroupId(uploadRequest.getGroupId());   
        	fileObj.setRefUrl(refMap.get(file.getFileName()));
        	fileObj.setStorageSource(new Byte(FPSStorageSourceEnum.AWS_CLOUD.ordinal()+""));
        	fileObj.setUseCase(FPSUsecaseEnum.ADD_LISTING);
        	fileObj.setStatus(FPSFileStatusEnum.CREATED);
        	fileObj.setCreatedDate(new Date());
        	fileObj.setLastModifiedDate(new Date());
        	dbFiles.add(fileObj);
        }
        
        //Need to get the error object as part of the response;
        if (FpsDALService.getInstance().uploadFiles(dbFiles)) {
        	return Response.status(200).entity("").build();
        } else {
        	return Response.status(400).entity("").build();
        }
	}
	
	@POST
	@Path("/uploadDone")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response uploadDone(UploadFileRequest uploadRequest) {
        Date curDate = new Date();
        
        long accountId = 10000;     //Need to change this one once baserequest populates
        Job jobObj = new Job();
        jobObj.setAccountId(accountId);
        jobObj.setTitle(uploadRequest.getUploadTitle());
        jobObj.setReqFileGroupId(uploadRequest.getGroupId());
        jobObj.setCreatedDate(curDate);
        jobObj.setLastModifiedDate(curDate);
        jobObj.setStatus(FPSJobStatusEnum.CREATED);
		jobObj.setIsoCountryCode("USA");
		jobObj.setUsecase(FPSUsecaseEnum.ADD_LISTING);
		
		long jobId = FpsDALService.getInstance().createJob(jobObj);
		
        if (jobId != -1  /*need to change this to constant value*/ ) {
        	FPSJobEvent jEvent = new FPSJobEvent();
        	jEvent.setJobId(jobId);
        	jEvent.sendEvent();
        }
		return Response.status(200).entity("").build();
	}
	
	@POST
	@Path("/processJob")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response startJob(ProcessJobRequest jobRequest) {
		
		if (jobRequest.getJobId() > 0) {
			Job job = FpsDALService.getInstance().getJob(jobRequest.getJobId());
			
			if (job != null && job.getStatus().equals(FPSJobStatusEnum.CREATED)) {
				List<File> lFiles = FpsDALService.getInstance().getFiles(job.getReqFileGroupId());
				
				if (lFiles != null && !lFiles.isEmpty()) {
					for (File file : lFiles) {
						if (file != null && file.getRefUrl() != null) {
							String fileUrl = file.getRefUrl();
							String filePath = "C:\\Users\\Gnanasekar\\Downloads\\Process\\"+file.getFileName();
							System.out.println("FilePath:"+filePath);
							
							AWSHelper.getInstance().downloadFileFromAWS(fileUrl, filePath);
														
							try {	
								CSVProductReader csvReader = new CSVProductReader(filePath);
								csvReader.processData(job.getJobId(), job.getReqFileGroupId(),
										job.getAccountId(), file.getFileId());								
							} catch (IOException e) {
							} catch (Exception ex){								
							}
						}
					}
				}
				
			}
		}

		return Response.status(200).entity("").build();
	}
	
	@POST
	@Path("/processTask")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response startTask(ProcessTaskRequest taskRequest) {
		
		if (taskRequest.getTaskId() > 0) {
			Task task = FpsDALService.getInstance().getTask(taskRequest.getTaskId());
			
			if (task != null && task.getStatus().equals(FPSTaskStatusEnum.CREATED)) {
				System.out.println("Here need to plugin the adapter code :" + taskRequest.getTaskId());
			}
		}

		return Response.status(200).entity("").build();
	}
	
	@GET
	@Path("/uploadedJobs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetJobsResponse getUploadedJobs(@QueryParam(value="email") String email) {
		GetJobsResponse response = new GetJobsResponse();	
		
		GetJobsRequest jobRequest = new GetJobsRequest();
		jobRequest.setAccountId(10000);
		
		if (jobRequest.getAccountId() !=0) {
			List<Job> lJobs = FpsDALService.getInstance().getJobs(jobRequest.getAccountId());
			List<JobDetails> lJDetails = new ArrayList<JobDetails>(lJobs.size());
			
			for (Job job:lJobs) {
				JobDetails jDetail = new JobDetails();
				jDetail.setJob(job);
				List<File> files = FpsDALService.getInstance().getFiles(job.getReqFileGroupId());
				if (files != null) {
					jDetail.setFiles(files);
				}
				lJDetails.add(jDetail);
			}
			response.setJobs(lJDetails);
		}
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
}