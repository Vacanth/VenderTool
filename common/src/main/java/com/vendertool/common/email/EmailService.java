package com.vendertool.common.email;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.vendertool.common.MsgSource;
import com.vendertool.common.validation.ValidationUtil;

public class EmailService {
	public static final boolean IS_HTML = true;
	private static final Logger logger = Logger.getLogger(EmailService.class);
	private static ValidationUtil validationUtil = ValidationUtil.getInstance();
	private static String UTF8 = "UTF-8";
	
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private String templateLocation;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public String getTemplateLocation() {
		return templateLocation;
	}

	public void setTemplateLocation(String templateLocation) {
		this.templateLocation = templateLocation;
	}

	public void sendEmail(final EmailDataModel dataModel, final Locale locale) {
		if(validationUtil.isNull(dataModel)) {
			logger.debug("email data model is null");
			return;
		}
		
		final MsgSource msgSource = new MsgSource();
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(dataModel.getToEmail());
				message.setSubject(dataModel.getSubject());
				
				Map<String, Object> modelMap = new HashMap<String, Object>();
				modelMap.put("emailData", dataModel);
				modelMap.put("msgSource", msgSource);
				modelMap.put("locale", locale);
				modelMap.put("msgparams", dataModel.getMsgParams());
				
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						getVelocityEngine(), templateLocation, UTF8,
						modelMap);
				message.setText(text, IS_HTML);
			}
		};
		this.getMailSender().send(preparator);
		logger.info("Email sent to '" + dataModel.getToEmail() + "' using template " + templateLocation);
	}
}
