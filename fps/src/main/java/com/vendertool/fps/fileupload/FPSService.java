package com.vendertool.fps.fileupload;

import java.io.IOException;
import java.io.InputStream;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.fps.dal.FpsDALService;
import com.vendertool.fps.fileupload.helper.AWSHelper;
import com.vendertool.fps.fileupload.helper.FPSJobEvent;
import com.vendertool.fps.fileupload.mappers.CSVBeanHelper;
import com.vendertool.fps.fileupload.mappers.CSVListingReader;
import com.vendertool.fps.fileupload.mappers.ListingBean;
import com.vendertool.fps.fileupload.validator.ProcessJobValidator;
import com.vendertool.fps.fileupload.validator.ProcessTaskValidator;
import com.vendertool.fps.fileupload.validator.UploadFileValidator;
import com.vendertool.listing.ListingServiceimpl;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.core.Listing;
import com.vendertool.sharedtypes.core.PaginationOutput;
import com.vendertool.sharedtypes.core.Product;
import com.vendertool.sharedtypes.core.fps.FPSFileStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSJobStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSStorageSourceEnum;
import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.core.fps.FPSUsecaseEnum;
import com.vendertool.sharedtypes.core.fps.File;
import com.vendertool.sharedtypes.core.fps.Job;
import com.vendertool.sharedtypes.core.fps.Task;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.AddListingRequest;
import com.vendertool.sharedtypes.rnr.AddListingResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.UploadsResponse;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessJobResponse;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskRequest;
import com.vendertool.sharedtypes.rnr.fps.ProcessTaskResponse;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;
import com.vendertool.sharedtypes.rnr.fps.UploadFileResponse;

@Path("/fps")
public class FPSService extends BaseVenderToolServiceImpl {
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	private static FpsDALService fpsDal =FpsDALService.getInstance();

	@POST
	@Path("/upload")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UploadFileResponse uploadFile(UploadFileRequest uRequest) {
		
		UploadFileResponse response = new UploadFileResponse();
		UploadFileValidator rv = new UploadFileValidator();
		rv.validateFiles(uRequest, response);
		if(response.hasErrors()){
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		String email = uRequest.getSignedInEmail();
		Long accountId  = getAccount(email,response);
		if (!response.hasErrors()) {
			Map<String,String> refMap = AWSHelper.getInstance().uploadFile2AWS(uRequest);
		
	        List<FileInformation> files = uRequest.getFiles();
	        List<File> dbFiles = new ArrayList<File>();
	        
	        for (FileInformation file : files) {
	        	File fileObj = new File();
	        	fileObj.setFileName(file.getFileName());
	        	fileObj.setAccountId(Long.valueOf(accountId));
	        	fileObj.setFileGroupId(uRequest.getGroupId());   
	        	fileObj.setRefUrl(refMap.get(file.getFileName()));
	        	fileObj.setStorageSource(new Byte(FPSStorageSourceEnum.AWS_CLOUD.ordinal()+""));
	        	fileObj.setUseCase(FPSUsecaseEnum.ADD_LISTING);
	        	fileObj.setStatus(FPSFileStatusEnum.CREATED);
	        	fileObj.setCreatedDate(new Date());
	        	fileObj.setLastModifiedDate(new Date());
	        	dbFiles.add(fileObj);
	        }
        
	        try {
	        	fpsDal.uploadFiles(dbFiles);
	        	response.setStatus(ResponseAckStatusEnum.SUCCESS);
	        } catch (DBConnectionException | InsertException | DatabaseException e) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				response.addFieldBindingError(
					Errors.FPS.DB_UPDATE_ERROR, null,
					(String[]) null);
	
	        	response.setStatus(ResponseAckStatusEnum.FAILURE);
	        }
		} else {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
		}
		
        return response;
	}
	
