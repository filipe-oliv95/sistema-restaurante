package com.restaurante.marmitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ProductAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1974961695258838153L;

	public ProductAlreadyExistsException(String message) {
		super(message);
	}
	
}
