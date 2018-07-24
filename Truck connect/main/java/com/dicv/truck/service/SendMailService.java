package com.dicv.truck.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender attachmentMailSender;

	private static Logger LOGGER = Logger.getLogger(SendMailService.class);

	public boolean sendMail(String from, String to, String subject, String msg) {
		boolean isMailSent = true;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);
		} catch (MailException e) {
			LOGGER.error("Exception in Sending Mail", e);
			isMailSent = false;
		}
		return isMailSent;

	}

	public boolean sendMail(String from, String[] to, String subject, String msg, String cc) {
		boolean isMailSent = true;
		try {
			MimeMessage mimeMessage = attachmentMailSender.createMimeMessage();
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
				helper.setText(msg, true);
				helper.setTo(to);
				if (cc != null) {
					helper.setCc(cc);
				}
				helper.setSubject(subject);
				helper.setFrom(from);
				attachmentMailSender.send(mimeMessage);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} catch (MailException e) {
			LOGGER.error("Exception in Sending Mail", e);
			isMailSent = false;
		}
		return isMailSent;

	}

}
