package com.vendertool.sharedtypes.rnr.fps;

import com.vendertool.sharedtypes.rnr.BaseResponse;

public class ProcessJobResponse extends BaseResponse {
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
