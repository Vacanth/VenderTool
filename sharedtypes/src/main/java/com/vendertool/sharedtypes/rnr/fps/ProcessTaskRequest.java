package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;
import com.vendertool.sharedtypes.rnr.BaseRequest;

@XmlRootElement
public class ProcessTaskRequest extends BaseRequest {
	private long taskId;
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
}
