package com.vendertool.registration.email;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.vendertool.common.MsgSource;
import com.vendertool.common.SpringApplicationContextUtils;
import com.vendertool.common.URLConstants;
import com.vendertool.common.email.EmailService;
import com.vendertool.common.validation.ValidationUtil;
import com.vendertool.sharedtypes.core.Account;

public class RegistrationEmailSender {
	private static final String CONTACT_EMAIL = "vendertool@gmail.com";
	
	private static final String PROPERTY_COMPANY_NAME = "email.company.name";
	private static final String PROPERTY_CONFIRM_REG_SUBJECT = "email.registration.confirmreg.subject";
	private static final String PROPERTY_REG_COMPLETE_SUBJECT = "email.registration.regcomplete.subject";
	private static final String PROPERTY_CHANGE_EMAIL_SUBJECT = "email.registration.changeemail.subject";
	private static final String PROPERTY_CHANGE_PASSWORD_SUBJECT = "email.registration.changepassword.subject";
	private static final String PROPERTY_FORGOT_PWD_EMAIL_SUBJECT = "email.registration.confirmforgotpwdemail.subject";
	
	private static ValidationUtil validationUtil = ValidationUtil.getInstance();
	private static final Logger logger = Logger.getLogger(RegistrationEmailSender.class);
	
	private static class RegistrationEmailHelperHolder {
		private static final RegistrationEmailSender INSTANCE = new RegistrationEmailSender();
	}
	
	private RegistrationEmailSender() {
	}
	
	public static RegistrationEmailSender getInstance() {
		return RegistrationEmailHelperHolder.INSTANCE;
	}
	
	
	public void sendConfirmRegistrationEmail(Account account, String baseurl, Locale locale) {
		
		if (!isValid(account, baseurl)) {
			
			logger.debug("NO CONFIRM REG EMAIL SENT: 'Account' or 'base url' or 'account email' is null " +
					"while sending the confirm registration email");
			return;
		}
		
		ConfirmRegistrationEmailDataModel emailModel = getConfirmEmailDataModel(
				account, baseurl, locale, URLConstants.CONFIRM_ACCOUNT_PATH,
				false, PROPERTY_CONFIRM_REG_SUBJECT);
    	
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	EmailService emailService = (EmailService) ctx.getBean("confirmRegistrationEmailService");
    	emailService.sendEmail(emailModel, locale);
	}

	private ConfirmRegistrationEmailDataModel getConfirmEmailDataModel(
			Account account, String baseurl, Locale locale, String commandName,
			boolean changeEmailUsecase, String subject) {
		
		MsgSource msgSource = new MsgSource();
		
		ConfirmRegistrationEmailDataModel emailModel = new ConfirmRegistrationEmailDataModel();
		String email = (changeEmailUsecase) ? 
				account.getAccountConf().getEmail() : account.getEmail();
		
    	emailModel.setToEmail(email);
		emailModel.setToName(account.getContactDetails().getFirstName());
		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(subject, null,
				locale));
    	
    	String sessiontoken = account.getAccountConf().getConfirmSessionId();
    	Integer confCode = account.getAccountConf().getConfirmCode();
    	
    	StringBuffer confirmRegUrl = new StringBuffer();
			confirmRegUrl
				.append(baseurl).append(URLConstants.WEB_APP_PATH)
				.append(commandName)
				.append(URLConstants.QUERY_PARAM_SEPARATOR).append("email")
				.append(URLConstants.VALUE_SEPARATOR).append(email)
				.append(URLConstants.FIELD_SEPARATOR).append("sessiontoken")
				.append(URLConstants.VALUE_SEPARATOR).append(sessiontoken)
				.append(URLConstants.FIELD_SEPARATOR).append("confirmationcode")
				.append(URLConstants.VALUE_SEPARATOR).append(confCode);
			
		if(changeEmailUsecase) {
			confirmRegUrl
				.append(URLConstants.FIELD_SEPARATOR).append("oldemail")
				.append(URLConstants.VALUE_SEPARATOR).append(account.getEmail());
		}
		
//		String encodedConfirmRegUrl = null;
//		
//		try {
//			encodedConfirmRegUrl = URLEncoder.encode(confirmRegUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			logger.debug("UNABLE TO SEND EMAIL TO: '" + email + "'. Error msg: " + e.getMessage(), e);
//			return;
//		}
    	
