/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/*
 * This is a generic class, that acts as a helper to other classes inside the micro service.
 * It is used to get the global variables from the application.properties file from the class path. 
 */
@Configuration
public class ApplicationProperties {

	@Autowired
	private Environment env;

	public String getImageConfig() {
		return env.getProperty("image.config");
	}

	public String getImageResourcePath() {
		return env.getProperty("image.resource.path");
	}

	public String getCustomerRegistrationEmailTemplate() {
		return env.getProperty("EmailTemplate.CustomerRegistrationTemplate");
	}

	public String getCustomerRegistrationEmailSubject() {
		return env.getProperty("EmailData.CustomerRegistrationSubject");
	}

	public String getSmtpProtocol() {
		return env.getProperty("mail.protocol");
	}

	public String getSmtpHost() {
		return env.getProperty("mail.smtp.host");
	}

	public String getMailUserName() {
		return env.getProperty("mail.support.username");
	}

	public String getMailPassword() {
		return env.getProperty("mail.support.password");
	}
}