	@POST
	@Path("/uploadDone")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UploadFileResponse uploadDone(UploadFileRequest uRequest) {
		
		UploadFileResponse response = new UploadFileResponse();
		UploadFileValidator rv = new UploadFileValidator();
		rv.validateGroupId(uRequest, response);
		
		if(response.hasErrors()){
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		String email = uRequest.getSignedInEmail();
		Long accountId  = getAccount(email,response);
		if (!response.hasErrors()) {
	        Date curDate = new Date();

	        Job jobObj = new Job();
	        jobObj.setAccountId(accountId);
	        jobObj.setTitle(uRequest.getUploadTitle());
	        jobObj.setReqFileGroupId(uRequest.getGroupId());
	        jobObj.setCreatedDate(curDate);
	        jobObj.setLastModifiedDate(curDate);
	        jobObj.setStatus(FPSJobStatusEnum.CREATED);
			jobObj.setIsoCountryCode("USA");
			jobObj.setUsecase(FPSUsecaseEnum.ADD_LISTING);
			
			Long jobId = null;
			
			try {
				jobId = fpsDal.createJob(jobObj);	
				response.setJobId(jobId);
	        } catch (DBConnectionException | InsertException | DatabaseException e) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				response.addFieldBindingError(
					Errors.FPS.DB_UPDATE_ERROR, null,
					(String[]) null);
	
	        	response.setStatus(ResponseAckStatusEnum.FAILURE);
	        }
			
	        if (VUTIL.isNotNull(jobId) ) {
	        	FPSJobEvent jEvent = new FPSJobEvent();
	        	jEvent.setJobId(jobId);
	        	jEvent.sendEvent();
	        }
		} else {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
		}
		
        return response;
	}
	
