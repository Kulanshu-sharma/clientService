package com.voteroid.clientService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.ClientInfoDTO;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.repositories.ClientTblRepository;

@RestController
public class ClientRegistrationController {

	@Autowired
	ClientTblRepository repository;
	
	@PostMapping("/client/registration")
	public Reply clientRegistration(@RequestHeader("userData") String data,@RequestBody ClientInfoDTO clientInfoDTO) {
		Reply reply = new Reply(data);
		reply.setAttribute("founder","Kulanshu");
		return reply;
	}
}
