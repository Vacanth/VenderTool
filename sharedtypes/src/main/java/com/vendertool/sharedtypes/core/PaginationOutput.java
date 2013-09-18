package com.vendertool.sharedtypes.core;


public class PaginationOutput {

	private int currentPage;
	private int entriesPerPage;
	private int totalResults;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getEntriesPerPage() {
		return entriesPerPage;
	}
	public void setEntriesPerPage(int entriesPerPage) {
		this.entriesPerPage = entriesPerPage;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResultCount) {
		this.totalResults = totalResultCount;
	}

	
	
}
