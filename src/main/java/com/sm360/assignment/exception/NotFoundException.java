package com.sm360.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3569304705793629616L;

	public NotFoundException(Long id) {
		super("Dealer not found for ID:" + id.toString());
	}
	
	public NotFoundException() {
		super();
	}
}

