package com.vendertool.fps.fileupload.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.rnr.fps.UploadFileRequest;

public class AWSHelper {
	private static final String UPLOAD_REQ_BUCKET = "vtuploadreqbucket";
	private static AWSHelper s_self = null;
    private static AmazonS3 s3 = null;

	private AWSHelper() {
	}

	public synchronized static AWSHelper getInstance() {
		if (s_self == null) {
			s_self = new AWSHelper();
			s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			s3.setRegion(usWest2);
		}
		return s_self;
	}
	
	public boolean isBucketExists(List<Bucket> buckets, String bucketName) {
        for (Bucket bucket : buckets) {
            if (bucket != null && bucket.getName().equals(bucketName)) {
            	return true;
            }
        }
        return false;
	}
	
	public boolean doesBucketExists(String bucketName) {
		return s3.doesBucketExist(bucketName);
	}
	
	public PutObjectResult putObjectRequest(String bucketName, String fileName, InputStream iStream, 
									ObjectMetadata omData) {
		PutObjectResult poResult = null;
		try {
			// TODO find the bucket already exists then don't create it
			if (!doesBucketExists(bucketName)) {
				System.out.println("Creating bucket " + bucketName + "\n");
				s3.createBucket(bucketName);
			}

			poResult = s3.putObject(new PutObjectRequest(bucketName, fileName, iStream,
					omData));

		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, which means your request made it "
							+ "to Amazon S3, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with S3, "
							+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return poResult;
	}
	
	public Map<String,String> uploadFile2AWS (UploadFileRequest fileRequest) {
		HashMap<String,String> fileRefMap = new HashMap<String,String>();
	
        System.out.println("Uploading a new object to S3 from a file\n");
        
        List<FileInformation> files = fileRequest.getFiles();
        
        for (FileInformation file : files) {
            Long contentLength = Long.valueOf(file.getFileSize());

    		String key = "userName_"+ new Date().getTime()+ "_"+ file.getFileName();
            fileRefMap.put(file.getFileName(), key);
            
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            InputStream fileIStream = new ByteArrayInputStream(file.getFileData());
            
        	putObjectRequest(UPLOAD_REQ_BUCKET, key, fileIStream, metadata);
        }
        
        return fileRefMap;
	}
	
	public File downloadFileFromAWS(String filename, String localFilename) {
		return downloadFileFromAWS(UPLOAD_REQ_BUCKET, filename, localFilename);
	}
	
	public File downloadFileFromAWS(String bucketName, String fileName, String localFilename) {
		File localFile = new File(localFilename);
		s3.getObject(new GetObjectRequest(bucketName,fileName), localFile);
		return localFile;
	}
	
	public void downloadFileToLocal(String bucketName, String fileName, String localFile) {
		S3Object object = s3.getObject(new GetObjectRequest(bucketName, fileName));

		try {
			File lFile = new File(localFile);
			
			InputStream reader = new BufferedInputStream(object.getObjectContent());	   
			OutputStream writer = new BufferedOutputStream(new FileOutputStream(lFile));
	
			int read = -1;
	
			while ((read = reader.read()) != -1) {
				writer.write(read);
			}
	
			writer.flush();
			writer.close();
			reader.close(); 
		} catch (FileNotFoundException fnfe){
		} catch (IOException ie) {
		}
	}
}
