package com.voteroid.clientService.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.configurations.ApplicationPropertiesConfiguration;
import com.voteroid.clientService.configurations.MailUtility;
import com.voteroid.clientService.dtos.ClientInfoDTO;
import com.voteroid.clientService.dtos.Constants;
import com.voteroid.clientService.dtos.Mail;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.dtos.Response;
import com.voteroid.clientService.entities.ClientTbl;
import com.voteroid.clientService.entities.PasswordResetTokenTbl;
import com.voteroid.clientService.exceptions.InvalidAccess;
import com.voteroid.clientService.exceptions.MailNotSent;
import com.voteroid.clientService.exceptions.NoClientCountryRecieved;
import com.voteroid.clientService.exceptions.NoClientEmailIdRecieved;
import com.voteroid.clientService.exceptions.NoClientInformationRecieved;
import com.voteroid.clientService.exceptions.NoClientNameRecieved;
import com.voteroid.clientService.exceptions.NoClientPasswordRecieved;
import com.voteroid.clientService.exceptions.NoClientPhoneNoRecieved;
import com.voteroid.clientService.exceptions.NoCompanyDomainNameRecieved;
import com.voteroid.clientService.exceptions.PhoneNoOrEmailIdAlreadyExist;
import com.voteroid.clientService.repositories.ClientTblRepository;
import com.voteroid.clientService.repositories.PasswordResetTokenRepository;

@RestController
public class ClientRegistrationController {

	@Autowired
	ApplicationPropertiesConfiguration configuration;
	
	@Autowired
	ClientTblRepository repository;
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	MailUtility mailUtility;
	
	@Autowired 
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/client/registration")
	public Reply clientRegistration(@RequestHeader("userData") String data,@RequestBody ClientTbl clientTbl,HttpServletRequest request) {
		Reply reply = new Reply(data);
		ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
		ClientTbl cliTbl = null;
		if(clientTbl==null)
			throw new NoClientInformationRecieved();
		if(clientTbl.getClientName()==null || clientTbl.getClientName().isEmpty())
			throw new NoClientNameRecieved();
		if(clientTbl.getCountry()==null||clientTbl.getCountry().isEmpty())
			throw new NoClientCountryRecieved();
		if(clientTbl.getClientPhoneNo()==null || clientTbl.getClientPhoneNo().isEmpty())
			throw new NoClientPhoneNoRecieved();
		if(clientTbl.getClientEmailId()==null || clientTbl.getClientEmailId().isEmpty())
			throw new NoClientEmailIdRecieved();
		if(clientTbl.getDomainName()==null || clientTbl.getDomainName().isEmpty() || clientTbl.getDomainName().contains(" "))
			throw new NoCompanyDomainNameRecieved();
		clientTbl.setClientPayment(0);
		clientTbl.setNextAPINo(1);
		clientTbl.setVerified(false);
		try {
		   cliTbl = repository.save(clientTbl);
		   
		   //Setting Information for Password Reset Token...
		   PasswordResetTokenTbl passwordResetTokenTbl = new PasswordResetTokenTbl();
		   passwordResetTokenTbl.setClientId(cliTbl.getClientId());
		   passwordResetTokenTbl.setToken(UUID.randomUUID().toString());
		   passwordResetTokenTbl.setExpiryDate(configuration.getPasswordResetTokenValidity());
		   
		   //Sending mail with link...
		   Mail mail = new Mail();
	       mail.setFrom(configuration.getFromMailingAddress());
	       mail.setTo(cliTbl.getClientEmailId());
	       mail.setSubject("E-Mail Account Verfication Request");
	       StringBuilder content = new StringBuilder();
	       String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+
   		        "/reset-password?token=" + passwordResetTokenTbl.getToken();
	       content.append("Dear "+cliTbl.getClientName()+" ,\n\n");
	       content.append("Please Verify and Set Your Password by Clicking on below URL\n\n");
	       content.append(url);
	       content.append("\n\n\nThanks & Regards\nSwagly");
	       mail.setBody(content.toString());
	       
           mailUtility.sendEMail(mail);
           passwordResetTokenRepository.save(passwordResetTokenTbl);
           
		   clientInfoDTO.setClientId(cliTbl.getClientId());
		   clientInfoDTO.setClientName(cliTbl.getClientName());
		   clientInfoDTO.setPhoneNo(cliTbl.getClientPhoneNo());
		   clientInfoDTO.setEmailId(cliTbl.getClientEmailId());
		   
		}catch(DataIntegrityViolationException ex) {
			throw new PhoneNoOrEmailIdAlreadyExist();
		}catch (MessagingException e) {
            repository.deleteById(cliTbl.getClientId());
            throw new MailNotSent();
        }catch (MailAuthenticationException e) {
            repository.deleteById(cliTbl.getClientId());
            throw new MailNotSent();
        }catch(Exception ex) {
			ex.printStackTrace();
		}
		reply.setData(clientInfoDTO);
		return reply;
	}
	
	@GetMapping("/client/verification")
	public Response emailIdVerification(@RequestParam("token") String token,@RequestParam("password") String password) {
		Response response = new Response();
		if(token==null)
			throw new InvalidAccess();
		PasswordResetTokenTbl passwordResetTokenTbl =  passwordResetTokenRepository.findByToken(token);
		if(passwordResetTokenTbl==null)
			throw new InvalidAccess();
		if(passwordResetTokenTbl.isExpired())
			throw new InvalidAccess();
		if(password==null)
			throw new NoClientPasswordRecieved();
		
		int clientId = passwordResetTokenTbl.getClientId();
		Optional<ClientTbl> clientTbl = repository.findById(clientId);
		if(!clientTbl.isPresent())
			throw new InvalidAccess();
		ClientTbl persistedTbl = clientTbl.get();
		persistedTbl.setPassword(passwordEncoder.encode(password));
		persistedTbl.setVerified(true);
		repository.save(persistedTbl);
		passwordResetTokenRepository.deleteById(passwordResetTokenTbl.getId());
		response.setMessage("E-Mail Verified and Password Generated Successfully");
		response.setSuccessfull(true);
		return response;
	}
	
	@GetMapping("/client/registration")
	public Reply clientRegistration(@RequestHeader(Constants.TOKEN_DATA) String data,@RequestParam(Constants.CLIENT_ID) int clientId) {
		Reply reply = new Reply(data);
		//ClientTbl clientTbl = repository. 
		return reply;
	}

}
