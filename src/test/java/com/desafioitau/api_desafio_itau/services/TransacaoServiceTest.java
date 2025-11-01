package com.desafioitau.api_desafio_itau.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafioitau.api_desafio_itau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.api_desafio_itau.controller.dtos.TransacaoRequestDTO;
import com.desafioitau.api_desafio_itau.exceptions.UnprocessableEntity;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

	@InjectMocks
	TransacaoService transacaoService;
	
	TransacaoRequestDTO transacao;
	
	EstatisticasResponseDTO estatisticas;
	
	@BeforeEach
	void setup() {
		transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
		estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
	}
	
	@Test
	void deveAdicionarTransacaoComSucesso() {
		transacaoService.criarTransacoes(transacao);
		
		List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);
		
		assertTrue(transacoes.contains(transacao));
	}
	
	@Test
	void deveLancaExcecaoCasoValorSejaNegativo() {
		UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
				() -> transacaoService.criarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));
		
		assertEquals("Valor não pode ser menor que 0", exception.getMessage());
	}
	
	@Test
	void deveLancaExcecaoCasoDataOuHoraMaiorQueAtual() {
		UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
				() -> transacaoService.criarTransacoes(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));
		
		assertEquals("Data e hora maiores que a data e hora atual", exception.getMessage());
	}
}
