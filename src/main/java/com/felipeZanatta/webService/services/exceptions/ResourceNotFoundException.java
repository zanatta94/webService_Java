package com.felipeZanatta.webService.services.exceptions;



// no construtor vou passar o id do objeto que tentei encontrar e não encontrei
// o super para chamar o construtor da super classe e dentro vai a msg


public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}
