package com.sm360.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TierLimitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6035990015232512944L;

	public TierLimitException(int total){
		super(String.format("Tier Limit has been exceeded, balance: [%s]", total));
	}
}