	@POST
	@Path("/processJob")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ProcessJobResponse startJob(ProcessJobRequest jRequest) {
		ProcessJobResponse response = new ProcessJobResponse();
		ProcessJobValidator rv = new ProcessJobValidator();
		rv.validateJobId(jRequest, response);
		
		if(response.hasErrors()){
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		String email = jRequest.getSignedInEmail();
		Long accountId  = getAccount(email,response);
		if (!response.hasErrors()) {
			if (jRequest.getJobId() > 0) {
				
				Job job = null;
				try {
					job = fpsDal.getJob(jRequest.getJobId());
				} catch (DBConnectionException | FinderException | DatabaseException e) {
					response.setStatus(ResponseAckStatusEnum.FAILURE);
					response.addFieldBindingError(
						Errors.FPS.DB_UPDATE_ERROR, null,
						(String[]) null);
		
		        	response.setStatus(ResponseAckStatusEnum.FAILURE);
		        }
					
				if (job != null && job.getStatus().equals(FPSJobStatusEnum.CREATED)) {
					rv.validateGroupId(job.getReqFileGroupId(), response);
			
					if (!response.hasErrors()) {
						List<File> lFiles;

						try {
							lFiles = fpsDal.getFiles(job.getReqFileGroupId());
						} catch (DBConnectionException | FinderException | DatabaseException e) {
							response.setStatus(ResponseAckStatusEnum.FAILURE);
							response.addFieldBindingError(
								Errors.FPS.DB_FIND_ERROR, null,
								(String[]) null);
				
				        	response.setStatus(ResponseAckStatusEnum.FAILURE);
				        	return response;
				        }
						
						if (lFiles != null && !lFiles.isEmpty()) {
							for (File file : lFiles) {
								if (file != null && file.getRefUrl() != null) {
									String fileUrl = file.getRefUrl();
									//String filePath = "C:\\Users\\Gnanasekar\\Downloads\\Process\\"+file.getFileName();
									
									//AWSHelper.getInstance().downloadFileFromAWS(fileUrl, filePath);
									
									InputStream iStream = AWSHelper.getInstance().downloadFileFromwAWS(fileUrl);						
									try {	
										//CSVProductReader csvReader = new CSVProductReader(filePath);
										//CSVListingReader csvReader = new CSVListingReader(filePath);
										
										CSVListingReader csvReader = new CSVListingReader(iStream);
										csvReader.processData(job.getJobId(), job.getReqFileGroupId(),
												job.getAccountId(), file.getFileId());	
										file.setStatus(FPSFileStatusEnum.IN_PROGRESS);
									} catch (Exception ex){								
									}
								
								}
							}
							
							try {
								fpsDal.updateFileStatus(lFiles);
								job.setStatus(FPSJobStatusEnum.IN_PROCESS);
								fpsDal.updateJobStatus(job);
					        	response.setStatus(ResponseAckStatusEnum.SUCCESS);
							} catch (DBConnectionException | UpdateException | DatabaseException e) {
							}
						}
					}
				}
			}
		}
		return response;
	}
	
	@POST
	@Path("/processTask")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ProcessTaskResponse startTask(ProcessTaskRequest tRequest) {
		
		ProcessTaskResponse response = new ProcessTaskResponse();
		ProcessTaskValidator rv = new ProcessTaskValidator();
		rv.validateTaskId(tRequest, response);
		
		if(response.hasErrors()){
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		String email = tRequest.getSignedInEmail();
		Long accountId  = getAccount(email,response);
		if (!response.hasErrors()) {
			if (tRequest.getTaskId() > 0) {
				Task task = null;

				try {
					task = fpsDal.getTask(tRequest.getTaskId());
					if (task != null && task.getStatus().equals(FPSTaskStatusEnum.CREATED)) {
						try {
							ListingBean lBean = new ObjectMapper().readValue(task.getRequest(), ListingBean.class);
							Listing listing = new CSVBeanHelper().beanToListing(lBean);
							
							Product product = listing.getProduct();
							product.setAccountId(accountId);
							listing.setProduct(product);
							AddListingRequest input = new AddListingRequest();
							input.setListing(listing);
							ListingServiceimpl impl = new ListingServiceimpl();
							AddListingResponse alResponse = impl.addListing(input);
							if (alResponse.hasErrors()) {
								task.setResponse(alResponse.getVTErrors().toString().getBytes());
								task.setStatus(FPSTaskStatusEnum.FAILED);
								task.setLastModifiedDate(new Date());
							} else {
								Listing mItem = alResponse.getListing();
								if (VUTIL.isNotNull(mItem)) {
									task.setResponse(mItem.getListingId().toString().getBytes());
								}
								task.setStatus(FPSTaskStatusEnum.SUCCESS);
								task.setLastModifiedDate(new Date());
							}
							try {
								fpsDal.updateTaskStatusResponse(task);
							} catch (UpdateException ue) {
								response.setStatus(ResponseAckStatusEnum.FAILURE);
								response.addFieldBindingError(
									Errors.FPS.DB_UPDATE_ERROR, null,
									(String[]) null);		
					        	response.setStatus(ResponseAckStatusEnum.FAILURE);
							}
							
						} catch (JsonProcessingException je) {
							// TODO Auto-generated catch block
						} catch (IOException ie) {
						}

					}
					
				} catch (DBConnectionException | FinderException | DatabaseException e) {
					response.setStatus(ResponseAckStatusEnum.FAILURE);
					response.addFieldBindingError(
						Errors.FPS.DB_FIND_ERROR, null,
						(String[]) null);		
		        	response.setStatus(ResponseAckStatusEnum.FAILURE);
				}
			}
		}
    	response.setStatus(ResponseAckStatusEnum.SUCCESS);
    	
		return response;
	}
	
	@GET
	@Path("/uploadedJobs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UploadsResponse getUploadedJobs(@QueryParam(value="email") String email,
			@QueryParam(value="pageNum") int pageNum, @QueryParam(value="pageSize") int pageSize) {
		UploadsResponse response = new UploadsResponse();	
		
		if(VUTIL.isEmpty(email)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}

		int size = pageSize != 0 ? pageSize : 10;
		int pNum = pageNum  != 0 ? pageNum  : 1;
		
		PaginationOutput paginationOutput = new PaginationOutput();
		paginationOutput.setEntriesPerPage(size);
		paginationOutput.setCurrentPage(pageNum);
		
		Long accountId  = getAccount(email,response);
		if (!response.hasErrors()) {
			List<Job> lJobs = null;
			
			try {
				lJobs = fpsDal.getJobs(accountId);
				if (lJobs == null) {
					lJobs = new ArrayList<Job>();
				}
				paginationOutput.setTotalResults(lJobs.size());
			} catch (DBConnectionException | FinderException | DatabaseException e) {
				lJobs = new ArrayList<Job>();
			}
			
			if (lJobs != null) {
				int i =0;
				List<Job> jobList = new ArrayList<Job>(size);
				for (Job job:lJobs) {
					
					if (i < (pNum-1)*size) {
						continue;
					} else if (i > pNum*size) {
						break;
					}
					
					List<File> files = null;
					
					try {
						files = fpsDal.getFiles(job.getReqFileGroupId());
					} catch (DBConnectionException | FinderException | DatabaseException e) {
						//Need to revisit the logic
						response.setStatus(ResponseAckStatusEnum.FAILURE);
						return response;
					}
					if (files != null) {
						job.setUploadedFiles(files);
						job.setProcessedFiles(files);
					}
					jobList.add(job);
					i++;
				}
				response.setJobs(jobList);
			}
			response.setPaginationOutput(paginationOutput);
		}
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
	
	private Long getAccount(String email, BaseResponse bResponse) {
		Long accountId =null;
		try {
			accountId = RegistrationDALService.getInstance().getAccountId(email);			
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			if(VUTIL.isNull(accountId) || (accountId <= 0)) {
				bResponse.setStatus(ResponseAckStatusEnum.FAILURE);
				bResponse.addFieldBindingError(
						Errors.FPS.ACCOUNT_NOT_FOUND, null,
						(String[]) null);
			}
		}
		return accountId;
		
	}
}