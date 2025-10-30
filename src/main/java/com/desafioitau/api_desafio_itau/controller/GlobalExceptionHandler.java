package com.desafioitau.api_desafio_itau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.desafioitau.api_desafio_itau.exceptions.UnprocessableEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UnprocessableEntity.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<String> handleUnprocessableEntity(UnprocessableEntity e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro: " + e.getMessage());
	}
}
