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
		
		MsgSource msgSource = new MsgSource();
		
    	ConfirmRegistrationEmailDataModel emailModel = new ConfirmRegistrationEmailDataModel();
    	emailModel.setToEmail(account.getEmail());
		if (validationUtil.isNotNull(account.getContactDetails())
				&& (validationUtil.isNotNull(account.getContactDetails().getFirstName()))) {
			emailModel.setToName(account.getContactDetails().getFirstName());
		} else {
			emailModel.setToName(account.getEmail());
		}
		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(PROPERTY_CONFIRM_REG_SUBJECT, null,
				locale));
    	
    	String email = account.getEmail();
    	String sessiontoken = account.getAccountConf().getConfirmSessionId();
    	Integer confCode = account.getAccountConf().getConfirmCode();
    	
		String confirmRegUrl = baseurl + URLConstants.WEB_APP_PATH
				+ URLConstants.CONFIRM_ACCOUNT_PATH
				+ URLConstants.QUERY_PARAM_SEPARATOR + "email"
				+ URLConstants.VALUE_SEPARATOR + email
				+ URLConstants.FIELD_SEPARATOR + "sessiontoken"
				+ URLConstants.VALUE_SEPARATOR + sessiontoken
				+ URLConstants.FIELD_SEPARATOR + "confirmationcode"
				+ URLConstants.VALUE_SEPARATOR + confCode;
		
//		String encodedConfirmRegUrl = null;
//		
//		try {
//			encodedConfirmRegUrl = URLEncoder.encode(confirmRegUrl, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			logger.debug("UNABLE TO SEND EMAIL TO: '" + email + "'. Error msg: " + e.getMessage(), e);
//			return;
//		}
    	
    	emailModel.setConfirmationUrl(confirmRegUrl);
    	
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	EmailService emailService = (EmailService) ctx.getBean("confirmRegistrationEmailService");
    	emailService.sendEmail(emailModel, locale);
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
		
		//sendToNewEmail
		ChangeEmailDataModel emailModel = new ChangeEmailDataModel();
    	emailModel.setToEmail(account.getEmail());
    	boolean fnExists = validationUtil.isNotNull(account.getContactDetails())
				&& (validationUtil.isNotNull(account.getContactDetails().getFirstName()));
    	
		if (fnExists) {
			emailModel.setToName(account.getContactDetails().getFirstName());
		} else {
			emailModel.setToName(account.getEmail());
		}
		
		Object[] params = new Object[]{account.getEmail(), CONTACT_EMAIL};
		emailModel.setMsgParams(params);
		
		MsgSource msgSource = new MsgSource();

		emailModel.setFromName(msgSource.getMessage(PROPERTY_COMPANY_NAME, null,
				locale));
		emailModel.setSubject(msgSource.getMessage(PROPERTY_CHANGE_EMAIL_SUBJECT, null,
				locale));
		emailModel.setContactusEmail(CONTACT_EMAIL);
		emailModel.setPreviousEmail(account.getEmail());
		
    	ApplicationContext ctx = SpringApplicationContextUtils.getApplicationContext();
    	
    	//send to new email
    	sendConfirmRegistrationEmail(account, baseurl, locale);
//    	EmailService emailService = (EmailService) ctx.getBean("changeEmailService");
//    	emailService.sendEmail(emailModel, locale);
    	
    	
    	//Now send to the old email
    	EmailService oldEmailService = (EmailService) ctx.getBean("changeEmailService");
    	emailModel.setToEmail(oldemail);
    	if(!fnExists) {
    		emailModel.setToName(oldemail);
    	}
    	emailModel.setPreviousEmail(account.getEmail());
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
}
