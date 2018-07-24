/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tcl.customer.util.ApplicationProperties;

import java.util.Properties;

@Configuration
public class MailingConfig {

	/*
	 * dependency injection and object initializtion using Autowired annotation
	 */
	@Autowired
	private ApplicationProperties applicationProperties;

	@Value("${mail.smtp.port}")
	private Integer port;

	/*
	 * This method defines the bean that is used for defining the configurations
	 * that can be added to the java mail sender definitions.
	 */
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

	/*
	 * This method gets the properties of the email that needs to be sent. The
	 * details are injected inside a property object and is sent via the response.
	 */
	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.debug", "false");
		return properties;
	}

}
