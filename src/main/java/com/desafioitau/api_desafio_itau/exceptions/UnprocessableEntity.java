package com.desafioitau.api_desafio_itau.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntity extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UnprocessableEntity(String message) {
		super(message);
	}

}
