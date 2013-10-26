package com.vendertool.registration.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.common.validation.Validator;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountStatusEnum;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.BaseRequest;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.ValidateSecurityQuestionsRequest;

public class ForgotPasswordSecurityQuestionValidator implements Validator {

	private static final Logger logger = Logger.getLogger(ConfirmForgotPasswordEmailValidator.class);
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	private RegistrationDALService dalservice = RegistrationDALService.getInstance();
	private static final int MAX_SEC_QUES_ANSW_ATTEMPTS_ONE_HOUR = 5;
	private static final int MAX_SEC_QUES_ANSW_ATTEMPTS = 10;
	
	@Override
	public void validate(BaseRequest _request, BaseResponse response) {
		logger.info(ForgotPasswordSecurityQuestionValidator.class.getName() + ".validate()");
		
		ValidateSecurityQuestionsRequest request = 
				(ValidateSecurityQuestionsRequest) _request;
		
		ConfirmForgotPasswordEmailRequest cfprequest = new ConfirmForgotPasswordEmailRequest();
		cfprequest.setEmail(request.getEmail());
		cfprequest.setConfirmCode(request.getConfirmCode());
		cfprequest.setConfirmSessionId(request.getConfirmSessionId());
		cfprequest.setIpAddress(request.getIpAddress());
		
		ConfirmForgotPasswordEmailValidator validator = 
				new ConfirmForgotPasswordEmailValidator();
		validator.validate(cfprequest, response);
		
		UpdateAccountSecurityQuestionsRequest sqrequest = new UpdateAccountSecurityQuestionsRequest();
		sqrequest.setEmail(request.getEmail());
		sqrequest.setQuestions(request.getQuestions());
		//this is to bypass for now
		sqrequest.setPassword("Oneempty1");
		
		SecurityQuestionsValidator sqvalidator = new SecurityQuestionsValidator();
		sqvalidator.validate(sqrequest, response);
	}

	public void validateSecurityAnswers(BaseResponse response,
			Account account, 
			List<AccountSecurityQuestion> dbquestions,
			List<AccountSecurityQuestion> inputQuestions) {
		
		String email = account.getEmail();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR, -1);
        Date stdt = cal.getTime();
        
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 1);
        Date eddt = cal.getTime();
        
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);
        Date totalstdt = cal.getTime();
        
        if(account.getAccountStatus().equals(AccountStatusEnum.SUSPENDED)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.ACCOUNT_SUSPENDED,
					null, (String[]) null);
			return;
        }
        
        long hourcount = 0;
        long totalcount = 0;
		try {
			hourcount = dalservice.getForgotPasswordCount(email, stdt, eddt,
					ForgotPasswordStatusEnum.SEC_QUES_FAILED);
			totalcount = dalservice.getForgotPasswordCount(email, totalstdt, new Date(),
					ForgotPasswordStatusEnum.SEC_QUES_FAILED);
		} catch (FinderException | DBConnectionException | DatabaseException e1) {
			logger.debug(e1.getMessage(), e1);
		}
		
		if(totalcount > MAX_SEC_QUES_ANSW_ATTEMPTS) {
			try {
				dalservice.updateAccountStatus(email, AccountStatusEnum.SUSPENDED);
			} catch (DBConnectionException | UpdateException
					| DatabaseException e) {
				logger.debug(e.getMessage(), e);
			}
			
			response.addFieldBindingError(
					Errors.REGISTRATION.TOO_MANY_ACCOUNT_PWD_ATTEMPTS,
					null, (String[]) null);
			return;
		}
		
		if(hourcount > MAX_SEC_QUES_ANSW_ATTEMPTS_ONE_HOUR) {
			response.addFieldBindingError(
					Errors.REGISTRATION.TOO_MANY_PWD_ATTEMPTS_IN_ONE_HOUR,
					null, (String[]) null);
			return;
		}
		
		if(dbquestions.size() != inputQuestions.size()) {
			response.addFieldBindingError(
					Errors.REGISTRATION.INVALID_SECURITY_QUESTIONS_ANSWERS,
					null, (String[]) null);
			return;
		}
		
		try {
			for(AccountSecurityQuestion question : dbquestions) {
				SecurityQuestionCodeEnum code = 
						question.getQuestion().getQuestionCode();
				AccountSecurityQuestion inputquestion = 
						getAccountSecurityQuestion(code, inputQuestions);
				
				if(VUTIL.isNull(inputquestion)) {
					response.addFieldBindingError(
							Errors.REGISTRATION.INVALID_SECURITY_QUESTIONS_ANSWERS,
							null, (String[]) null);
					return;
				}
				
				if(! question.getAnswer().equalsIgnoreCase(inputquestion.getAnswer())) {
					response.addFieldBindingError(
							Errors.REGISTRATION.INVALID_SECURITY_QUESTIONS_ANSWERS,
							null, (String[]) null);
					return;
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(
					Errors.REGISTRATION.INVALID_SECURITY_QUESTIONS_ANSWERS,
					null, (String[]) null);
			return;
		}
		
		try {
			dalservice.updateForgotPasswordByStatus(email, ForgotPasswordStatusEnum.INVALIDATE);
		} catch (DeleteException | DBConnectionException | UpdateException
				| DatabaseException e) {
			logger.debug(e.getMessage(), e);
		}
	}
	
	private AccountSecurityQuestion getAccountSecurityQuestion(SecurityQuestionCodeEnum scode, 
			List<AccountSecurityQuestion> questions) {
		
		for(AccountSecurityQuestion question : questions) {
			if(question.getQuestion() != null) {
				SecurityQuestionCodeEnum code = 
						question.getQuestion().getQuestionCode();
				if(code.equals(scode)) {
					return question;
				}
			}
		}
		
		return null;
	}
}