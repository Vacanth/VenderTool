package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class UploadFileResponse extends BaseResponse {
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
