package com.voteroid.clientService.repositories;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.voteroid.clientService.dtos.Response;

@FeignClient(name="sag")
public interface SAGProxy {
	
	@PutMapping("/sag/blockSubscriptions/{apiId}")
	public Response blockNewSubscriptions(@RequestHeader("accessKey") String accessKey,@PathVariable(name="apiId") int apiId);
	
	@PutMapping("/sag/unblockSubscriptions/{apiId}")
	public Response unblockNewSubscriptions(@RequestHeader("accessKey") String accessKey,@PathVariable(name="apiId") int apiId);
	
	@PutMapping("/sag/setApiPrice/apiId/{apiId}/amount/{amount}")
	public Response setorUpdateApiPrice(@RequestHeader("accessKey") String accessKey,@PathVariable(name="apiId") int apiId,
																					 @PathVariable(name="amount") float amount);
	@GetMapping("/sag/fetchTokens/client")
	public Response fetchTokensForClientId(@RequestHeader("accessKey") String accessKey,@RequestParam("clientId") int clientId);
	
}
