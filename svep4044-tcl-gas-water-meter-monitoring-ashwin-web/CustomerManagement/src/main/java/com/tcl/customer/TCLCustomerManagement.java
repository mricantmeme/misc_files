/*
 * @category Customer Management
 * @copyright Copyright (C) 2018 Contus. All rights reserved.
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package com.tcl.customer;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableSwagger2
@ComponentScan
@EnableZuulProxy
/*
 * This class acts as the main class, where the application starts. It contains
 * the main method. Every time this application/micro-service starts, this class
 * gets executed.
 */
public class TCLCustomerManagement {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TCLCustomerManagement.class, args);
	}

	/*
	 * This method defines the bean that is used for mapping the objects of two
	 * different classes.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*
	 * This method defines the bean that is used for creating and handling the HTML
	 * templates that needs to be sent in the mail responses.
	 */
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader." + "ClasspathResourceLoader");
		factory.setVelocityProperties(props);

		return factory.createVelocityEngine();
	}
}
