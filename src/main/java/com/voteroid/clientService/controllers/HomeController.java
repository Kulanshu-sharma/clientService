package com.voteroid.clientService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.Reply;
import com.voteroid.clientService.dtos.Response;
import com.voteroid.clientService.repositories.SAGProxy;

@RestController
public class HomeController {

	@Autowired
	SAGProxy proxy;
	
	@GetMapping("/client/home")
	public Reply retriveLimitConfiguration(@RequestHeader("userData") String data) {
		Reply reply = new Reply(data);
		return reply;
	}
	
	@PutMapping("/client/blockSubscriptions/{apiId}")
	public Reply blockNewSubscriptions(@RequestHeader("userData") String data,@PathVariable(name="apiId") int apiId) {
		Reply reply = new Reply(data);
		Response response = proxy.blockNewSubscriptions("hwhps1427k",apiId);
		reply.setData(response);
		return reply;	
	}
	
	@PutMapping("/client/unblockSubscriptions/{apiId}")
	public Reply unblockNewSubscriptions(@RequestHeader("userData") String data,@PathVariable(name="apiId") int apiId) {
		Reply reply = new Reply(data);
		Response response = proxy.unblockNewSubscriptions("hwhps1427k",apiId);
		reply.setData(response);
		return reply;	
	}
}
