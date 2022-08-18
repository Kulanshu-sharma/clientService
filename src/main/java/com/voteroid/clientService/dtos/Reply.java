package com.voteroid.clientService.dtos;

import java.util.Map;

import org.springframework.boot.json.BasicJsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Reply {

	private Map<String,Object> session = null;
	private Object data = null;
	private boolean isAllowed = true;
	
	public Reply(String userData) {
		this.session = (new BasicJsonParser()).parseMap(userData);
	}
	
	public String getjsonStringFromMap() {
		try {
			return new ObjectMapper().writeValueAsString(session);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Object getAttribute(String key) {
		return session.get(key);
	}
	
	public void setAttribute(String key,Object value) {
		session.put(key,value);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}
	
	
	
	
}
