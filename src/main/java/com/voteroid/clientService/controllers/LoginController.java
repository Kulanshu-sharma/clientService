package com.voteroid.clientService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.Constants;
import com.voteroid.clientService.dtos.Messages;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.entities.ClientTbl;
import com.voteroid.clientService.exceptions.InvalidClientId;
import com.voteroid.clientService.exceptions.InvalidPassword;
import com.voteroid.clientService.exceptions.NoClientEmailIdRecieved;
import com.voteroid.clientService.exceptions.NoClientPasswordRecieved;
import com.voteroid.clientService.exceptions.VerificationPending;
import com.voteroid.clientService.repositories.ClientTblRepository;

@RestController
public class LoginController {

	@Autowired
	ClientTblRepository repository;
	
	@PostMapping("/client/login")
	public Reply validateClientUser(@RequestHeader("userData") String data,@RequestBody ClientTbl clientTbl) {
		Reply reply = new Reply(data);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		if(clientTbl.getClientId() == 0)
			throw new NoClientEmailIdRecieved();
		if(clientTbl.getPassword()==null||clientTbl.getPassword().isEmpty())
			throw new NoClientPasswordRecieved();
		ClientTbl clientTblResponse = repository.findByClientId(clientTbl.getClientId());
		if(clientTblResponse==null) {	//Invalid Login Id...
			throw new InvalidClientId();
		}else if(!clientTblResponse.isVerified()) {
			throw new VerificationPending();
		}//else if(!encoder.matches(clientTbl.getPassword(),clientTblResponse.getPassword())) {
			//throw new InvalidPassword();
		//}
		else {
			reply.setAllowed(true);
			reply.setAttribute(Constants.CLIENT_ID,clientTbl.getClientId());
			reply.setData(clientTblResponse);
		}
		return reply;
	}
}
