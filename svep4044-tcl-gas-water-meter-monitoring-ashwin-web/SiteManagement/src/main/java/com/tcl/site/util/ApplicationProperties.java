/*
 * @category PropertyManagement
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.site.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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

	public String getSiteRegistrationEmailTemplate() {
		return env.getProperty("EmailTemplate.SiteRegistrationTemplate");
	}

	public String getSiteRegistrationEmailSubject() {
		return env.getProperty("EmailData.SiteRegistrationSubject");
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
