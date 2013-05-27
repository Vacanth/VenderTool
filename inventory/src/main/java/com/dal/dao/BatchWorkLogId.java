package com.dal.dao;

// Generated May 26, 2013 5:25:20 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BatchWorkLogId generated by hbm2java
 */
@Embeddable
public class BatchWorkLogId implements java.io.Serializable {

	private long batchWorkLogId;
	private long batchId;
	private long fileId;

	public BatchWorkLogId() {
	}

	public BatchWorkLogId(long batchWorkLogId, long batchId, long fileId) {
		this.batchWorkLogId = batchWorkLogId;
		this.batchId = batchId;
		this.fileId = fileId;
	}

	@Column(name = "BATCH_WORK_LOG_ID", nullable = false)
	public long getBatchWorkLogId() {
		return this.batchWorkLogId;
	}

	public void setBatchWorkLogId(long batchWorkLogId) {
		this.batchWorkLogId = batchWorkLogId;
	}

	@Column(name = "BATCH_ID", nullable = false)
	public long getBatchId() {
		return this.batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	@Column(name = "FILE_ID", nullable = false)
	public long getFileId() {
		return this.fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BatchWorkLogId))
			return false;
		BatchWorkLogId castOther = (BatchWorkLogId) other;

		return (this.getBatchWorkLogId() == castOther.getBatchWorkLogId())
				&& (this.getBatchId() == castOther.getBatchId())
				&& (this.getFileId() == castOther.getFileId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getBatchWorkLogId();
		result = 37 * result + (int) this.getBatchId();
		result = 37 * result + (int) this.getFileId();
		return result;
	}

}