package com.voteroid.clientService.configurations;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ClientCofigurations {
	
	@Autowired
	ApplicationPropertiesConfiguration configuration;

	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select().apis(RequestHandlerSelectors.basePackage("com.voteroid.clientService.controllers"))
	                .paths(PathSelectors.any())
	                .build();

	    } 
	 
	 @Bean
	    public JavaMailSender javaMailSender() {
		  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	      mailSender.setHost("smtp.gmail.com");
	      mailSender.setPort(587);
	      mailSender.setUsername(configuration.getFromMailingAddress());
	      mailSender.setPassword(configuration.getMailingPassword());
	      Properties props = mailSender.getJavaMailProperties();
	      props.put("mail.transport.protocol", "smtp");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.debug", "true");
	        
	      return mailSender;
	    }
	 
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder()
	 {
	     return new BCryptPasswordEncoder();
	 }
}
