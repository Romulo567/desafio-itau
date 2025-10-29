package com.desafioitau.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.controller.dtos.TransacaoRequestDTO;
import com.desafioitau.services.TransacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transacao")
@RequiredArgsConstructor
public class TransacaoController {

	private final TransacaoService transacaoService;
	
	@PostMapping
	@Operation(description = "Recurso responsável por adicionar transações")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"),
			@ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
			@ApiResponse(responseCode = "400", description = "Erro de requisição"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){
		
		transacaoService.criarTransacoes(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping
	@Operation(description = "Recurso responsável por deletar transações")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Transação deletadas com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro de requisição"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<Void> deletarTransacao(){
		
		transacaoService.limparTransacoes();
		return ResponseEntity.ok().build();
	}

}
