package com.voteroid.clientService.dtos;

import org.springframework.stereotype.Component;

@Component
public class General {

	public int fetchClientIdFromAPIId(int apiId) {
		return apiId/100000;
	}
}
