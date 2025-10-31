package com.desafioitau.api_desafio_itau.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafioitau.api_desafio_itau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.api_desafio_itau.controller.dtos.TransacaoRequestDTO;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {

	@InjectMocks
	EstatisticasService estatisticasService;
	
	@Mock
	TransacaoService transacaoService;
	
	TransacaoRequestDTO transacao;
	
	EstatisticasResponseDTO estatisticas;
	
	@BeforeEach
	void setup() {
		transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
		estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
	}
	
	@Test
	void calcularEstatisticasComSucesso() {
		
		when(transacaoService.buscarTransacoes(60))
		.thenReturn(Collections.singletonList(transacao));
		
		EstatisticasResponseDTO resultado = estatisticasService.calcularEstatisticasTransacoes(60);
		
		verify(transacaoService, times(1)).buscarTransacoes(60);
		Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
	}
}
