package com.vendertool.registration;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import com.vendertool.common.MsgSource;
import com.vendertool.common.SessionIdGenerator;
import com.vendertool.common.SpringApplicationContextUtils;
import com.vendertool.common.URLConstants;
import com.vendertool.common.dal.exception.DBConnectionException;
import com.vendertool.common.dal.exception.DatabaseException;
import com.vendertool.common.dal.exception.DeleteException;
import com.vendertool.common.dal.exception.FinderException;
import com.vendertool.common.dal.exception.InsertException;
import com.vendertool.common.dal.exception.UpdateException;
import com.vendertool.common.service.BaseVenderToolServiceImpl;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.registration.dal.RegistrationDALService;
import com.vendertool.registration.email.RegistrationEmailSender;
import com.vendertool.registration.validation.ChangeEmailValidator;
import com.vendertool.registration.validation.ChangePasswordValidator;
import com.vendertool.registration.validation.ConfirmEmailValidator;
import com.vendertool.registration.validation.ConfirmForgotPasswordEmailValidator;
import com.vendertool.registration.validation.ForgotPasswordEmailValidator;
import com.vendertool.registration.validation.ForgotPasswordSecurityQuestionValidator;
import com.vendertool.registration.validation.RegistrationValidator;
import com.vendertool.registration.validation.SecurityQuestionsValidator;
import com.vendertool.registration.validation.UpdateAccountProfileValidator;
import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountConfirmation;
import com.vendertool.sharedtypes.core.AccountConfirmation.AccountConfirmationStatusEnum;
import com.vendertool.sharedtypes.core.AccountRoleEnum;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.AccountStatusEnum;
import com.vendertool.sharedtypes.core.ForgotPassword;
import com.vendertool.sharedtypes.core.ForgotPassword.ForgotPasswordStatusEnum;
import com.vendertool.sharedtypes.core.SecurityQuestionCodeEnum;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.AuthorizeMarketRequest;
import com.vendertool.sharedtypes.rnr.AuthorizeMarketResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse;
import com.vendertool.sharedtypes.rnr.BaseResponse.ResponseAckStatusEnum;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangeEmailResponse;
import com.vendertool.sharedtypes.rnr.ChangeForgotPasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangeForgotPasswordResponse;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordResponse;
import com.vendertool.sharedtypes.rnr.CloseAccountRequest;
import com.vendertool.sharedtypes.rnr.CloseAccountResponse;
import com.vendertool.sharedtypes.rnr.ConfirmEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmEmailResponse;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmForgotPasswordEmailResponse;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationRequest;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationResponse;
import com.vendertool.sharedtypes.rnr.GetAccountPasswordResponse;
import com.vendertool.sharedtypes.rnr.GetAccountResponse;
import com.vendertool.sharedtypes.rnr.GetAccountSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.LinkOtherSiteRequest;
import com.vendertool.sharedtypes.rnr.LinkOtherSiteResponse;
import com.vendertool.sharedtypes.rnr.ProcessForgotPasswordEmailRequest;
import com.vendertool.sharedtypes.rnr.ProcessForgotPasswordEmailResponse;
import com.vendertool.sharedtypes.rnr.RegisterAccountRequest;
import com.vendertool.sharedtypes.rnr.RegisterAccountResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.ValidateSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.ValidateSecurityQuestionsResponse;

