package com.vendertool.registration;

import com.vendertool.common.service.IVenderToolService;
import com.vendertool.sharedtypes.rnr.AuthorizeMarketRequest;
import com.vendertool.sharedtypes.rnr.AuthorizeMarketResponse;
import com.vendertool.sharedtypes.rnr.ChangeEmailRequest;
import com.vendertool.sharedtypes.rnr.ChangeEmailResponse;
import com.vendertool.sharedtypes.rnr.ChangePasswordRequest;
import com.vendertool.sharedtypes.rnr.ChangePasswordResponse;
import com.vendertool.sharedtypes.rnr.CloseAccountRequest;
import com.vendertool.sharedtypes.rnr.CloseAccountResponse;
import com.vendertool.sharedtypes.rnr.ConfirmEmailRequest;
import com.vendertool.sharedtypes.rnr.ConfirmEmailResponse;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationRequest;
import com.vendertool.sharedtypes.rnr.ConfirmRegistrationResponse;
import com.vendertool.sharedtypes.rnr.GetAccountPasswordResponse;
import com.vendertool.sharedtypes.rnr.GetAccountResponse;
import com.vendertool.sharedtypes.rnr.GetAccountSecurityQuestionsResponse;
import com.vendertool.sharedtypes.rnr.LinkOtherSiteRequest;
import com.vendertool.sharedtypes.rnr.LinkOtherSiteResponse;
import com.vendertool.sharedtypes.rnr.RegisterAccountRequest;
import com.vendertool.sharedtypes.rnr.RegisterAccountResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountResponse;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.UpdateAccountSecurityQuestionsResponse;


public interface IRegistrationService extends IVenderToolService {

	/**
	 * HTTP POST call to register an account
	 * 
	 * @param request
	 * @return
	 */
	public RegisterAccountResponse registerAccount(RegisterAccountRequest request);
	
	/**
	 * HTTP POST call to confirm registration
	 * 
	 * @param request
	 * @return
	 */
	public ConfirmRegistrationResponse confirmRegistration(ConfirmRegistrationRequest request);
	
	/**
	 * HTTP GET call to get the account details
	 * 
	 * @param request
	 * @return
	 */
	public GetAccountResponse getAccount(String email);
	
	/**
	 * HTTP GET call to get the account password
	 * 
	 * @param request
	 * @return
	 */
	public GetAccountPasswordResponse getAccountPassword(String email);
	
	/**
	 * HTTP POST to update the account details/profile
	 * 
	 * @param request
	 * @return
	 */
	public UpdateAccountResponse updateAccount(UpdateAccountRequest request);
	
	/**
	 * HTTP POST to change the password
	 * 
	 * @param request
	 * @return
	 */
	public ChangePasswordResponse changePassword(ChangePasswordRequest request);
	
	/**
	 * HTTP POST to change the email / username
	 * 
	 * @param request
	 * @return
	 */
	public ChangeEmailResponse changeEmail(ChangeEmailRequest request);
	
	/**
	 * HTTP POST call to close the account
	 * 
	 * @param request
	 * @return
	 */
	public CloseAccountResponse closeAccount(CloseAccountRequest request);
	
	/**
	 * HTTP POST to authorize market resources
	 * 
	 * @param request
	 * @return
	 */
	public AuthorizeMarketResponse authorizeMarket(AuthorizeMarketRequest request);
	
	/**
	 * HTTP POST to link other sites like FaceBook, Google, etc.
	 * 
	 * @param request
	 * @return
	 */
	public LinkOtherSiteResponse linkOtherSite(LinkOtherSiteRequest request);
	
	/**
	 * HTTP POST to update security questions.
	 * 
	 * @param request
	 * @return
	 */
	public UpdateAccountSecurityQuestionsResponse updateSecurityQuestions(
			UpdateAccountSecurityQuestionsRequest request);
	
	/**
	 * HTTP GET call to get the account security details
	 * 
	 * @param email
	 * @return
	 */
	public GetAccountSecurityQuestionsResponse getSecurityQuestions(String email);
	
	/**
	 * HTTP POST to confirm the email.
	 * 
	 * @param request
	 * @return
	 */
	public ConfirmEmailResponse confirmEmail(ConfirmEmailRequest request);
}
