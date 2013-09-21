package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.fps.FPSTaskStatusEnum;
import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class GetTaskStatusResponse extends BaseResponse {
	private FPSTaskStatusEnum taskStatus;
	
	public void setTaskStatus(FPSTaskStatusEnum taskStatus) {
		this.taskStatus = taskStatus;
	}

	public FPSTaskStatusEnum getTaskStatus() {
		return taskStatus;
	}
}
