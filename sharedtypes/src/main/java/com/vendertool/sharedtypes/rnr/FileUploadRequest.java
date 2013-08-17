package com.vendertool.sharedtypes.rnr;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.FileInformation;

@XmlRootElement
public class FileUploadRequest extends BaseRequest {

	private List<FileInformation> files;

	public List<FileInformation> getFiles() {
		return files;
	}

	public void setFiles(List<FileInformation> files) {
		this.files = files;
	}
}