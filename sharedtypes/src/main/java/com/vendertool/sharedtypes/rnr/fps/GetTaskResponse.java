package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.core.fps.Task;
import com.vendertool.sharedtypes.rnr.BaseResponse;

@XmlRootElement
public class GetTaskResponse extends BaseResponse {
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
