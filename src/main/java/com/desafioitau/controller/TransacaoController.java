package com.desafioitau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.controller.dtos.TransacaoRequestDTO;
import com.desafioitau.services.TransacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transacao")
@RequiredArgsConstructor
public class TransacaoController {

	private final TransacaoService transacaoService;
	
	@PostMapping
	public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){
		
		transacaoService.criarTransacoes(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
