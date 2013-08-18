package com.vendertool.registration.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.vendertool.common.email.EmailDataModel;
import com.vendertool.common.email.VenderToolEmailService;

public class RegistrationEmailService extends VenderToolEmailService {
	private static final Logger logger = Logger.getLogger(RegistrationEmailService.class);

	@Override
	public void sendEmail(EmailDataModel _dataModel) {
		if((_dataModel == null) || (!(_dataModel instanceof RegistrationEmailDataModel))) {
			return;
		}
		final RegistrationEmailDataModel dataModel = (RegistrationEmailDataModel)_dataModel;
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(dataModel.getToEmail());
				message.setSubject(dataModel.getSubject());
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("emailData", dataModel);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						getVelocityEngine(), "emailtemplates/Registration.vm",
						model);
				message.setText(text, true);
			}
		};
		this.getMailSender().send(preparator);
		logger.info("Registration email sent to '" + dataModel.getToEmail() + "'");
	}

}
