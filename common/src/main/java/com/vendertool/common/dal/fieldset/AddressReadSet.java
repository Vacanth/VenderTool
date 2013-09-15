package com.vendertool.common.dal.fieldset;

import com.mysema.query.types.Path;
import com.vendertool.common.dal.dao.codegen.QAddress;

public class AddressReadSet {
	private static final QAddress ADDRESS = QAddress.address;
	
	private static class AddressReadSetHolder {
		private static final AddressReadSet INSTANCE = new AddressReadSet();
	}
	
	public static AddressReadSet getInstance() {
		return AddressReadSetHolder.INSTANCE;
	}
	
	public final Path<?>[] ALL = ADDRESS.all();
}
