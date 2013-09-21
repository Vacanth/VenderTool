package com.vendertool.sharedtypes.rnr.fps;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.FileInformation;
import com.vendertool.sharedtypes.rnr.BaseRequest;

@XmlRootElement
public class UploadFileRequest extends BaseRequest {

	private List<FileInformation> files;
	private String fileGroupId;
	private String title;

	public List<FileInformation> getFiles() {
		return files;
	}

	public void setFiles(List<FileInformation> files) {
		this.files = files;
	}

	public String getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
