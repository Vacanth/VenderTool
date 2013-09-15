package com.vendertool.common.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QAddress;

public class AddressUpdateSet {
	private static final QAddress ADDRESS = QAddress.address;
	
	private static class AddressUpdateSetHolder {
		private static final AddressUpdateSet INSTANCE = new AddressUpdateSet();
	}
	
	public static AddressUpdateSet getInstance() {
		return AddressUpdateSetHolder.INSTANCE;
	}
	
	public final Path<?>[] METADATA = {
			ADDRESS.addrType,
			ADDRESS.status,
			ADDRESS.useCase
	};
}
