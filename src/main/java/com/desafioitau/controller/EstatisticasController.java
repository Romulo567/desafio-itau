package com.desafioitau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.services.EstatisticasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {

	private final EstatisticasService estatisticasService;
	
	@GetMapping
	public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
			@RequestParam(value = "intervaloBusca", required = false , defaultValue = "60") Integer intervaloBusca){
		
		return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
	}
}