@Path("/registration")
public class RegistrationServiceImpl extends BaseVenderToolServiceImpl
		implements IRegistrationService {
	
	@Context
	UriInfo uri;
	@Context
    HttpServletRequest httpServletRequest;
	@Context
    HttpServletResponse httpServletResponse;
	
	private static final Logger logger = Logger.getLogger(RegistrationServiceImpl.class);
	private static int RANDOM_CODE_DIGIT_COUNT = 5;
	private static int MAX_EMAIL_CONFIRM_RETRY_ATTEMPTS = 5;
	private static int ACCOUNT_CONFIRMATION_EXPIRY_DAYS = 14;
	private static ValidationUtil VUTIL = ValidationUtil.getInstance();
	
	private RegistrationDALService dalservice;

	
	//Set up few things as part of the constructor
	public RegistrationServiceImpl() {
		dalservice = RegistrationDALService.getInstance();
	}
	
	
	@POST
	@Path("/register")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public RegisterAccountResponse registerAccount(
			RegisterAccountRequest request) {
		
		//check for nulls in the validator
		RegisterAccountResponse response = new RegisterAccountResponse();
		RegistrationValidator rv = new RegistrationValidator();
		rv.validate(request, response);
		if(response.hasErrors()){
			if(request != null) {
				if(request.getAccount() != null) {
					request.getAccount().clearPassword();
				}
				response.setAccount(request.getAccount());
			}
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		Account account = request.getAccount();
		prepareAccountForRegistration(account);
		
		String hashedPassword = saltHashPassword(account.getPasswordSalt(),
				account.getPassword());
		account.setPassword(hashedPassword);
		account.setConfirmPassword(hashedPassword);
		
		Long accountId = null;
		try {
			accountId = dalservice.registerAccount(account);
		} catch (DBConnectionException | FinderException | InsertException
				| DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_REGISTER, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		//all good, now clean up account sensitive data
		account.clearPassword();
		account.setId(accountId);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		response.setSuccess(true);
		response.setAccount(account);
		
		Locale locale = getLocale();
		String baseurl = getBaseUrl();
    	
		try { 
			RegistrationEmailSender.getInstance().sendConfirmRegistrationEmail(
					account, baseurl, locale);
		} catch (Exception e) {
			//don't block if email fails
			logger.debug(e.getMessage(), e);
		}

		return response;
	}

	
	//Use this for new registration, email change & password change functionality
	private void prepareAccountForRegistration(Account account) {
		
		AccountConfirmation ac = prepareAccountConfirmation(account.getEmail());
		
		account.setAccountStatus(AccountStatusEnum.NOT_VERIFIED);
		String passwordSalt = SessionIdGenerator.getInstance()
				.generateSessionId(false);
		account.setPasswordSalt(passwordSalt);
		account.setAccountConf(ac);
		account.addRole(AccountRoleEnum.ROLE_USER);
	}


	private AccountConfirmation prepareAccountConfirmation(String email) {
		SessionIdGenerator sidg = SessionIdGenerator.getInstance();
		
		String sessionId = sidg.generateSessionId(true);
		Integer code = sidg.getRandomNumber(RANDOM_CODE_DIGIT_COUNT);
		
		AccountConfirmation ac = new AccountConfirmation();
		ac.setConfirmCode(code);
		ac.setConfirmSessionId(sessionId);
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, ACCOUNT_CONFIRMATION_EXPIRY_DAYS);
        
		ac.setExpiryDate(cal.getTime());
		ac.setStatus(AccountConfirmationStatusEnum.NOT_VERIFIED);
		ac.setEmail(email);
		
		return ac;
	}

	
	private String saltHashPassword(String salt, String password) {
		ApplicationContext appContext = SpringApplicationContextUtils.getApplicationContext();
		MessageDigestPasswordEncoder encoder = (MessageDigestPasswordEncoder) appContext
				.getBean("passwordEncoder");
		
		String hashedPwd = encoder.encodePassword(password, salt);
		return hashedPwd;
	}

	
	private String getBaseUrl() {
		String url = uri.getBaseUri().getHost();
		int port = uri.getBaseUri().getPort();
		String baseurl = URLConstants.HTTP + url + ((port > 0) ? (URLConstants.PORT_SEPARATOR + port) : "");
		//clean up trailing '/'
//		if(baseurl.charAt(baseurl.length()-1) == '/') {
//			baseurl = baseurl.substring(0, baseurl.length()-1);
//		}
		return baseurl;
	}

	
	private Locale getLocale() {
		Locale locale = httpServletRequest.getLocale();
		if(locale == null) {
			locale = Locale.US;
		}
		return locale;
	}
	
	
	@POST
	@Path("/confirmRegistration")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ConfirmRegistrationResponse confirmRegistration(ConfirmRegistrationRequest request) {
		ConfirmRegistrationResponse response = new ConfirmRegistrationResponse();

		if (VUTIL.isNull(request) || VUTIL.isNull(request.getAccountConf())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		if(VUTIL.isEmpty(request.getEmail())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, null, (String[])null);
			return response;
		}
		
		String email = request.getEmail();
		String inputSessionId = request.getAccountConf().getConfirmSessionId();
		Integer inputCode = request.getAccountConf().getConfirmCode();
		
		Account account;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		_confirmEmail(response, account, email, email, inputSessionId, inputCode, true);
		
		if(response.hasErrors()) {
			return response;
		}
		
		try {
			dalservice.updateAccountStatus(email, AccountStatusEnum.VERIFIED);
		} catch (DBConnectionException | UpdateException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_REGISTER, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		response.setSuccess(true);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		
		Locale locale = getLocale();
		String baseurl = getBaseUrl();
    	
		try {
			RegistrationEmailSender.getInstance().sendRegistrationCompleteEmail(
					account, baseurl, locale);
		} catch (Exception e) {
			//don't block
			logger.debug(e.getMessage(), e);
		}
		
		return response;
	}
	

	@GET
	@Path("/getAccount")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetAccountResponse getAccount(@QueryParam(value="email") String email) {
		
		GetAccountResponse response = new GetAccountResponse();
		
		if (VUTIL.isEmpty(email)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		Account account;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_RETRIEVE_ACCOUNT_INFORMATION,
					null, (String[]) null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		if(account == null) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		account.clearPassword();
		account.clearAccountConfirmation();
		response.setAccount(account);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
	
	
	@GET
	@Path("/getAccountPassword")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public GetAccountPasswordResponse getAccountPassword(@QueryParam(value="email") String email) {
		
		GetAccountPasswordResponse response = new GetAccountPasswordResponse();
		
		if (VUTIL.isEmpty(email)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		Account account;
		try {
			account = dalservice.getAccountPassword(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_RETRIEVE_ACCOUNT_INFORMATION,
					null, (String[]) null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		if(account == null) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		response.setAccount(account);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}

	
	@POST
	@Path("/updateAccount")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UpdateAccountResponse updateAccount(UpdateAccountRequest request) {
		
		UpdateAccountResponse response = new UpdateAccountResponse();
		Account account = request.getAccount();
		
		UpdateAccountProfileValidator validator = new UpdateAccountProfileValidator();
		validator.validate(request, response);
		verifyPassword(response, account.getEmail(), account.getPassword());
		
		if(response.hasErrors()){
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.setAccount(account);
			return response;
		}
		
		
		try {
			dalservice.updateAccountProfile(account);
			response.setAccount(account);
		} catch (DBConnectionException | UpdateException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.setAccount(account);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_UPDATE_ACCOUNT, null, (String[])null);
			return response;
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}

	
	@POST
	@Path("/changePassword")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
		ChangePasswordResponse response = new ChangePasswordResponse();
		
		ChangePasswordValidator validator = new ChangePasswordValidator();
		validator.validate(request, response);
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		String email = request.getEmail();
		
		Account account = null;
				
		try {
			account = dalservice.getAccountPassword(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CHANGE_PASSWORD, null, (String[])null);
			return response;
		}
		
		if(VUTIL.isNull(account)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			return response;
		}
		
		//old password should still be available from the DB in case of forgot password use case
		String oldDBPassword = account.getPassword();
		
		if(! request.isForgotPasswordUsecase()) {
			String oldInputHashedPassword = saltHashPassword(account.getPasswordSalt(),
					request.getOldPassword());
			
			if(!oldDBPassword.equals(oldInputHashedPassword)) {
				response.setStatus(ResponseAckStatusEnum.FAILURE);
				response.addFieldBindingError(Errors.REGISTRATION.INVALID_OLD_PASSWORD, null, (String[])null);
				return response;
			}
		}
		
		String hashedNewPassword = saltHashPassword(account.getPasswordSalt(),
				request.getNewPassword());
		account.setPassword(hashedNewPassword);
		account.setConfirmPassword(hashedNewPassword);
		
		boolean updated = false;
		try {
			updated = dalservice.updatePassword(email, oldDBPassword, hashedNewPassword);
		} catch (FinderException | DBConnectionException | DatabaseException
				| InsertException e) {
			logger.debug(e.getMessage(), e);
			updated = false;
		}
		
		if(! updated) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CHANGE_PASSWORD, null, (String[])null);
			return response;
		}
		
		Locale locale = getLocale();
		String baseurl = getBaseUrl();
		
		try { 
			RegistrationEmailSender.getInstance().sendPasswordChangeEmail(
					account, baseurl, locale);
		} catch (Exception e) {
			//don't block if email fails
			logger.debug(e.getMessage(), e);
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
	
	private void verifyPassword(BaseResponse response, String email, String inputPassword) {
		Account account = null;
		
		try {
			account = dalservice.getAccountPassword(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_VERIFY_PASSWORD, null, (String[])null);
			return;
		}
		
		if(VUTIL.isNull(account)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			return;
		}
		
		//old password should still be available from the DB in case of forgot password use case
		String dbPassword = account.getPassword();
		
		String hashedPassword = saltHashPassword(account.getPasswordSalt(), inputPassword);
			
		if(!dbPassword.equals(hashedPassword)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.INVALID_PASSWORD, null, (String[])null);
			return;
		}
	}
	
	@POST
	@Path("/changeEmail")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
		ChangeEmailResponse response = new ChangeEmailResponse();
		
		ChangeEmailValidator validator = new ChangeEmailValidator();
		validator.validate(request, response);
		if ((!VUTIL.isEmpty(request.getPassword()))
				&& (!VUTIL.isEmpty(request.getOldEmail()))) {
			verifyPassword(response, request.getOldEmail(), request.getPassword());
		}
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.setEmail(request.getOldEmail());
			return response;
		}
		
		String oldInputEmail = request.getOldEmail();
		Account account = null;
		try {
			account = dalservice.getAccountProfile(oldInputEmail);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CHANGE_EMAIL, null, (String[])null);
			response.setEmail(request.getOldEmail());
			return response;
		}
		
		if(VUTIL.isNull(account)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setEmail(request.getOldEmail());
			return response;
		}
		
		AccountConfirmation ac = prepareAccountConfirmation(request.getNewEmail());
		account.setAccountConf(ac);
		account.setAccountStatus(AccountStatusEnum.EMAIL_CHANGE_NOT_VERIFIED);
		
		boolean inserted = false;
		try {
			inserted = dalservice.insertAccountConfirmation(account.getId(), ac);
		} catch (DBConnectionException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			inserted = false;
		}
		
		if(! inserted) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CHANGE_EMAIL, null, (String[])null);
			response.setEmail(request.getOldEmail());
			return response;
		}
		
		Locale locale = getLocale();
		String baseurl = getBaseUrl();
		
		try { 
			RegistrationEmailSender.getInstance().sendAccountEmailChangeEmail(
					account, oldInputEmail, baseurl, locale);
		} catch (Exception e) {
			//don't block if email fails
			logger.debug(e.getMessage(), e);
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
	
	@POST
	@Path("/closeAccount")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public CloseAccountResponse closeAccount(CloseAccountRequest request) {
		CloseAccountResponse response = new CloseAccountResponse();
		
		if(VUTIL.isNull(request) || VUTIL.isEmpty(request.getEmail())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		try {
			dalservice.removeAccount(request.getEmail());
		} catch (DeleteException | DBConnectionException | DatabaseException
				| FinderException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CLOSE_ACCOUNT, null, (String[])null);
			return response;
		}
		
		response.setAccountStatus(AccountStatusEnum.CLOSED);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}

	@POST
	@Path("/authorizeMarket")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public AuthorizeMarketResponse authorizeMarket(
			AuthorizeMarketRequest request) {
		return null;
	}

	@POST
	@Path("/linkOtherSite")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public LinkOtherSiteResponse linkOtherSite(LinkOtherSiteRequest request) {
		return null;
	}


	@POST
	@Path("/updateSecurityQuestions")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public UpdateAccountSecurityQuestionsResponse updateSecurityQuestions(
			UpdateAccountSecurityQuestionsRequest request) {
		
		UpdateAccountSecurityQuestionsResponse response = 
				new UpdateAccountSecurityQuestionsResponse();
		
		SecurityQuestionsValidator validator = new SecurityQuestionsValidator();
		validator.validate(request, response);
		if ((!VUTIL.isEmpty(request.getPassword()))
				&& (!VUTIL.isEmpty(request.getEmail()))) {
			verifyPassword(response, request.getEmail(), request.getPassword());
		}
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.setQuestions(request.getQuestions());
			return response;
		}
		
		Long accountId = null;
		try {
			accountId = dalservice.getAccountId(request.getEmail());
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_UPDATE_SEC_QUESTIONS, null,
					(String[]) null);
			response.setQuestions(request.getQuestions());
			return response;
		}
		
		if(VUTIL.isNull(accountId) || (accountId <= 0)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		try {
			dalservice.updateAccountSecurityQuestions(request.getEmail(), request.getQuestions());
			response.setQuestions(request.getQuestions());
		} catch (UpdateException | DBConnectionException | FinderException
				| DatabaseException | DeleteException | InsertException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_UPDATE_SEC_QUESTIONS, null,
					(String[]) null);
			return response;
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}


	@GET
	@Path("/getSecurityQuestions")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public GetAccountSecurityQuestionsResponse getSecurityQuestions(@QueryParam(value="email") String email) {
		GetAccountSecurityQuestionsResponse response = 
				new GetAccountSecurityQuestionsResponse();
		
		if(VUTIL.isEmpty(email)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		Long accountId = null;
		try {
			accountId = dalservice.getAccountId(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_GET_SEC_QUESTIONS, null,
					(String[]) null);
			return response;
		}
		
		if(VUTIL.isNull(accountId) || (accountId <= 0)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null,
					(String[]) null);
			return response;
		}
		
		try {
			List<AccountSecurityQuestion> questions = 
					dalservice.getAccountSecurityQuestions(email);
			response.setQuestions(questions);
			response.setStatus(ResponseAckStatusEnum.SUCCESS);
			return response;
		} catch (FinderException | DBConnectionException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_GET_SEC_QUESTIONS, null,
					(String[]) null);
			return response;
		}
	}
	
	
	@POST
	@Path("/confirmEmail")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public ConfirmEmailResponse confirmEmail(ConfirmEmailRequest request) {
		ConfirmEmailResponse response = new ConfirmEmailResponse();

		ConfirmEmailValidator validator = new ConfirmEmailValidator();
		validator.validate(request, response);
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		String email = request.getEmail();
		String oldemail = request.getOldEmail();
		String inputSessionId = request.getConfirmSessionId();
		Integer inputCode = request.getConfirmCode();
		
		Account account;
		try {
			account = dalservice.getAccountProfile(oldemail);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		_confirmEmail(response, account, oldemail, email, inputSessionId, inputCode, true);
		
		if(response.hasErrors()) {
			return response;
		}
		
		account.setEmail(email);
		try {
			dalservice.updateEmail(oldemail, account);
		} catch (DBConnectionException | UpdateException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CONFIRM_EMAIL, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
	
	private void _confirmEmail(BaseResponse response, Account account,
			String oldemail, String email, String inputSessionId,
			Integer inputCode, boolean updateACStatus) {
		
		Long accountId = account.getId();
		
		AccountConfirmation accountConf;
		try {
			accountConf = dalservice.getAccountConfirmation(accountId, email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			accountConf = null;
		}
		
		if(VUTIL.isNull(accountConf)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return;
		}
		
		//Now increment the attempt count.
		accountConf.incrementAttempts();
		
		if(accountConf.getConfirmationAttempts() > MAX_EMAIL_CONFIRM_RETRY_ATTEMPTS) {
			response.addFieldBindingError(Errors.REGISTRATION.MAX_ACCOUNT_RECONFIRM_ATTEMPTS_REACHED, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			try {
				dalservice.updateAccountStatus(account.getEmail(), AccountStatusEnum.SUSPENDED);
				dalservice.updateConfirmationAttempts(accountId,
						accountConf.getId(), accountConf.getConfirmationAttempts());
				account.setAccountStatus(AccountStatusEnum.SUSPENDED);
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			return;
		}
		
		String dbSessionId = accountConf.getConfirmSessionId();
		Integer dbCode = accountConf.getConfirmCode();
		if( (!dbSessionId.equals(inputSessionId)) || 
				(!dbCode.equals(inputCode)) ) {
			
			//don't set the account to the response
			response.addFieldBindingError(Errors.REGISTRATION.UNAUTHORIZED_ACCOUNT_CONFIRMATION, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			
			try {
				dalservice.updateConfirmationAttempts(accountId,
						accountConf.getId(), accountConf.getConfirmationAttempts());
			} catch (DBConnectionException | UpdateException
					| DatabaseException e) {
				logger.debug(e.getMessage(), e);
			}
			
			return;
		}
		
		if(! updateACStatus) {
			return;
		}
		
		try {
			dalservice.updateAccountConfirmationStatus(accountConf.getId(), AccountConfirmationStatusEnum.VERIFIED);
		} catch (DBConnectionException | UpdateException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.REGISTRATION.UNABLE_TO_CONFIRM_EMAIL, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return;
		}
	}


	@POST
	@Path("/processForgotPasswordEmail")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public ProcessForgotPasswordEmailResponse processForgotPasswordEmail(
			ProcessForgotPasswordEmailRequest request) {
		
		ProcessForgotPasswordEmailResponse response = new ProcessForgotPasswordEmailResponse();
		String email = request.getEmail();
		
		ForgotPasswordEmailValidator validator = new ForgotPasswordEmailValidator();
		validator.validate(request, response);
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.setEmail(email);
			return response;
		}
		
		response.setEmail(email);
		
		Account account = null;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		ForgotPassword fp = new ForgotPassword();
		fp.setEmail(request.getEmail());
		fp.setAccountId(account.getId());
		fp.setIpAddress(request.getIpAddress());
		fp.setStatus(ForgotPasswordStatusEnum.ATTEMPTED);
		
		insertForgotPassword(fp);
		
		AccountConfirmation ac = prepareAccountConfirmation(email);
		account.setAccountConf(ac);
		
		boolean inserted = false;
		try {
			inserted = dalservice.insertAccountConfirmation(account.getId(), ac);
		} catch (DBConnectionException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			inserted = false;
		}
		
		if(! inserted) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			return response;
		}
		
		Locale locale = getLocale();
		String baseurl = getBaseUrl();
		
		try { 
			RegistrationEmailSender.getInstance().sendForgotPasswordEmail(
					account, baseurl, locale);
		} catch (Exception e) {
			//block if email fails
			logger.debug(e.getMessage(), e);
			response.addFieldBindingError(Errors.SYSTEM.INTERNAL_ERROR, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}


	@POST
	@Path("/confirmForgotPasswordEmail")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public ConfirmForgotPasswordEmailResponse confirmForgotPasswordEmail(
			ConfirmForgotPasswordEmailRequest request) {
		
		ConfirmForgotPasswordEmailResponse response = new ConfirmForgotPasswordEmailResponse();
		
		ConfirmForgotPasswordEmailValidator validator = 
				new ConfirmForgotPasswordEmailValidator();
		validator.validate(request, response);
		
		String email = request.getEmail();
		response.setEmail(email);
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		String inputSessionId = request.getConfirmSessionId();
		Integer inputCode = request.getConfirmCode();
		
		Account account;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		_confirmEmail(response, account, email, email, inputSessionId, inputCode, false);
		
		if(response.hasErrors()) {
			return response;
		}
		
		MsgSource msgsrc = new MsgSource();
		try {
			List<AccountSecurityQuestion> questions = dalservice.getAccountSecurityQuestions(email);
			if(!VUTIL.isEmptyList(questions)) {
				for(AccountSecurityQuestion question : questions) {
					if(VUTIL.isNotNull(question)) {
						question.setAnswer(null);
						populateSecQuestionDisplayName(msgsrc, question);
					}
				}
				response.setQuestions(questions);
			}
		} catch (FinderException | DBConnectionException | DatabaseException e) {
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		return response;
		
	}


	private void populateSecQuestionDisplayName(MsgSource msgsrc,
			AccountSecurityQuestion question) {
		if (VUTIL.isNull(question.getQuestion())
				|| VUTIL.isNull(question.getQuestion().getQuestionCode())) {
			return;
		}
		
		SecurityQuestionCodeEnum scode = question.getQuestion().getQuestionCode();
		String localizedDisplayName = msgsrc.getMessage(scode.getCode(), null, getLocale());
		question.getQuestion().setQuestionDisplayName(localizedDisplayName);
	}


	@POST
	@Path("/validateSecurityQuestions")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public ValidateSecurityQuestionsResponse validateSecurityQuestions(
			ValidateSecurityQuestionsRequest request) {
		
		ValidateSecurityQuestionsResponse response = 
				new ValidateSecurityQuestionsResponse();
		response.setEmail(request.getEmail());
		response.setConfirmCode(request.getConfirmCode());
		response.setConfirmSessionId(request.getConfirmSessionId());
		
		ForgotPasswordSecurityQuestionValidator validator = 
				new ForgotPasswordSecurityQuestionValidator();
		validator.validate(request, response);
		
		String email = request.getEmail();
		
		List<AccountSecurityQuestion> questions = request.getQuestions();
		
		if(response.hasErrors()) {
			prepareSecurityQuestionResponse(response, questions);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		String inputSessionId = request.getConfirmSessionId();
		Integer inputCode = request.getConfirmCode();
		
		Account account;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			logger.debug("Forgot Password Security Question Answer Attempt by email='"
					+ email + "'");
			logger.debug("Forgot Password Security Question Answer Attempt by IP ADDRESS='"
					+ request.getIpAddress() + "'");
			
			prepareSecurityQuestionResponse(response, questions);
			response.addFieldBindingError(Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null, (String[])null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		_confirmEmail(response, account, email, email, inputSessionId, inputCode, false);
		
		ForgotPassword fp = new ForgotPassword();
		fp.setAccountId(account.getId());
		fp.setEmail(email);
		fp.setIpAddress(request.getIpAddress());
		fp.setStatus(ForgotPasswordStatusEnum.SEC_QUES_FAILED);
		
		if(response.hasError(
				Errors.REGISTRATION.UNAUTHORIZED_ACCOUNT_CONFIRMATION.getErrorCode())) {
			insertForgotPassword(fp);
		}
		
		if(response.hasErrors()) {
			prepareSecurityQuestionResponse(response, questions);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		List<AccountSecurityQuestion> dbquestions = null;
		try {
			dbquestions = dalservice.getAccountSecurityQuestions(email);
		} catch (FinderException | DBConnectionException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			dbquestions = null;
		}
		
		if(VUTIL.isNull(dbquestions) || dbquestions.isEmpty()) {
			prepareSecurityQuestionResponse(response, questions);
			response.addFieldBindingError(
					Errors.REGISTRATION.UNABLE_TO_VERIFY_SECURITY_ANSWERS,
					null, (String[]) null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		insertForgotPassword(fp);
		
		validator.validateSecurityAnswers(response, account, dbquestions,
				request.getQuestions());
		if(response.hasErrors()) {
			prepareSecurityQuestionResponse(response, questions);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		prepareSecurityQuestionResponse(response, questions);
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}


	private void prepareSecurityQuestionResponse(
			ValidateSecurityQuestionsResponse response, 
			List<AccountSecurityQuestion> questions) {
		
		MsgSource msgsrc = new MsgSource();
		if(!VUTIL.isEmptyList(questions)) {
			for(AccountSecurityQuestion question : questions) {
				if(VUTIL.isNotNull(question)) {
					question.setAnswer(null);
					populateSecQuestionDisplayName(msgsrc, question);
				}
			}
			response.setQuestions(questions);
		}
	}


	private void insertForgotPassword(ForgotPassword fp) {
		try {
			dalservice.insertForgotPassword(fp);
		} catch (DBConnectionException | DatabaseException | InsertException e) {
			//do block for this here, just log id
			logger.info(fp.toString());
			logger.debug(e.getMessage(), e);
		}
	}

	
	@POST
	@Path("/changeForgotPassword")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Override
	public ChangeForgotPasswordResponse changeForgotPassword(
			ChangeForgotPasswordRequest request) {
		
		ChangeForgotPasswordResponse response = new ChangeForgotPasswordResponse();
		response.setEmail(request.getEmail());
		response.setConfirmCode(request.getConfirmCode());
		response.setConfirmSessionId(request.getConfirmSessionId());
		
		if (VUTIL.isNull(request)) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.COMMON.NULL_ARGUMENT_PASSED, null, (String[])null);
			return response;
		}
		
		if (VUTIL.isEmpty(request.getEmail())) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			response.addFieldBindingError(Errors.REGISTRATION.EMAIL_MISSING, null, (String[])null);
			return response;
		}
		
		String inputSessionId = request.getConfirmSessionId();
		Integer inputCode = request.getConfirmCode();
		String email = request.getEmail();
		
		Account account;
		try {
			account = dalservice.getAccountProfile(email);
		} catch (DBConnectionException | FinderException | DatabaseException e) {
			logger.debug(e.getMessage(), e);
			account = null;
		}
		
		if(VUTIL.isNull(account)) {
			response.addFieldBindingError(
					Errors.REGISTRATION.ACCOUNT_NOT_FOUND, null,
					(String[]) null);
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		_confirmEmail(response, account, email, email, inputSessionId, inputCode, true);
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		ChangePasswordRequest chpwdRequest = new ChangePasswordRequest();
		chpwdRequest.setEmail(request.getEmail());
		chpwdRequest.setNewPassword(request.getNewPassword());
		chpwdRequest.setConfirmPassword(request.getConfirmPassword());
		chpwdRequest.setForgotPasswordUsecase(true);
		
		ChangePasswordResponse chpwdresponse = changePassword(chpwdRequest);
		response.addFieldBindingErrors(chpwdresponse.getFieldBindingErrors());
		
		if(response.hasErrors()) {
			response.setStatus(ResponseAckStatusEnum.FAILURE);
			return response;
		}
		
		response.setStatus(ResponseAckStatusEnum.SUCCESS);
		return response;
	}
}
