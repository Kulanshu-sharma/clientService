package com.voteroid.clientService.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.Constants;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.entities.ClientTbl;
import com.voteroid.clientService.repositories.ClientTblRepository;

@RestController
public class APIGenerationController {

	@Autowired
	ClientTblRepository repository;
	
	@GetMapping("/client/nextApiNumber/{clientId}")
	public int fetchNextApiNumber(@PathVariable int clientId) {
		Optional<ClientTbl> clienttbl = repository.findById(clientId);
		int nextApiId = clienttbl.get().getNextAPINo();
		clienttbl.get().setNextAPINo(clienttbl.get().getNextAPINo()+1);
		repository.save(clienttbl.get());
		return nextApiId;
	}
	
}
