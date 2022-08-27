package com.voteroid.clientService.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.voteroid.clientService.dtos.Messages;
import com.voteroid.clientService.dtos.ExceptionFieldsDTO;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoClientInformationRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientInformationRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_information_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientNameRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientNameRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_NAME_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientPhoneNoRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientPhoneNoRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_PHONE_NO_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientEmailIdRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientEmailIdRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_EMAIL_ID_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientPasswordRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientPasswordRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_PASSWORD_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientCountryRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientCountryRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_CLIENT_COUNTRY_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PhoneNoOrEmailIdAlreadyExist.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(PhoneNoOrEmailIdAlreadyExist ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.PHONE_EMAIL_ALREADY_EXIST);
        return new ResponseEntity<>(body, HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(NoCompanyDomainNameRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoCompanyDomainNameRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.NO_DOMAIN_NAME_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAccess.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(InvalidAccess ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.INVALID_TOKEN);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MailNotSent.class)
	public ResponseEntity<Object> handleNoMailSent(MailNotSent ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.MAIL_NOT_SENT);
        return new ResponseEntity<>(body, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(InvalidClientId.class)
	public ResponseEntity<Object> handleInvalidClientId(InvalidClientId ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.INVALID_CLIENT_ID);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(InvalidPassword.class)
	public ResponseEntity<Object> handleInvalidPassword(InvalidPassword ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.INVALID_PASSWORD);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(VerificationPending.class)
	public ResponseEntity<Object> handleVerificationPending(VerificationPending ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Messages.Exceptions.VERIFICATION_PENDING);
        return new ResponseEntity<>(body, HttpStatus.PRECONDITION_REQUIRED);
	}
}
