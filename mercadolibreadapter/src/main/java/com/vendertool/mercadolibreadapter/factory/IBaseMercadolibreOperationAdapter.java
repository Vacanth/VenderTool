package com.vendertool.mercadolibreadapter.factory;

import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;

public interface IBaseMercadolibreOperationAdapter {

	public void execute(BaseRequest request, BaseResponse response);
}