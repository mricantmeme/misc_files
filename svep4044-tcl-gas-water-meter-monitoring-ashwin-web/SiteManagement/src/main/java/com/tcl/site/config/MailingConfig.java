/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tcl.site.util.ApplicationProperties;

import java.util.Properties;

@Configuration
public class MailingConfig {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Value("${mail.smtp.port}")
	private Integer port;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setProtocol(applicationProperties.getSmtpProtocol());
		javaMailSender.setHost(applicationProperties.getSmtpHost());
		javaMailSender.setPort(port);
		javaMailSender.setUsername(applicationProperties.getMailUserName());
		javaMailSender.setPassword(applicationProperties.getMailPassword());

		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "false");
		return properties;
	}

}
