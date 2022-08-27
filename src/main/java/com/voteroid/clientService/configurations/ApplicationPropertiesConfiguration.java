package com.voteroid.clientService.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("client-service")
public class ApplicationPropertiesConfiguration {

	private String fromMailingAddress;
	private int passwordResetTokenValidity;
	private String mailingPassword;
	
	public String getFromMailingAddress() {
		return fromMailingAddress;
	}
	public void setFromMailingAddress(String fromMailingAddress) {
		this.fromMailingAddress = fromMailingAddress;
	}
	public int getPasswordResetTokenValidity() {
		return passwordResetTokenValidity;
	}
	public void setPasswordResetTokenValidity(int passwordResetTokenValidity) {
		this.passwordResetTokenValidity = passwordResetTokenValidity;
	}
	public String getMailingPassword() {
		return mailingPassword;
	}
	public void setMailingPassword(String mailingPassword) {
		this.mailingPassword = mailingPassword;
	}
	
	
}
