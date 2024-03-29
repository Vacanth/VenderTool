package com.vendertool.registration.validation;

import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;

public class SecurityQuestionsValidator implements Validator {

	private static final Logger logger = Logger.getLogger(SecurityQuestionsValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	@Override
	public void validate(BaseRequest _request, BaseResponse response) {
		logger.info(SecurityQuestionsValidator.class.getName() + ".validate()");
		
		UpdateAccountSecurityQuestionsRequest request = (UpdateAccountSecurityQuestionsRequest) _request;
		
		if(VUTIL.isNull(request)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return;
		}
		
		if (VUTIL.isEmpty(request.getEmail())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.EMAIL_MISSING, null,
					(String[]) null);
			return;
		}
		
		if (VUTIL.isNull(request.getQuestions()) || request.getQuestions().isEmpty()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.MISSING_SECURITY_QUESTION, null,
					(String[]) null);
			return;
		}
		
		if (VUTIL.isEmpty(request.getPassword())) {
			response.addFieldBindingError(Errors.REGISTRATION.MISSING_PASSWORD,
					null, (String[])null);
		}
		
		List<AccountSecurityQuestion> questions = request.getQuestions();
		for(AccountSecurityQuestion question : questions){
			if(question.getQuestion() == null || VUTIL.isNull(question.getQuestion().getQuestionCode())) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				response.addFieldBindingError(
						Errors.REGISTRATION.MISSING_SECURITY_QUESTION, null,
						(String[]) null);
				continue;
			}
			
			if(VUTIL.isEmpty(question.getAnswer())) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				response.addFieldBindingError(
						Errors.REGISTRATION.MISSING_SECURITY_ANSWER, null,
						(String[]) null);
				continue;
			}
		}
	}

}
