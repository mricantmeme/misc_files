/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.service;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tcl.site.util.ApplicationProperties;
import com.tcl.site.util.EmailTemplateType;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Async
	public <T> boolean sendEmail(T emailTemplateDto, String toEmailAddress, String CcAddresses,
			EmailTemplateType emailTemplateType) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		// MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false,
		// "utf-8");
		try {
			MimeMessageHelper helper = addTemplateDetails(new MimeMessageHelper(mimeMessage, false, "utf-8"),
					emailTemplateDto, emailTemplateType, toEmailAddress, CcAddresses);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		javaMailSender.send(mimeMessage);
		return true;
	}

	private <T> MimeMessageHelper addTemplateDetails(MimeMessageHelper helper, T emailTemplateDto,
			EmailTemplateType emailTemplateType, String toAddress, String ccAddress) throws MessagingException {
		// HashMap<String, String> emailContent = new HashMap<String, String>();

		Template template = null;
		VelocityContext velocityContext = new VelocityContext();
		StringWriter stringWriter = new StringWriter();
		switch (emailTemplateType) {
		case SITE_REGISTRATION_EMAIL_TEMPLATE:
			helper.setTo(toAddress);
			helper.setSubject(applicationProperties.getSiteRegistrationEmailSubject());
			template = velocityEngine
					.getTemplate("templates/" + applicationProperties.getSiteRegistrationEmailTemplate());
			// lstValue.set(1,properties.getMailConfirmationSubject());
			// emailContent.put("subject",
			// applicationProperties.getForgotPasswordEmailSubject());
			velocityContext.put("context", emailTemplateDto);
			template.merge(velocityContext, stringWriter);
			break;

		}

		helper.setText(stringWriter.toString(), true);
		return helper;

	}

}