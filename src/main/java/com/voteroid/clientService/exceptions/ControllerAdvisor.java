package com.voteroid.clientService.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.voteroid.clientService.dtos.Constants;
import com.voteroid.clientService.dtos.ExceptionFieldsDTO;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoClientInformationRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientInformationRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.NO_CLIENT_information_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientNameRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientNameRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.NO_CLIENT_NAME_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientPhoneNoRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientPhoneNoRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.NO_CLIENT_PHONE_NO_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientEmailIdRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientEmailIdRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.NO_CLIENT_EMAIL_ID_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoClientPasswordRecieved.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(NoClientPasswordRecieved ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.NO_CLIENT_PASSWORD_RECIEVED);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PhoneNoOrEmailIdAlreadyExist.class)
	public ResponseEntity<Object> handleNoClientNameRecieved(PhoneNoOrEmailIdAlreadyExist ex) {
		ExceptionFieldsDTO body = new ExceptionFieldsDTO(LocalDateTime.now(),Constants.Exceptions.PHONE_EMAIL_ALREADY_EXIST);
        return new ResponseEntity<>(body, HttpStatus.ALREADY_REPORTED);
	}
}
