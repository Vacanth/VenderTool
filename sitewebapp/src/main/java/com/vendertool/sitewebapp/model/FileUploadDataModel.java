package com.vendertool.sitewebapp.model;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadDataModel {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}