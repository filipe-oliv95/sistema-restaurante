package com.restaurante.marmitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8997726134851490553L;

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s não encontrado com os dados de entrada fornecidos %s : '%s'", resourceName, fieldName, fieldValue));
    }

}