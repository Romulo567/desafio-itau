package com.desafioitau.api_desafio_itau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.api_desafio_itau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.api_desafio_itau.services.EstatisticasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Estatisticas", description = "Contém método para buscar estatisticas de transações")
@RestController
@RequestMapping("api/v1/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

	private final EstatisticasService estatisticasService;
	
	@GetMapping
	@Operation(summary = "Buscar Estatisticas", description = "Recurso responsável por buscar estatisticas de transações")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas de transações"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
			@RequestParam(value = "intervaloBusca", required = false , defaultValue = "60") Integer intervaloBusca){
		
		return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
	}
}
