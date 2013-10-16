package com.vendertool.sharedtypes.rnr.fps;

import javax.xml.bind.annotation.XmlRootElement;

import com.vendertool.sharedtypes.rnr.BaseRequest;

@XmlRootElement
public class GetJobsRequest extends BaseRequest {

	private long[] jobIds;
	private long accountId;
	private int pageSize = 25;
	private int pageNum = 1;

	public long[] getJobIds() {
		return jobIds;
	}

	public void setJobIds(long[] jobIds) {
		this.jobIds = jobIds;
	}
	
	public long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
