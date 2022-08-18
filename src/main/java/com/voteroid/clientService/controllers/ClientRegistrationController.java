package com.voteroid.clientService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.ClientInfoDTO;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.entities.ClientTbl;
import com.voteroid.clientService.exceptions.NoClientEmailIdRecieved;
import com.voteroid.clientService.exceptions.NoClientInformationRecieved;
import com.voteroid.clientService.exceptions.NoClientNameRecieved;
import com.voteroid.clientService.exceptions.NoClientPasswordRecieved;
import com.voteroid.clientService.exceptions.NoClientPhoneNoRecieved;
import com.voteroid.clientService.exceptions.NoCompanyDomainNameRecieved;
import com.voteroid.clientService.exceptions.PhoneNoOrEmailIdAlreadyExist;
import com.voteroid.clientService.repositories.ClientTblRepository;

@RestController
public class ClientRegistrationController {

	@Autowired
	ClientTblRepository repository;
	
	@PostMapping("/client/registration")
	public Reply clientRegistration(@RequestHeader("userData") String data,@RequestBody ClientTbl clientTbl) {
		Reply reply = new Reply(data);
		ClientInfoDTO clientInfoDTO = new ClientInfoDTO();
		if(clientTbl==null)
			throw new NoClientInformationRecieved();
		if(clientTbl.getClientName()==null || clientTbl.getClientName().isEmpty())
			throw new NoClientNameRecieved();
		if(clientTbl.getPassword()==null||clientTbl.getPassword().isEmpty())
			throw new NoClientPasswordRecieved();
		if(clientTbl.getClientPhoneNo()==null || clientTbl.getClientPhoneNo().isEmpty())
			throw new NoClientPhoneNoRecieved();
		if(clientTbl.getClientEmailId()==null || clientTbl.getClientEmailId().isEmpty())
			throw new NoClientEmailIdRecieved();
		if(clientTbl.getDomainName()==null || clientTbl.getDomainName().isEmpty() || clientTbl.getDomainName().contains(" "))
			throw new NoCompanyDomainNameRecieved();
		clientTbl.setClientPayment(0);
		clientTbl.setNextAPINo(1);
		try {
		   ClientTbl cliTbl = repository.save(clientTbl);
		   clientInfoDTO.setClientId(cliTbl.getClientId());
		   clientInfoDTO.setClientName(cliTbl.getClientName());
		   clientInfoDTO.setPhoneNo(cliTbl.getClientPhoneNo());
		   clientInfoDTO.setEmailId(cliTbl.getClientEmailId());
		}catch(DataIntegrityViolationException ex) {
			throw new PhoneNoOrEmailIdAlreadyExist();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		reply.setData(clientInfoDTO);
		return reply;
	}

}
