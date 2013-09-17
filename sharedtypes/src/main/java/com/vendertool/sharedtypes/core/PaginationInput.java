package com.vendertool.sharedtypes.core;

public class PaginationInput {
	
	private int currentPageNumber;
	private int entriesPerPage;
	
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public int getEntriesPerPage() {
		return entriesPerPage;
	}
	public void setEntriesPerPage(int entriesPerPage) {
		this.entriesPerPage = entriesPerPage;
	}
	
	
}
