package com.events.desafio.service.exception;


@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException (String msg) {
		super(msg);
	}

}
