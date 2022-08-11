package com.voteroid.clientService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.voteroid.clientService.dtos.Reply;

@RestController
public class HomeController {

	@GetMapping("/client/home")
	public Reply retriveLimitConfiguration(@RequestHeader("userData") String data) {
		Reply reply = new Reply(data);
		return reply;
	}
}
