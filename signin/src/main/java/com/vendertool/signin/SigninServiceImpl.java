package com.vendertool.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.sharedtypes.rnr.SigninRequest;
import com.vendertool.sharedtypes.rnr.SigninResponse;
import com.vendertool.sharedtypes.rnr.SignoutRequest;
import com.vendertool.sharedtypes.rnr.SignoutResponse;

@Controller
@RequestMapping("/signin")
public class SigninServiceImpl extends BaseVenderToolServiceImpl implements
		ISigninService {

	@RequestMapping(value = "/signin", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public SigninResponse signin(SigninRequest request) {
		return null;
	}

	@RequestMapping(value = "/signout", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public SignoutResponse signout(SignoutRequest request) {
		return null;
	}

}