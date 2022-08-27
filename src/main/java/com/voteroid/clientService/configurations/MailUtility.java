package com.voteroid.clientService.configurations;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.voteroid.clientService.dtos.Mail;

@Component
public class MailUtility {

	@Autowired
    private JavaMailSender emailSender;
	
	public void sendEMail(Mail mail) throws MessagingException,AddressException {
	            MimeMessage message = emailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message,
	                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	                    StandardCharsets.UTF_8.name());

	            helper.setTo(mail.getTo());
	            helper.setText(mail.getBody(), true);
	            helper.setSubject(mail.getSubject());
	            helper.setFrom("voteroid@gmail.com");

	            emailSender.send(message); 
	}
}
