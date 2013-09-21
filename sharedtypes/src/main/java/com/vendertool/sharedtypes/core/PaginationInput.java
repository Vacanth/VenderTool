package com.vendertool.sharedtypes.core;

public class PaginationInput {
	
	private int currentPage;
	private int entriesPerPage;
	
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
	
	
}
