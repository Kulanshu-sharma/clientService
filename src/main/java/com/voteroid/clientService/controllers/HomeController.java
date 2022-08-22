package com.voteroid.clientService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.Constants;
import com.voteroid.clientService.dtos.General;
import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.dtos.Response;
import com.voteroid.clientService.entities.ClientTbl;
import com.voteroid.clientService.exceptions.InvalidAccess;
import com.voteroid.clientService.exceptions.NoClientInformationRecieved;
import com.voteroid.clientService.repositories.ClientTblRepository;
import com.voteroid.clientService.repositories.SAGProxy;

@RestController
public class HomeController {

	@Autowired
	SAGProxy proxy;
	
	@Autowired
	ClientTblRepository repository;
	
	@Autowired
	General general;
	
	@GetMapping("/client/home")
	public Reply retriveLimitConfiguration(@RequestHeader("userData") String data) {
		Reply reply = new Reply(data);
		return reply;
	}
	
	@PutMapping("/client/blockSubscriptions/{apiId}")
	public Reply blockNewSubscriptions(@RequestHeader("userData") String data,@PathVariable(name="apiId") int apiId) {
		Reply reply = new Reply(data);
		if(general.fetchClientIdFromAPIId(apiId) != (int)reply.getAttribute(Constants.CLIENT_ID))
			throw new InvalidAccess();
		Response response = proxy.blockNewSubscriptions("hwhps1427k",apiId);
		reply.setData(response);
		return reply;	
	}
	
	@PutMapping("/client/unblockSubscriptions/{apiId}")
	public Reply unblockNewSubscriptions(@RequestHeader("userData") String data,@PathVariable(name="apiId") int apiId) {
		Reply reply = new Reply(data);
		if(general.fetchClientIdFromAPIId(apiId) != (int)reply.getAttribute(Constants.CLIENT_ID))
			throw new InvalidAccess();
		Response response = proxy.unblockNewSubscriptions("hwhps1427k",apiId);
		reply.setData(response);
		return reply;	
	}
	
	@PutMapping("/client/setApiPrice/apiId/{apiId}/amount/{amount}")
	public Reply setorUpdateApiPrice(@RequestHeader("userData") String data,@PathVariable(name="apiId") int apiId,
			                                                                @PathVariable(name="amount") float amount) {
		Reply reply = new Reply(data);
		if(general.fetchClientIdFromAPIId(apiId) != (int)reply.getAttribute(Constants.CLIENT_ID))
			throw new InvalidAccess();
		Response response = proxy.setorUpdateApiPrice("hwhps1427k",apiId,amount);
		reply.setData(response);
		return reply;	
	}
	
	@GetMapping("/client/getClientId/{domainName}")
	public int getClientIdBydomainName(@PathVariable("domainName") String domainName) {
		ClientTbl clientTbl = repository.findByDomainName(domainName);
		if(clientTbl==null)
			return 0;
		return clientTbl.getClientId();
	}
	
	@GetMapping("/sag/fetchTokens/client")
	public Reply fetchTokensForClientId(@RequestHeader("userData") String data) {
		Reply reply = new Reply(data);
		if(reply.getAttribute(Constants.CLIENT_ID)==null)
			throw new NoClientInformationRecieved();
		Response response = proxy.fetchTokensForClientId("hwhps1427k",(int)reply.getAttribute(Constants.CLIENT_ID));
		reply.setData(response);
		return reply;
	}
}
