package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.FileInformation;

@XmlRootElement
public class FileUploadRequest extends BaseRequest {

	private List<FileInformation> files;
	private String groupId;
	private String uploadTitle;

	public List<FileInformation> getFiles() {
		return files;
	}

	public void setFiles(List<FileInformation> files) {
		this.files = files;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUploadTitle() {
		return uploadTitle;
	}

	public void setUploadTitle(String uploadTitle) {
		this.uploadTitle = uploadTitle;
	}

}