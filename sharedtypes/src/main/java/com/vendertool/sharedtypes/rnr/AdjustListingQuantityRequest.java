package com.vendertool.sharedtypes.rnr;

public class AdjustListingQuantityRequest extends BaseRequest {
	private int quantity;
	private ArithmeticOpCodeEnum operation;
	private long listingId;
	
	public long getListingId() {
		return listingId;
	}

	public void setListingId(long listingId) {
		this.listingId = listingId;
	}

	public AdjustListingQuantityRequest(){}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArithmeticOpCodeEnum getOperation() {
		return operation;
	}

	public void setOperation(ArithmeticOpCodeEnum operation) {
		this.operation = operation;
	}
}
