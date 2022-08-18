package com.voteroid.clientService.repositories;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.voteroid.clientService.dtos.Response;

@FeignClient(name="sag")
public interface SAGProxy {
	
	@PutMapping("/sag/blockSubscriptions/{apiId}")
	public Response blockNewSubscriptions(@RequestHeader("accessKey") String accessKey,@PathVariable(name="apiId") int apiId);
	
	@PutMapping("/sag/unblockSubscriptions/{apiId}")
	public Response unblockNewSubscriptions(@RequestHeader("accessKey") String accessKey,@PathVariable(name="apiId") int apiId);
}
