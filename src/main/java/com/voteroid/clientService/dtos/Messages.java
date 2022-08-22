package com.voteroid.clientService.dtos;

public interface Messages {
	
	interface Exceptions{
		public static final String PHONE_EMAIL_ALREADY_EXIST = "OOPS!! It seems you are already registered with this Phone No or Email Id or Domain Name";
		public static final String NO_CLIENT_information_RECIEVED = "No Client Information Recieved!!!";
		public static final String NO_CLIENT_NAME_RECIEVED = "No Client Name Recieved!!!";
		public static final String NO_CLIENT_PHONE_NO_RECIEVED = "No Client Phone Number Recieved!!!";
		public static final String NO_CLIENT_EMAIL_ID_RECIEVED = "No Client Email Id Recieved!!!";
		public static final String NO_CLIENT_PASSWORD_RECIEVED = "No Client Password Recieved!!!";
		public static final String NO_DOMAIN_NAME_RECIEVED = "No Company Domain Name Recieved!!!";
		public static final String INVALID_TOKEN = "Invalid Access for this api id!!!";
	}
	
	interface loginErrors{
		public static final String INVALID_USER_ID_PASSWORD = "Invalid User Id or Password!!!";
	}
}