    	emailModel.setConfirmationUrl(confirmRegUrl.toString());
		return emailModel;
	}

	
	private boolean isValid(Account account, String baseurl) {
		return validationUtil.isNotNull(account) && validationUtil.isNotNull(baseurl)
				&& (!validationUtil.isEmpty(baseurl))
				&& validationUtil.isNotNull(account.getEmail());
	}
	
	
	public void sendRegistrationCompleteEmail(Account account, String baseurl, Locale locale) {
		if (!isValid(account, baseurl)) {
			
			logger.debug("NO REG COMPLETE EMAIL SENT: 'Account' or 'base url' or 'account email' is null " +
					"while sending the registration complete email");
			return;
		}
		
		ContactUsEmailDataModel emailModel = new ContactUsEmailDataModel();
    	emailModel.setToEmail(account.getEmail());
    	emailModel.setContactusEmail(CONTACT_EMAIL);
		if (validationUtil.isNotNull(account.getContactDetails())
				&& (validationUtil.isNotNull(account.getContactDetails().getFirstName()))) {
			emailModel.setToName(account.getContactDetails().getFirstName());
		} else {
			emailModel.setToName(account.getEmail());
		}
		Object[] params = new Object[]{CONTACT_EMAIL};
		emailModel.setMsgParams(params);
		
		MsgSource msgSource = new MsgSource();
		
		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(PROPERTY_REG_COMPLETE_SUBJECT, null,
				locale));
		
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	EmailService emailService = (EmailService) ctx.getBean("registrationCompleteEmailService");
    	emailService.sendEmail(emailModel, locale);
	}
	
	
	public void sendAccountEmailChangeEmail(Account account, String oldemail, String baseurl, Locale locale) {
		if (!isValid(account, baseurl)) {
			logger.debug("Invalid input passed while attempting to send " +
					"email for the the change account email case");
			return;
		}
		
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	
    	
    	//send to new email
    	ConfirmRegistrationEmailDataModel confirmEmailModel = getConfirmEmailDataModel(
				account, baseurl, locale, URLConstants.CONFIRM_EMAIL_PATH, true, PROPERTY_CHANGE_EMAIL_SUBJECT);
    	EmailService confirmEmailService = (EmailService) ctx.getBean("confirmEmailService");
    	confirmEmailService.sendEmail(confirmEmailModel, locale);
    	
    	
    	
    	//Now send to the old email
		ChangeEmailDataModel emailModel = new ChangeEmailDataModel();
    	emailModel.setToEmail(account.getEmail());
    	boolean fnExists = validationUtil.isNotNull(account.getContactDetails())
				&& (validationUtil.isNotNull(account.getContactDetails().getFirstName()));
    	
		if (fnExists) {
			emailModel.setToName(account.getContactDetails().getFirstName());
		} else {
			emailModel.setToName(account.getEmail());
		}
		
		Object[] params = new Object[]{account.getAccountConf().getEmail(), CONTACT_EMAIL};
		emailModel.setMsgParams(params);
		
		MsgSource msgSource = new MsgSource();

		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(PROPERTY_CHANGE_EMAIL_SUBJECT, null,
				locale));

    	EmailService oldEmailService = (EmailService) ctx.getBean("changeEmailService");
    	emailModel.setToEmail(oldemail);
    	if(!fnExists) {
    		emailModel.setToName(oldemail);
    	}

    	oldEmailService.sendEmail(emailModel, locale);
	}
	
	
	public void sendPasswordChangeEmail(Account account, String baseurl, Locale locale) {
		if (!isValid(account, baseurl)) {
			logger.debug("Invalid input passed while attempting to send " +
					"email for the the change password case");
			return;
		}
		
		ContactUsEmailDataModel emailModel = new ContactUsEmailDataModel();
    	emailModel.setToEmail(account.getEmail());
    	emailModel.setContactusEmail(CONTACT_EMAIL);
    	boolean fnExists = validationUtil.isNotNull(account.getContactDetails())
				&& (validationUtil.isNotNull(account.getContactDetails().getFirstName()));
    	
		if (fnExists) {
			emailModel.setToName(account.getContactDetails().getFirstName());
		} else {
			emailModel.setToName(account.getEmail());
		}
		
		Object[] params = new Object[]{CONTACT_EMAIL};
		emailModel.setMsgParams(params);
		
		MsgSource msgSource = new MsgSource();
		
		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(PROPERTY_CHANGE_PASSWORD_SUBJECT, null,
				locale));
		
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	
    	EmailService emailService = (EmailService) ctx.getBean("passwordChangeEmailService");
    	emailService.sendEmail(emailModel, locale);
	}

	public void sendForgotPasswordEmail(Account account, String baseurl,
			Locale locale) {
		if (!isValid(account, baseurl)) {
			logger.debug("Invalid input passed while attempting to send " +
					"email for the the change password case");
			return;
		}
		
		ConfirmRegistrationEmailDataModel emailModel = getConfirmEmailDataModel(
				account, baseurl, locale,
				URLConstants.CONFIRM_FORGOT_PASSWORD_EMAIL_PATH, false,
				PROPERTY_FORGOT_PWD_EMAIL_SUBJECT);
		
		Object[] params = new Object[]{CONTACT_EMAIL};
		emailModel.setMsgParams(params);
		
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	
    	EmailService emailService = (EmailService) ctx.getBean("confirmForgotPasswordEmailService");
    	emailService.sendEmail(emailModel, locale);
	}
}
