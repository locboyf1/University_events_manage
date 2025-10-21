package com.event.university.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
		return factory -> {
			factory.addConnectorCustomizers((Connector connector) -> {

				connector.setMaxPartCount(50);

			});
		};
	}
}